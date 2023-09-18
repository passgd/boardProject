<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="insertDialog">
	 	<h1>회원 가입</h1>
		   <form id="mForm" name="mForm" method="post" onsubmit="return false;">
		       <label for="email">이메일:</label>
		       <input type="text" name="email" id="email" required>
		   	<input type="button" id="checkEmail" value="중복확인"/>
		       <br>
		       
		       <label for="pwd">비밀번호:</label>
		       <input type="password" name="pwd" id="pwd" required>
		       <br>
		       
		       <label for="name">이름:</label>
		       <input type="text" name="name" id="uname" required>
		       <br>
		
		       <label for="birthday">생년월일:</label>
		       <input type="text" name="birthday" id="birthday" required>
		       <br>
		
		       <label for="phone">전화번호:</label>
		       <input type="text" name="phone" id="phone" required>
		       <br>
		
		       <label for="gender">성별:</label>
		       <input type="radio" name="gender" id="gender-male" value="남성" required>남성
		       <input type="radio" name="gender" id="gender-female" value="여성" required>여성
		       <br>
		
		       <label for="address">주소:</label>
		       <input type="text" name="address" id="address" required>
		       <br>
		       
		        <input type="submit" id="insert" value="회원 가입"/>
		   </form>
	 </div>
	 

<script>
document.querySelector("#insert").addEventListener("click", e =>{
	insertDialog('<c:url value="member/insert"/>');
});

document.querySelector("#checkEmail").addEventListener("click", e =>{
	checkEmail(email, '<c:url value="/member/checkEmail"/>');
});
</script>
