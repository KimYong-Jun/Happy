<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="kr.co.happy.*" %>
<% BoardDTO detail = (BoardDTO)request.getAttribute("detail"); %>
<% int pageNo = Integer.parseInt((String)request.getAttribute("page"));  %>
<script>
function checkpw(pw){

	var userpw = prompt("비밀번호를 입력해주세요 :");
	
	if(pw == userpw){
		alert('비밀번호가 맞습니다');
		return true;
	}else{
		alert('비밀번호가 틀립니다');
		return false;
	}
}

</script>

<div>
	<table>
		<tr>
			<td>번호</td>
			<th><%=detail.getSeq() %>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=detail.getBtitle() %>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=detail.getBcontent() %>
		</tr>
		<tr>
			<th>등록일시</th>
			<td><%=detail.getBregdate() %>
		</tr>
	</table>
	<a href="boardUpdate?bid=<%=detail.getBid() %>&page=<%=pageNo%>"><input type="button" value="수정" onclick="return checkpw('<%=detail.getPw() %>')"></a>
	<a href="boardDelete?bid=<%=detail.getBid() %>&btype=<%=detail.getBtype()%>&&page=<%=pageNo%>"><input type="button" value="삭제" onclick="return checkpw('<%=detail.getPw() %>')"></a>
</div>