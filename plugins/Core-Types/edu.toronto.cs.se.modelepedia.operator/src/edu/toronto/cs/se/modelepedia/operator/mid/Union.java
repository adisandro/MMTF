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
package edu.toronto.cs.se.modelepedia.operator.mid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNull;

import edu.toronto.cs.se.mmint.MultiModelTypeRegistry;
import edu.toronto.cs.se.mmint.mid.GenericElement;
import edu.toronto.cs.se.mmint.mid.MIDFactory;
import edu.toronto.cs.se.mmint.mid.MIDLevel;
import edu.toronto.cs.se.mmint.mid.MIDPackage;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.ModelOrigin;
import edu.toronto.cs.se.mmint.mid.MultiModel;
import edu.toronto.cs.se.mmint.mid.library.MultiModelOperatorUtils;
import edu.toronto.cs.se.mmint.mid.library.MultiModelRegistry;
import edu.toronto.cs.se.mmint.mid.library.MultiModelUtils;
import edu.toronto.cs.se.mmint.mid.operator.impl.OperatorImpl;

public class Union extends OperatorImpl {

	@NonNull
	private final static String INPUT_MIDS = "mids";
	@NonNull
	private final static String OUTPUT_MID = "unionMid";

	@NonNull
	private final static String UNION_SEPARATOR = "+";

	@Override
	public Map<String, Model> run(
			Map<String, Model> inputsByName, Map<String, GenericElement> genericsByName,
			Map<String, MultiModel> outputMIDsByName) throws Exception {

		List<Model> inputMIDModels = MultiModelOperatorUtils.getVarargs(inputsByName, INPUT_MIDS);
		MultiModel instanceMID = outputMIDsByName.get(OUTPUT_MID);

		// create union of input mids
		MultiModel unionMID = MIDFactory.eINSTANCE.createMultiModel();
		Model midModelType = MultiModelTypeRegistry.getType(MIDPackage.eNS_URI);
		String baseOutputUri = MultiModelRegistry.getModelAndModelElementUris(instanceMID, MIDLevel.INSTANCES)[0];
		String unionMIDModelName = inputMIDModels.stream()
			.map(Model::getName)
			.collect(Collectors.joining(UNION_SEPARATOR));
		String unionMIDModelUri= MultiModelUtils.getUniqueUri(
			MultiModelUtils.replaceFileNameInUri(baseOutputUri, unionMIDModelName),
			true,
			false);

		Model unionMIDModel = midModelType.createInstanceAndEditor(unionMIDModelUri, ModelOrigin.CREATED, outputMIDsByName.get(OUTPUT_MID));

		Map<String, Model> outputsByName = new HashMap<>();
		outputsByName.put(OUTPUT_MID, unionMIDModel);

		return outputsByName;
	}

}
