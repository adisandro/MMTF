/**
 * Copyright (c) 2012-2015 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.modelepedia.icmt15.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;

import edu.toronto.cs.se.mmint.MMINT;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.ModelOrigin;
import edu.toronto.cs.se.mmint.mid.MultiModel;
import edu.toronto.cs.se.mmint.mid.library.MultiModelOperatorUtils;
import edu.toronto.cs.se.mmint.mid.library.MultiModelRegistry;
import edu.toronto.cs.se.mmint.mid.library.MultiModelUtils;
import edu.toronto.cs.se.mmint.mid.operator.impl.RandomOperatorImpl;
import edu.toronto.cs.se.modelepedia.z3.Z3Utils;

//TODO MMINT[OPERATOR] Move other paper experiments to examples
public class ICMT15 extends RandomOperatorImpl {

	@NonNull private final static String PROPERTY_IN_MODELMULTIPLIER = "modelMultiplier";
	@NonNull private final static String PROPERTY_IN_VARIABLESMULTIPLIER = "variablesMultiplier";
	@NonNull private static final String PROPERTY_IN_IDATTRIBUTE = "idAttribute";
	@NonNull private static final String PROPERTY_IN_CONSTRAINT = "constraint";
	@NonNull private static final String PROPERTY_IN_VARIABLES = "variables";
	@NonNull private static final String PROPERTY_IN_CLAUSESTOVARIABLESRATIO = "clausesToVariablesRatio";
	@NonNull private static final String PROPERTY_IN_PRESENCECONDITIONSTOMODELSIZERATIO = "presenceConditionsToModelSizeRatio";

	@NonNull private final static String CSV_SEPARATOR = ";";
	@NonNull private final static String MODEL_GENERATED_SUFFIX = "_generated";
	private final static double EASY_PRESENCECONDITION_PERCENTAGE = 0.8;
	private final static double HARD_PRESENCECONDITION_PERCENTAGE = 0.2;
	private final static int VARIABLES_PER_CLAUSE = 2;

	private int modelMultiplier;
	private int variablesMultiplier;
	private String idAttribute;
	private String constraint;
	private List<String> variables;
	private double clausesToVariablesRatio;
	private double presenceConditionsToModelSizeRatio;

	private List<String> outputVariables;
	private String outputConstraint;

	@Override
	public void readInputProperties(Properties inputProperties) throws MMINTException {

		super.readInputProperties(inputProperties);
		modelMultiplier = MultiModelOperatorUtils.getIntProperty(inputProperties, PROPERTY_IN_MODELMULTIPLIER);
		variablesMultiplier = MultiModelOperatorUtils.getIntProperty(inputProperties, PROPERTY_IN_VARIABLESMULTIPLIER);
		idAttribute = MultiModelOperatorUtils.getStringProperty(inputProperties, PROPERTY_IN_IDATTRIBUTE);
		constraint = MultiModelOperatorUtils.getStringProperty(inputProperties, PROPERTY_IN_CONSTRAINT);
		variables = MultiModelOperatorUtils.getStringPropertyList(inputProperties, PROPERTY_IN_VARIABLES);
		clausesToVariablesRatio = MultiModelOperatorUtils.getDoubleProperty(inputProperties, PROPERTY_IN_CLAUSESTOVARIABLESRATIO);
		presenceConditionsToModelSizeRatio = MultiModelOperatorUtils.getDoubleProperty(inputProperties, PROPERTY_IN_PRESENCECONDITIONSTOMODELSIZERATIO);
	}

	@Override
	public void init() {

		outputConstraint = "";
		outputVariables = new ArrayList<>();
		for (int i = 0; i < Math.pow(2, variablesMultiplier-1); i++) {
			String tempConstraint = constraint;
			for (int j = 0; j < variables.size(); j++) {
				String variable = variables.get(j);
				String outputVariable = variable + "_" + i;
				outputVariables.add(outputVariable);
				tempConstraint = tempConstraint.replace(variable, outputVariable);
			}
			outputConstraint += tempConstraint;
		}
	}

	private String getModelObjectEncoding(EObject modelObj) throws MMINTException {

		return
			modelObj.eClass().getName() +
			CSV_SEPARATOR +
			MultiModelUtils.getModelObjFeature(modelObj, idAttribute) +
			CSV_SEPARATOR;
	}

	private String getModelReferenceEncoding(EObject srcModelObj, EObject tgtModelObj, EStructuralFeature reference) throws MMINTException {

		return
			reference.getName() +
			CSV_SEPARATOR +
			getModelObjectEncoding(srcModelObj) +
			getModelObjectEncoding(tgtModelObj);
	}

	private List<String> generateModelEncodings(EObject inputRootModelObj) {

		List<String> modelEncodings = new ArrayList<>();
		TreeIterator<EObject> iter = inputRootModelObj.eAllContents();
		while (iter.hasNext()) {
			EObject modelObj = iter.next();
			try {
				modelEncodings.add(getModelObjectEncoding(modelObj));
				EContentsEList.FeatureIterator<EObject> crossIter = (EContentsEList.FeatureIterator<EObject>) modelObj.eCrossReferences().iterator();
				while (crossIter.hasNext()) {
					modelEncodings.add(getModelReferenceEncoding(modelObj, crossIter.next(), crossIter.feature()));
				}
			}
			catch (MMINTException e) {
				continue;
			}
		}

		return modelEncodings;
	}

	private int getNumClauses() {

		double numClauses = (clausesToVariablesRatio * (double) outputVariables.size() - EASY_PRESENCECONDITION_PERCENTAGE) / HARD_PRESENCECONDITION_PERCENTAGE;

		return (int) Math.max(0, Math.round(numClauses));
	}

	private void changeCopyIds(@NonNull EObject rootModelObjCopy, @NonNull String sliceIdSuffix) throws Exception {

		TreeIterator<EObject> iter = rootModelObjCopy.eAllContents();
		while (iter.hasNext()) {
			EObject modelObjCopy = iter.next();
			String id = null, newId = null;
			try {
				id = (String) MultiModelUtils.getModelObjFeature(modelObjCopy, idAttribute);
				if (id != null) {
					newId = id + sliceIdSuffix;
					MultiModelUtils.setModelObjFeature(modelObjCopy, idAttribute, newId);
				}
			}
			catch (MMINTException e) {
				// ignore and continue
				continue;
			}
		}
	}

	private String generatePresenceCondition(List<String> outputModelEncodings, int numClauses) {

		Random random = getState();
		int i = random.nextInt(outputModelEncodings.size());
		String outputModelEncoding = outputModelEncodings.remove(i);
		String presenceCondition = "";
		if (numClauses == 1) {
			i = random.nextInt(outputVariables.size());
			presenceCondition = Z3Utils.emptyPredicate(outputVariables.get(i));
		}
		else {
			for (int j = 0; j < numClauses-1; j++) {
				List<String> clause = new ArrayList<>();
				for (int k = 0; k < VARIABLES_PER_CLAUSE; k++) {
					i = random.nextInt(outputVariables.size());
					String variable = outputVariables.get(i);
					if (clause.contains(variable)) {
						k--;
						continue;
					}
					clause.add(variable);
				}
				presenceCondition += Z3Utils.or(String.join(" ", clause));
			}
			presenceCondition = Z3Utils.and(presenceCondition);
		}

		return outputModelEncoding + presenceCondition;
	}

	@Override
	public EList<Model> execute(EList<Model> actualParameters) throws Exception {

		// inputs
		Model inputModel = actualParameters.get(0);

		// generate output model
		EObject inputRootModelObj = inputModel.getEMFInstanceRoot();
		EObject outputRootModelObj = inputRootModelObj.eClass().getEPackage().getEFactoryInstance().create(inputRootModelObj.eClass());
		String presenceConditions = Z3Utils.or(outputConstraint) + "\n";
		for (int i = 0; i < Math.pow(2, modelMultiplier-1); i++) {
			EObject inputRootModelObjCopy = EcoreUtil.copy(inputRootModelObj);
			changeCopyIds(inputRootModelObjCopy, "_" + i);
			for (EReference containmentFeature : inputRootModelObjCopy.eClass().getEAllContainments()) {
				@SuppressWarnings("unchecked")
				EList<EObject> inputModelObjsCopy = (EList<EObject>) MultiModelUtils.getModelObjFeature(inputRootModelObjCopy, containmentFeature.getName());
				@SuppressWarnings("unchecked")
				EList<EObject> outputModelObjs = (EList<EObject>) MultiModelUtils.getModelObjFeature(outputRootModelObj, containmentFeature.getName());
				outputModelObjs.addAll(inputModelObjsCopy);
			}
		}
		// generate presence conditions
		List<String> outputModelEncodings = generateModelEncodings(outputRootModelObj);
		int numPresenceConditions = (int) (outputModelEncodings.size() * presenceConditionsToModelSizeRatio);
		int numClauses = getNumClauses();
		for (int i = 0; i < (numPresenceConditions * EASY_PRESENCECONDITION_PERCENTAGE); i++) {
			presenceConditions += generatePresenceCondition(outputModelEncodings, 1) + "\n";
		}
		if (numClauses > 0) {
			for (int i = 0; i < (numPresenceConditions * HARD_PRESENCECONDITION_PERCENTAGE); i++) {
				presenceConditions += generatePresenceCondition(outputModelEncodings, numClauses) + "\n";
			}
		}
		for (String outputModelEncoding : outputModelEncodings) {
			presenceConditions += outputModelEncoding + Z3Utils.emptyPredicate(Z3Utils.SMTLIB_TRUE) + "\n";
		}

		// outputs
		EList<Model> outputs = new BasicEList<>();
		String uri = (getInputSubdir() != null) ?
			MultiModelUtils.replaceLastSegmentInUri(
				inputModel.getUri(),
				getInputSubdir() + MMINT.URI_SEPARATOR + MultiModelUtils.getLastSegmentFromUri(inputModel.getUri())
			) :
			inputModel.getUri();
		String outputModelUri = MultiModelUtils.getUniqueUri(MultiModelUtils.addFileNameSuffixInUri(uri, MODEL_GENERATED_SUFFIX), true, false);
		MultiModelUtils.createModelFile(outputRootModelObj, outputModelUri, true);
		MultiModel instanceMID = MultiModelRegistry.getMultiModel(inputModel);
		Model outputModel = (isUpdateMID()) ?
			inputModel.getMetatype().createInstanceAndEditor(outputModelUri, ModelOrigin.CREATED, instanceMID) :
			inputModel.getMetatype().createInstance(outputModelUri, ModelOrigin.CREATED, null);
		outputs.add(outputModel);
		MultiModelUtils.createTextFile(MultiModelUtils.replaceFileExtensionInUri(outputModelUri, "csv"), presenceConditions, true);

		return outputs;
	}

}