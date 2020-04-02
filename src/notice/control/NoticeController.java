package notice.control;

import java.io.File;
import java.io.IOException;

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

import file.model.FileSet;
import notice.model.NoticeService;
import sabujak.domain.*;
import sabujak.vo.NoticeR;

@WebServlet("/notice/board.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String m = "";
	MultipartRequest mr = null;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m = m.trim();
			if(m.equals("list")) {
				list(request, response);
			}else if(m.equals("content")){
				getNotice(request, response);
			}else if(m.equals("write")) {
				write(request, response);
			}else if(m.equals("insert")) {
				insert(request, response);
			}else if(m.equals("update")) {
				getNotice(request,response);
			}else if(m.equals("updateOk")) {
				updateOk(request,response);
			}else if(m.equals("del")) {
				del(request,response);
			}
		}else {
			list(request, response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		HttpSession session = request.getSession();
		//(1)cp
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2)ps
		int ps = 20;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);	
		
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp =1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		NoticeService service = NoticeService.getInstance();
		NoticeR noticer = service.getListResult(cp, ps);
		request.setAttribute("noticer", noticer);
		if(noticer.getList().size() == 0) {
			if(cp>1) {
				response.sendRedirect("board.do?m=list&cp="+(cp-1));
			}else{
				request.setAttribute("listResult", null);
				String view = "list.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}else {
			String view = "list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}
	private void getNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		int n_no = getSeq(request);
		if(n_no != -1) {
			NoticeService service = NoticeService.getInstance();
			Notice notice = service.getNoticeS(n_no);
			request.setAttribute("Notice", notice);
			
			String view ="";
			if(m.equals("content")) {
				view = "content.jsp";
			}else {
				view = "update.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}
	}
	private void write(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String view ="write.jsp";
		response.sendRedirect(view);
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		setMR(request,response);
		NoticeService service = NoticeService.getInstance();
		
		String email_fk = mr.getParameter("writer");
		String n_sub = mr.getParameter("title");
		String n_cont = mr.getParameter("content");
		String fname = mr.getFilesystemName("fname");
		String ofname = mr.getOriginalFileName("fname");
		service.insertS(new Notice(-1,n_sub,n_cont,null,email_fk, fname, ofname));
		String view = "board.do";
		response.sendRedirect(view);

	}
	private void updateOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String saveDirectory = FileSet.FILE_DIR;
		int n_no = getSeq(request);
		if(n_no != -1) {
			setMR(request,response);
			NoticeService service = NoticeService.getInstance();
			
			String n_sub = mr.getParameter("title");
			String n_cont = mr.getParameter("content");
			String email_fk = mr.getParameter("writer");
			String fname = mr.getFilesystemName("fname");
			String ofname = mr.getOriginalFileName("fname");
			String oldfname = mr.getParameter("ofname");
			System.out.println("fname: "+fname+", ofname: "+ofname+", oldfname: "+oldfname);
			if(fname==null) {
				service.updateS(new Notice(n_no, n_sub, n_cont, null, email_fk, oldfname, ofname));
			}else if(ofname.equals(oldfname)) {
				File f = new File(saveDirectory, oldfname);
				if(f.exists()) f.delete();
				service.updateS(new Notice(n_no, n_sub, n_cont, null, email_fk, fname, ofname));
			}else {
				File f = new File(saveDirectory, oldfname);
				if(f.exists()) f.delete();
				service.updateS(new Notice(n_no, n_sub, n_cont, null, email_fk, fname, ofname));
			}
			String view = "board.do";
			response.sendRedirect(view);
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}
	}
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int n_no = getSeq(request);
		String saveDirectory = FileSet.FILE_DIR;
		if(n_no != -1) {
			NoticeService service = NoticeService.getInstance();
			String fname = request.getParameter("fname");
			File f = new File(saveDirectory, fname);
			if (f.exists()) {
				service.delS(n_no);
				f.delete();
			}
			String view = "board.do";
			response.sendRedirect(view);
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}

	}
	
	private int getSeq(HttpServletRequest request) {
		String n_noStr = request.getParameter("n_no");
		int n_no = 0;
		if(n_noStr == null) {
			return -1;
		}else {
			n_noStr = n_noStr.trim();
			try {
				n_no = Integer.parseInt(n_noStr);
				return n_no;
			}catch(NumberFormatException ne) {
				return -1;
			}
		}
	}
	private void setMR(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		NoticeService service = NoticeService.getInstance();
		ServletContext application = getServletContext();
		
		String saveDirectory = FileSet.FILE_DIR;
		File fSaveDir = new File(saveDirectory);
		if(!fSaveDir.exists()) fSaveDir.mkdirs();
		
		int maxPostSize = 300*1024*1024;
		String encoding = "utf-8";
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		try {
			mr = new MultipartRequest(request, saveDirectory, maxPostSize,encoding,policy);
		}catch(IOException ie) {
		}
	}
}
	
	
