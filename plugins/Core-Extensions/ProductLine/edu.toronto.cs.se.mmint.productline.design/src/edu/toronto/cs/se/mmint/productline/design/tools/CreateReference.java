package edu.toronto.cs.se.mmint.productline.design.tools;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.business.api.action.AbstractExternalJavaAction;
import org.eclipse.sirius.business.api.session.SessionManager;

import edu.toronto.cs.se.mmint.mid.ui.MIDDialogCancellation;
import edu.toronto.cs.se.mmint.mid.ui.MIDDialogs;
import edu.toronto.cs.se.mmint.productline.Class;
import edu.toronto.cs.se.mmint.productline.ProductLine;
import edu.toronto.cs.se.mmint.productline.ProductLineFactory;

public class CreateReference extends AbstractExternalJavaAction {

  private EReference[] getReferences(Class srcClass, @Nullable Class tgtClass) {
    return srcClass.getType().getEAllReferences().stream()
    .filter(r -> !r.isDerived())
    .filter(r -> tgtClass == null || r.getEReferenceType().isSuperTypeOf(tgtClass.getType()))
    .toArray(EReference[]::new);
  }

  @Override
  public boolean canExecute(Collection<? extends EObject> arg0) {
    var srcClass = (Class) arg0.iterator().next();
    return getReferences(srcClass, null).length != 0;
  }

  @Override
  public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
    var srcClass = (Class) arg1.get("source");
    var tgtClass = (Class) arg1.get("target");
    var sSession = SessionManager.INSTANCE.getSession(srcClass);
    var sDomain = sSession.getTransactionalEditingDomain();
    sDomain.getCommandStack().execute(new CreateReferenceCommand(sDomain, srcClass, tgtClass));
  }

  private class CreateReferenceCommand extends RecordingCommand {
    private Class srcClass, tgtClass;

    public CreateReferenceCommand(TransactionalEditingDomain domain, Class srcClass, Class tgtClass) {
      super(domain);
      this.srcClass = srcClass;
      this.tgtClass = tgtClass;
    }

    @Override
    protected void doExecute() {
      try {
        var labelProvider = LabelProvider.createTextProvider(c -> ((EReference) c).getName());
        var contentProvider = new ArrayContentProvider();
        var references = getReferences(this.srcClass, this.tgtClass);
        if (references.length == 0) {
          throw new MIDDialogCancellation();
        }
        var type = MIDDialogs.<EReference>openListDialog("Create Reference", "Select Reference", references,
                                                         contentProvider, labelProvider);
        var reference = ProductLineFactory.eINSTANCE.createReference();
        reference.setType(type);
        reference.setSource(this.srcClass);
        reference.getTargets().add(this.tgtClass);
        ((ProductLine) this.srcClass.eContainer()).getReferences().add(reference);
      }
      catch (MIDDialogCancellation e) {
        // do nothing
      }
    }
  }
}
