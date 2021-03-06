/*******************************************************************************
 * Copyright (c) 2020, 2021 Alessio Di Sandro.
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
package edu.toronto.cs.se.modelepedia.gsn.reasoning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.annotation.Nullable;

import edu.toronto.cs.se.mmint.mid.Model;
import edu.toronto.cs.se.mmint.mid.ui.GMFUtils;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogCancellation;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogs;
import edu.toronto.cs.se.modelepedia.gsn.GSNFactory;
import edu.toronto.cs.se.modelepedia.gsn.Property;

/**
 * The specification of a Lean encoder that handles GSN property decomposition quirks.
 *
 * @author Alessio Di Sandro
 */
public interface IGSNLeanEncoder {

  static class PropertyVariable {
    public interface Replacer {
      public String replace(List<String> modelNames);
    }
    public String name;
    public Map<EClass, EStructuralFeature> validTypes;
    public Replacer replacer;
    public PropertyVariable(String name, Map<EClass, EStructuralFeature> validTypes, Replacer replacer) {
      this.name = Objects.requireNonNull(name);
      this.validTypes = Objects.requireNonNull(validTypes);
      this.replacer = replacer;
    }
  }
  static class PropertyTemplate {
    public String formal;
    public String informal;
    public @Nullable String category;
    public List<PropertyVariable> variables;
    public static PropertyTemplate CUSTOM = new PropertyTemplate("", "Custom property", null, List.of());

    public PropertyTemplate(String formal, String informal, @Nullable String category,
                            List<PropertyVariable> variables) {
      this.formal  = formal;
      this.informal = Objects.requireNonNull(informal);
      this.category = category;
      this.variables = Objects.requireNonNull(variables);
    }

    public Property bindVariables(String title, Map<EClass, List<EObject>> modelObjs) throws MIDDialogCancellation {
      var property = GSNFactory.eINSTANCE.createProperty();
      if (this.variables.isEmpty()) {
        property.setFormal(this.formal);
        property.setInformal(this.informal);
        return property;
      }

      var message = ":\nSelect one or more model elements corresponding to variable ";
      var labelProvider = new AdapterFactoryLabelProvider(GMFUtils.getAdapterFactory());
      var boundFormal = this.formal;
      var boundInformal = this.informal;
      for (var variable : this.variables) {
        var validModelObjs = new ArrayList<EObject>();
        variable.validTypes.keySet().forEach(t -> validModelObjs.addAll(modelObjs.getOrDefault(t, List.of())));
        var boundModelObjs = MIDDialogs.<EObject>openListMultipleDialog(title, boundInformal + message + variable.name,
                                                                        validModelObjs, labelProvider);
        var boundVariables = new ArrayList<String>();
        for (var modelObj : boundModelObjs) {
          var modelObjClass = modelObj.eClass();
          var feature = Stream.concat(Stream.of(modelObjClass), modelObjClass.getEAllSuperTypes().stream())
            .filter(c -> variable.validTypes.containsKey(c))
            .map(c -> variable.validTypes.get(c))
            .findFirst();
          boundVariables.add(feature.map(f -> (String) modelObj.eGet(f)).orElse(modelObj.toString()));
        }
        boundFormal = boundFormal.replace(variable.name, variable.replacer.replace(boundVariables));
        boundInformal = boundInformal.replace(variable.name, String.join(",", boundVariables));
      }
      property.setFormal(boundFormal);
      property.setInformal(boundInformal);

      return property;
    }
  }

  default List<PropertyTemplate> getTemplateProperties() {
    return List.of();
  }

  /**
   * Encodes a Lean property that is decomposed into sub-properties.
   *
   * @param model
   *          The model on which the property is applied.
   * @param property
   *          The Lean property being decomposed.
   * @param subProperties
   *          The Lean sub-properties.
   * @return The Lean encoding representing the property decomposition.
   */
  default String encodePropertyDecomposition(Model model, String property, List<String> subProperties) {
    return property + "\n" + String.join("\n", subProperties);
  }
}
