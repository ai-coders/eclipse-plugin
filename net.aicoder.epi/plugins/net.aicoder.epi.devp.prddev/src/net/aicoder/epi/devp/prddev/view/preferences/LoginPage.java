package net.aicoder.epi.devp.prddev.view.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import net.aicoder.epi.devp.prddev.model.sys.LoginResult;
import net.aicoder.epi.util.network.NetworkConstant;
import net.aicoder.epi.util.network.NetworkHelper;

public class LoginPage extends PreferencePage implements IWorkbenchPreferencePage{
	private Text accountText;
	private Text passwordText;
	
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Control createContents(Composite parent) {
		GridLayout gridLayout = new GridLayout(2,false);
		gridLayout.makeColumnsEqualWidth = false;
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		
		Group loginGroup = new Group(parent,SWT.FILL);
		loginGroup.setText("登录");
		loginGroup.setLayout(gridLayout);
		loginGroup.setLayoutData(gridData);
		
		Label accountLabel = new Label(loginGroup,SWT.NONE);
		accountLabel.setText("帐号");
		
		accountText = new Text(loginGroup, SWT.BORDER);
		accountText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
		accountText.setText("admin");
		
		
		Label passwordLabel = new Label(loginGroup,SWT.NONE);
		passwordLabel.setText("密码");
		
		passwordText = new Text(loginGroup, SWT.BORDER);
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));
		
		Button loginButton = new Button(loginGroup, SWT.NONE);
		loginButton.setText("登录");
		
		GridData buttonGridData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);		
		buttonGridData.horizontalSpan = 2;
		buttonGridData.widthHint = 80;
		buttonGridData.heightHint = 25;
		loginButton.setLayoutData(buttonGridData);
		loginButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				login(accountText.getText().trim(),passwordText.getText().trim());
			}
		});
		
		return parent.getShell();
	}
	
	
	private void login(String account,String password) {
		MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
		request.add("username", account);
		request.add("password", password);
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				LoginResult result = NetworkHelper.postForObject(NetworkConstant.AUTHENTICATE, request, LoginResult.class);
				System.out.println("登陆请求的result:"+result);
			}
		});
	}
	
//	private void showMessageDialog(String result) {
//		if(result != null && "success".contains(result) && "true".contains(result)) {
//			PrdMagPlugin.setValue(SIGNIN_ACCOUNT_ALIAS, accountText.getText().trim());
//			PrdMagPlugin.setBoolean(SIGNIN_STATE_ALIAS, true);
//			MessageDialog.openInformation(getShell(), "登陆", "登陆成功");
//		}else {
//			PrdMagPlugin.setValue(SIGNIN_ACCOUNT_ALIAS, "");
//			PrdMagPlugin.setBoolean(SIGNIN_STATE_ALIAS, false);
//			MessageDialog.openInformation(getShell(), "登陆", "登陆失败,请重新登陆");
//		}
//	}

}
