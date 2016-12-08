package action.form;

import org.apache.struts.action.ActionForm;

public class LoginForm  extends ActionForm{
    private String name;
    private String password;
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}


}
