package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bid = request.getParameter("bid");
		int intBid = 0;
		if(bid != null) {
			intBid = Integer.parseInt(bid);
		}
		String page = request.getParameter("page");
		request.setAttribute("page", page);
		request.setAttribute("title", "게시글 보기");
		request.setAttribute("bid", intBid);
		request.setAttribute("target", "boardDetail");
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO detail = dao.boardDetail(intBid);
		request.setAttribute("detail", detail);
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
