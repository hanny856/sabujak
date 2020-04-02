package sabujak.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import sabujak.domain.Sabujak;
import sabujak.model.FileSet;
import sabujak.model.SabujakService;
import sabujak.vo.ListResult;
import sabujak.vo.Reply;
import reply.model.ReplyService;
import sabujak.domain.DTO;

@WebServlet("/Sabujak/sabujak.do")
public class SabujakController extends HttpServlet {
	String c_fname = "";
	String c_ofname = "";
	String newfname = "";
	private static final long serialVersionUID = 1L;
	private String m = "";
	MultipartRequest mr = null;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if (m != null) {
			m = m.trim();
			if (m.equals("list")) {
				list(request, response);
			} else if (m.equals("write")) {
				write(request, response);
			} else if (m.equals("content")) {
				content(request, response);
			} else if (m.equals("addmem")) {
				addmem(request, response);
			} else if (m.equals("login")) {
				login(request, response);
			} else if (m.equals("del")) {
				del(request, response);
			} else if (m.equals("insert")) {
				insert(request, response);
			} else if (m.equals("download")) {
				download(request, response);
			} else if (m.equals("update")) {
				update(request, response);
			} else if (m.equals("upcont")) {
				upcont(request, response);
			} else if (m.equals("inreply")) {
				inreply(request, response);
			} else if (m.equals("upreply")) {
				upreply(request, response);
			} else if (m.equals("delreply")) {
				delreply(request, response);
			} else {
				list(request, response);
			}
		} else {
			list(request, response);
		}
	}

	private void delreply(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int cre_no = getCRE_NO(request);
		if (cre_no != -1) {
			ReplyService service = ReplyService.getInstance();
			service.delS(cre_no);
		}
		String view = "sabujak.do?m=content&c_no="+request.getParameter("c_no")+"&rcp=1";
		response.sendRedirect(view);
	}

	private void upreply(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

	}

	private void inreply(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String email_fk = request.getParameter("email");
		String cre_cont = request.getParameter("content");
		int c_no_fk = getC_NO(request);
		ReplyService service = ReplyService.getInstance();
		service.insertS(new DTO(email_fk,cre_cont,c_no_fk));
		String view = "sabujak.do?m=content&c_no="+c_no_fk;
		response.sendRedirect(view);
	}

	private void upcont(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int c_no = getC_NO(request);
		if (c_no != -1) {
			SabujakService service = SabujakService.getInstance();
			Sabujak sabujak = service.getBoardS(c_no);
			System.out.println(c_no);
			request.setAttribute("sabujak", sabujak);
			String view = "update.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int c_no = 0;
		ServletContext application = getServletContext();
		String saveDirectory = FileSet.FILEDIR;
		File fsaveDir = new File(saveDirectory);
		if (!fsaveDir.exists())
			fsaveDir.mkdir();
		int maxPostSize = 1 * 1024 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		try {
			mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		} catch (IOException ie) {
			System.out.println("업로드 실패: " + ie);
		}
		String c_noStr = mr.getParameter("c_no");
		if (c_noStr != null)
			c_noStr = c_noStr.trim();
		c_no = Integer.parseInt(c_noStr);
		String email_fk_pk = mr.getParameter("writer");
		String c_sub = mr.getParameter("title");
		String c_cont = mr.getParameter("content");
		c_fname = mr.getParameter("fname");
		String newfname = mr.getFilesystemName("newfname");
		if (newfname == null) {
			SabujakService service = SabujakService.getInstance();
			service.upS(new Sabujak(c_no, c_sub, c_cont));
			String view = "sabujak.do";
			response.sendRedirect(view);
		} else {
			String newofname = mr.getOriginalFileName("newfname");
			SabujakService service = SabujakService.getInstance();
			service.updateS(new Sabujak(c_no, c_sub, c_cont, newfname, newofname));
			updel(c_fname);
			String view = "sabujak.do";
			response.sendRedirect(view);
		}

	}

	private void updel(String c_fname) throws ServletException, IOException {
		String saveDirectory = FileSet.FILEDIR;
		File f = new File(saveDirectory, c_fname);
		if (f.exists()) {
			f.delete();
		}

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean inputF = uploadCheck(request, response);
		if (inputF) {
			String email_fk_pk = mr.getParameter("writer");
			String c_sub = mr.getParameter("title");
			String c_cont = mr.getParameter("content");
			SabujakService service = SabujakService.getInstance();
			service.insertS(new Sabujak(email_fk_pk, c_sub, c_cont, c_fname, c_ofname));
			String view = "sabujak.do";
			response.sendRedirect(view);
		} else {
			String view = "list.jsp";
			response.sendRedirect(view);
		}
	}

	private void del(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int c_no = getC_NO(request);
		if (c_no != -1) {
			SabujakService service = SabujakService.getInstance();
			service.delS(c_no);
			String saveDirectory = FileSet.FILEDIR;
			String fname = request.getParameter("fname");
			File f = new File(saveDirectory, fname);
			if (f.exists()) {
				f.delete();
			}
		}
		String view = "sabujak.do";
		response.sendRedirect(view);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String view = "login.jsp";
		response.sendRedirect(view);
	}

	private void addmem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "addmem.jsp";
		response.sendRedirect(view);
	}

	private void content(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int c_no = getC_NO(request);
		if (c_no != -1) {
			String rcpStr = request.getParameter("rcp");
			String rpsStr = request.getParameter("rps");
			HttpSession session = request.getSession();
			int rcp = 1;
			if (rcpStr == null) {
				Object rcpObj = session.getAttribute("rcp");
				if (rcpObj != null) {
					rcp = (Integer) rcpObj;
				}
			} else {
				rcpStr = rcpStr.trim();
				rcp = Integer.parseInt(rcpStr);
			}
			session.setAttribute("rcp", rcp);
			int rps = 5;
			if (rpsStr == null) {
				Object rpsObj = session.getAttribute("rps");
				if (rpsObj != null) {
					rps = (Integer) rpsObj;
				}
			} else {
				rpsStr = rpsStr.trim();
				int rpsParam = Integer.parseInt(rpsStr);

				Object rpsObj = session.getAttribute("rps");
				if (rpsObj != null) {
					int psSession = (Integer) rpsObj;
					if (psSession != rpsParam) {
						rcp = 1;
						session.setAttribute("rcp", rcp);
					}
				} else {
					if (rps != rpsParam) {
						rcp = 1;
						session.setAttribute("rcp", rcp);
					}
				}
				rps = rpsParam;
			}
			session.setAttribute("rps", rps);
			ReplyService rservice = ReplyService.getInstance();
			Reply reply = rservice.getReply(rcp, rps,c_no);
			request.setAttribute("reply", reply);
			SabujakService service = SabujakService.getInstance();
			service.getView(c_no);
			Sabujak sabujak = service.getBoardS(c_no);
			request.setAttribute("sabujak", sabujak);
			String view = "content.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			String view = "list.jsp";
			response.sendRedirect(view);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		HttpSession session = request.getSession();
		int cp = 1;
		if (cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if (cpObj != null) {
				cp = (Integer) cpObj;
			}
		} else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		int ps = 20;
		if (psStr == null) {
			Object psObj = session.getAttribute("ps");
			if (psObj != null) {
				ps = (Integer) psObj;
			}
		} else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);

			Object psObj = session.getAttribute("ps");
			if (psObj != null) {
				int psSession = (Integer) psObj;
				if (psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			} else {
				if (ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		SabujakService service = SabujakService.getInstance();
		ListResult listResult = service.getListResult(cp, ps);
		request.setAttribute("listResult", listResult);
		
		if (listResult.getList().size() == 0) {
			if (cp > 1) {
				response.sendRedirect("sabujak.do?m=list&cp=" + (cp - 1));
			} else {
				request.setAttribute("listResult", null);
				String view = "list.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		} else {
			String view = "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}


	private void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "write.jsp";
		response.sendRedirect(view);
	}

	private int getC_NO(HttpServletRequest request) {
		String cnoStr = request.getParameter("c_no");
		int c_no = 0;
		if (cnoStr == null) {
			return -1;
		} else {
			cnoStr = cnoStr.trim();
			try {
				c_no = Integer.parseInt(cnoStr);

				return c_no;
			} catch (NumberFormatException ne) {
				return -1;
			}
		}
	}

	private int getCRE_NO(HttpServletRequest request) {
		String cre_noStr = request.getParameter("cre_no");
		int cre_no = 0;
		if (cre_noStr == null) {
			return -1;
		} else {
			cre_noStr = cre_noStr.trim();
			try {
				cre_no = Integer.parseInt(cre_noStr);

				return cre_no;
			} catch (NumberFormatException ne) {
				return -1;
			}
		}
	}

	private void download(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fname");
		String saveDirectory = FileSet.FILEDIR;
		File file = new File(saveDirectory, fileName);
		response.setContentType("application/octet-stream");
		String Agent = request.getHeader("USER-AGENT");
		if (Agent.indexOf("MSIE") >= 0) {
			int i = Agent.indexOf('M', 2);
			String IEV = Agent.substring(i + 5, i + 8);
			if (IEV.equalsIgnoreCase("5.5")) {
				response.setHeader("Content-Disposition",
						"filename=" + new String(fileName.getBytes("utf-8"), "8859_1"));
			} else {
				response.setHeader("Content-Disposition",
						"attachment;filename=" + new String(fileName.getBytes("utf-8"), "8859_1"));
			}
		} else {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("utf-8"), "8859_1"));
		}

		if (file.exists() && file.isFile()) {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			OutputStream os = null;
			BufferedOutputStream bos = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				os = response.getOutputStream();
				bos = new BufferedOutputStream(os);
				byte b[] = new byte[1024];
				int read = 0;
				while ((read = bis.read(b)) != -1) {
					bos.write(b, 0, read);
				}
				bos.flush();
			} catch (IOException ie) {
			} finally {
				try {
					if (bos != null)
						bos.close();
					if (bis != null)
						bis.close();
					if (fis != null)
						fis.close();
					if (os != null)
						os.close();
				} catch (IOException ie) {
				}
			}
		}
	}

	private boolean uploadCheck(HttpServletRequest request, HttpServletResponse response) {
		ServletContext application = getServletContext();
		String saveDirectory = FileSet.FILEDIR;
		File fsaveDir = new File(saveDirectory);
		if (!fsaveDir.exists())
			fsaveDir.mkdir();
		System.out.println(saveDirectory);
		int maxPostSize = 1 * 1024 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		try {
			mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		} catch (IOException ie) {
			System.out.println("업로드 실패: " + ie);
			return false;
		}
		String email_fk_pk = mr.getParameter("writer");
		c_fname = mr.getFilesystemName("fname");
		c_ofname = mr.getOriginalFileName("fname");

		return true;
	}
}
