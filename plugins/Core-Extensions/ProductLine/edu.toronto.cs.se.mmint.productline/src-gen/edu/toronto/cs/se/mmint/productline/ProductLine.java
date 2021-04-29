/*******************************************************************************
 * Copyright (c) 2021, 2021 Alessio Di Sandro.
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
package edu.toronto.cs.se.mmint.productline;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Line</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.toronto.cs.se.mmint.productline.ProductLine#getFeatures <em>Features</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.productline.ProductLine#getClasses <em>Classes</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.productline.ProductLine#getReferences <em>References</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmint.productline.ProductLine#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 *
 * @see edu.toronto.cs.se.mmint.productline.ProductLinePackage#getProductLine()
 * @model
 * @generated
 */
public interface ProductLine extends EObject {
  /**
   * Returns the value of the '<em><b>Features</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' attribute.
   * @see #setFeatures(String)
   * @see edu.toronto.cs.se.mmint.productline.ProductLinePackage#getProductLine_Features()
   * @model required="true"
   * @generated
   */
  String getFeatures();

  /**
   * Sets the value of the '{@link edu.toronto.cs.se.mmint.productline.ProductLine#getFeatures <em>Features</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Features</em>' attribute.
   * @see #getFeatures()
   * @generated
   */
  void setFeatures(String value);

  /**
   * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
   * The list contents are of type {@link edu.toronto.cs.se.mmint.productline.Class}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classes</em>' containment reference list.
   * @see edu.toronto.cs.se.mmint.productline.ProductLinePackage#getProductLine_Classes()
   * @model containment="true"
   * @generated
   */
  EList<edu.toronto.cs.se.mmint.productline.Class> getClasses();

  /**
   * Returns the value of the '<em><b>References</b></em>' containment reference list.
   * The list contents are of type {@link edu.toronto.cs.se.mmint.productline.Reference}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>References</em>' containment reference list.
   * @see edu.toronto.cs.se.mmint.productline.ProductLinePackage#getProductLine_References()
   * @model containment="true"
   * @generated
   */
  EList<Reference> getReferences();

  /**
   * Returns the value of the '<em><b>Metamodel</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Metamodel</em>' reference.
   * @see #setMetamodel(EPackage)
   * @see edu.toronto.cs.se.mmint.productline.ProductLinePackage#getProductLine_Metamodel()
   * @model required="true"
   * @generated
   */
  EPackage getMetamodel();

  /**
   * Sets the value of the '{@link edu.toronto.cs.se.mmint.productline.ProductLine#getMetamodel <em>Metamodel</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Metamodel</em>' reference.
   * @see #getMetamodel()
   * @generated
   */
  void setMetamodel(EPackage value);

} // ProductLine
