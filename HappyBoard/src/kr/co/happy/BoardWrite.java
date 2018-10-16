package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardWrite
 */
@WebServlet("/boardWrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btype = request.getParameter("btype");
		String page = request.getParameter("page");
		request.setAttribute("btype", btype);
		request.setAttribute("page", page);
		request.setAttribute("title", "글쓰기");
		request.setAttribute("target", "boardWrite");
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String btype = request.getParameter("btype");
		int intBtype = 0;
		if(btype != null) {
			intBtype = Integer.parseInt(btype);
		}
		String page = request.getParameter("page");
		int intPage = 0;
		if(page != null) {
			intPage = Integer.parseInt(page);
		}
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		BoardDAO dao = BoardDAO.getInstance();
		dao.insert(intBtype, btitle, bcontent, pw);
		
		response.sendRedirect("boardList?btype="+intBtype+"&page="+intPage);
	}
}
