/**
 * Copyright (c) 2012-2020 Marsha Chechik, Alessio Di Sandro, Michalis Famelis,
 * Rick Salay, Nick Fung.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Alessio Di Sandro - Implementation.
 *    Nick Fung - Implementation.
 */
package edu.toronto.cs.se.modelepedia.safetycase.operator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import edu.toronto.cs.se.modelepedia.safetycase.Goal;
import edu.toronto.cs.se.modelepedia.safetycase.Solution;

public class GSNSliceRecheck extends GSNSlice {

  // Get all model elements in a safety case that needs to be re-checked for
  // state validity given the input element that requires content re-check.
  @Override
  protected Map<EObject, Set<EObject>> getAllImpactedElements(EObject critModelObj, Set<EObject> alreadyImpacted) {
    var impacted = new HashMap<EObject, Set<EObject>>();
    var impactedModelObjs = new HashSet<EObject>();
    impactedModelObjs.add(critModelObj);
    alreadyImpacted.add(critModelObj);
    // Slice all elements iteratively until a fixed point is reached.
    var impactedCur = new HashSet<EObject>();
    var impactedNext = new HashSet<EObject>();
    impactedCur.addAll(alreadyImpacted);
    while (!impactedCur.isEmpty()) {
      for (var elem : alreadyImpacted) {
        impactedNext.addAll(getDirectlyImpactedElements(elem, alreadyImpacted));
      }
      var isRepeat = false;
      impactedCur.clear();
      for (var elem : impactedNext) {
        if (!alreadyImpacted.contains(elem)) {
          isRepeat = true;
          alreadyImpacted.add(elem);
          impactedModelObjs.add(elem);
        }
      }
      if (isRepeat) {
        impactedCur.addAll(alreadyImpacted);
      }
    }
    impacted.put(critModelObj, impactedModelObjs);

    return impacted;
  }

  @Override
  protected Set<EObject> getDirectlyImpactedElements(EObject modelObj, Set<EObject> alreadyImpacted) {
    var impacted = new HashSet<EObject>();

    // If input is a goal, then the state validity of all ancestor goals should be rechecked.
    if (modelObj instanceof Goal) {
      var g = (Goal) modelObj;
      impacted.addAll(getAncestorGoals(g, alreadyImpacted));
    }

    // If input is a solution, then the state validity of all ancestor goals should be rechecked.
    if (modelObj instanceof Solution) {
      var s = (Solution) modelObj;
      impacted.addAll(getAncestorGoals(s, alreadyImpacted));
    }

    impacted.removeAll(alreadyImpacted);
    return impacted;
  }
}
