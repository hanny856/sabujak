package sabujak.login.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sabujak.domain.Member;
import sabujak.login.model.LoginService;
import sabujak.login.model.LoginSet;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String m = "";
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("form")) {
				form(request, response);
			}else if(m.equals("check")){
				check(request, response);
			}else if(m.equals("out")){
				out(request, response);
			}
			else{
				response.sendRedirect("../index.do");
			}
		}else {
			response.sendRedirect("../index.do");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String view = "login_form.jsp";
		response.sendRedirect(view);
	}
	private void check(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		if(email != null) email = email.trim();
		if(pwd != null) pwd = pwd.trim();
		 System.out.println("email : " +email);
		 System.out.println("pwd : " +pwd);
		LoginService service = LoginService.getInstance();
		
		int rCode = service.checkMember(email,  pwd);
		request.setAttribute("rCode",rCode);
		
		if(rCode == LoginSet.PASS){
			HttpSession session = request.getSession();
			Member m = service.getMemberS(email);
			session.setAttribute("loginUser",m);
		}
		String view = "login_msg.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	private void out(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//session.removeAttribute("loginUser");
		session.invalidate();
		String view = "../index.do";
		response.sendRedirect(view);
	}
}
