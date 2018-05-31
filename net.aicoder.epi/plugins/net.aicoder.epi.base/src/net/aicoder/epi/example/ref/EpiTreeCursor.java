package net.aicoder.epi.example.ref;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeCursor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import net.aicoder.epi.base.view.element.table.EpiCellModifier;
import net.aicoder.epi.base.view.element.tree.EpiTree;

public class EpiTreeCursor extends TreeCursor {
	private EpiTree epiTree;

	public EpiTreeCursor(EpiTree epiTree) {
		super(epiTree.getTree(), SWT.NONE);
		this.epiTree = epiTree;
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION));
		setForeground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
		registerCellEditorsListener();
		registerKeyListener();
		registerMouseListener();
		registerTraverseListener();
	}
	

    protected void registerTraverseListener() {
		addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				handleTraverse(e);
			}
		});     
    }
	
    protected void registerMouseListener() {
		addMouseListener(new MouseListener() {
            public void mouseDoubleClick(MouseEvent e) {
            }
            public void mouseDown(MouseEvent e) {
                if (e.button==1)
                    edit();
            }
            public void mouseUp(MouseEvent e) {
            }
		});
    }

    protected void registerKeyListener() {
 		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
			   	TreeViewer viewer = epiTree.getViewer();
				if(e.character!='\0' && e.character!=SWT.CR && e.character!=SWT.LF &&
				        e.character!=SWT.BS && e.character!=SWT.DEL && e.character!=SWT.TAB &&
				        e.character!=SWT.ESC
				    && (e.stateMask==0 || e.stateMask==SWT.SHIFT)){
					
					edit();
					
					CellEditor editor = viewer.getCellEditors()[getColumn()];
					if (editor instanceof TextCellEditor) {
						editor.setValue(String.valueOf(e.character));
						((Text)editor.getControl()).setSelection(1);
					}

				}
			}
			public void keyReleased(KeyEvent e) {
			}
		});	
    }



	protected void registerCellEditorsListener() {
		ICellEditorListener editorListener = new ICellEditorListener() {
			public void applyEditorValue() {
				setVisible(true);
				redraw();
			}

			public void cancelEditor() {
				setVisible(true);
			}

			public void editorValueChanged(boolean oldValidState, boolean newValidState) {
			}
		};

		TreeViewer viewer = epiTree.getViewer();
		CellEditor editors[] = viewer.getCellEditors();
		if (editors != null) {
			for (int i = 0; i < editors.length; ++i) {
				if (editors[i] != null)
					editors[i].addListener(editorListener);
			}
		}
	}
	
	public void edit() {
		TreeViewer viewer = epiTree.getViewer();
	    TreeItem row = getRow();
	    if (row != null) {
	        Object obj = row.getData();
	        ICellModifier cellModifier = viewer.getCellModifier();
	        if (cellModifier instanceof EpiCellModifier) {
	        	EpiCellModifier tableCellModifier = (EpiCellModifier) cellModifier;
	            tableCellModifier.setCanModify(true);
	            viewer.editElement(obj, getColumn());
	            tableCellModifier.setCanModify(false);
	        }
	    }
	}
	
	protected void handleTraverse(TraverseEvent event) {
		Tree tree = epiTree.getTree();
		int row = (getRow() == null) ? 0 : tree.indexOf(getRow());
		int col = getColumn();

		switch (event.detail) {
		case SWT.TRAVERSE_TAB_PREVIOUS:
			if (col != 0)
				col--;
			else {
				if (row != 0) {
					col = tree.getColumnCount() - 1;
					row--;
				} else {
					return;
				}
			}
			setSelection(row, col);
			notifyListeners(SWT.Selection, new Event());
			event.doit = false;
			return;
		case SWT.TRAVERSE_TAB_NEXT:
			if (col != tree.getColumnCount() - 1)
				col++;
			else {
				if (row != tree.getItemCount() - 1) {
					col = 0;
					row++;
				} else {
					return;
				}
			}
			setSelection(row, col);
			notifyListeners(SWT.Selection, new Event());
			event.doit = false;
			return;
		case SWT.TRAVERSE_RETURN:
			edit();
			event.doit = false;
			return;
		}
	}	

}
