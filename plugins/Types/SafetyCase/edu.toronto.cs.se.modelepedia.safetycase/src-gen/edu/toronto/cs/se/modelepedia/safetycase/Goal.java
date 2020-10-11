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
package edu.toronto.cs.se.modelepedia.safetycase;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goal</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see edu.toronto.cs.se.modelepedia.safetycase.SafetyCasePackage#getGoal()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='GoalSupporter GoalContext ASILInheritance'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot GoalSupporter='\n\t\t\tlet children : Set(Supporter) = self.supportedBy.target -&gt; closure(p | if p.oclIsKindOf(CoreElement) then p.oclAsSet() else p.oclAsType(Supportable).supportedBy.target endif) -&gt; asSet()\n\t\t\tin children -&gt; selectByKind(CoreElement) -&gt; forAll(s | s.oclIsKindOf(Goal) or s.oclIsKindOf(Strategy) or s.oclIsKindOf(Solution))' GoalContext='self.inContextOf.context -&gt; forAll(c | c.oclIsKindOf(Context) or c.oclIsKindOf(Assumption) or c.oclIsKindOf(Justification))' ASILInheritance='\n\t\t\tlet parents : Set(Goal) = self.supports.source -&gt; closure(c | if c.oclIsKindOf(Goal) or c.oclIsKindOf(ASILDecompositionStrategy) then c.oclAsSet() else c.supports.source endif) -&gt; selectByKind(Goal) -&gt; asSet()\n\t\t\tin parents -&gt; forAll(g | if g.asil = null then true else if self.asil = null then false else g.asil.value = ASILLevel::QM or (g.asil.value.toString() &lt;= self.asil.value.toString() and self.asil.value &lt;&gt; ASILLevel::QM) endif endif)'"
 * @generated
 */
public interface Goal extends DecomposableCoreElement, StatefulElement, ASILfulElement {
} // Goal
