<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
<form action="boardWrite" method="post" name="write">
<input type="hidden" value="${btype}" name="btype" >
<input type="hidden" value="${page}" name="page">
 제목 : <input type="text" name="btitle">
 <br><br>
내용 : <textarea rows="6" cols="6" name="bcontent"></textarea>
<br><br>
비번 : <input type="password" name="pw">
<br><br>
<input type="submit" value="저장">
<input type="reset" value="취소">
</form>
</div>