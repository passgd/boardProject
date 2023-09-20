<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 

<div id="tabMenu">

    <label id="noticeTab" for="tab1">
    <a href="<c:url value='/notice/list'/>">공지사항</a></label>
        <div class="subMenu" id="noticeSubMenu">
            <h3>공지사항</h3>
             <c:forEach var="notice" items="${noticeTop5}">
                 <a href="#" onclick="dialogDetail(${notice.noticeid})">${notice.title}</a>
             </c:forEach>
        </div>
        
       <label id="boardTab" for="tab2">
    <a href="<c:url value='/board/list'/>">전체게시판</a></label>
        <div class="subMenu" id="boardSubMenu">
            <h3>전체게시판</h3>
            <c:forEach var="board" items="${boardTop5}">
 					<a href="#" onclick="boardDetail(${board.boardid})">${board.title} </a>
            </c:forEach>
        </div>
    
</div>

<div id="container">		
	<div id="content-main">				
		<video autoplay controls loop muted preload="auto">
		 	<source src="<c:url value='/images/nike.mp4'/>" type="video/mp4"> 
		</video>
		
		<div id="NewJeans">
		 	<h1>뉴진스의 국가대표팀 유니폼 스타일</h1>
		 	<br>
		 	<h3>나이키 앱에서 뉴진스 멤버의 개성을 담아 완성한</h3>
		 	<h3>국가대표팀 저지 스타일을 만나 보세요.</h3>
		 	<br>
		 	<a href="#"><h3>구매하기</h3></a>
		 	<img src="<c:url value='/images/영원을-쟁취하다.jpg'/>" alt="">
		 	<h3>나이키 앱</h3>
		</div>
			<br>
			
	<img src="<c:url value='/images/nike1.jpg'/>" alt="">
		<br>
		<div id="nike">
		 	<h1>영원을 쟁취하다</h1>
		 	<br>
		 	<h3>공이든,골이든,관중의 함성이든 원하는 건 무엇이든 다 차지하는 대한민국 축구 국가대표팀.</h3>
		 	<h3>끝없이 영원을 쟁취해 나가는 이들의 열정을 느껴 보세요.</h3>
		 	<br>
		 	<a href="#"><h3>자세히 보기</h3></a>
		 	<a href="#"><h3>구매하기</h3></a>
		 </div>	
		 <br>
		 
	<img src="<c:url value='/images/nike2.jpg'/>" alt="">
		<br>
		<div id="nike">
			<h3>나이키 코르테즈</h3>
		 	<h1>클래식의 재해석</h1>
		 	<br>
		 	<h3>어떤 스타일과도 조화로운 멋을 선사하는 나이키 코르테즈와 함께 나만의 개성을 표현해 보세요.</h3>
		 	<br>
		 	<a href="#"><h3>구매하기</h3></a>
		 </div>
		 <br>
		 
	<img src="<c:url value='/images/nike3.jpg'/>" alt="">
		<br>
		<div id="nike">
			<h3>나이키와 함께하는 가을 스타일링</h3>
		 	<h1>아웃도어 스타일링</h1>
		 	<br>
		 	<h3>뛰어난 기능성의 나이키 아웃도어 룩으로 맞이하는 새로운 가을.</h3>
		 	<h3>매주 공개되는 스타일링 미션에 참여하여 특별한 기프트도 받아 보세요.</h3>
		 	<br>
		 	<a href="#"><h3>자세히 보기</h3></a>
		 	<a href="#"><h3>구매하기</h3></a>
		 </div>
		 <br>
		 
	<img src="<c:url value='/images/nike4.jpg'/>" alt="">
		<br>
		<div id="nike">
			<h3>에어맥스1</h3>
		 	<h1>모든 형식에서 벗어나 자유롭게</h1>
		 	<br>
		 	<h3>패션 아이템을 넘어 라이프 스타일로.</h3>
		 	<h3>새로운 장르를 만들어낸 에어맥스 1을 만나 보세요.</h3>
		 	<br>
		 	<a href="#"><h3>구매하기</h3></a>
		 </div>
		 <br>
		 
		 <div id="Trending">
		  	<h2>Trending Now</h2>
		 	<div class="image-wrapper">
   				 <img src="<c:url value='/images/nike5.jpg'/>" alt="">
   				  <div class="button-container">
   				  <h3>샌들&슬리퍼 컬랙션</h3>
      			    <a href="#">구매하기</a>
    			  </div>
 			 </div>
 			 
  			 <div class="image-wrapper">
    		   <img src="<c:url value='/images/nike6.jpg'/>" alt="">
    		 	<div class="button-container">
    		 	<h3>나이키 레깅스</h3>
	    		 	<a href="#">자세히 보기</a>
	    		 	<a href="#">구매하기</a>
	    		</div>
  			 </div>
		  </div>
		  <br><br>
		  
		  <div id="Featured">
		  	<h2>Featured Shoes</h2>
 			 <div class="image-slide">
 			 	<a href="#">
    			<img src="<c:url value='/images/shoes1.jpg'/>" alt="">
    			<h4>나이키 페가수스40</h4>
    			<p>여성 로드 러닝화</p>
    			<p>149,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes2.jpg'/>" alt="">
    			<h4>나이키 코르테즈</h4>
    			<p>여성 신발</p>
    			<p>119,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes3.jpg'/>" alt="">
    			<h4>나이키 인피니티 에이스 NN NGR</h4>
    			<p>남성 골프화(와이드)</p>
    			<p>149,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes4.jpg'/>" alt="">
    			<h4>나이키 에어 포스 1 '07</h4>
    			<p>남성 신발</p>
    			<p>139,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes5.jpg'/>" alt="">
    			<h4>ACG 에어 데슈츠+</h4>
    			<p>샌들</p>
    			<p>89,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes6.jpg'/>" alt="">
    			<h4>나이키 머큐리얼 슈퍼플라이 9 아카데미</h4>
    			<p>인조 잔디 축구화</p>
    			<p>119,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes7.jpg'/>" alt="">
    			<h4>프릭 5 EP</h4>
    			<p>농구화</p>
    			<p>159,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes8.jpg'/>" alt="">
    			<h4>나이키 프리 메트콘 5</h4>
    			<p>남성 운동화</p>
    			<p>139,000 원</p></a>
    			
    			<a href="#">
    			<img src="<c:url value='/images/shoes9.jpg'/>" alt="">
    			<h4>조던 헥스</h4>
    			<p>여성 슬라이드</p>
    			<p>59,000 원</p></a>

			 </div>
		  </div>
		 
	</div>
</div>
	
<div id="detail" title="글 상세보기" >
	<div class="detail">
	  	<input type="hidden" name="noticeid" id="noticeid2" value="${notice.noticeid}"/> 
      	<h3 id="title2">[  ] </h3>
      	<hr>
	      <br>
	      <div class="meta-info" id="info2">
	  		작성자 :  <span id="email2">${notice.email}</span>  |　 작성날짜 :  <span id="reg_date2">${notice.reg_date}</span>　 |　 조회수 :  <span id="view_count2">${notice.view_count}</span>
		  </div>
	      <div class="contents" id="contents2">
	    	${notice.contentsHTML}
		  </div>
	</div>
</div> 

<div id="detailBoard" title="글 상세보기" >
	<div class="detailBoard">
	  	<input type="hidden" name="boardid" id="boardId" value="${board.boardid}"/> 
      	<h3 id="boardTitle">[  ] </h3>
      	<hr>
	      <br>
	      <div class="meta-info" id="info2">
	  		작성자 :  <span id="boardEmail">${board.email}</span>  |　 작성날짜 :  <span id="boardReg_date">${board.reg_date}</span>　 |　 조회수 :  <span id="boardView_count">${board.view_count}</span>
		  </div>
	      <div class="contents" id="boardContents">
	    	${board.contentsHTML}
		  </div>
	</div>
</div> 

<script type="text/javascript">
/*--------------------------------------------대화상자----------------------------------------------*/
/* 공지사항 상세 */
$(document).ready(function() {
	$("#detail").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        Close: function() {
	            $(this).dialog("close");
	        }
	    }
	});
});

/* 게시판 상세 */
$(document).ready(function() {
	$("#detailBoard").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        Close: function() {
	            $(this).dialog("close");
	        }
	    }
	});
});
/*--------------------------------------------실행부----------------------------------------------*/
/* 글 상세 패치 코드 */
function dialogDetail(noticeid) {
 	const noticeid2 = document.querySelector("#noticeid2");
	const title2 = document.querySelector("#title2");
	const contents2 = document.querySelector("#contents2");
	const email2 = document.querySelector("#email2");
	const reg_date2 = document.querySelector("#reg_date2");
	const view_count2 = document.querySelector("#view_count2");

	
	const param = {
	        noticeid: noticeid
	      };

	      fetch("<c:url value='/notice/view'/>", {
	        method: "POST",
	        headers: {
	          "Content-Type": "application/json; charset=UTF-8",
	        },
	        body: JSON.stringify(param),
	      })
	      .then((response) => response.json())
	      .then((json) => {
	    	   noticeid2.innerText = json.noticeid;  
	    	   title2.innerText = json.title;  
	    	   contents2.innerText = json.contents;  
	    	   email2.innerText = json.email;  
	    	   reg_date2.innerText = json.reg_date;  
	    	   view_count2.innerText = json.view_count;  
	       	   $("#detail").dialog("open");
	      });
	
	return false;
	
}

/* 글 상세 패치 코드 */
function boardDetail(boardid) {
 	const boardId = document.querySelector("#boardId");
	const boardTitle = document.querySelector("#boardTitle");
	const boardContents = document.querySelector("#boardContents");
	const boardEmail = document.querySelector("#boardEmail");
	const boardReg_date = document.querySelector("#boardReg_date");
	const boardView_count = document.querySelector("#boardView_count");

	
	const param = {
	        boardid: boardid
	      };

	      fetch("<c:url value='/board/view'/>", {
	        method: "POST",
	        headers: {
	          "Content-Type": "application/json; charset=UTF-8",
	        },
	        body: JSON.stringify(param),
	      })
	      .then((response) => response.json())
	      .then((json) => {
	    	   boardId.innerText = json.boardid;  
	    	   boardTitle.innerText = json.title;  
	    	   boardContents.innerText = json.contents;  
	    	   boardEmail.innerText = json.email;  
	    	   boardReg_date.innerText = json.reg_date;  
	    	   boardView_count.innerText = json.view_count;  
	       	   $("#detailBoard").dialog("open");
	      });
	
	return false;
	
}

//서브메뉴 마우스 효과
document.getElementById("noticeTab").addEventListener("mouseenter", function() {
    document.getElementById("noticeSubMenu").style.display = "inline";
});

document.getElementById("boardTab").addEventListener("mouseenter", function() {
    document.getElementById("boardSubMenu").style.display = "inline";
});

document.getElementById("tabMenu").addEventListener("mouseleave", function() {
    document.getElementById("noticeSubMenu").style.display = "none";
    document.getElementById("boardSubMenu").style.display = "none";
});
</script>

</html>