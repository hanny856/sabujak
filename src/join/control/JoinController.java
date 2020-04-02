package join.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import join.domain.Join;
import join.model.JoinService;


@WebServlet("/Sabujak/join.do")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String m = "";
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if (m != null) {
			m = m.trim();
			if (m.equals("addmem")) {
				addmem(request, response);
			}else if(m.equals("member")) {
				member(request, response);
			}else {
				addmem(request, response);
			}
		}else {
			addmem(request, response);
		}
	}
	private void addmem(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String view = "addmem.jsp";
		response.sendRedirect(view);
	}
	private void member(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		String email = request.getParameter("emailId");
		String domain = request.getParameter("domain");
		String a = "@";
		String email_fk_pk=email+a+domain;
		String m_pw = request.getParameter("password");
		String m_name = request.getParameter("name");
		String m_call = request.getParameter("phone");
		String m_addr = request.getParameter("addr");
		JoinService service = JoinService.getInstance();
		boolean b1 = service.memberADDS(new Join(-1,email_fk_pk,m_pw,m_name,m_call,m_addr));
		String view = "addck.jsp";
		response.sendRedirect(view);
	}
}
