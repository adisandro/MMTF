/**
 */
package edu.toronto.cs.se.mmint3.mm.presentation;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * This is the central singleton for the MM editor plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public final class MMEditorPlugin extends EMFPlugin {
  /**
   * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public static final MMEditorPlugin INSTANCE = new MMEditorPlugin();

  /**
   * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  private static Implementation plugin;

  /**
   * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public MMEditorPlugin() {
    super(new ResourceLocator[] {});
  }

  /**
   * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the singleton instance.
   * @generated
   */
  @Override
  public ResourceLocator getPluginResourceLocator() {
    return MMEditorPlugin.plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @return the singleton instance.
   * @generated
   */
  public static Implementation getPlugin() {
    return MMEditorPlugin.plugin;
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public static class Implementation extends EclipseUIPlugin {
    /**
     * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Implementation() {
      super();

      // Remember the static instance.
      //
      MMEditorPlugin.plugin = this;
    }
  }

}
