package net.aicoder.epi.base.view.context;

import net.aicoder.epi.base.model.IBaseVo;

public interface IViewContext {
	
	public <T extends IEpiInput> T getInput();

	public <T extends IEpiInput> T getInput(IBaseVo selectionElement);

	public void setInput(IEpiInput input);
	
	public <T extends IEpiEditorInput> T getEditorInput();
	
	public <T extends IEpiEditorInput> T getEditorInput(IBaseVo selectionElement);
	
	public void setEditorInput(IEpiEditorInput editorInput);
	
	public <T extends IEpiInput> T createInput(IBaseVo selectionElement);
	
	public <T extends IEpiEditorInput> T createEditorInput(IBaseVo selectionElement);

}
