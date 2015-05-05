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
package edu.toronto.cs.se.modelepedia.operator.merge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;

import edu.toronto.cs.se.mmint.MMINT;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.MultiModelTypeHierarchy;
import edu.toronto.cs.se.mmint.mid.EMFInfo;
import edu.toronto.cs.se.mmint.mid.GenericElement;
import edu.toronto.cs.se.mmint.mid.MIDLevel;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.ModelElement;
import edu.toronto.cs.se.mmint.mid.ModelOrigin;
import edu.toronto.cs.se.mmint.mid.MultiModel;
import edu.toronto.cs.se.mmint.mid.impl.ModelElementImpl;
import edu.toronto.cs.se.mmint.mid.library.MultiModelRegistry;
import edu.toronto.cs.se.mmint.mid.library.MultiModelUtils;
import edu.toronto.cs.se.mmint.mid.operator.impl.OperatorImpl;
import edu.toronto.cs.se.mmint.mid.relationship.LinkReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelElementEndpointReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelElementReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelRel;

//TODO MMINT[OPERATOR] Review this whole operator to find examples on how to make apis easier to use
// e.g. direct access through ext table is useful, but there's that _AS_ thing to be fixed first
public class ModelMerge extends OperatorImpl {

	@NonNull
	private final static String INPUT_MODELREL = "match";
	@NonNull
	private final static String OUTPUT_MODEL = "merged";
	@NonNull
	private final static String OUTPUT_MODELREL1 = "trace1";
	@NonNull
	private final static String OUTPUT_MODELREL2 = "trace2";

	@NonNull
	private static final String MERGED_MODELOBJECT_ATTRIBUTE = "name";
	@NonNull
	private static final String MERGED_SEPARATOR = "+";

	// TODO MMINT[OPERATOR] Make this an api
	private @NonNull Set<ModelElementReference> getConnected(@NonNull ModelElementReference modelElemRef) {

		Set<ModelElementReference> connModelElemRefs = new HashSet<>();
		for (ModelElementEndpointReference modelElemEndpointRef : modelElemRef.getModelElemEndpointRefs()) {
			for (ModelElementEndpointReference connModelElemEndpointRef : ((LinkReference) modelElemEndpointRef
					.eContainer()).getModelElemEndpointRefs()) {
				if (connModelElemEndpointRef == modelElemEndpointRef) {
					continue;
				}
				connModelElemRefs.add(connModelElemEndpointRef.getModelElemRef());
			}
		}

		return connModelElemRefs;
	}

	private @NonNull EObject createMergedModel(
			@NonNull Model model1, @NonNull Model model2, @NonNull ModelRel matchRel, @NonNull Model mergedModel,
			@NonNull ModelRel traceRel1, @NonNull ModelRel traceRel2) throws MMINTException {

		// create merged root
		EObject rootModelObj1 = model1.getEMFInstanceRoot();
		EFactory mergedModelFactory = rootModelObj1.eClass().getEPackage().getEFactoryInstance();
		EObject rootMergedModelObj = mergedModelFactory.create(rootModelObj1.eClass());
		Map<String, ModelElement> matchModelElems1 = new HashMap<>();
		for (ModelElementReference modelElemRef1 : matchRel.getModelEndpointRefs().get(0).getModelElemRefs()) {
			ModelElementReference modelElemRef2 = getConnected(modelElemRef1).stream().findFirst().get();
			matchModelElems1.put(
				modelElemRef1.getUri().substring(0, modelElemRef1.getUri().indexOf(MMINT.ROLE_SEPARATOR)),
				modelElemRef2.getObject());
		}
		Map<String, EObject> mergedModelObjs = new HashMap<>();

		// copy elements from model1
		for (EObject modelObj1 : rootModelObj1.eContents()) {
			EObject mergedModelObj = EcoreUtil.copy(modelObj1);
			String modelElemUri1 = MultiModelRegistry.getModelAndModelElementUris(modelObj1, MIDLevel.INSTANCES)[1];
			if (matchModelElems1.keySet().contains(modelElemUri1)) {
				ModelElement modelElem2 = matchModelElems1.get(modelElemUri1);
				EObject modelObj2 = modelElem2.getEMFInstanceObject();
				mergedModelObjs.put(
					modelElem2.getUri().substring(0, modelElem2.getUri().indexOf(MMINT.ROLE_SEPARATOR)),
					mergedModelObj);
				try { // change merged attribute
					MultiModelUtils.setModelObjFeature(
						mergedModelObj,
						MERGED_MODELOBJECT_ATTRIBUTE,
						MultiModelUtils.getModelObjFeature(modelObj1, MERGED_MODELOBJECT_ATTRIBUTE) + MERGED_SEPARATOR
								+ MultiModelUtils.getModelObjFeature(modelObj2, MERGED_MODELOBJECT_ATTRIBUTE));
				}
				catch (MMINTException e) {
					// no attribute to be merged
				}
			}
			MultiModelUtils.setModelObjFeature(
				rootMergedModelObj,
				modelObj1.eContainingFeature().getName(),
				mergedModelObj);
			EList<ModelElementReference> traceModelElemRefs1 = new BasicEList<>();
			traceModelElemRefs1.add(ModelElementImpl.createInstanceAndReference(
				modelObj1,
				null,
				traceRel1.getModelEndpointRefs().get(0)));
			String newModelElemUri = mergedModel.getUri()
					+ MultiModelRegistry.getModelAndModelElementUris(mergedModelObj, MIDLevel.INSTANCES)[1];
			EMFInfo eInfo = MultiModelRegistry.getModelElementEMFInfo(mergedModelObj, MIDLevel.INSTANCES);
			String newModelElemName = MultiModelRegistry.getModelElementName(eInfo, mergedModelObj, MIDLevel.INSTANCES);
			traceModelElemRefs1.add( // merged model element is not serialized yet
				MultiModelTypeHierarchy.getRootModelElementType().createInstanceAndReference(
					newModelElemUri,
					newModelElemName,
					eInfo,
					traceRel1.getModelEndpointRefs().get(1)));
			MultiModelTypeHierarchy.getRootLinkType().createInstanceAndReferenceAndEndpointsAndReferences(
				true,
				traceModelElemRefs1);
		}

		// copy elements from model2
		EObject rootModelObj2 = model2.getEMFInstanceRoot();
		for (EObject modelObj2 : rootModelObj2.eContents()) {
			EObject mergedModelObj;
			String modelElemUri2 = MultiModelRegistry.getModelAndModelElementUris(modelObj2, MIDLevel.INSTANCES)[1];
			if (mergedModelObjs.keySet().contains(modelElemUri2)) {
				// already copied
				mergedModelObj = mergedModelObjs.get(modelElemUri2);
			}
			else {
				mergedModelObj = EcoreUtil.copy(modelObj2);
				MultiModelUtils.setModelObjFeature(
					rootMergedModelObj,
					modelObj2.eContainingFeature().getName(),
					mergedModelObj);
			}
			EList<ModelElementReference> traceModelElemRefs2 = new BasicEList<>();
			traceModelElemRefs2.add(ModelElementImpl.createInstanceAndReference(
				modelObj2,
				null,
				traceRel2.getModelEndpointRefs().get(0)));
			String newModelElemUri = mergedModel.getUri()
					+ MultiModelRegistry.getModelAndModelElementUris(mergedModelObj, MIDLevel.INSTANCES)[1];
			EMFInfo eInfo = MultiModelRegistry.getModelElementEMFInfo(mergedModelObj, MIDLevel.INSTANCES);
			String newModelElemName = MultiModelRegistry.getModelElementName(eInfo, mergedModelObj, MIDLevel.INSTANCES);
			traceModelElemRefs2.add( // merged model element is not serialized yet
				MultiModelTypeHierarchy.getRootModelElementType().createInstanceAndReference(
					newModelElemUri,
					newModelElemName,
					eInfo,
					traceRel2.getModelEndpointRefs().get(1)));
			MultiModelTypeHierarchy.getRootLinkType().createInstanceAndReferenceAndEndpointsAndReferences(
				true,
				traceModelElemRefs2);
		}

		return rootMergedModelObj;
	}

	@Override
	public Map<String, Model> run(
			Map<String, Model> inputsByName, Map<String, GenericElement> genericsByName,
			Map<String, MultiModel> outputMIDsByName) throws Exception {

		// TODO MMINT[MERGE] Support more complex cases than just first-level objects without references
		ModelRel matchRel = (ModelRel) inputsByName.get(INPUT_MODELREL);
		// check input constraints
		if (matchRel.getModelEndpoints().size() != 2) {
			throw new MMINTException("The match model relationship " + matchRel + " doesn't have 2 model endpoints");
		}
		Model model1 = matchRel.getModelEndpoints().get(0).getTarget();
		Model model2 = matchRel.getModelEndpoints().get(1).getTarget();
		if (model1.getMetatype() != model2.getMetatype()) {
			throw new MMINTException("The models to be merged " + model1 + " and " + model2 + "aren't of the same type");
		}

		// create merged model and trace relationships as placeholders
		MultiModel mergedModelMID = outputMIDsByName.get(OUTPUT_MODEL);
		String mergedModelUri = MultiModelUtils.replaceLastSegmentInUri(
			MultiModelRegistry.getModelAndModelElementUris(mergedModelMID, MIDLevel.INSTANCES)[0],
			model1.getName() + MERGED_SEPARATOR + model2.getName() + MMINT.MODEL_FILEEXTENSION_SEPARATOR
					+ model1.getFileExtension());
		Model mergedModel = model1.getMetatype().createInstance(mergedModelUri, ModelOrigin.CREATED, mergedModelMID);
		EList<Model> traceModels1 = new BasicEList<Model>();
		traceModels1.add(model1);
		traceModels1.add(mergedModel);
		ModelRel traceRel1 = MultiModelTypeHierarchy.getRootModelRelType().createInstanceAndEndpointsAndReferences(
			null,
			true,
			ModelOrigin.CREATED,
			traceModels1);
		traceRel1.setName(OUTPUT_MODELREL1);
		EList<Model> traceModels2 = new BasicEList<Model>();
		traceModels2.add(model2);
		traceModels2.add(mergedModel);
		ModelRel traceRel2 = MultiModelTypeHierarchy.getRootModelRelType().createInstanceAndEndpointsAndReferences(
			null,
			true,
			ModelOrigin.CREATED,
			traceModels2);
		traceRel2.setName(OUTPUT_MODELREL2);
		// merge the models
		EObject rootMergedModelObj = createMergedModel(model1, model2, matchRel, mergedModel, traceRel1, traceRel2);
		MultiModelUtils.createModelFile(rootMergedModelObj, mergedModelUri, true);
		mergedModel.createInstanceEditor(); // opens the new model editor as side effect

		Map<String, Model> outputsByName = new HashMap<>();
		outputsByName.put(OUTPUT_MODEL, mergedModel);
		outputsByName.put(OUTPUT_MODELREL1, traceRel1);
		outputsByName.put(OUTPUT_MODELREL2, traceRel2);

		return outputsByName;
	}

}
