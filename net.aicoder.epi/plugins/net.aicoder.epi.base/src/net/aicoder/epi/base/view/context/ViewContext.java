package net.aicoder.epi.base.view.context;

import net.aicoder.epi.base.model.IBaseVo;

public class ViewContext implements IViewContext {
	private IEpiInput input;
	private IEpiEditorInput editorInput;
	
	public ViewContext() {
		super();
		setInput(createInput(null));
		IBaseVo currData = null;
		if(getInput() != null) {
			currData = getInput().getCurrentData();
		}else {
			input = new EpiInput();
		}
		setEditorInput(createEditorInput(currData));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IEpiInput> T getInput() {
		return (T)input;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public final <T extends IEpiInput> T getInput(IBaseVo selectionElement) {
		input = createInput(selectionElement);
		return (T)input;
	}

	@Override
	public void setInput(IEpiInput adapter) {
		this.input = adapter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IEpiEditorInput> T getEditorInput() {
		return (T)editorInput;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T extends IEpiEditorInput> T getEditorInput(IBaseVo selectionElement) {
		editorInput = createEditorInput(selectionElement);
		return (T)editorInput;
	}

	@Override
	public void setEditorInput(IEpiEditorInput editorInput) {
		this.editorInput = editorInput;
	}

	@Override
	public <T extends IEpiInput> T createInput(IBaseVo selectionElement) {
		return null;
	}

	@Override
	public <T extends IEpiEditorInput> T createEditorInput(IBaseVo selectionElement) {
		return null;
	}
}
