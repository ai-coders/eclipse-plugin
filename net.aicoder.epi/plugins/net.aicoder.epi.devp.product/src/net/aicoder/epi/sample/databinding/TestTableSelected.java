package net.aicoder.epi.sample.databinding;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.sideeffect.ISideEffect;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.jface.viewers.CheckboxTableViewer;

import java.util.Arrays;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TestTableSelected extends Dialog {
    private DataBindingContext m_bindingContext;
    private WritableSet<String> observableSet=new WritableSet<String>();
    private CheckboxTableViewer checkboxTableViewer;
    private Label lblSelected;
    /**
     * Create the dialog.
     * @param parentShell
     */
    public TestTableSelected(Shell parentShell) {
        super(parentShell);
    }

    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(null);
        observableSet.add("tom");

        checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        Table table = checkboxTableViewer.getTable();
        WritableSet<String> input=new WritableSet<String>();
        input.addAll(Arrays.asList("tom","jerry","donald","snow white"));
        checkboxTableViewer.setContentProvider(new ObservableSetContentProvider());
        checkboxTableViewer.setInput(input);
        table.setBounds(29, 42, 183, 85);
        table.pack();

        Button btnAdd = new Button(container, SWT.NONE);
        btnAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                observableSet.add("jerry");
            }
        });
        btnAdd.setBounds(267, 42, 80, 27);
        btnAdd.setText("添加");

        Button btnDelete = new Button(container, SWT.NONE);
        btnDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                observableSet.remove("tom");
            }
        });
        btnDelete.setBounds(267, 84, 80, 27);
        btnDelete.setText("删除");

        Button btnClear = new Button(container, SWT.NONE);
        btnClear.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                observableSet.clear();
            }
        });
        btnClear.setBounds(267, 126, 80, 27);
        btnClear.setText("清除");
        lblSelected = new Label(container, SWT.NONE);
        lblSelected.setBounds(29, 182, 449, 36);

        Label lblNewLabel_1 = new Label(container, SWT.NONE);
        lblNewLabel_1.setBounds(29, 163, 61, 17);
        lblNewLabel_1.setText("已选择：");

        return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        m_bindingContext = initDataBindings();
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(494, 333);
    }
    public static void main(String[] args) {
        Display display = Display.getDefault();
        Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {
            public void run() {
                try {
                    TestTableSelected testTable = new TestTableSelected(null);
                    testTable.open();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();
        //
        IViewerObservableSet observeCheckedSetCheckboxTableViewer = ViewerProperties.checkedElements(String.class).observe(checkboxTableViewer);
        bindingContext.bindSet(observeCheckedSetCheckboxTableViewer, observableSet, null, null);
        //
        ISideEffect.create(
                observableSet::size, (s)->{
                    lblSelected.setText(String.join(",", observableSet));
            });
        return bindingContext;
    }
}