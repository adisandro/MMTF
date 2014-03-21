/**
 * Copyright (c) 2012-2014 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmint.mid.diagram.library;

import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

import edu.toronto.cs.se.mmint.MMINTException;
import edu.toronto.cs.se.mmint.MultiModelTypeRegistry;
import edu.toronto.cs.se.mmint.MMINTException.Type;
import edu.toronto.cs.se.mmint.mid.ExtendibleElementConstraintLanguage;
import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.MultiModel;
import edu.toronto.cs.se.mmint.mid.diagram.part.MidElementChooserDialog;
import edu.toronto.cs.se.mmint.mid.editor.Editor;
import edu.toronto.cs.se.mmint.mid.library.MultiModelUtils;
import edu.toronto.cs.se.mmint.mid.relationship.ModelEndpointReference;
import edu.toronto.cs.se.mmint.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmint.mid.ui.EditorCreationWizardDialog;
import edu.toronto.cs.se.mmint.mid.ui.MultiModelTreeSelectionDialog;

/**
 * A container for common functions of a Mid diagram.
 * 
 * @author Alessio Di Sandro
 * 
 */
public class MidDiagramUtils {

	public final static String CONSTRAINT_LANGUAGE_SEPARATOR = ":";

	protected static Object openSelectDialog(MultiModelTreeSelectionDialog dialog, String title, String message) throws MMINTException {

		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setAllowMultiple(false);

		Object selection = dialog.getOnlyResult();
		if (selection == null) { // more than one choice
			if (dialog.open() == Window.CANCEL) {
				throw new MMINTException(Type.WARNING, "Dialog cancel button pressed");
			}
			selection = dialog.getFirstResult();
			if (selection == null) { // dialog opened and nothing selected
				throw new MMINTException(Type.WARNING, "Dialog ok button pressed with no selection");
			}
		}

		return selection;
	}

	/**
	 * Shows a dialog to choose one among existing models and imports it.
	 * 
	 * @param relOnly
	 *            True to allow the selection of relationship files only, false
	 *            to allow all registered model files.
	 * @return The uri of the imported model.
	 * @throws Exception
	 *             If the model import was not completed for any reason.
	 */
	public static String selectModelToImport(boolean relOnly) throws Exception {

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		DiagramDocumentEditor editor = (DiagramDocumentEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		View view = (View) editor.getDiagramEditPart().getModel();

		MidElementChooserDialog dialog = new MidElementChooserDialog(shell, view, relOnly);
		if (dialog.open() == Window.CANCEL) {
			throw new MMINTException("Dialog cancel button pressed");
		}

		return dialog.getSelectedModelElementURI().toPlatformString(true);
	}

	/**
	 * Shows a tree dialog to create a model choosing from the registered model
	 * types, and executes its wizard.
	 * 
	 * @param multiModel
	 *            The multimodel.
	 * @return The editor for the created model.
	 * @throws Exception
	 *             If the model creation was not completed for any reason.
	 */
	public static Editor selectModelTypeToCreate(MultiModel multiModel) throws Exception {

		ElementTreeSelectionDialog dialog = MultiModelTypeRegistry.getModelCreationDialog();
		dialog.setTitle("Create new model");
		dialog.setMessage("Choose editor to create model");
		dialog.setAllowMultiple(false);

		if (dialog.open() == Window.CANCEL) {
			throw new MMINTException("Model creation cancelled");
		}
		Object selection = dialog.getFirstResult();
		if (selection == null) {
			throw new MMINTException("Model creation dialog ok button pressed with no selection");
		}
		Editor editorType = (Editor) selection;

		IStructuredSelection multiModelContainer;
		String multiModelContainerUri = MultiModelUtils.replaceLastSegmentInUri(multiModel.eResource().getURI().toPlatformString(true), "");
		try {
			multiModelContainer = new StructuredSelection(
				ResourcesPlugin.getWorkspace().getRoot().getFolder(
					new Path(multiModelContainerUri)
				)
			);
		}
		catch (Exception e) {
			multiModelContainer = new StructuredSelection(
				ResourcesPlugin.getWorkspace().getRoot().getProject(multiModelContainerUri)
			);
		}
		EditorCreationWizardDialog wizDialog = editorType.invokeInstanceWizard(multiModelContainer);
		if (wizDialog == null) {
			throw new MMINTException("Model creation cancelled");
		}

		return editorType.createInstance(wizDialog.getCreatedModelUri(), multiModel);
	}

	public static ModelEndpointReference selectModelTypeEndpointToCreate(ModelRel modelRel, List<String> modelTypeEndpointUris, String modelEndpointId) throws MMINTException {

		MultiModelTreeSelectionDialog dialog = MultiModelTypeRegistry.getModelEndpointCreationDialog(modelRel, modelTypeEndpointUris);
		String title = "Create new model endpoint";
		String message = "Choose " + modelEndpointId + "model type endpoint role";

		return (ModelEndpointReference) openSelectDialog(dialog, title, message);
	}

	public static ModelRel selectModelRelTypeToCreate(Model srcModel, Model tgtModel) throws MMINTException {

		MultiModelTreeSelectionDialog dialog = MultiModelTypeRegistry.getModelRelCreationDialog(srcModel, tgtModel);
		String title = "Create new model relationship";
		String message = "Choose model relationship type";

		return (ModelRel) openSelectDialog(dialog, title, message);
	}

	/**
	 * Shows a tree dialog to select a model type choosing from the registered
	 * model types.
	 * 
	 * @return The choosen model type.
	 * @throws MMINTException
	 *             If the selection was not completed for any reason.
	 */
	public static Model selectModelTypeToExtend(MultiModel multiModel) throws MMINTException {

		MultiModelTreeSelectionDialog dialog = MultiModelTypeRegistry.getModelTypeCreationDialog(multiModel);
		String title = "Create new light model type";
		String message = "Choose model supertype";

		return (Model) openSelectDialog(dialog, title, message);
	}

	/**
	 * Shows a tree dialog to select a model relationship type choosing from the
	 * registered model relationship types.
	 * 
	 * @return The choosen model relationship type.
	 * @throws MMINTException
	 *             If the selection was not completed for any reason.
	 */
	public static ModelRel selectModelRelTypeToExtend(MultiModel multiModel, Model srcModelType, Model tgtModelType) throws MMINTException {

		MultiModelTreeSelectionDialog dialog = MultiModelTypeRegistry.getModelRelTypeCreationDialog(srcModelType, tgtModelType, multiModel);
		String title = "Create new light model relationship type";
		String message = "Choose model relationship supertype";

		return (ModelRel) openSelectDialog(dialog, title, message);
	}

	public static boolean getBooleanInput(String dialogTitle, String dialogMessage) {

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		return MessageDialog.openQuestion(shell, dialogTitle, dialogMessage);
	}

	/**
	 * Shows an input dialog to get text from the user.
	 * 
	 * @param dialogTitle
	 *            The dialog title.
	 * @param dialogMessage
	 *            The dialog message.
	 * @param dialogInitial TODO
	 * @return The text input from the user.
	 * @throws MMINTException
	 *             If the text input was not completed for any reason.
	 */
	public static String getStringInput(String dialogTitle, String dialogMessage, String dialogInitial) throws MMINTException {

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		InputDialog dialog = new InputDialog(shell, dialogTitle, dialogMessage, dialogInitial, null);

		if (dialog.open() == Window.CANCEL) {
			throw new MMINTException("Operation cancelled");
		}
		String text = dialog.getValue();
		if (text == null) {
			text = "";
		}

		return text;
	}

	public static String getBigStringInput(String dialogTitle, String dialogMessage, String dialogInitial) throws MMINTException {

		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		InputDialog dialog = new InputDialog(shell, dialogTitle, dialogMessage, dialogInitial, null) {
			@Override
			protected int getInputTextStyle() {
				return SWT.MULTI | SWT.BORDER;
			}
			@Override
			protected Control createDialogArea(Composite parent) {
				Control res = super.createDialogArea(parent);
				((GridData) this.getText().getLayoutData()).heightHint = 100;
				return res;
			}
		};

		if (dialog.open() == Window.CANCEL) {
			throw new MMINTException("Operation cancelled");
		}
		String text = dialog.getValue();
		if (text == null) {
			text = "";
		}

		return text;
	}

	public static String[] getConstraintInput(String dialogTitle, String dialogInitial) throws MMINTException {

		String text = getBigStringInput(dialogTitle, "Insert new constraint", dialogInitial);
		String[] constraint = text.split(CONSTRAINT_LANGUAGE_SEPARATOR);
		if (constraint.length == 1) {
			String constraintImplementation = constraint[0];
			constraint = new String[2];
			constraint[0] = ExtendibleElementConstraintLanguage.OCL.getLiteral();
			constraint[1] = constraintImplementation;
		}

		return constraint;
	}

}