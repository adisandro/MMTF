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
package edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.impl;

import edu.toronto.cs.se.mavo.impl.MAVOReferenceImpl;

import edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.ClassReference;
import edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.ICSE15_SequenceDiagram_MAVOPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.impl.ClassReferenceImpl#getSource <em>Source</em>}</li>
 *   <li>{@link edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.impl.ClassReferenceImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassReferenceImpl extends MAVOReferenceImpl implements ClassReference {
	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ICSE15_SequenceDiagram_MAVOPackage.Literals.CLASS_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object getSource() {
		if (eContainerFeatureID() != ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE) return null;
		return (edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object newSource, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSource, ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object newSource) {
		if (newSource != eInternalContainer() || (eContainerFeatureID() != ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, ICSE15_SequenceDiagram_MAVOPackage.OBJECT__CLASS, edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class newTarget, NotificationChain msgs) {
		edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, ICSE15_SequenceDiagram_MAVOPackage.CLASS__OBJECTS, edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, ICSE15_SequenceDiagram_MAVOPackage.CLASS__OBJECTS, edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSource((edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object)otherEnd, msgs);
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, ICSE15_SequenceDiagram_MAVOPackage.CLASS__OBJECTS, edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class.class, msgs);
				return basicSetTarget((edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				return basicSetSource(null, msgs);
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET:
				return basicSetTarget(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				return eInternalContainer().eInverseRemove(this, ICSE15_SequenceDiagram_MAVOPackage.OBJECT__CLASS, edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				return getSource();
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				setSource((edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object)newValue);
				return;
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET:
				setTarget((edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				setSource((edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Object)null);
				return;
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET:
				setTarget((edu.toronto.cs.se.modelepedia.icse15_sequencediagram_mavo.Class)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__SOURCE:
				return getSource() != null;
			case ICSE15_SequenceDiagram_MAVOPackage.CLASS_REFERENCE__TARGET:
				return target != null;
		}
		return super.eIsSet(featureID);
	}

} //ClassReferenceImpl