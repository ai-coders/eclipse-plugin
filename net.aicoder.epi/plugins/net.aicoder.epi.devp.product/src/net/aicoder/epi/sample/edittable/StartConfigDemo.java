package net.aicoder.epi.sample.edittable;

/*
 * 文件名：StartConfigDemo.java
 * 版权：Copyright 1986-2012 Andy. All Rights Reserved. 
 * 描述： StartConfigDemo.java
 * 修改人：Andy
 * 修改时间：2012-10-26
 * 修改内容：新增
 */

import java.util.Hashtable;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * 
 * <pre>
 * </pre>
 * 
 * @author Andy
 */
public class StartConfigDemo
{
    private static Hashtable<TableItem, TableItemControls> tablecontrols = new Hashtable<TableItem, TableItemControls>();

    public static void main(String[] args)
    {
        Display display = Display.getDefault();
        final Shell shell = new Shell(display);

        shell.setText("StartConfigDemo");
        shell.setBounds(150, 150, 300, 300);
        shell.setLayout(new FillLayout());

        Composite rootComposite = new Composite(shell, SWT.NONE);
        GridLayout configGridLayout = new GridLayout();
        configGridLayout.numColumns = 2;
        rootComposite.setLayout(configGridLayout);
        rootComposite.setBackground(new Color(Display.getCurrent(), 255, 255,
                255));

        ToolBar toolBar = new ToolBar(rootComposite, SWT.FLAT);
        final ToolItem addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setImage(new Image(toolBar.getDisplay(), "icons/actions/add.gif"));
        final ToolItem deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem
                .setImage(new Image(toolBar.getDisplay(), "icons/actions/delete.gif"));
        final ToolItem upItem = new ToolItem(toolBar, SWT.PUSH);
        upItem.setImage(new Image(toolBar.getDisplay(), "icons/actions/add.gif"));
        final ToolItem downItem = new ToolItem(toolBar, SWT.PUSH);
        downItem.setImage(new Image(toolBar.getDisplay(), "icons/actions/delete.gif"));

        GridData barGridData = new GridData();
        barGridData.horizontalSpan = 2;
        barGridData.horizontalAlignment = SWT.END;
        toolBar.setLayoutData(barGridData);

        Label inputLabel = new Label(rootComposite, SWT.NONE);
        inputLabel.setText("Input:");
        inputLabel
                .setBackground(new Color(Display.getCurrent(), 255, 255, 255));
        GridData inGridData = new GridData();
        inGridData.horizontalAlignment = SWT.BEGINNING;
        inGridData.verticalAlignment = SWT.TOP;
        inputLabel.setLayoutData(inGridData);

        // ViewForm viewForm = new ViewForm(rootComposite, SWT.NONE);
        // viewForm.setTopCenterSeparate(true);
        //
        // Composite tabComposite = new Composite(viewForm, SWT.NONE);
        // GridLayout gridLayout = new GridLayout();
        // gridLayout.numColumns = 1;
        // tabComposite.setLayout(gridLayout);

        final Table table = new Table(rootComposite, SWT.MULTI
                | SWT.FULL_SELECTION | SWT.BORDER);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        String[] tableHeader = {"Name", "Type", "Default"};
        for (int i = 0; i < tableHeader.length; i++)
        {
            TableColumn tableColumn = new TableColumn(table, SWT.NONE);
            tableColumn.setText(tableHeader[i]);
        }

        TableItem item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"aaa", "vvv", "ccc"});
        item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"bbb", "111", "cc222"});
        item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"周五", "女", "567", ""});

        GridData gridData = new GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = SWT.FILL;

        table.setLayoutData(gridData);

        // 重新布局表格
        for (int i = 0; i < tableHeader.length; i++)
        {
            table.getColumn(i).pack();
        }

        // 添加可编辑的单元格
        // /******************************************************
        TableItem[] items = table.getItems();
        for (int i = 0; i < items.length; i++)
        {
            // 第一列设置，创建TableEditor对象
            final TableEditor editName = new TableEditor(table);
            // 创建一个文本框，用于输入文字
            final Text name = new Text(table, SWT.NONE);
            // 将文本框当前值，设置为表格中的值
            name.setText(items[i].getText(0));
            // 设置编辑单元格水平填充
            editName.grabHorizontal = true;
            // 关键方法，将编辑单元格与文本框绑定到表格的第一列
            editName.setEditor(name, items[i], 0);
            // 当文本框改变值时，注册文本框改变事件，该事件改变表格中的数据。
            // 否则即使改变的文本框的值，对表格中的数据也不会影响
            name.addModifyListener(new ModifyListener()
            {
                public void modifyText(ModifyEvent e)
                {
                    editName.getItem().setText(1, name.getText());
                }

            });

            // 第一列设置，创建TableEditor对象
            final TableEditor editType = new TableEditor(table);
            // 创建一个文本框，用于输入文字
            final Text type = new Text(table, SWT.NONE);
            // 将文本框当前值，设置为表格中的值
            type.setText(items[i].getText(1));
            // 设置编辑单元格水平填充
            editType.grabHorizontal = true;
            // 关键方法，将编辑单元格与文本框绑定到表格的第一列
            editType.setEditor(type, items[i], 1);
            // 当文本框改变值时，注册文本框改变事件，该事件改变表格中的数据。
            // 否则即使改变的文本框的值，对表格中的数据也不会影响
            type.addModifyListener(new ModifyListener()
            {
                public void modifyText(ModifyEvent e)
                {
                    editType.getItem().setText(1, type.getText());
                }

            });

            // 第一列设置，创建TableEditor对象
            final TableEditor editValue = new TableEditor(table);
            // 创建一个文本框，用于输入文字
            final Text value = new Text(table, SWT.NONE);
            // 将文本框当前值，设置为表格中的值
            value.setText(items[i].getText(2));
            // 设置编辑单元格水平填充
            editValue.grabHorizontal = true;
            // 关键方法，将编辑单元格与文本框绑定到表格的第一列
            editValue.setEditor(value, items[i], 2);
            // 当文本框改变值时，注册文本框改变事件，该事件改变表格中的数据。
            // 否则即使改变的文本框的值，对表格中的数据也不会影响
            value.addModifyListener(new ModifyListener()
            {
                public void modifyText(ModifyEvent e)
                {
                    editValue.getItem().setText(1, value.getText());
                }

            });
            // 保存TableItem与绑定Control的对应关系，删除TableItem时使用
            TableItemControls cons = new TableItemControls(name, type, value,
                    editName, editType, editValue);
            tablecontrols.put(items[i], cons);
        }

        Label outputLabel = new Label(rootComposite, SWT.NONE);
        outputLabel.setText("Output Type:");
        outputLabel
                .setBackground(new Color(Display.getCurrent(), 255, 255, 255));
        GridData outGridData = new GridData();
        inGridData.horizontalAlignment = SWT.BEGINNING;
        outputLabel.setLayoutData(outGridData);

        Text outPut = new Text(rootComposite, SWT.BORDER);
        gridData.horizontalAlignment = SWT.FILL;
        // gridData.grabExcessHorizontalSpace = true;
        // gridData.grabExcessVerticalSpace = true;

        outPut.setLayoutData(outGridData);

        // 工具栏按钮事件处理
        Listener listener = new Listener()
        {
            @Override
            public void handleEvent(Event event)
            {
                // 如果单击添加按钮，添加一行，在实际的项目实现中通常是接收输入的参数，案后添加
                // 这里为了简单起见，添加固定的一条记录
                if (event.widget == addItem)
                {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{"郑六" + new Random().nextInt(15),
                            "女", "568"});
                }
                // 如果单击删除按钮
                else if (event.widget == deleteItem)
                {
                    // 首先获得表格中所有的行
                    TableItem[] items = table.getItems();
                    // 循环所有行
                    for (int i = items.length - 1; i >= 0; i--)
                    {
                        // 如果该行没有被选中，继续循环
                        if (!items[i].getChecked())
                            continue;
                        // 否则选中，查找该表格中是否有该行
                        int index = table.indexOf(items[i]);
                        // 如果没有该行，继续循环
                        if (index < 0)
                            continue;
                        // 删除绑定的控件
                        TableItemControls cons = tablecontrols
                                .get(items[index]);
                        if (cons != null)
                        {
                            cons.dispose();
                            tablecontrols.remove(items[index]);
                            System.out.println("dispose " + index);
                        }
                        // 如果有该行，删除该行
                        // items[index].dispose();
                        table.remove(index);
                        System.out.println("i=" + i + ", index=" + index);
                        System.out.println("行数:" + table.getItemCount());
                        // table.pack();
                    }
                }
                // 如果单击上移按钮
                else if (event.widget == upItem)
                {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > 0)
                        table.setSelection(selectedRow - 1);// 设置选中的行数
                }
                // 如果单击下移按钮
                else if (event.widget == upItem)
                {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > -1
                            && selectedRow < table.getItemCount() - 1)
                        table.setSelection(selectedRow + 1);// 设置选中的行数
                }
            }

        };
        // 为工具栏的按钮注册事件
        addItem.addListener(SWT.Selection, listener);
        deleteItem.addListener(SWT.Selection, listener);
        upItem.addListener(SWT.Selection, listener);
        downItem.addListener(SWT.Selection, listener);

        shell.open();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }

        }
        display.dispose();

    }
}

class TableItemControls
{
    Text name;

    Text type;

    Text value;

    TableEditor tableeditor;

    TableEditor tableeditor2;

    TableEditor tableeditor1;

    public TableItemControls(Text name, Text type, Text value,
            TableEditor tableeditor, TableEditor tableeditor1,
            TableEditor tableeditor2)
    {
        // super();
        this.name = name;
        this.type = type;
        this.value = value;
        this.tableeditor = tableeditor;
        this.tableeditor1 = tableeditor1;
        this.tableeditor2 = tableeditor2;
    }

    public void dispose()
    {
        name.dispose();
        value.dispose();
        value.dispose();
        tableeditor.dispose();
        tableeditor1.dispose();
        tableeditor2.dispose();
    }
}