package controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import model.CustomerBean;
import model.CustomerService;
//繼承
//public class LoginServlet extends ActionSupport  {

//實作
public class LoginAction extends ActionSupport implements SessionAware {
	
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
	this.session=session;
		
	}
	
//	String username = request.getParameter("username");
//	String password = request.getParameter("password");
	
	//接收資料	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//驗證資料	

	@Override
	public void validate() {
//
//		if(username==null || username.trim().length()==0) {
//			
//			this.addFieldError("username", this.getText("login.name.required"));
////			error.put("username", "Please enter ID to login");
//		}
//		if(password==null || password.trim().length()==0) {
//			this.addFieldError("password", this.getText("login.password.required"));
////			error.put("password", "Please enter PWD to login");
//		}		
	}
	private CustomerService customerService = new CustomerService();
	@Override
	public String execute() throws Exception {
		
		CustomerBean bean = customerService.login(username, password);
		if(bean==null) {
			
//			error.put("password", "Login failed, please try again");
			
			this.addFieldError("password",this.getText("login.failed.required"));
//			request.getRequestDispatcher(
//					"/secure/login.jsp").forward(request, response);
			return "input";
		} else {
//			HttpSession session = request.getSession();
//			session.setAttribute("user", bean);
			session.put("user", bean);
			
//			
//			String path = request.getContextPath();
//			response.sendRedirect(path+"/index.jsp");
			return "success";
		}	
	}
	

	

}



