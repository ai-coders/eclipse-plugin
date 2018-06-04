package net.aicoder.epi.base.view.control.celleditor;

import java.util.Date;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.aicoder.epi.base.view.control.DatePickerCombo;

/**
 * 可编辑的日期控件
 * 
 */
public class DateComboCellEditor extends CellEditor {
	Date selectedDate;

	DatePickerCombo dateCombo;

	private java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();

	private static final int defaultStyle = SWT.NONE;

	/**
	 * 构造函数
	 */
	public DateComboCellEditor() {
		setStyle(defaultStyle);
	}

	/**
	 * 构造函数
	 * 
	 * 在哪个容器上构造 构造样式
	 */
	public DateComboCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * 创建控件
	 */
	protected Control createControl(Composite parent) {
		dateCombo = new DatePickerCombo(parent, getStyle() | SWT.READ_ONLY);
		dateCombo.setFont(parent.getFont());

		dateCombo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				keyReleaseOccured(e);
			}
		});

		dateCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent event) {
				applyEditorValueAndDeactivate();
			}

			public void widgetSelected(SelectionEvent event) {
				selectedDate = dateCombo.getDate();
			}
		});

		dateCombo.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
				}
			}
		});

		dateCombo.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				DateComboCellEditor.this.focusLost();
			}
		});
		return dateCombo;
	}

	/**
	 * 取得控件值
	 */
	protected Object doGetValue() {
		focusLost();
		return selectedDate;
	}

	/**
	 * 设计控件焦点
	 */
	protected void doSetFocus() {
		dateCombo.setFocus();
		// ((CellEditor) dateCombo.getData()).setFocus();
	}

	/**
	 * 
	 * @return 返回下拉框选择的日期
	 */
	public Date getSelectedDate() {
		return dateCombo.getDate();
	}

	/**
	 * 设置下拉框日期
	 * 
	 * @param date
	 *            此日期设置到下拉框中
	 */
	public void setSelectedDate(Date date) {
		dateCombo.setDate(date);
	}

	/**
	 * 取得控件要展现的布局
	 */
	public LayoutData getLayoutData() {
		LayoutData layoutData = super.getLayoutData();
		if ((dateCombo == null) || dateCombo.isDisposed()) {
			layoutData.minimumWidth = 60;
		} else {
			GC gc = new GC(dateCombo);
			layoutData.minimumWidth = (gc.getFontMetrics().getAverageCharWidth() * 10) + 10;
			gc.dispose();
		}
		return layoutData;
	}

	/**
	 * 设置控件日期
	 */
	protected void doSetValue(Object value) {
		Assert.isTrue(dateCombo != null && (value instanceof String));
		try {
			dateCombo.setDate(dateFormat.parse((String) value));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void applyEditorValueAndDeactivate() {
		// Object newValue = doGetValue();
		selectedDate = dateCombo.getDate();
		markDirty();
		boolean isValid = isCorrect(selectedDate);
		setValueValid(isValid);

		fireApplyEditorValue();
		deactivate();

		// selectedDate = dateCombo.getDate();
		// markDirty();
		// fireApplyEditorValue();
		// deactivate();
	}

	/**
	 * 控件丢失焦点
	 */
	protected void focusLost() {
		if (isActivated()) {
			applyEditorValueAndDeactivate();
		}
	}

}