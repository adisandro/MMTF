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
package edu.toronto.cs.se.mmint.mid.relationship.provider;

import edu.toronto.cs.se.mmint.mid.relationship.util.RelationshipAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RelationshipItemProviderAdapterFactory extends RelationshipAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
   * This constructs an instance.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public RelationshipItemProviderAdapterFactory() {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.ModelRel} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelRelItemProvider modelRelItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.ModelRel}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createModelRelAdapter() {
    if (modelRelItemProvider == null) {
      modelRelItemProvider = new ModelRelItemProvider(this);
    }

    return modelRelItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.BinaryModelRel} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BinaryModelRelItemProvider binaryModelRelItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.BinaryModelRel}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createBinaryModelRelAdapter() {
    if (binaryModelRelItemProvider == null) {
      binaryModelRelItemProvider = new BinaryModelRelItemProvider(this);
    }

    return binaryModelRelItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.ModelEndpointReference} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelEndpointReferenceItemProvider modelEndpointReferenceItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.ModelEndpointReference}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createModelEndpointReferenceAdapter() {
    if (modelEndpointReferenceItemProvider == null) {
      modelEndpointReferenceItemProvider = new ModelEndpointReferenceItemProvider(this);
    }

    return modelEndpointReferenceItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.ModelElementReference} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelElementReferenceItemProvider modelElementReferenceItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.ModelElementReference}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createModelElementReferenceAdapter() {
    if (modelElementReferenceItemProvider == null) {
      modelElementReferenceItemProvider = new ModelElementReferenceItemProvider(this);
    }

    return modelElementReferenceItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.Mapping} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected MappingItemProvider mappingItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.Mapping}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createMappingAdapter() {
    if (mappingItemProvider == null) {
      mappingItemProvider = new MappingItemProvider(this);
    }

    return mappingItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.BinaryMapping} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BinaryMappingItemProvider binaryMappingItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.BinaryMapping}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createBinaryMappingAdapter() {
    if (binaryMappingItemProvider == null) {
      binaryMappingItemProvider = new BinaryMappingItemProvider(this);
    }

    return binaryMappingItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.ModelElementEndpoint} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelElementEndpointItemProvider modelElementEndpointItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.ModelElementEndpoint}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createModelElementEndpointAdapter() {
    if (modelElementEndpointItemProvider == null) {
      modelElementEndpointItemProvider = new ModelElementEndpointItemProvider(this);
    }

    return modelElementEndpointItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.MappingReference} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected MappingReferenceItemProvider mappingReferenceItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.MappingReference}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createMappingReferenceAdapter() {
    if (mappingReferenceItemProvider == null) {
      mappingReferenceItemProvider = new MappingReferenceItemProvider(this);
    }

    return mappingReferenceItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.BinaryMappingReference} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected BinaryMappingReferenceItemProvider binaryMappingReferenceItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.BinaryMappingReference}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createBinaryMappingReferenceAdapter() {
    if (binaryMappingReferenceItemProvider == null) {
      binaryMappingReferenceItemProvider = new BinaryMappingReferenceItemProvider(this);
    }

    return binaryMappingReferenceItemProvider;
  }

	/**
   * This keeps track of the one adapter used for all {@link edu.toronto.cs.se.mmint.mid.relationship.ModelElementEndpointReference} instances.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected ModelElementEndpointReferenceItemProvider modelElementEndpointReferenceItemProvider;

	/**
   * This creates an adapter for a {@link edu.toronto.cs.se.mmint.mid.relationship.ModelElementEndpointReference}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter createModelElementEndpointReferenceAdapter() {
    if (modelElementEndpointReferenceItemProvider == null) {
      modelElementEndpointReferenceItemProvider = new ModelElementEndpointReferenceItemProvider(this);
    }

    return modelElementEndpointReferenceItemProvider;
  }

	/**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public ComposeableAdapterFactory getRootAdapterFactory() {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

	/**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
    this.parentAdapterFactory = parentAdapterFactory;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public boolean isFactoryForType(Object type) {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

	/**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
    return super.adapt(notifier, this);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object adapt(Object object, Object type) {
    if (isFactoryForType(type)) {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
        return adapter;
      }
    }

    return null;
  }

	/**
   * This adds a listener.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public void addListener(INotifyChangedListener notifyChangedListener) {
    changeNotifier.addListener(notifyChangedListener);
  }

	/**
   * This removes a listener.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public void removeListener(INotifyChangedListener notifyChangedListener) {
    changeNotifier.removeListener(notifyChangedListener);
  }

	/**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public void fireNotifyChanged(Notification notification) {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null) {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

	/**
   * This disposes all of the item providers created by this factory. 
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
  public void dispose() {
    if (modelRelItemProvider != null) modelRelItemProvider.dispose();
    if (binaryModelRelItemProvider != null) binaryModelRelItemProvider.dispose();
    if (modelEndpointReferenceItemProvider != null) modelEndpointReferenceItemProvider.dispose();
    if (modelElementReferenceItemProvider != null) modelElementReferenceItemProvider.dispose();
    if (mappingItemProvider != null) mappingItemProvider.dispose();
    if (binaryMappingItemProvider != null) binaryMappingItemProvider.dispose();
    if (modelElementEndpointItemProvider != null) modelElementEndpointItemProvider.dispose();
    if (mappingReferenceItemProvider != null) mappingReferenceItemProvider.dispose();
    if (binaryMappingReferenceItemProvider != null) binaryMappingReferenceItemProvider.dispose();
    if (modelElementEndpointReferenceItemProvider != null) modelElementEndpointReferenceItemProvider.dispose();
  }

}
