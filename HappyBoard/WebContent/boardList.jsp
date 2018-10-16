<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.co.happy.*" %>
<% ArrayList<BoardDTO> data = (ArrayList<BoardDTO>)request.getAttribute("data");%>
<% int maxPage = Integer.parseInt((String)request.getAttribute("maxPage")); %>
<% int pageNo = Integer.parseInt((String)request.getAttribute("page")); %>
<div>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>등록일시</th>
		</tr>
		
		<% for(BoardDTO dto : data) { %>
		<tr>
			<td><%=dto.getSeq() %></td>
			<td><a href="boardDetail?bid=<%=dto.getBid() %>&page=<%=pageNo%>"><%=dto.getBtitle() %></a></td>
			<td><%=dto.getBregdate() %></td>
		</tr>
		<%} %>	
	</table>
	<div>
	
	<% for(int p=1; p<=maxPage; p++){%>
		<a href="boardList?btype=${btype}&page=<%=p%>"><%=p %></a>
	<%}%>
	</div>
	<a href="boardWrite?btype=${btype}&page=<%=pageNo%>"><input type="button" value="글쓰기"></a>
</div>