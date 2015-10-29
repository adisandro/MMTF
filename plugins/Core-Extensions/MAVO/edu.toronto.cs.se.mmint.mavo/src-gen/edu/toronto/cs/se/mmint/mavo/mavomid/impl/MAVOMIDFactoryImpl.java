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
package edu.toronto.cs.se.mmint.mavo.mavomid.impl;

import edu.toronto.cs.se.mmint.mavo.mavomid.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MAVOMIDFactoryImpl extends EFactoryImpl implements MAVOMIDFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MAVOMIDFactory init() {
		try {
			MAVOMIDFactory theMAVOMIDFactory = (MAVOMIDFactory)EPackage.Registry.INSTANCE.getEFactory(MAVOMIDPackage.eNS_URI);
			if (theMAVOMIDFactory != null) {
				return theMAVOMIDFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MAVOMIDFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOMIDFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MAVOMIDPackage.MAVO_MODEL: return createMAVOModel();
			case MAVOMIDPackage.MAVO_MODEL_ELEMENT: return createMAVOModelElement();
			case MAVOMIDPackage.MAVO_MODEL_REL: return createMAVOModelRel();
			case MAVOMIDPackage.BINARY_MAVO_MODEL_REL: return createBinaryMAVOModelRel();
			case MAVOMIDPackage.MAVO_MODEL_ENDPOINT_REFERENCE: return createMAVOModelEndpointReference();
			case MAVOMIDPackage.MAVO_MAPPING: return createMAVOMapping();
			case MAVOMIDPackage.BINARY_MAVO_MAPPING: return createBinaryMAVOMapping();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOModel createMAVOModel() {
		MAVOModelImpl mavoModel = new MAVOModelImpl();
		return mavoModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOModelElement createMAVOModelElement() {
		MAVOModelElementImpl mavoModelElement = new MAVOModelElementImpl();
		return mavoModelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOModelRel createMAVOModelRel() {
		MAVOModelRelImpl mavoModelRel = new MAVOModelRelImpl();
		return mavoModelRel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryMAVOModelRel createBinaryMAVOModelRel() {
		BinaryMAVOModelRelImpl binaryMAVOModelRel = new BinaryMAVOModelRelImpl();
		return binaryMAVOModelRel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOModelEndpointReference createMAVOModelEndpointReference() {
		MAVOModelEndpointReferenceImpl mavoModelEndpointReference = new MAVOModelEndpointReferenceImpl();
		return mavoModelEndpointReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOMapping createMAVOMapping() {
		MAVOMappingImpl mavoMapping = new MAVOMappingImpl();
		return mavoMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryMAVOMapping createBinaryMAVOMapping() {
		BinaryMAVOMappingImpl binaryMAVOMapping = new BinaryMAVOMappingImpl();
		return binaryMAVOMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MAVOMIDPackage getMAVOMIDPackage() {
		return (MAVOMIDPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MAVOMIDPackage getPackage() {
		return MAVOMIDPackage.eINSTANCE;
	}

} //MAVOMIDFactoryImpl
