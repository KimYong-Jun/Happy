<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.happy.*" %>
<% BoardDTO detail = (BoardDTO)request.getAttribute("detail"); %>

<div>
<form action="boardUpdate" method="post" name="update">
<input type="hidden" value=<%=detail.getBid()%> name="bid" >
<input type="hidden" value=<%=detail.getBtype()%> name="btype">
<input type="hidden" value=${page } name="page">
 제목 : <input type="text" name="btitle" value="<%=detail.getBtitle()%>">
 <br><br>
내용 : <textarea rows="6" cols="6" name="bcontent"><%=detail.getBcontent() %></textarea>
<br><br>
비번 : <input type="password" name="pw" value="<%=detail.getPw()%>">
<br><br>
<input type="submit" value="수정">
</form>
</div>