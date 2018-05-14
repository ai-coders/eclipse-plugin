package net.aicoder.epi.sample.edittable;

/* 
* 通常在一个表格中的数据是一致的（同类型），所以我们通常做的table编辑工作是针对列的， 
* 如在tableviewer中使用cellEditor和cellModifyer来编辑表格。 
*  
* 但是在某些情况下，我们需要对行进行编辑。本示例中是展示表格的单行是文本编辑，双行是下拉框编。， 
* 
*/  
import org.eclipse.swt.SWT;  
import org.eclipse.swt.custom.CCombo;  
import org.eclipse.swt.custom.TableEditor;  
import org.eclipse.swt.events.ModifyEvent;  
import org.eclipse.swt.events.ModifyListener;  
import org.eclipse.swt.events.SelectionAdapter;  
import org.eclipse.swt.events.SelectionEvent;  
import org.eclipse.swt.layout.FillLayout;  
import org.eclipse.swt.widgets.Control;  
import org.eclipse.swt.widgets.Display;  
import org.eclipse.swt.widgets.Shell;  
import org.eclipse.swt.widgets.Table;  
import org.eclipse.swt.widgets.TableColumn;  
import org.eclipse.swt.widgets.TableItem;  
import org.eclipse.swt.widgets.Text;  

public class TableEditoExamples {  
  public static void main(String[] args) {  
      Display display = new Display();  
      Shell shell = new Shell(display);  
      shell.setLayout(new FillLayout());  
        
      // 下拉框items  
      final String[] options = { "下拉框 1", "下拉框 2", "下拉框 3" };  
        
      // 创建table 和 tableItems  
      final Table table = new Table(shell, SWT.FULL_SELECTION| SWT.HIDE_SELECTION);  
      TableColumn column1 = new TableColumn(table, SWT.NONE);  
      TableColumn column2 = new TableColumn(table, SWT.NONE);  
      for (int i = 0; i < 10; i++) {  
          if (i % 2 == 1) {  
              TableItem item = new TableItem(table, SWT.NONE);  
              item.setText(new String[] { "item " + i, "文本框编辑" });  
          } else {  
              TableItem item = new TableItem(table, SWT.NONE);  
              item.setText(new String[] { "item " + i, "下拉框 1" });  
          }  
      }  
      column1.pack();  
      column2.pack();  

      final TableEditor editor = new TableEditor(table);  
      // The editor must have the same size as the cell and must  
      // not be any smaller than 50 pixels.  
      editor.horizontalAlignment = SWT.LEFT;  
      editor.grabHorizontal = true;  
      editor.minimumWidth = 50;  
      // editing the second column  
      final int Edit_Table_Column = 1;  

      table.addSelectionListener(new SelectionAdapter() {  
          public void widgetSelected(SelectionEvent e) {  
              // Clean up any previous editor control  
              Control oldEditor = editor.getEditor();  
              if (oldEditor != null)  
                  oldEditor.dispose();  

              // Identify the selected row  
              final TableItem item = (TableItem) e.item;  
              if (item == null)  
                  return;  

              int index = table.getSelectionIndex();  
                
              // 单行文本框  
              if (index % 2 == 1) {  
                  // The control that will be the editor must be a child of  
                  // the  
                  // Table  
                  Text newEditor = new Text(table, SWT.NONE);  
                  newEditor.setText(item.getText(Edit_Table_Column));  
                  newEditor.addModifyListener(new ModifyListener() {  
                      public void modifyText(ModifyEvent me) {  
                          Text text = (Text) editor.getEditor();  
                          editor.getItem().setText(Edit_Table_Column,  
                                  text.getText());  
                      }  
                  });  
                  newEditor.selectAll();  
                  newEditor.setFocus();  
                  editor.setEditor(newEditor, item, Edit_Table_Column);  
              } else { //// 双行下拉框  
                  final CCombo combo = new CCombo(table, SWT.READ_ONLY);  
                  for (int i = 0, n = options.length; i < n; i++) {  
                      combo.add(options[i]);  
                  }  
                  // Select the previously selected item from the cell  
                  combo.select(combo.indexOf(item.getText(Edit_Table_Column)));  

                  // Compute the width for the editor  
                  // Also, compute the column width, so that the dropdown fits  
                  editor.minimumWidth = combo.computeSize(SWT.DEFAULT,  
                          SWT.DEFAULT).x;  
                  table.getColumn(Edit_Table_Column).setWidth(  
                          editor.minimumWidth);  

                  // Set the focus on the dropdown and set into the editor  
                  combo.setFocus();  
                  editor.setEditor(combo, item, Edit_Table_Column);  

                  // Add a listener to set the selected item back into the  
                  // cell  
                  final int col = Edit_Table_Column;  
                  combo.addSelectionListener(new SelectionAdapter() {  
                      public void widgetSelected(SelectionEvent event) {  
                          item.setText(col, combo.getText());  

                          // They selected an item; end the editing session  
                          combo.dispose();  
                      }  
                  });  
              }  

          }  
      });  
      shell.setSize(200, 200);  
      shell.open();  

      while (!shell.isDisposed()) {  
          if (!display.readAndDispatch())  
              display.sleep();  
      }  
      display.dispose();  
  }  

}  