package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import action.form.LoginForm;
import jdbc.MySQLConnector;

public class LoginAction extends Action{
    Log log = LogFactory.getLog(LoginAction.class);

    public ActionForward execute(ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
    throws Exception {
        LoginForm helloForm = (LoginForm) form;
		ActionForward result;

        request.setCharacterEncoding("Shift_JIS");
		MySQLConnector c;
		c=new MySQLConnector();

		if(c.permitUser(helloForm.getName(), helloForm.getPassword())){
			HttpSession httpSession = request.getSession(true);
			if (httpSession.isNew()) {
				// [1-1-1]attribute set up
				String sessionAttribute = "";
				httpSession.setAttribute("sessionAttribute", sessionAttribute);
			} else {
				// [1-1-2]
				// Do nothing
			}
			result=mapping.findForward("success");
		}else{
			result =null;
		}
		c.destructor();
		return result;

    }
}
