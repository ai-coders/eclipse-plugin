package net.aicoder.epi.base.view.explorer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

import net.aicoder.epi.base.model.IBaseVo;
import net.aicoder.epi.base.model.StateFlagEnum;
import net.aicoder.epi.base.view.IViewElement;
import net.aicoder.epi.base.view.definer.IViewDefiner;
import net.aicoder.tcom.tools.util.AiStringUtil;

public class EpiExplorer extends CommonNavigator implements IContextProvider, IViewElement {
	public static String ID = EpiExplorer.class.getName();

	private EpiExplorerDefiner definer;
	private CommonViewer viewer;

	private boolean editable = true;
	private boolean dirty = false;
	private List<IBaseVo> insertedDataList = new ArrayList<IBaseVo>(0);
	private List<IBaseVo> deletedDataList = new ArrayList<IBaseVo>(0);
	private List<IBaseVo> updatedDataList = new ArrayList<IBaseVo>(0);

	//private Set<TreeItem> dirtyBackgroundSet = new HashSet<TreeItem>(0);

	public EpiExplorer() {
		super();
	}

	public EpiExplorer(EpiExplorerDefiner definer) {
		super();
		this.definer = definer;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		viewer = this.getCommonViewer();
		viewer.setContentProvider(definer.getContentProvider());
		viewer.setLabelProvider(definer.getLabelProvider());
		viewer.setInput(definer.getAdapter());
		viewer.setSorter(definer.getSorter());

		if (definer.hasOpenEditAction()) {
			hookDoubleClickAction();
		}

		hookToolBars();
		hookContextMenu();
		hookKeybordActions();
	}

	protected void hookToolBars() {

	}

	protected void hookContextMenu() {

	}

	protected void hookKeybordActions() {

	}

	private void hookDoubleClickAction() {
		Action openEditorAction = new Action() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				ISelection selection = viewer.getSelection();
				IBaseVo vo = (IBaseVo) ((IStructuredSelection) selection).getFirstElement();
				String editorId = definer.getViewItemDefiner(vo.getEtype()).getEditorId();
				if (!AiStringUtil.isEmpty(editorId)) {
					try {
						page.openEditor(definer.createEditorInput(vo), editorId);
					} catch (PartInitException e) {
						System.out.println(e);
					}
				}
			}
		};

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				openEditorAction.run();
			}
		});
	}

	// Added for IContextProvider
	@Override
	public int getContextChangeMask() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IContext getContext(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSearchExpression(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	//// getter/setter
	@Override
	public IBaseVo getFirstSelectedItem() {
		IBaseVo item = null;
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return null;
		}
		item = (IBaseVo) selection.getFirstElement();
		return item;
	}

	@SuppressWarnings("rawtypes")
	public IBaseVo[] getSelectedItems() {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 0) {
			return null;
		}
		IBaseVo[] items = new IBaseVo[selection.size()];
		Iterator iter = selection.iterator();
		int index = 0;
		while (iter.hasNext()) {
			items[index++] = (IBaseVo) iter.next();
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IViewDefiner getDefiner() {
		return definer;
	}

	public void setDefiner(EpiExplorerDefiner definer) {
		this.definer = definer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeViewer getViewer() {
		return viewer;
	}

	public void setViewer(CommonViewer viewer) {
		this.viewer = viewer;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public List<IBaseVo> getInsertedDataList() {
		return insertedDataList;
	}

	public void putInsertedData(IBaseVo item) {
		item.setDataState(StateFlagEnum.INSERTED);
		insertedDataList.add(item);
		setDirty(true);
	}

	public List<IBaseVo> getDeletedDataList() {
		return deletedDataList;
	}

	public void putDeletedData(IBaseVo item) {
		if (item.getDataState() == StateFlagEnum.INSERTED) {
			insertedDataList.remove(item);
		} else {
			item.setDataState(StateFlagEnum.DELETED);
			deletedDataList.add(item);
			setDirty(true);
		}
	}

	public List<IBaseVo> getUpdatedDataList() {
		return updatedDataList;
	}

	public void putUpdatedData(IBaseVo item) {
		if (item.getDataState() == StateFlagEnum.INSERTED) {
			// insertedDataList.remove(item);
		} else {
			item.setDataState(StateFlagEnum.UPDATED);
			// updatedDataList.remove(item);
			if (updatedDataList.indexOf(item) < 0) {
				updatedDataList.add(item);
				setDirty(true);
			}
		}
	}

	public void revert() {
		insertedDataList.clear();
		deletedDataList.clear();
		updatedDataList.clear();
		setDirty(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public EpiExplorer getViewElement() {
		return this;
	}

	@Override
	public void setDirtyBackground(String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirtyBackground(int columnIndex) {
		// TODO Auto-generated method stub
		
	}
}
