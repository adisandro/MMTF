/**
 * Copyright (c) 2012-2021 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 */
package edu.toronto.cs.se.modelepedia.classdiagram;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.toronto.cs.se.modelepedia.classdiagram.Operation#getOwner <em>Owner</em>}</li>
 *   <li>{@link edu.toronto.cs.se.modelepedia.classdiagram.Operation#getParameterTypes <em>Parameter Types</em>}</li>
 * </ul>
 *
 * @see edu.toronto.cs.se.modelepedia.classdiagram.ClassDiagramPackage#getOperation()
 * @model annotation="gmf.node label='name'"
 * @generated
 */
public interface Operation extends TypedElement {
	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.se.modelepedia.classdiagram.Class#getOwnedOperations <em>Owned Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owner</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(edu.toronto.cs.se.modelepedia.classdiagram.Class)
	 * @see edu.toronto.cs.se.modelepedia.classdiagram.ClassDiagramPackage#getOperation_Owner()
	 * @see edu.toronto.cs.se.modelepedia.classdiagram.Class#getOwnedOperations
	 * @model opposite="ownedOperations" required="true" transient="false"
	 * @generated
	 */
	edu.toronto.cs.se.modelepedia.classdiagram.Class getOwner();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.se.modelepedia.classdiagram.Operation#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(edu.toronto.cs.se.modelepedia.classdiagram.Class value);

	/**
	 * Returns the value of the '<em><b>Parameter Types</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.se.modelepedia.classdiagram.Typeable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Types</em>' reference list.
	 * @see edu.toronto.cs.se.modelepedia.classdiagram.ClassDiagramPackage#getOperation_ParameterTypes()
	 * @model
	 * @generated
	 */
	EList<Typeable> getParameterTypes();

} // Operation
