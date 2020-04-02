package sabujak.hobby.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;

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
import sabujak.domain.Hboard;
import sabujak.domain.Review;
import sabujak.hobby.model.HboardService;
import sabujak.hobby.vo.ListResult;
import sabujak.review.model.ReviewService;
import sabujak.review.vo.ListResultRev;

/**
 * Servlet implementation class HboardController
 */
@WebServlet("/hboard/board.do")
public class HboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String m ="";
    MultipartRequest mr = null;
    
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		m = request.getParameter("m");
		if(m != null) {
			m=m.trim();
			if(m.equals("list")) {
				list(request,response);
			}else if(m.equals("write")){
				write(request,response);
			}else if(m.equals("insert")){
				insert(request,response);
			}else if(m.equals("content")){
				content(request,response);
			}else if(m.equals("del")){
				del(request,response);
			}else if(m.equals("update")){
				content(request,response);
			}else if(m.equals("updateOk")){
				updateOk(request,response);
			}else if(m.equals("rev_save")){
				rev_save(request,response);
			}else if(m.equals("rev_del")){
				content(request,response);
			}else if(m.equals("goodView")){
				list(request,response);
			}else if(m.equals("rev")){
				content(request,response);
			}else if(m.equals("updateRev")){
				content(request,response);
			}else if(m.equals("rev_updateOk")) {
				rev_updateOk(request,response);
			}else if(m.equals("download")) {
				download(request,response);
			}
		}else {
			list(request,response);
		}
	}
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		HttpSession session = request.getSession();
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
		int ps = 3;
		if(psStr == null) {
			Object psObj = request.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = request.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp =1;
					session.setAttribute("cp", cp);
				}
			}
			ps = psParam;
		}
		request.setAttribute("ps", ps);
		
		HboardService service = HboardService.getInstance();
		if(m !=null) {
			if(m.equals("goodView")){
				ListResult listResult = service.getListResultView(cp, ps);
				request.setAttribute("listResult", listResult);
				if(listResult.getList().size()==0) {
					if(cp>1) {
						response.sendRedirect("board.do?m=list&cp="+(cp-1));
					}else {
						request.setAttribute("listResult", null);
						String view = "list.jsp";
						RequestDispatcher rd = request.getRequestDispatcher(view);
						rd.forward(request,response);
					}
				}else {
					String view = "list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
			}else{
				ListResult listResult = service.getListResult(cp, ps);
				request.setAttribute("listResult", listResult);
				if(listResult.getList().size()==0) {
					if(cp>1) {
						response.sendRedirect("board.do?m=list&cp="+(cp-1));
					}else {
						request.setAttribute("listResult", null);
						String view = "list.jsp";
						RequestDispatcher rd = request.getRequestDispatcher(view);
						rd.forward(request,response);
					}
				}else {
					String view = "list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
			}
		}else {
			ListResult listResult = service.getListResult(cp, ps);
			request.setAttribute("listResult", listResult);
			if(listResult.getList().size()==0) {
				if(cp>1) {
					response.sendRedirect("board.do?m=list&cp="+(cp-1));
				}else {
					request.setAttribute("listResult", null);
					String view = "list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request,response);
				}
			}else {
				String view = "list.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
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
		HboardService service = HboardService.getInstance();
		
		String hcode_fkStr = mr.getParameter("category");
		if(hcode_fkStr != null) hcode_fkStr = hcode_fkStr.trim();
		int hcode_fk =0;
		if(hcode_fkStr.length() != 0) {
			hcode_fk = Integer.parseInt(hcode_fkStr);
		}
		String email_fk = mr.getParameter("writer");
		String h_sub = mr.getParameter("title");
		String h_cont = mr.getParameter("content");
		String fname = mr.getFilesystemName("fname");
		String ofname = mr.getOriginalFileName("fname");
		service.insertS(new Hboard(-1,hcode_fk,h_sub,h_cont,0,null,0,email_fk, fname, ofname));
		String view = "board.do";
		response.sendRedirect(view);

	}
	private void content(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int h_no = getH_no(request);
		if(h_no != -1) {	
			HboardService service = HboardService.getInstance();
			String h_viewStr = request.getParameter("h_view");
			if(h_viewStr != null) h_viewStr = h_viewStr.trim();
			int h_view = 0;
			if(h_viewStr.length()!=0) {
				h_view = Integer.parseInt(h_viewStr);
			}
			
			String revcpStr = request.getParameter("cpRev");
			String psStr = request.getParameter("ps");
			HttpSession session = request.getSession();
			
			int cpRev = 1;
			if(revcpStr == null) {
				Object cpObj = session.getAttribute("cpRev");
				if(cpObj != null) {
					cpRev = (Integer)cpObj;
				}
			}else {
				revcpStr = revcpStr.trim();
				cpRev = Integer.parseInt(revcpStr);
			}
			session.setAttribute("cpRev", cpRev);
			int ps = 5;
			if(psStr == null) {
				Object psObj = request.getAttribute("ps");
				if(psObj != null) {
					ps = (Integer)psObj;
				}
			}else {
				psStr = psStr.trim();
				int psParam = Integer.parseInt(psStr);
				
				Object psObj = request.getAttribute("ps");
				if(psObj != null) {
					int psSession = (Integer)psObj;
					if(psSession != psParam) {
						cpRev = 1;
						request.setAttribute("cpRev", cpRev);
					}
				}else {
					if(ps != psParam) {
						cpRev =1;
						request.setAttribute("cpRev", cpRev);
					}
				}
				ps = psParam;
			}
			request.setAttribute("ps", ps);
			
			Hboard hboard = service.contentS(h_no);
			service.viewUpS(h_no, h_view);
			request.setAttribute("hboard", hboard);
			ReviewService revService = ReviewService.getInstance();
			ArrayList<Review> rev = revService.calcultotalS(h_no);
			request.setAttribute("rev", rev);
			ListResultRev listResultRev = revService.getListResultRev(cpRev, ps, h_no);
			request.setAttribute("listResultRev", listResultRev);
			String view ="";
			if(m.equals("content")) {
				if(listResultRev.getList().size()==0) {
					if(cpRev>1) {
						response.sendRedirect("board.do?m=content&h_no="+h_no+"&h_view="+h_view+"&cpRev="+(cpRev-1));
					}else {
						request.setAttribute("listResultRev", null);
						view = "content.jsp";
						RequestDispatcher rd = request.getRequestDispatcher(view);
						rd.forward(request,response);
					}
				}else {
					view = "content.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
			}else if(m.equals("update")) {
				view  ="update.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}else if(m.equals("rev_del")) {
				String rev_noStr = request.getParameter("rev_no");
				if(rev_noStr != null) rev_noStr = rev_noStr.trim();
				int rev_no = 0;
				if(rev_noStr.length()!=0) {
					rev_no = Integer.parseInt(rev_noStr);
				}
				revService.delRevS(rev_no);
				view ="board.do?m=content&h_no="+h_no+"&h_view="+h_view;
				response.sendRedirect(view);
			}else if(m.equals("updateRev")){
				String rev_noStr = request.getParameter("rev_no");
				if(rev_noStr != null) rev_noStr = rev_noStr.trim();
				int rev_no = 0;
				if(rev_noStr.length()!=0) {
					rev_no = Integer.parseInt(rev_noStr);
				}
				
				Review review = revService.contentRevS(rev_no,h_no);
				request.setAttribute("review",review);
				view  ="updateRev.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}
	}
	private void del(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String saveDirectory = FileSet.FILE_THUMB;
		int h_no = getH_no(request);
		if(h_no != -1) {	
			HboardService service = HboardService.getInstance();
			String fname= request.getParameter("fname");
			File f = new File(saveDirectory, fname);
			if(f.exists()) {
				service.delS(h_no);
				f.delete();
			}
			String view = "board.do";
			response.sendRedirect(view);
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}
	}
	private void updateOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String saveDirectory = FileSet.FILE_THUMB;
		HboardService service = HboardService.getInstance();
		setMR(request,response);
		int h_no = getH_no(request);
		if(h_no != -1) {	
			String hcode_fkStr = mr.getParameter("category");
			if(hcode_fkStr != null) hcode_fkStr = hcode_fkStr.trim();
			int hcode_fk =0;
			if(hcode_fkStr.length() != 0) {
				hcode_fk = Integer.parseInt(hcode_fkStr);
			}
			String h_sub = mr.getParameter("title");
			String h_cont = mr.getParameter("content");
			String old_Fname = mr.getParameter("Ofname");
			String fname = mr.getFilesystemName("fname");
			String ofname = mr.getOriginalFileName("fname");
			
			if(fname==null) {
				service.updateS(new Hboard(h_no,hcode_fk,h_sub,h_cont,0,null,0,null,fname,ofname));
			}else if(ofname.equals(old_Fname)){
				File f = new File(saveDirectory, old_Fname);
				if(f.exists()) f.delete();
				service.updateS(new Hboard(h_no,hcode_fk,h_sub,h_cont,0,null,0,null,fname,ofname));
			}else {
				File f = new File(saveDirectory, old_Fname);
				if(f.exists()) f.delete();
				service.updateS(new Hboard(h_no,hcode_fk,h_sub,h_cont,0,null,0,null,fname,ofname));
			}
			String view = "board.do";
			response.sendRedirect(view);
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}
	}
	private void rev_save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewService revService = ReviewService.getInstance();
		int h_no = getH_no(request);
		if(h_no != -1) {	
			String h_viewStr = request.getParameter("h_view");
			if(h_viewStr != null) h_viewStr = h_viewStr.trim();
			int h_view = 0;
			System.out.println(h_viewStr);
			if(h_viewStr.length()!=0) {
				h_view = Integer.parseInt(h_viewStr);
			}
			String rev_goodStr = request.getParameter("rev_good");
			if(rev_goodStr != null) rev_goodStr = rev_goodStr.trim();
			int rev_good =0;
			if(rev_goodStr.length() != 0) {
				rev_good = Integer.parseInt(rev_goodStr);
			}
			String email_fk = request.getParameter("email_fk");
			String rev_cont = request.getParameter("rev_cont");
			revService.insertRevS(new Review(-1, h_no, rev_good, null, rev_cont, null, email_fk));
			String view = "board.do?m=content&h_no="+h_no+"&h_view="+h_view;
			response.sendRedirect(view);
			
		}else{
			String view = "board.do";
			response.sendRedirect(view);
		}
		
	}
	private void rev_updateOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ReviewService revService = ReviewService.getInstance();
		int h_no = getH_no(request);
		if(h_no != -1) {	
			String h_viewStr = request.getParameter("h_view");
			if(h_viewStr != null) h_viewStr = h_viewStr.trim();
			int h_view = 0;
			System.out.println(h_viewStr);
			if(h_viewStr.length()!=0) {
				h_view = Integer.parseInt(h_viewStr);
			}
			String rev_goodStr = request.getParameter("rev_good");
			if(rev_goodStr != null) rev_goodStr = rev_goodStr.trim();
			int rev_good =0;
			if(rev_goodStr.length() != 0) {
				rev_good = Integer.parseInt(rev_goodStr);
			}
			String rev_noStr = request.getParameter("rev_no");
			if(rev_noStr != null) rev_noStr = rev_noStr.trim();
			int rev_no =0;
			if(rev_noStr.length() != 0) {
				rev_no = Integer.parseInt(rev_noStr);
			}
			String rev_cont =request.getParameter("rev_cont");
			revService.updateRevS(new Review(rev_no, h_no, rev_good, null, rev_cont, null, null));
			String view = "board.do?m=content&h_no="+h_no+"&h_view="+h_view;
			response.sendRedirect(view);
		}else {
			String view = "board.do";
			response.sendRedirect(view);
		}
			
	}
	private void setMR(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HboardService service = HboardService.getInstance();
		ServletContext application = getServletContext();
		
		String saveDirectory = FileSet.FILE_THUMB;
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
	
	private void download(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fname");
		String saveDirectory = FileSet.FILE_THUMB;
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
	private int getH_no(HttpServletRequest request) {
		String h_noStr = request.getParameter("h_no");
		int h_no = 0;
		if(h_noStr == null) {
			return -1;
		}else {
			h_noStr = h_noStr.trim();
			try {
				h_no = Integer.parseInt(h_noStr);
				
				return h_no;
			}catch(NumberFormatException ne) {
				return -1;
			}
		}
	}
}
