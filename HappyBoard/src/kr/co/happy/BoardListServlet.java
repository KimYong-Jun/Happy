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
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btype = request.getParameter("btype");
		String page = request.getParameter("page");
		int intBtype = 0;
		if(btype != null) {
			intBtype = Integer.parseInt(btype);
		}
		String title = null;
		switch(intBtype) {
			case 0 :
				title = "게시판1";
			break;
			case 1 :
				title = "게시판2";
			break;
			case 2 :
				title = "게시판3";
			break;
		}
		int intPage = 1;
		if(page != null) {
			intPage = Integer.parseInt(page);
		}
		request.setAttribute("btype", btype);
		request.setAttribute("title", title);
		request.setAttribute("page", page);
		request.setAttribute("target", "boardList");
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardDTO> list = dao.getBoardList(intBtype, intPage);
		request.setAttribute("data", list);
		int maxPage = 0;
		maxPage = dao.maxPage(intBtype);
		String str = String.valueOf(maxPage);
		request.setAttribute("maxPage", str);
		RequestDispatcher rd = request.getRequestDispatcher("template.jsp");
		rd.forward(request, response);
		
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
