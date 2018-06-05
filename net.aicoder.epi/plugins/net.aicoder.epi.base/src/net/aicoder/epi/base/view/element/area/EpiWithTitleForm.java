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
package net.aicoder.epi.base.view.element.area;

//import org.eclipse.wb.core.controls.CImageLabel;
//import org.eclipse.wb.internal.core.utils.check.Assert;
//import org.eclipse.wb.internal.core.utils.ui.GridDataFactory;
//import org.eclipse.wb.internal.core.utils.ui.GridLayoutFactory;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;

import net.aicoder.epi.base.view.control.CImageLabel;
import net.aicoder.epi.base.view.util.GridDataFactory;
import net.aicoder.epi.base.view.util.GridLayoutFactory;

/**
 * The site {@link Composite} for {@link IPage}.
 *
 * @author scheglov_ke
 * @coverage core.editor.structure
 */
public final class EpiWithTitleForm extends Composite {
	private final CImageLabel m_title;
	private final ToolBarManager m_toolBarManager;
	private final ToolBar m_toolBar;
	private IEpiArea m_page;

	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	public EpiWithTitleForm(Composite parent, int style) {
		super(parent, style);
		GridLayoutFactory.create(this).noMargins().spacingV(0).columns(2);
		// title
		{
			m_title = new CImageLabel(this, SWT.NONE);
			GridDataFactory.create(m_title).grabH().fill();
		}
		// toolbar
		{
			m_toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT);
			GridDataFactory.create(m_toolBar).fill();
			m_toolBarManager = new ToolBarManager(m_toolBar);
		}
		// separator
		{
			Label separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
			GridDataFactory.create(separator).spanH(2).grabH().fillH();
		}
	}

	////////////////////////////////////////////////////////////////////////////
	//
	// Access
	//
	////////////////////////////////////////////////////////////////////////////
	/**
	 * Sets the {@link Image} for title;
	 */
	public void setTitleImage(Image image) {
		if (image == null) {
			return;
		}
		m_title.setImage(image);
	}

	/**
	 * Sets the text for title.
	 */
	public void setTitleText(String title) {
		if (title == null) {
			return;
		}
		m_title.setText(title);
	}

	/**
	 * Sets the {@link IPage} to display.
	 */
	public void setArea(IEpiArea page) {
		//Assert.isNull(m_page);
		if(m_page != null) {
			return;
		}
		Assert.isNotNull(page);
		m_page = page;
		// create Control
		if(m_page.getControl() == null){
			m_page.createControl(this);
		}
		Control control = null;
		if(page instanceof BaseWithTitleArea) {
			control = ((BaseWithTitleArea)m_page).getBodyControl();
		}else {
			control = m_page.getControl();
		}
		GridDataFactory.create(control).spanH(2).grab().fill();
		// set toolbar
		m_page.setToolBar(m_toolBarManager);
	}
}
