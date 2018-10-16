package kr.co.happy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDeleteservlet
 */
@WebServlet("/boardDelete")
public class BoardDeleteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		BoardDAO dao = BoardDAO.getInstance();
		dao.delete(intBid);
		
		response.sendRedirect("boardList?btype="+intBtype+"&page="+page);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
