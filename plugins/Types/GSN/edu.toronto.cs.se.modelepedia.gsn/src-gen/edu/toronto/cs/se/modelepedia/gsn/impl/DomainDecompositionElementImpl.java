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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import edu.toronto.cs.se.modelepedia.gsn.Domain;
import edu.toronto.cs.se.modelepedia.gsn.DomainDecompositionElement;
import edu.toronto.cs.se.modelepedia.gsn.GSNPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Domain Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link edu.toronto.cs.se.modelepedia.gsn.impl.DomainDecompositionElementImpl#getDomain <em>Domain</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class DomainDecompositionElementImpl extends MinimalEObjectImpl.Container implements
  DomainDecompositionElement {
  /**
   * The cached value of the '{@link #getDomain() <em>Domain</em>}' containment reference. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @see #getDomain()
   * @generated
   * @ordered
   */
  protected Domain domain;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected DomainDecompositionElementImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return GSNPackage.Literals.DOMAIN_DECOMPOSITION_ELEMENT;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Domain getDomain() {
    return domain;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetDomain(Domain newDomain, NotificationChain msgs) {
    Domain oldDomain = domain;
    domain = newDomain;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                                                             GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN, oldDomain,
                                                             newDomain);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void setDomain(Domain newDomain) {
    if (newDomain != domain) {
      NotificationChain msgs = null;
      if (domain != null)
        msgs = ((InternalEObject) domain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
          - GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN, null, msgs);
      if (newDomain != null)
        msgs = ((InternalEObject) newDomain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
          - GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN, null, msgs);
      msgs = basicSetDomain(newDomain, msgs);
      if (msgs != null)
        msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN, newDomain,
                                    newDomain));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
    case GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN:
      return basicSetDomain(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
    case GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN:
      return getDomain();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
    case GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN:
      setDomain((Domain) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
    case GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN:
      setDomain((Domain) null);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
    case GSNPackage.DOMAIN_DECOMPOSITION_ELEMENT__DOMAIN:
      return domain != null;
    }
    return super.eIsSet(featureID);
  }

} // DomainElementImpl
