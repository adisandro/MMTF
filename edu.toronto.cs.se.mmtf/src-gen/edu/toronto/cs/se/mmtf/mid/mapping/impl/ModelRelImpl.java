/**
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
package edu.toronto.cs.se.mmtf.mid.mapping.impl;

import edu.toronto.cs.se.mmtf.mid.Model;

import edu.toronto.cs.se.mmtf.mid.impl.ModelImpl;

import edu.toronto.cs.se.mmtf.mid.mapping.Link;
import edu.toronto.cs.se.mmtf.mid.mapping.MappingPackage;
import edu.toronto.cs.se.mmtf.mid.mapping.ModelContainer;
import edu.toronto.cs.se.mmtf.mid.mapping.ModelRel;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Rel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.mapping.impl.ModelRelImpl#getModels <em>Models</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.mapping.impl.ModelRelImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.mapping.impl.ModelRelImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link edu.toronto.cs.se.mmtf.mid.mapping.impl.ModelRelImpl#isUnbounded <em>Unbounded</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelRelImpl extends ModelImpl implements ModelRel {
	/**
	 * The cached value of the '{@link #getModels() <em>Models</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModels()
	 * @generated
	 * @ordered
	 */
	protected EList<Model> models;

	/**
	 * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> links;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelContainer> containers;

	/**
	 * The default value of the '{@link #isUnbounded() <em>Unbounded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnbounded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNBOUNDED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnbounded() <em>Unbounded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnbounded()
	 * @generated
	 * @ordered
	 */
	protected boolean unbounded = UNBOUNDED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelRelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.MODEL_REL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Model> getModels() {
		if (models == null) {
			models = new EObjectResolvingEList<Model>(Model.class, this, MappingPackage.MODEL_REL__MODELS);
		}
		return models;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Link> getLinks() {
		if (links == null) {
			links = new EObjectContainmentEList<Link>(Link.class, this, MappingPackage.MODEL_REL__LINKS);
		}
		return links;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelContainer> getContainers() {
		if (containers == null) {
			containers = new EObjectContainmentEList<ModelContainer>(ModelContainer.class, this, MappingPackage.MODEL_REL__CONTAINERS);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnbounded() {
		return unbounded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnbounded(boolean newUnbounded) {
		boolean oldUnbounded = unbounded;
		unbounded = newUnbounded;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.MODEL_REL__UNBOUNDED, oldUnbounded, unbounded));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MappingPackage.MODEL_REL__LINKS:
				return ((InternalEList<?>)getLinks()).basicRemove(otherEnd, msgs);
			case MappingPackage.MODEL_REL__CONTAINERS:
				return ((InternalEList<?>)getContainers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MappingPackage.MODEL_REL__MODELS:
				return getModels();
			case MappingPackage.MODEL_REL__LINKS:
				return getLinks();
			case MappingPackage.MODEL_REL__CONTAINERS:
				return getContainers();
			case MappingPackage.MODEL_REL__UNBOUNDED:
				return isUnbounded();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MappingPackage.MODEL_REL__MODELS:
				getModels().clear();
				getModels().addAll((Collection<? extends Model>)newValue);
				return;
			case MappingPackage.MODEL_REL__LINKS:
				getLinks().clear();
				getLinks().addAll((Collection<? extends Link>)newValue);
				return;
			case MappingPackage.MODEL_REL__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection<? extends ModelContainer>)newValue);
				return;
			case MappingPackage.MODEL_REL__UNBOUNDED:
				setUnbounded((Boolean)newValue);
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
			case MappingPackage.MODEL_REL__MODELS:
				getModels().clear();
				return;
			case MappingPackage.MODEL_REL__LINKS:
				getLinks().clear();
				return;
			case MappingPackage.MODEL_REL__CONTAINERS:
				getContainers().clear();
				return;
			case MappingPackage.MODEL_REL__UNBOUNDED:
				setUnbounded(UNBOUNDED_EDEFAULT);
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
			case MappingPackage.MODEL_REL__MODELS:
				return models != null && !models.isEmpty();
			case MappingPackage.MODEL_REL__LINKS:
				return links != null && !links.isEmpty();
			case MappingPackage.MODEL_REL__CONTAINERS:
				return containers != null && !containers.isEmpty();
			case MappingPackage.MODEL_REL__UNBOUNDED:
				return unbounded != UNBOUNDED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (unbounded: ");
		result.append(unbounded);
		result.append(')');
		return result.toString();
	}

} //ModelRelImpl
