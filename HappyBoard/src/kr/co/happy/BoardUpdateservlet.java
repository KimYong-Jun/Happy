package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardUpdateservlet
 */
@WebServlet("/boardUpdate")
public class BoardUpdateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		
		int intBid = 0;
		if(bid != null) {
			intBid = Integer.parseInt(bid);
		}
		String page = request.getParameter("page");
		request.setAttribute("page", page);
		request.setAttribute("bid", bid);
		request.setAttribute("title", "글 수정");
		request.setAttribute("target", "boardUpdate");
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO detail = dao.boardDetail(intBid);
		request.setAttribute("detail", detail);
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String bid = request.getParameter("bid");
		int intBid = 0;
		if(bid != null) {
			intBid = Integer.parseInt(bid);
		}
		String btype = request.getParameter("btype");
		int intBtype = 0;
		if(btype != null) {
			intBtype = Integer.parseInt(btype);
		}
		String page = request.getParameter("page");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String pw = request.getParameter("pw");
		BoardDAO dao = BoardDAO.getInstance();
		dao.update(intBid, btitle, bcontent, pw);
		
		response.sendRedirect("boardList?btype="+intBtype+"&page="+page);
	}

}
