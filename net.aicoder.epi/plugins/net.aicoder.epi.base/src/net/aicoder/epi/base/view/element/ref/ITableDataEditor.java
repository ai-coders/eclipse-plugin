/*******************************************************************************
 * Copyright (c) 2001, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.aicoder.epi.base.view.element.ref;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;

import net.aicoder.epi.base.model.IBaseVo;

public interface ITableDataEditor {
    //public ITableData getTableData();
    
    //public org.eclipse.datatools.modelbase.sql.tables.Table getSqlTable();
    
    public TableDataTableCursor getCursor();
    
    public boolean isReadonly();
    
    public IBaseVo getRow();
    
    public IBaseVo getOrCreateRow();
    
    public void setDirtyBackground(int columnIndex, TableItem item);
    
    public void setDirty(boolean value);
    
    public void doRevert();
    
    public void doDelete();
    
    public void doInsertRow();
    
    public void doUpdateValue();
    
    public void doSetNull();
    
    public void doRefresh();
    
    public TableViewer getTableViewer();
    
    public TableDataEditorSelectionProvider getSelectionProvider();

}
