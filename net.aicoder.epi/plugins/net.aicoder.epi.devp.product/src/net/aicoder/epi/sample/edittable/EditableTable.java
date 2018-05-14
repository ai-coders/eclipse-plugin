package net.aicoder.epi.sample.edittable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class EditableTable {

	private Table table;
	private Display display;
	
	private static	int ITEM_COUNT = 10;
	private static int COLUMN_COUNT=4;
	private static int COLUMN_WIDTH=150;


	public void Test() {
		display = new Display();

		Shell shell = new Shell(display);
		shell.setText("SWT示例：可编辑的Table ");
		shell.setLayout(new GridLayout());

		//  Table的初始化
		table = new Table(shell, SWT.BORDER	| SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setItemCount(ITEM_COUNT);
		table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		
		TableColumn[] columns = new TableColumn[COLUMN_COUNT];
		for (int i = 0; i < COLUMN_COUNT; i++) {
			columns[i] = new TableColumn(table, SWT.CENTER);
			columns[i].setText("Column "+new Integer(i+1).toString());
			columns[i].setWidth(COLUMN_WIDTH);
		}

		//监听对table的双击事件，执行可编辑操作
		table.addListener(SWT.MouseDoubleClick, listener);
		
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		shell.dispose();
		display.dispose();
	}
	
	//为每个TableItem增加Listener， 当有Selection事件时， 根据行和列可以定位到每一个Cell，然后就可以处理事件，
	//为一个Cell添加可编辑的方法是增加一个TableEditor
	private Listener listener = new Listener() {
		private int EDITABLECOLUMN;

		@Override
		public void handleEvent(Event event) {
		
			//获取鼠标点击的位置
			Point point = new Point(event.x, event.y);
		
			//得到鼠标点击的行（item）
			TableItem item = table.getItem(point);
			if (item == null) {
				return;
			}
			for (int i = 0; i < 4; i++) {
				
				//得到选中行（item）的每一列的矩形范围，根据鼠标点击位置point来进一步确定是哪一格（cell）
				Rectangle rect = item.getBounds(i);
				if (rect.contains(point)) {
					EDITABLECOLUMN = i;
					
					final TableEditor editor = new TableEditor(table);
					Control oldEditor = editor.getEditor();
					if (oldEditor != null)
					{
						oldEditor.dispose();
					}
					
					// 添加Text，并使高度符合item的高度
					final Text text = new Text(table, SWT.NONE);
					text.computeSize(SWT.DEFAULT,	table.getItemHeight());

					//调整editor的高度宽度
					editor.grabHorizontal = true;
					editor.minimumHeight = text.getSize().y;
					editor.minimumWidth = text.getSize().x;
					
					editor.setEditor(text, item, EDITABLECOLUMN);
					
					text.setText(item.getText(EDITABLECOLUMN));

					text.forceFocus();
					
					// 开始编辑的事件
					text.addModifyListener(new ModifyListener() {
						@Override
						public void modifyText(ModifyEvent e) {
							text.setForeground(display.getSystemColor(SWT.COLOR_RED));
							editor.getItem().setText(EDITABLECOLUMN,text.getText());
						}
					});
					
					//编辑后内容恢复表格前景色
					text.addFocusListener(new FocusAdapter() {
						@Override
						public void focusLost(FocusEvent e) {
							text.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
						}
					});
				}
			}
		}
	};


	public static void main(String[] args) {
		EditableTable et=new EditableTable();
		et.Test();
	}

}