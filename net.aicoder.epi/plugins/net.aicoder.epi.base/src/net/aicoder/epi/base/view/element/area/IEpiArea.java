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

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import net.aicoder.epi.base.view.context.IViewContext;

/**
 * View-like page.
 *
 * @author scheglov_ke
 * @coverage core.editor.structure
 */
public interface IEpiArea {
  /**
   * Creates the {@link Control} for this page.
   */
  void createControl(Composite parent);

  /**
   * @return the {@link Control} of this page, may be <code>null</code>.
   */
  Control getControl();

  /**
   * Allows the page to make contributions to the given {@link IToolBarManager}. The contributions
   * will be visible when the page is visible. This method is automatically called shortly after
   * {@link #createControl(Composite)} is called.
   */
  void setToolBar(IToolBarManager toolBarManager);

  /**
   * Asks this page to take focus.
   */
  public boolean setFocus();
  
  /**
   * Disposes this page.
   * <p>
   * This is the last method called on the {@link IEpiArea}. Implementors should clean up any resources
   * associated with the page.
   * <p>
   * Note that there is no guarantee that {@link #createControl(Composite)} has been called, so the
   * control may never have been created.
   */
  void dispose();

  public String getTitleText();
  
  public Image getTitleImage();
  
  public IEditorPart getEditor();
  
  public void setEditor(IEditorPart editor);
}
