/**
 * Copyright (c) 2012-2021 Alessio Di Sandro, Marsha Chechik, Nick Fung.
 * All rights reserved. This program and the accompanying materials are made available under the terms
 * of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Alessio Di Sandro - Implementation
 *   Nick Fung - Implementation.
 *
 */
package edu.toronto.cs.se.modelepedia.gsn.impl;

import org.eclipse.emf.ecore.EClass;

import edu.toronto.cs.se.modelepedia.gsn.GSNPackage;
import edu.toronto.cs.se.modelepedia.gsn.OrSupporter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Or Supporter</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class OrSupporterImpl extends SupportConnectorImpl implements OrSupporter {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected OrSupporterImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GSNPackage.Literals.OR_SUPPORTER;
  }

} // OrSupporterImpl
