/*
 * Copyright (c) 2012 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Vivien Suen.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.mmtf.mid.relationship.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import edu.toronto.cs.se.mmtf.MMTF.MMTFRegistry;
import edu.toronto.cs.se.mmtf.mid.MultiModel;
import edu.toronto.cs.se.mmtf.mid.relationship.Link;
import edu.toronto.cs.se.mmtf.mid.relationship.ModelRel;
import edu.toronto.cs.se.mmtf.mid.trait.MultiModelConstraintChecker;

/**
 * The command to delete a link.
 * 
 * @author Alessio Di Sandro
 * 
 */
public class LinkDelCommand extends DestroyElementCommand {

	/**
	 * Constructor: initialises the superclass.
	 * 
	 * @param request
	 *            The request.
	 */
	public LinkDelCommand(DestroyElementRequest request) {

		super(request);
	}

	@Override
	public boolean canExecute() {

		return super.canExecute();
	}

	protected CommandResult doExecuteInstancesLevel(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		return super.doExecuteWithResult(monitor, info);
	}

	protected CommandResult doExecuteTypesLevel(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		MultiModel multiModel = (MultiModel) getElementToDestroy().eContainer().eContainer();
		MMTFRegistry.removeLightLinkType((Link) getElementToDestroy());
		CommandResult result = super.doExecuteWithResult(monitor, info);
		MMTFRegistry.updateRepository(multiModel);

		return result;
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		return (MultiModelConstraintChecker.isInstancesLevel((ModelRel) getElementToDestroy().eContainer())) ?
			doExecuteInstancesLevel(monitor, info) :
			doExecuteTypesLevel(monitor, info);
	}

}
