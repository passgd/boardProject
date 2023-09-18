<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
<h1>공지사항 목록</h1>
<h1>회원 목록</h1>

	 <form name="pageForm" id="pageForm" action="<c:url value='/notice/list'/>" method="post">
		 <input type="hidden" name="pageNo" id="pageNo" value="${result.notice.pageNo}" />
		 <input type="hidden" name="searchTitle" id="searchTitle" value="${result.notice.searchTitle}" />
		 <input type="hidden" name="pageLength" id="pageLength" value="${result.notice.pageLength}" />
	 </form>
	 
     <form id="mForm" name="mForm" action="<c:url value='/notice/list'/>" method="post">
     <input type="hidden" name="pageNo" id="pageNo" value="${result.notice.pageNo}" />
	<div style="margin:0px auto;">
			<div style="display: flex; margin:0px auto; width:70%; justify-align: center">
				<label>건수: </label>
				<select name="pageLength" id="pageLength" >
					<option value="10" ${result.notice.pageLength == 10 ? 'selected="selected"' : ''} >10건</option>
					<option value="20" ${result.notice.pageLength == 20 ? 'selected="selected"' : ''} >20건</option>
					<option value="50" ${result.notice.pageLength == 50 ? 'selected="selected"' : ''} >50건</option>
					<option value="100" ${result.notice.pageLength == 100 ? 'selected="selected"' : ''} >100건</option>
				</select>
				
				<label>제목 : </label>
				<input type="text" name="searchTitle" id="searchTitle" value="${result.notice.searchTitle}" style="flex:1">
				<input type="submit" value="검색"/>
			</div>
		</div>	

		<button id="noticeInsertButton" type="button">등록</button>

    <table border="1" class="notice-list-table">
	  <tr id="noticeItem" style="display:none;" >
            <td id="noticeid"></td>
            <td id="title"></td>
            <td id="email"></td>
            <td id="reg_date"></td>
            <td id="view_count"></td>
         </tr>  
    
        <tr>

            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
    <tbody id="noticeList">
		<c:forEach var="notice" items="${result.list}">
		    <tr class="notice-list-tr">
		        <td>${notice.noticeid}</td>
		        <td onclick="dialogDetail(${notice.noticeid})">${notice.title}</td>
		        <td>${notice.email}</td>
		        <td>${notice.reg_date}</td>
		        <td>${notice.view_count}</td>
		    </tr>
		</c:forEach>
     </tbody>
    </table>
    </form>
    <br>

<div style="text-align: center;margin-top:20px;">
      	<c:if test="${result.notice.navStart != 1}">
      		<a href="javascript:jsPageNo(${result.notice.navStart-1})" style="padding: 10px;border: 1px solid red;"> &lt; </a> 
      	</c:if>
      	<c:forEach var="item" begin="${result.notice.navStart}" end="${result.notice.navEnd}">
      		<c:choose>
      			<c:when test="${result.notice.pageNo != item }">
      				<a href="javascript:jsPageNo(${item})" style="padding: 10px;border: 1px solid red;">${item}</a>  
      			</c:when>
      			<c:otherwise>
      				<strong style="font-size:2rem">${item}</strong>   
      			</c:otherwise>
      		</c:choose>
      	</c:forEach>
      	<c:if test="${result.notice.navEnd != result.notice.totalPageSize}">
      		<a href="javascript:jsPageNo(${result.notice.navEnd+1})" style="padding: 10px;border: 1px solid red;"> &gt; </a> 
      	</c:if>	
</div>

		<div id="insertNotice" title="게시판 등록">
	        <div id="insertContent">
		        <input type="text" id="inTitle" placeholder="제목"><br>
		        <textarea id="inContents" placeholder="내용"></textarea><br>
	      	 	 <label for="fixedYN">고정 여부:</label>
	        <input type="radio" id="fixedYN" name="fixedYN" value="Y">예
	        <input type="radio" id="fixedYN" name="fixedYN" value="N">아니오
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

	<div id="updateNotice">
	  <h1>공지사항 수정</h1>
	    <form id="updateNoticeForm" name="updateNoticeForm" method="post">

			<input type="text" name="title" id="updateNoticeTitle" placeholder="제목" class="text_title" value="${notice.title}"><br>
		    <textarea name="contents" id="updateNoticeContents" placeholder="내용" class="notice_ct">${notice.contents}</textarea>
		    <p><input type="hidden" id="email" name="updateNoticeEmail" value="${loginMember.email}"  readonly></p>
		    <span class="radio-group">
	    	상단고정 여부　      
	        <label><input type="radio" name="fixed_yn" class="updateFixed_yn" id="updateFixed_yn" value="Y" checked>Y</label>
	        <label><input type="radio" name="fixed_yn" class="updateFixed_yn" id="updateFixed_yn" value="N">N</label>
	    	</span>
	   	</form>
	</div>
	
	<div id="deleteNotice" title="게시물 삭제">
	    <p>이 게시물을 삭제하시겠습니까?</p><br>
	</div>

<script type="text/javascript">
/*--------------------------------------------버튼----------------------------------------------*/
//공지사항 등록
$("#noticeInsertButton").on("click", function() {
	insertNotice.dialog("open");
});
/*--------------------------------------------대화상자----------------------------------------------*/
/* 공지사항 상세 */
$(document).ready(function() {
	$("#detail").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        "수정 하기": function() {
	        	
	        	const title = $("#title2").text();
	        	const contents = $("#contents2").text();
	        	const noticeid = $("#noticeid2").text();
	        	
	        	$("#updateNoticeTitle").val(title);
	        	$("#updateNoticeContents").val(contents);
	        	console.log(title);
	        	console.log(contents);
	        	console.log(noticeid);
	        	
	        	updateNotice.dialog("open");
	            
	        },
	        "삭제": function() {
	            const noticeid = $("#noticeid2").text();
	            
	            deleteNotice.dialog("open");
	        },
	        Close: function() {
	            $(this).dialog("close");
	        }
	    }
	});
});

/* 공지사항 수정 */
const updateNotice = $("#updateNotice").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        "수정":dialogUpdate,
	        Close : function() {
	            $(this).dialog("close");
	        }
	    }
	});
	
/* 공지사항 등록 */
const insertNotice = $("#insertNotice").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        "등록":dialogInsert,
	        Close : function() {
	            $(this).dialog("close");
	        }
	    }
	});
/* 공지사항 삭제 */
const deleteNotice = $("#deleteNotice").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        "삭제":dialogDelete,
	        Close : function() {
	            $(this).dialog("close");
	        }
	    }
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

//수정하기
function dialogUpdate() {
	
	const noticeid3 = $("#noticeid2").text();
    const title3 = $("#updateNoticeTitle").val();
    const contents3 = $("#updateNoticeContents").val();
    const email3 = $("#updateNoticeEmail").val(); 
    const fixed_yn3 = $("input[name='fixed_yn']:checked").val(); // 수정: # 추가
    
    const param = {
    	noticeid: noticeid3,
        title: title3,
        contents: contents3,
        email: email3,
        fixed_yn: fixed_yn3
    };
    
    
    fetch("<c:url value='/notice/update'/>", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(param)
    })
    .then(response => response.json())
    .then(json => {
            alert(json.message);
            updateNotice.dialog("close");
            
            // 수정이 성공한 경우에 업데이트된 내용을 화면에 반영
            $("#title2").text(title3);
            $("#contents2").text(contents3);
            $("#fixed_yn2").prop("checked", fixed_yn3 === "Y");
    });
    
}

//등록하기
function dialogInsert() {

    const checkFixed = document.querySelector('input[name="fixedYN"]:checked'); // 수정: # 추가
    const fixedValue = checkFixed ? checkFixed.value: '';
    const insetEmail = "${loginMember.email}";
    
    const param = {
    	email: insetEmail,
        title: inTitle.value,
        contents: inContents.value,
        fixed_yn: fixedValue
    };
    
    fetch("<c:url value='/notice/insert'/>", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(param)
    })
    .then(response => response.json())
    .then(json => {
            alert(json.message);
            insertNotice.dialog("close");

    });
    
}

//삭제하기
function dialogDelete() {

    const deleteNoticeid = $("#noticeid2").text();
    
    const param = {
		noticeid : deleteNoticeid
    };
    
    fetch("<c:url value='/notice/delete'/>", {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify(param)
    })
    .then(response => response.json())
    .then(json => {
            alert(json.message);
            deleteNotice.dialog("close");

    });
    
}

/*--------------------------------------------기타------------------------------------------------------*/
function jsPageNo(pageNo){
	document.querySelector("#pageForm > #pageNo").value = pageNo;
	document.querySelector("#pageForm").submit();
}
</script>
</body>
</html>