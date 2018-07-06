/*******************************************************************************
 * Copyright (c) 2011 Google, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Google, Inc. - initial API and implementation
 *******************************************************************************/
package net.aicoder.epi.base.view.control.table;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;

import net.aicoder.epi.base.view.util.EventTable;

/**
 * Implementation of {@link ISelectionProvider} for models of {@link EditPart}'s
 * in GEF {@link IEditPartViewer}.
 *
 * @author scheglov_ke
 * @coverage gef.core
 */
public final class EpiSelectionProvider implements ISelectionProvider {
	private final StructuredViewer m_viewer;
	private final EventTable m_eventTable = new EventTable();

	private final ISelectionChangedListener m_selectionListener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent event) {
			fireSelectionChanged();
		}
	};

	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	public EpiSelectionProvider(StructuredViewer viewer) {
		m_viewer = viewer;
	}

	////////////////////////////////////////////////////////////////////////////
	//
	// Access
	//
	////////////////////////////////////////////////////////////////////////////
	public ISelection getSelection() {
		return m_viewer.getSelection();
	}

	public void setSelection(ISelection selection) {
		m_viewer.setSelection(selection);
	}

	////////////////////////////////////////////////////////////////////////////
	//
	// Listeners
	//
	////////////////////////////////////////////////////////////////////////////
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		m_eventTable.addListener(ISelectionChangedListener.class, listener);
		if (m_eventTable.getListeners(ISelectionChangedListener.class).size() == 1) {
			m_viewer.addSelectionChangedListener(m_selectionListener);
		}
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		m_eventTable.removeListener(ISelectionChangedListener.class, listener);
		if (m_eventTable.getListeners(ISelectionChangedListener.class).isEmpty()) {
			m_viewer.removeSelectionChangedListener(m_selectionListener);
		}
	}

	/**
	 * Notifies subscribers of this {@link ISelectionProvider}.
	 */
	private void fireSelectionChanged() {
		// prepare event
		SelectionChangedEvent event;
		{
			ISelection selection = getSelection();
			event = new SelectionChangedEvent(this, selection);
		}
		// notify subscribers
		for (ISelectionChangedListener listener : m_eventTable.getListeners(ISelectionChangedListener.class)) {
			listener.selectionChanged(event);
		}
	}
}
