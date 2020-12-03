/*******************************************************************************
 * Copyright (c) 2020, 2020 Alessio Di Sandro.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Alessio Di Sandro - Implementation
 *******************************************************************************/
package edu.toronto.cs.se.mmint.types.lts.operators;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import edu.toronto.cs.se.mmint.MIDTypeRegistry;
import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.lean.operators.ToLean;
import edu.toronto.cs.se.mmint.mid.GenericElement;
import edu.toronto.cs.se.mmint.mid.MID;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.operator.Operator;
import edu.toronto.cs.se.mmint.mid.operator.OperatorPackage;
import edu.toronto.cs.se.mmint.mid.utils.FileUtils;
import edu.toronto.cs.se.mmint.mid.utils.MIDRegistry;

public class LTSToLean extends ToLean {

  private final static String LEAN_EXT = ".lean";
  private final static String LEAN_MAIN_FILE = "main";
  private final static List<String> LEAN_BUNDLE_FILES = List.of("justification", "LTS", "Meta", "patterns");
  private final static String LEAN_BUNDLE_DIR = "lean";

  @Override
  public void createWorkflowInstanceOutputs(Operator newOperator, Map<String, GenericElement> genericsByName,
                                            Map<String, Model> inputsByName, MID workflowMID) throws MMINTException {
    var fileModelType = MIDTypeRegistry.<Model>getType(Output.MODEL_TYPE_ID);
    // dynamic and static lean files as varargs
    for (var i = 0; i < LTSToLean.LEAN_BUNDLE_FILES.size() + 2; i++) {
      var outputModelId = MIDRegistry.getNextWorkflowID(workflowMID);
      var outputModel = fileModelType.createWorkflowInstance(outputModelId, workflowMID);
      var outputModelEndpoint = this.getOutputs().get(0).createWorkflowInstance(
        outputModel, newOperator, OperatorPackage.eINSTANCE.getOperator_Outputs().getName());
      String fileName;
      fileName = switch (i) {
        case 0  -> LTSToLean.LEAN_MAIN_FILE;
        case 1  -> inputsByName.get(Input.MODEL).getName();
        default -> LTSToLean.LEAN_BUNDLE_FILES.get(i-2);
      };
      var outputName = outputModelEndpoint.getName() + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
      outputModelEndpoint.setName(outputName);
    }
  }

  @Override
  protected void init(Map<String, Model> inputsByName, Map<String, MID> outputMIDsByName) throws Exception {
    super.init(inputsByName, outputMIDsByName);
    var workingPath = getWorkingPath() + File.separator;
    // dynamic lean files generated from the input model
    super.output.leanPaths.add(workingPath + LTSToLean.LEAN_MAIN_FILE + LTSToLean.LEAN_EXT);
    super.output.leanPaths.add(workingPath + this.input.model.getName() + LTSToLean.LEAN_EXT);
    // static lean files from this bundle
    for (var bundleFile : LTSToLean.LEAN_BUNDLE_FILES) {
      bundleFile += LTSToLean.LEAN_EXT;
      var leanPath = workingPath + bundleFile;
      var bundlePath = MIDTypeRegistry.getFileBundlePath(this.getMetatype(),
                                                         LTSToLean.LEAN_BUNDLE_DIR + File.separator + bundleFile);
      Files.copy(Path.of(bundlePath), Path.of(FileUtils.prependWorkspacePath(leanPath)),
                 StandardCopyOption.REPLACE_EXISTING);
      super.output.leanPaths.add(leanPath);
    }
    this.leanGenerator = new LTSToLeanAcceleo(this.input.model.getEMFInstanceRoot(), this.output.leanFolder,
                                              List.of(this.input.model.getName()));
  }
}
