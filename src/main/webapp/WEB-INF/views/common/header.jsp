<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value='/js/member.js'/>"></script>
<c:import url="../member/insert.jsp" />
<header>	
 	<div id="loginButtonContainer">
 		<ul>
 			<li>
            <a id="customerCenterButton" href="#">고객센터</a>
            <ul id="customerCenterMenu">
                <li><a href="#">결재방법</a></li>
                <li><a href="#">반품</a></li>
                <li><a href="#">사이즈가이드</a></li>         
                <li><a href="<c:url value='/notice/list.do'/>">공지사항</a></li>
                <li><a href="<c:url value='/board/list.do'/>">전체게시판</a></li>
            </ul></li> 
           <li><a id="findStore" href="#">매장찾기</a></li>

<c:choose>
    <c:when test="${not empty sessionScope['loginMember']}">
        <li><button id="viewButton" type="button">회원정보</button></li>
        <li><a id="logout" href="#">로그아웃</a></li>
    </c:when>
    <c:otherwise>
        <li><button id="insertButton" type="button">회원가입</button></li>
        <li><button id="loginButton" type="button">로그인</button></li>
    </c:otherwise>
</c:choose>
		</ul>
	</div>	
 	<div id="headerContainer">		
		<div id="logo">
			<a href="<c:url value='/'/>">
				<h1>Just Do It</h1>
			</a>
		</div>
		
		<nav>
			<ul id="topMenu">
				<li><a href="#">신발<span>▼</span></a>
					<ul>
						<li><a href="#">조던</a></li>
						<li><a href="#">라이프스타일</a></li>
						<li><a href="#">샌들 & 슬리퍼</a></li>
						<li><a href="#">러닝화</a></li>
						<li><a href="#">농구화</a></li>
						<li><a href="#">축구화</a></li>
					</ul></li>
				<li><a href="#">의류<span>▼</span></a>
					<ul>
						<li><a href="#">탑 & 티셔츠</a></li>
						<li><a href="#">후드 & 크루</a></li>
						<li><a href="#">재킷 & 베스트</a></li>
						<li><a href="#">트랙수트</a></li>
					</ul></li>
				<li><a href="#">용품<span>▼</span></a>
					<ul>
						<li><a href="#">가방</a></li>
						<li><a href="#">공</a></li>
						<li><a href="#">모자&헤드밴드</a></li>
						<li><a href="#">요가 매트</a></li>
						<li><a href="#">보호대</a></li>
					</ul></li>
			</ul>
		  </nav>	
		 </div>	
		 
   	<div id="dialogDiv">
   	 <h1>로그인</h1>
	    <form id="loginForm" name="loginForm" method="post" onsubmit="return false;">
	        <label for="email">이메일:</label>
	        <input type="text" id="loginemail" name="loginemail" required="required"/><br>
	        <label for="pwd">비밀번호:</label>
	        <input type="password" id="loginpwd" name="loginpwd" required="required"/><br>
	    </form>
	</div>
	
	<div id="findIdDiv">
			<h1>아이디 찾기</h1> 
		<form id="findIdForm" name="findIdForm" method="post" autocomplete="off">
				<label for="name" class="label-placeholder">이름</label>
				<input type="text" name="findIdName" id="findIdName" required><br>
				<label for="phone" class="label-placeholder">전화번호</label> 
				<input id="findIdPhone" type="tel" name="findIdPhone" required>
	 	</form>
 	</div>
 	
 	<div id="findPwdDiv">
		<h1>비밀번호 찾기</h1>
		<form id="FindPwdForm" name="FindPwdForm" method="post" autocomplete="off">
           <label for="email" class="label-placeholder">이메일</label>
           <input id="findEmail" type="text" name="findEmail" required><br>
			<label for="phone" class="label-placeholder">전화번호</label> 
			<input id="findPwdPhone" type="tel" name="findPwdPhone" required>
 		</form>
 	</div>
 	
	<div id="viewDiv">
	  <h1>회원 정보</h1>
	  <form id="viewForm" name="viewForm" method="get" autocomplete="off">
		    <p><strong>이메일:</strong> <span id="viewemail"></span></p>
		    <p><strong>이름:</strong> <span id="viewname"></span></p>
		    <p><strong>생년월일:</strong> <span id="viewbirthday"></span></p>
		    <p><strong>전화번호:</strong> <span id="viewphone"></span></p>
		    <p><strong>성별:</strong> <span id="viewgender"></span></p>
		    <p><strong>주소:</strong> <span id="viewaddress"></span></p>
	  </form>
	</div>
	
	<div id="updateDiv">
	  <h1>회원 정보 수정</h1>
	    <form id="updateForm" name="updateForm" method="post">
	        <label for="pwd">비밀번호</label>
	        <input type="password" id="updatepwd" name="pwd" required><br>
	        <label for="phone">전화번호</label>
	        <input type="text" id="updatephone" name="phone" required><br>
	        <label for="address">주소</label>
	        <input type="text" id="updateaddress" name="address" required><br>
	   	</form>
	</div>
	
	<div id="deleteDiv">
	 <h1>회원 탈퇴</h1><br>
		<form id="deleteForm" name="deleteForm" method="post" style="display: inline;">
		    <input type="hidden" id="deleteEmail" value="${loginMember.email}">
		    <label>
		    <h1> 정말로 탈퇴 하시겠습니까?</h1><br>
		    	<h3>탈퇴를 원하면 비밀번호를 입력해 주세요.</h3><br>
		    </label>
		    <label for="pwd">비밀번호</label>
	        <input type="password" id="deletepwd" name="pwd" required><br>
		</form>
	</div>	
</header>
<script>
/*--------------------------------------------버튼----------------------------------------------*/
//로그인
$("#loginButton").on("click", function() {
    dialogDiv.dialog("open");
});
// 아이디 찾기
$("#findIdButton").on("click", function() {
	findIdDiv.dialog("open");
});
//비밀번호 찾기
$("#findPwdButton").on("click", function() {
	findPwdDiv.dialog("open");
});
//상세보기
$("#viewButton").on("click", function() {
    view();
});
//회원정보 수정
$("#updateButton").on("click", function() {
	updateDiv.dialog("open");
});
//회원정보 삭제
$("#deleteButton").on("click", function() {
	deleteDiv.dialog("open");
});
/*--------------------------------------------대화상자----------------------------------------------*/
//로그인 
const dialogDiv = $("#dialogDiv").dialog({
     autoOpen: false,
     height: 800,
     width: 500,
     modal: true,
     buttons: {
   	    "로그인": login,
   	 	"아이디 찾기": function() {
	        findIdDiv.dialog("open");
	    },
	    "비밀번호 찾기": function() {
	        findPwdDiv.dialog("open");
	    },
   	    Close: function() {
   	      $(this).dialog("close");
   	    }
   	  }
   	});

//아이디 찾기
const findIdDiv = $("#findIdDiv").dialog({
    autoOpen: false,
    height: 800,
    width: 500,
    modal: true,
    buttons: {
    	"찾기": findId,
  	    Close: function() {
  	      $(this).dialog("close");
  	    }
  	  }
  	});
  	
//비밀번호 찾기
const findPwdDiv = $("#findPwdDiv").dialog({
    autoOpen: false,
    height: 800,
    width: 500,
    modal: true,
    buttons: {
    	"찾기": findPwd,
  	    Close: function() {
  	      $(this).dialog("close");
  	    }
  	  }
  	});
  	
//회원 정보 보기
const viewDialog = $("#viewDiv").dialog({
    autoOpen: false,
    height: 800,
    width: 500,
    modal: true,
    buttons: {
    	"회원 수정": function() {
    		// 기존 값 가져오기
            const pwdValue = $("#viewpwd").text();
            const phoneValue = $("#viewphone").text();
            const addressValue = $("#viewaddress").text();

            // 가져온 값 설정
            $("#updatepwd").val(pwdValue);
            $("#updatephone").val(phoneValue);
            $("#updateaddress").val(addressValue);
	        updateDiv.dialog("open");
	    },
	    "회원 탈퇴": function() {

	        deleteDiv.dialog("open");
	    },
  	    Close: function() {
  	      $(this).dialog("close");
  	    }
  	  }
  	});  	
  	
//회원정보 수정
const updateDiv = $("#updateDiv").dialog({
    autoOpen: false,
    height: 800,
    width: 500,
    modal: true,
    buttons: {
    	"수정": update,
  	    Close: function() {
  	      $(this).dialog("close");
  	    }
  	  }
  	});

//회원정보 삭제
const deleteDiv = $("#deleteDiv").dialog({
    autoOpen: false,
    height: 800,
    width: 500,
    modal: true,
    buttons: {
    	"탈퇴하기": memberDelete,
  	    Close: function() {
  	      $(this).dialog("close");
  	    }
  	  }
  	});


/*--------------------------------------------실행부----------------------------------------------*/	
 //로그인
 function login(){
	 const email = $("#loginemail").val();
 	 const pwd = $("#loginpwd").val();
	 const param = {
		email: email,
		pwd: pwd
	};

	fetch('<c:url value="/member/login"/>',{
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
		},
		body: JSON.stringify(param),
	})
	.then((response) => response.json())
	.then((json) =>{
		alert(json.message);
		dialogDiv.dialog("close");
	});
}

//아이디 찾기 
 function findId(){
	 const name = $("#findIdName").val();
 	 const phone = $("#findIdPhone").val();
	 const param = {
		name: name,
		phone: phone
	};

	fetch('<c:url value="/member/findId"/>',{
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
		},
		body: JSON.stringify(param),
	})
	.then((response) => response.json())
	.then((json) =>{
		alert(json.message);
		findIdDiv.dialog("close");
	});
}

// 비밀번호 찾기
 function findPwd(){
	 const email = $("#findEmail").val();
 	 const phone = $("#findPwdPhone").val();
	 const param = {
		email: email,
		phone: phone
	};

	fetch('<c:url value="/member/findPwd"/>',{
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
		},
		body: JSON.stringify(param),
	})
	.then((response) => response.json())
	.then((json) =>{
		alert(json.message);
		findPwdDiv.dialog("close");
	});
}

//회원정보 보기
function view() {
  $.ajax({
    url: '<c:url value="/member/view"/>', 
    contentType: "application/json; charset=UTF-8",
    type: "GET",
    dataType: "json",
    success: function(result) {
      $("#viewemail").text(result.memberInfo.email);
      $("#viewname").text(result.memberInfo.name);
      $("#viewbirthday").text(result.memberInfo.birthday);
      $("#viewphone").text(result.memberInfo.phone);
      $("#viewgender").text(result.memberInfo.gender);
      $("#viewaddress").text(result.memberInfo.address);

      viewDialog.dialog("open");
    },
  });
}

//회원정보 수정
function update(){
	 const pwd = $("#updatepwd").val();
	 const phone = $("#updatephone").val();
	 const address = $("#updateaddress").val();
	
	 const param = {
		email: '${memberUpdate.email}',
		pwd: pwd,
		phone: phone,
		address: address
	};

	fetch('<c:url value="/member/update"/>',{
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
		},
		body: JSON.stringify(param),
	})
	.then((response) => response.json())
	.then((json) =>{
		alert(json.message);
		updateDiv.dialog("close");
		
		// 수정 후, 변경된 값을 화면에 업데이트
        $("#viewpwd").text(pwd);
        $("#viewphone").text(phone);
        $("#viewaddress").text(address);
	});
}


//회원 탈퇴
function memberDelete() {	
    const password = $("#deletepwd").val();

    if (password) {
        const param = {
            email: $("#deleteEmail").val(),
            pwd: password,
        };

        fetch('<c:url value="/member/delete"/>', {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=UTF-8",
            },
            body: JSON.stringify(param),
        })
        .then((response) => response.json())
        .then((json) => {
            if (json.success) { // 탈퇴가 성공한 경우
                alert(json.message);
                deleteDiv.dialog("close");
            } else { // 탈퇴가 실패한 경우
                alert(json.message); // 실패 메시지 표시
            }
        });
    }
}


 /*--------------------------------------------기타----------------------------------------------*/	 
//로그아웃
const logout = document.querySelector("#logout");
if (logout != null) {
	logout.addEventListener("click", e =>{
		jsLogout('<c:url value="/member/logout"/>');
	});
}

</script>