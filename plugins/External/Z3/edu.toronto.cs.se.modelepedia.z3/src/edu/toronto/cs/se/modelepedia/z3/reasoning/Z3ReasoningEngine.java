/**
 * Copyright (c) 2012-2015 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Naama Ben-David.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 *    Naama Ben-David - Concretization highlighting.
 */
package edu.toronto.cs.se.modelepedia.z3.reasoning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import edu.toronto.cs.se.mavo.LogicElement;
import edu.toronto.cs.se.mavo.MAVOCollection;
import edu.toronto.cs.se.mavo.MAVODecision;
import edu.toronto.cs.se.mavo.MAVOElement;
import edu.toronto.cs.se.mavo.MAVOModel;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.MultiModelTypeRegistry;
import edu.toronto.cs.se.mmint.mavo.library.MAVOUtils;
import edu.toronto.cs.se.mmint.mavo.reasoning.IMAVOReasoningEngine;
import edu.toronto.cs.se.mmint.mid.ExtendibleElementConstraint;
import edu.toronto.cs.se.mmint.mid.MIDLevel;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.constraint.MultiModelConstraintChecker.MAVOTruthValue;
import edu.toronto.cs.se.mmint.mid.editor.Diagram;
import edu.toronto.cs.se.mmint.mid.library.MultiModelRegistry;
import edu.toronto.cs.se.mmint.mid.operator.Operator;
import edu.toronto.cs.se.mmint.mid.ui.MultiModelDiagramUtils;
import edu.toronto.cs.se.modelepedia.z3.Z3IncrementalSolver;
import edu.toronto.cs.se.modelepedia.z3.Z3IncrementalSolver.Z3IncrementalBehavior;
import edu.toronto.cs.se.modelepedia.z3.Z3Model;
import edu.toronto.cs.se.modelepedia.z3.Z3Model.Z3Bool;
import edu.toronto.cs.se.modelepedia.z3.Z3Utils;
import edu.toronto.cs.se.modelepedia.z3.mavo.EcoreMAVOToSMTLIB;
import edu.toronto.cs.se.modelepedia.z3.mavo.MAVOConcretizationHighlighter;
import edu.toronto.cs.se.modelepedia.z3.mavo.MAVORefiner;
import edu.toronto.cs.se.modelepedia.z3.mavo.Z3MAVOModelParser;

//TODO MMINT[Z3] Support refinement and highlighting for the complex full-MAVO encoding
public class Z3ReasoningEngine implements IMAVOReasoningEngine {

	private final static @NonNull String ECOREMAVOTOSMTLIB_OPERATOR_URI = "http://se.cs.toronto.edu/modelepedia/Operator_EcoreMAVOToSMTLIB";

	private Z3Model z3ConstraintModel;
	private Z3Model z3NotConstraintModel;

	private @NonNull Z3MAVOModelParser generateSMTLIBEncoding(@NonNull Model model) throws Exception {

		if (!(model.getEMFInstanceRoot() instanceof MAVOModel)) {
			//TODO MMINT[Z3] Support non-mavo models (create acceleo transformation, check constraint once)
			throw new MMINTException("Model " + model.getName() + " is not a MAVO model");
		}

		EcoreMAVOToSMTLIB ecore2smt = (EcoreMAVOToSMTLIB) MultiModelTypeRegistry.<Operator>getType(ECOREMAVOTOSMTLIB_OPERATOR_URI);
		EList<Model> actualParameters = new BasicEList<Model>();
		actualParameters.add(model);
		ecore2smt.execute(actualParameters);
		ecore2smt.cleanup();

		return ecore2smt.getZ3MAVOModelParser();
	}

	public @NonNull MAVOTruthValue checkMAVOConstraintWithSolver(@NonNull Z3IncrementalSolver z3IncSolver, @NonNull String smtConstraint) {

		Z3Model z3Model = z3IncSolver.checkSatAndGetModel(Z3Utils.assertion(smtConstraint), Z3IncrementalBehavior.POP);
		boolean constraintTruthValue = z3Model.getZ3Bool() == Z3Bool.SAT;
		z3ConstraintModel = (constraintTruthValue) ? z3Model : null;
		z3Model = z3IncSolver.checkSatAndGetModel(Z3Utils.assertion(Z3Utils.not(smtConstraint)), Z3IncrementalBehavior.POP);
		boolean notConstraintTruthValue = z3Model.getZ3Bool() == Z3Bool.SAT;
		z3NotConstraintModel = (notConstraintTruthValue) ? z3Model : null;

		return MAVOTruthValue.toMAVOTruthValue(constraintTruthValue, notConstraintTruthValue);
	}

	public @NonNull MAVOTruthValue checkMAVOConstraint(@NonNull String smtEncoding, @NonNull String smtConstraint) {

		Z3IncrementalSolver z3IncSolver = new Z3IncrementalSolver();
		z3IncSolver.firstCheckSatAndGetModel(smtEncoding);

		return checkMAVOConstraintWithSolver(z3IncSolver, smtConstraint);
	}

	@Override
	public @NonNull MAVOTruthValue checkConstraint(@NonNull Model model, ExtendibleElementConstraint constraint, @NonNull MIDLevel constraintLevel) {

		Z3MAVOModelParser z3ModelParser;
		try {
			z3ModelParser = generateSMTLIBEncoding(model);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't generate SMTLIB encoding, evaluating to false", e);
			return MAVOTruthValue.FALSE;
		}
		MAVOTruthValue constraintTruthValue = checkMAVOConstraint(z3ModelParser.getSMTLIBEncoding(), constraint.getImplementation());

		// show example if: maybe, has a diagram, user accepts
		if (constraintTruthValue != MAVOTruthValue.MAYBE) {
			return constraintTruthValue;
		}
		Diagram modelDiagram = MultiModelRegistry.getModelDiagram(model);
		if (modelDiagram == null) {
			return constraintTruthValue;
		}
		if (!MultiModelDiagramUtils.getBooleanInput(
			"Concretization highlighter",
			"The result is MAYBE, do you want to see a concretization that violates the constraint?"
		)) {
			return constraintTruthValue;
		}

		// show example
		MAVOConcretizationHighlighter highlighter;
		try {
			highlighter = new MAVOConcretizationHighlighter();
			highlighter.highlightMAVOCounterExample(modelDiagram, z3ModelParser.getZ3MAVOModelElements(z3NotConstraintModel));
		}
		catch (Exception e) {
			MMINTException.print(IStatus.WARNING, "Can't highlight concretization, skipping it", e);
		}

		return constraintTruthValue;
	}

	@Override
	public boolean checkConstraintConsistency(@NonNull Model modelType, String constraint) {

		return true;
	}

	public int allSATWithSolver(@NonNull Z3IncrementalSolver z3IncSolver, @NonNull Z3MAVOModelParser z3ModelParser, @NonNull Z3Model z3Model, @NonNull Map<String, MAVOElement> mavoModelObjs, MAVOModel rootMavoModelObj) throws MMINTException {

		int numSolutions = -1;

		do {
			numSolutions++;
			String smtConcretizationConstraint = "";
			Map<String, List<String>> z3ModelElems = new HashMap<>();
			for (Entry<String, String> z3ModelElem : z3ModelParser.getZ3MAVOModelElements(z3Model).entrySet()) {
				// M: 1 universe id to 1 formula var, or nothing
				// S: x universe ids to 1 formula var
				// V: 1 universe id to x formula vars
				String universeId = z3ModelElem.getKey();
				String formulaVar = z3ModelElem.getValue();
				List<String> formulaVars = z3ModelElems.get(universeId);
				if (formulaVars == null) {
					formulaVars = new ArrayList<>();
					z3ModelElems.put(universeId, formulaVars);
				}
				formulaVars.add(formulaVar);
			}
			for (Entry<String, MAVOElement> mavoModelObjEntry : mavoModelObjs.entrySet()) {
				MAVOElement mavoModelObj = mavoModelObjEntry.getValue();
				String formulaVar = mavoModelObjEntry.getKey();
				int counterMS = 0;
				List<String> mergedV = null;
				for (List<String> z3ModelElemFormulaVars : z3ModelElems.values()) {
					if (z3ModelElemFormulaVars.contains(formulaVar)) {
						counterMS++;
						mergedV = z3ModelElemFormulaVars;
					}
				}
				boolean isNegation;
				String smtConstraint = "";
				if (mavoModelObj.isMay()) {
					isNegation = (counterMS == 0);
					smtConstraint = getSMTLIBMayModelObjectEncoding(mavoModelObj, z3ModelParser.isMayOnly(), isNegation);
				}
				if (mavoModelObj.isSet() && counterMS > 0) {
					isNegation = (counterMS > 1);
					smtConstraint = getSMTLIBSetModelObjectEncoding(mavoModelObj, isNegation);
				}
				if (mavoModelObj.isVar() && counterMS > 0) {
					isNegation = (mergedV.size() > 1);
					if (!isNegation) {
						mergedV = MAVOUtils.getMergeableFormulaVars(rootMavoModelObj, mavoModelObj);
						if (mergedV.size() == 0) {
							continue;
						}
					}
					smtConstraint = getSMTLIBVarModelObjectEncoding(mavoModelObj, mergedV, isNegation);
				}
				smtConcretizationConstraint += smtConstraint;
			}
			smtConcretizationConstraint = Z3Utils.assertion(Z3Utils.not(Z3Utils.and(smtConcretizationConstraint)));
			z3Model = z3IncSolver.checkSatAndGetModel(smtConcretizationConstraint, Z3IncrementalBehavior.NORMAL);
		}
		while (z3Model.getZ3Bool().toBoolean());

		return numSolutions;
	}

	public int allSAT(@NonNull String smtEncoding, @NonNull Z3MAVOModelParser z3ModelParser, @NonNull Map<String, MAVOElement> mavoModelObjs, @NonNull MAVOModel rootMavoModelObj) throws MMINTException {

		Z3IncrementalSolver z3IncSolver = new Z3IncrementalSolver();
		Z3Model z3Model = z3IncSolver.firstCheckSatAndGetModel(smtEncoding);

		return allSATWithSolver(z3IncSolver, z3ModelParser, z3Model, mavoModelObjs, rootMavoModelObj);
	}

	public @NonNull String getSMTLIBMayModelObjectEncoding(@NonNull MAVOElement mayModelObj, boolean isMayOnly, boolean isNegation) throws MMINTException {

		String smtEncoding;
		EClass mavoModelObjClass = mayModelObj.eClass();
		if (mavoModelObjClass.getEAnnotation("gmf.node") != null) {
			smtEncoding = (isMayOnly) ?
				Z3Utils.predicate(Z3Utils.SMTLIB_NODE_FUNCTION, mayModelObj.getFormulaVariable()) :
				Z3Utils.exists(
					Z3Utils.predicate(Z3Utils.SMTLIB_CONCRETIZATION_QUANTIFIER, mavoModelObjClass.getName()),
					Z3Utils.predicate(Z3Utils.SMTLIB_NODE_FUNCTION, mayModelObj.getFormulaVariable() + " " + Z3Utils.SMTLIB_CONCRETIZATION)
				);
		}
		else if (mavoModelObjClass.getEAnnotation("gmf.link") != null) {
			smtEncoding = (isMayOnly) ?
				Z3Utils.predicate(Z3Utils.SMTLIB_EDGE_FUNCTION, mayModelObj.getFormulaVariable()) :
				Z3Utils.exists(
					Z3Utils.predicate(Z3Utils.SMTLIB_CONCRETIZATION_QUANTIFIER, mavoModelObjClass.getName()),
					Z3Utils.predicate(Z3Utils.SMTLIB_EDGE_FUNCTION, mayModelObj.getFormulaVariable() + " " + Z3Utils.SMTLIB_CONCRETIZATION)
				);
		}
		else {
			throw new MMINTException("The model object " + mayModelObj.getFormulaVariable() + " doesn't have a Eugenia node/link annotation");
		}

		return (isNegation) ? Z3Utils.not(smtEncoding) : smtEncoding;
	}

	public @NonNull String getSMTLIBSetModelObjectEncoding(@NonNull MAVOElement setModelObj, boolean isNegation) throws MMINTException {

		String smtEncoding;
		EClass mavoModelObjClass = setModelObj.eClass();
		if (mavoModelObjClass.getEAnnotation("gmf.node") != null) {
			smtEncoding = Z3Utils.exists(
				Z3Utils.predicate(Z3Utils.SMTLIB_CONCRETIZATION_QUANTIFIER, mavoModelObjClass.getName()),
				Z3Utils.predicate(Z3Utils.SMTLIB_NODE_FUNCTION, setModelObj.getFormulaVariable() + " " + Z3Utils.SMTLIB_CONCRETIZATION)
			);
		}
		else if (mavoModelObjClass.getEAnnotation("gmf.link") != null) {
			smtEncoding = Z3Utils.exists(
				Z3Utils.predicate(Z3Utils.SMTLIB_CONCRETIZATION_QUANTIFIER, mavoModelObjClass.getName()),
				Z3Utils.predicate(Z3Utils.SMTLIB_EDGE_FUNCTION, setModelObj.getFormulaVariable() + " " + Z3Utils.SMTLIB_CONCRETIZATION)
			);
		}
		else {
			throw new MMINTException("The model object " + setModelObj.getFormulaVariable() + " doesn't have a Eugenia node/link annotation");
		}

		return (isNegation) ? Z3Utils.not(smtEncoding) : smtEncoding;
	}

	public @NonNull String getSMTLIBVarModelObjectEncoding(@NonNull MAVOElement varModelObj, @NonNull List<String> unmergeableFormulaVars, boolean isNegation) throws MMINTException {

		String smtEncoding;
		EClass mavoModelObjClass = varModelObj.eClass();
		if (mavoModelObjClass.getEAnnotation("gmf.node") != null) {
			String smtThenTerms = "";
			for (String unmergeableFormulaVar : unmergeableFormulaVars) {
				smtThenTerms += Z3Utils.predicate(Z3Utils.SMTLIB_NODE_FUNCTION, unmergeableFormulaVar + " " + Z3Utils.SMTLIB_CONCRETIZATION);
			}
			smtThenTerms = (isNegation) ? Z3Utils.and(smtThenTerms) : Z3Utils.not(Z3Utils.or(smtThenTerms));
			smtEncoding = Z3Utils.forall(
				Z3Utils.predicate(Z3Utils.SMTLIB_CONCRETIZATION_QUANTIFIER, mavoModelObjClass.getName()),
				Z3Utils.implication(
					Z3Utils.predicate(Z3Utils.SMTLIB_NODE_FUNCTION, varModelObj.getFormulaVariable() + " " + Z3Utils.SMTLIB_CONCRETIZATION),
					smtThenTerms
				)
			);
		}
		else if (mavoModelObjClass.getEAnnotation("gmf.link") != null) {
			String smtThenTerms = "";
			for (String unmergeableFormulaVar : unmergeableFormulaVars) {
				smtThenTerms += Z3Utils.predicate(Z3Utils.SMTLIB_EDGE_FUNCTION, unmergeableFormulaVar + " " + Z3Utils.SMTLIB_CONCRETIZATION);
			}
			smtThenTerms = (isNegation) ? Z3Utils.and(smtThenTerms) : Z3Utils.not(Z3Utils.or(smtThenTerms));
			smtEncoding = Z3Utils.forall(
				Z3Utils.predicate(Z3Utils.SMTLIB_CONCRETIZATION_QUANTIFIER, mavoModelObjClass.getName()),
				Z3Utils.implication(
					Z3Utils.predicate(Z3Utils.SMTLIB_EDGE_FUNCTION, varModelObj.getFormulaVariable() + " " + Z3Utils.SMTLIB_CONCRETIZATION),
					smtThenTerms
				)
			);
		}
		else {
			throw new MMINTException("The model object " + varModelObj.getFormulaVariable() + " doesn't have a Eugenia node/link annotation");
		}

		return smtEncoding;
	}

	@Override
	public @Nullable Model refineByConstraint(@NonNull Model model) {

		Z3MAVOModelParser z3ModelParser;
		try {
			z3ModelParser = generateSMTLIBEncoding(model);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't generate SMTLIB encoding, aborting refinement", e);
			return null;
		}
		MAVOTruthValue constraintTruthValue = checkMAVOConstraint(z3ModelParser.getSMTLIBEncoding(), model.getConstraint().getImplementation());

		// refine if: maybe
		if (constraintTruthValue != MAVOTruthValue.MAYBE) {
			MMINTException.print(IStatus.ERROR, "The constraint is not MAYBE, aborting refinement", null);
			return null;
		}

		// refine
		Diagram modelDiagram = MultiModelRegistry.getModelDiagram(model);
		String smtEncoding = z3ModelParser.getSMTLIBEncoding() + Z3Utils.assertion(model.getConstraint().getImplementation());
		MAVORefiner refiner = new MAVORefiner(this);
		try {
			return refiner.refine(model, modelDiagram, null, smtEncoding);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't refine the model by constraint, aborting (some incomplete result could appear in your instance MID)", e);
			return null;
		}
	}

	private @NonNull String generateMayRefinementSMTLIBEncoding(@NonNull Model model, @NonNull List<? extends LogicElement> mayLogicElems) throws Exception {

		// base encoding
		Z3MAVOModelParser z3ModelParser = generateSMTLIBEncoding(model);
		String smtEncoding = z3ModelParser.getSMTLIBEncoding();

		// constraints
		if (model.getConstraint() != null) {
			smtEncoding += Z3Utils.assertion(model.getConstraint().getImplementation());
		}
		String smtMayLogicElem = "";
		for (LogicElement mayLogicElem : mayLogicElems) {
			if (mayLogicElem instanceof MAVOCollection) {
				smtMayLogicElem = mayLogicElem.getFormulaVariable();
			}
			else if (mayLogicElem instanceof MAVOElement) {
				smtMayLogicElem = getSMTLIBMayModelObjectEncoding((MAVOElement) mayLogicElem, z3ModelParser.isMayOnly(), false);
			}
			smtEncoding += Z3Utils.assertion(smtMayLogicElem);
		}

		return smtEncoding;
	}

	@Override
	public @Nullable Model refineByMayAlternative(@NonNull Model model, @NonNull MAVOCollection mayAlternative) {

		String smtEncoding;
		try {
			List<MAVOCollection> mayLogicElems = new ArrayList<>();
			mayLogicElems.add(mayAlternative);
			smtEncoding = generateMayRefinementSMTLIBEncoding(model, mayLogicElems);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't generate SMTLIB encoding, aborting refinement", e);
			return null;
		}

		// refine
		Diagram modelDiagram = MultiModelRegistry.getModelDiagram(model);
		MAVORefiner refiner = new MAVORefiner(this);
		try {
			return refiner.refine(model, modelDiagram, mayAlternative, smtEncoding);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't refine the model by may alternative, aborting (some incomplete result could appear in your instance MID)", e);
			return null;
		}
	}

	@Override
	public @Nullable Model refineByMayModelObjects(@NonNull Model model, @NonNull List<MAVOElement> mayModelObjs) {

		String smtEncoding;
		try {
			smtEncoding = generateMayRefinementSMTLIBEncoding(model, mayModelObjs);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't generate SMTLIB encoding, aborting refinement", e);
			return null;
		}

		// refine
		Diagram modelDiagram = MultiModelRegistry.getModelDiagram(model);
		MAVORefiner refiner = new MAVORefiner(this);
		try {
			return refiner.refine(model, modelDiagram, null, smtEncoding);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.ERROR, "Can't refine the model by may model objects, aborting (some incomplete result could appear in your instance MID)", e);
			return null;
		}
	}

	@Override
	public @Nullable Model refineByVarDomain(@NonNull Model model, @NonNull MAVOCollection varDomain, @NonNull MAVOElement mergedModelObj, @NonNull List<MAVOElement> varModelObjs) {

		//TODO MMINT[VAR-MMINT] Need to come up with a different approach than the may constraint-based: first merge in the model and remove Vs, then run the solver to cascade
		//TODO MMINT[MU-MMINT] Migrate var approach to may too (first remove elements and Ms in the model, then run the solver to cascade)

		return null;
	}

	@Override
	public void highlightMAVODecision(@NonNull Diagram modelDiagram, @NonNull MAVODecision mavoDecision) {

		MAVOConcretizationHighlighter highlighter = new MAVOConcretizationHighlighter();
		try {
			highlighter.highlight(modelDiagram, mavoDecision);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.WARNING, "Can't highlight MAVO decision, skipping it", e);
		}
	}

	@Override
	public void highlightMAVOCollection(@NonNull Diagram modelDiagram, @NonNull MAVOCollection mavoCollection) {

		MAVOConcretizationHighlighter highlighter = new MAVOConcretizationHighlighter();
		try {
			highlighter.highlight(modelDiagram, mavoCollection);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.WARNING, "Can't highlight MAVO collection, skipping it", e);
		}
	}

	@Override
	public void highlightMAVOElement(@NonNull Diagram modelDiagram, @NonNull MAVOElement mavoModelObj) {

		MAVOConcretizationHighlighter highlighter = new MAVOConcretizationHighlighter();
		try {
			highlighter.highlight(modelDiagram, mavoModelObj);
		}
		catch (Exception e) {
			MMINTException.print(IStatus.WARNING, "Can't highlight MAVO element, skipping it", e);
		}
	}

}
