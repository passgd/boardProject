<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 게시판</title>
</head>
<body>
<h1>전체 게시판 목록</h1>


	 <form name="pageForm" id="pageForm" action="<c:url value='/board/list'/>" method="post">
		 <input type="hidden" name="pageNo" id="pageNo" value="${result.board.pageNo}" />
		 <input type="hidden" name="searchTitle" id="searchTitle" value="${result.board.searchTitle}" />
		 <input type="hidden" name="pageLength" id="pageLength" value="${result.board.pageLength}" />
	 </form>
	 
     <form id="mForm" name="mForm" action="<c:url value='/board/list'/>" method="post">
     <input type="hidden" name="pageNo" id="pageNo" value="${result.board.pageNo}" />
	<div style="margin:0px auto;">
			<div style="display: flex; margin:0px auto; width:70%; justify-align: center">
				<label>건수: </label>
				<select name="pageLength" id="pageLength" >
					<option value="10" ${result.board.pageLength == 10 ? 'selected="selected"' : ''} >10건</option>
					<option value="20" ${result.board.pageLength == 20 ? 'selected="selected"' : ''} >20건</option>
					<option value="50" ${result.board.pageLength == 50 ? 'selected="selected"' : ''} >50건</option>
					<option value="100" ${result.board.pageLength == 100 ? 'selected="selected"' : ''} >100건</option>
				</select>
				
				<label>제목 : </label>
				<input type="text" name="searchTitle" id="searchTitle" value="${result.board.searchTitle}" style="flex:1">
				<input type="submit" value="검색"/>
			</div>
		</div>	

		<label>
			<input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)">
			전체 선택
		</label>
		<div class="buttons">
			<button class="button" type="button" id="deleteBtn">선택한 항목 삭제</button>
		</div>
		
		<button id="boardInsertButton" type="button">등록</button>

    <table border="1" class="board-list-table">
	  <tr id="boardItem" style="display:none;" >
	  		<td><input id="checkItem" type="checkbox" class="item" /></td>
            <td id="boardid"></td>
            <td id="title"></td>
            <td id="email"></td>
            <td id="reg_date"></td>
            <td id="view_count"></td>
         </tr>  
    
        <tr>
			<th>체크</th>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
    <tbody id="boardList">
		<c:forEach var="board" items="${result.list}">
		    <tr class="board-list-tr">
		    <td>
		    	<input type="checkbox" class="item" name="item" onclick="checkSelectAll()" value="${board.boardid}"  data-pid="${board.pid}">
		    </td>
		        <td>${board.boardid}</td>
		        <td style="text-align: left">
	        		<span style="padding-left:${(board.level-1)*20}px"></span>
	        		${board.level != 1 ? "[답변] " : "" }
		        <a onclick="boardDetail(${board.boardid})">${board.title}</a>
		        </td>
		        <td>${board.email}</td>
		        <td>${board.reg_date}</td>
		        <td>${board.view_count}</td>
		    </tr>
		</c:forEach>
     </tbody>
    </table>
    </form>
    <br>

<div style="text-align: center;margin-top:20px;">
      	<c:if test="${result.board.navStart != 1}">
      		<a href="javascript:jsPageNo(${result.board.navStart-1})" style="padding: 10px;border: 1px solid red;"> &lt; </a> 
      	</c:if>
      	<c:forEach var="item" begin="${result.board.navStart}" end="${result.board.navEnd}">
      		<c:choose>
      			<c:when test="${result.board.pageNo != item }">
      				<a href="javascript:jsPageNo(${item})" style="padding: 10px;border: 1px solid red;">${item}</a>  
      			</c:when>
      			<c:otherwise>
      				<strong style="font-size:2rem">${item}</strong>   
      			</c:otherwise>
      		</c:choose>
      	</c:forEach>
      	<c:if test="${result.board.navEnd != result.board.totalPageSize}">
      		<a href="javascript:jsPageNo(${result.board.navEnd+1})" style="padding: 10px;border: 1px solid red;"> &gt; </a> 
      	</c:if>	
</div>

	<div id="insertBoard" title="게시판 등록">
        <div id="insertContent">
	        <input type="text" id="inTitle" placeholder="제목"><br>
	        <textarea id="inContents" placeholder="내용"></textarea><br>
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
	
	<!-- 댓글 jsp -->
	<div id="commentBoard">
        <div id="insertContent">
	        <input type="text" id="inTitle" placeholder="제목"><br>
	        <textarea id="inContents" placeholder="내용"></textarea><br>
      	</div>
      </div>
</div> 

<div id="updateBoard">
  <h1>공지사항 수정</h1>
    <form id="updateBoardForm" name="updateBoardForm" method="post">
		제목:<input type="text" name="title" id="updateBoardTitle" placeholder="제목" class="text_title" value="${board.title}"><br>
	    <br>내용:<textarea name="contents" id="updateBoardContents" placeholder="내용" class="board_ct">${board.contents}</textarea>
	    <p><input type="hidden" id="updateBoardEmail" name="updateBoardEmail" value="${loginMember.email}"  readonly></p>
   	</form>
</div>

	<div id="deleteBoard" title="게시물 삭제">
	    <p>이 게시물을 삭제하시겠습니까?</p><br>
	</div>
	
	<div id="replyBoard" title="게시판 답변">
	        <div id="replyContent">
		        제목: <input type="text" id="replyTitle" placeholder="제목"><br>
		        내용: <textarea id="replyContents" placeholder="내용"></textarea><br>
	      	</div>
      </div>
	
<script type="text/javascript">
/*--------------------------------------------버튼----------------------------------------------*/
//게시판 등록
$("#boardInsertButton").on("click", function() {
	insertBoard.dialog("open");
});
/*--------------------------------------------대화상자----------------------------------------------*/
/* 게시판 상세 */
$(document).ready(function() {
	$("#detailBoard").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	    	"답변 하기": function(){
	    		replyBoard.dialog("open");
	    	},
	        	 "수정 하기": function() {
	 	        	
	 	        	const title = $("#boardTitle").text();
	 	        	const contents = $("#boardContents").text();
	 	        	const boardid = $("#boardId").text();
	 	        	
	 	        	$("#updateBoardTitle").val(title);
	 	        	$("#updateBoardContents").val(contents);
	 	        	console.log(title);
	 	        	console.log(contents);
	 	        	console.log(boardid);
	 	        	
	 	        	updateBoard.dialog("open");
	 	            
	 	        },
	 	       "삭제": function() {
		            const boardid = $("#boardId").text();
		            
		            deleteBoard.dialog("open");
		        },
		        Close: function() {
	            $(this).dialog("close");
	        }
	    }
	});
});

/* 게시판 등록 */
const insertBoard = $("#insertBoard").dialog({
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
	
/* 게시판 수정 */
const updateBoard = $("#updateBoard").dialog({
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

/* 게시판 삭제 */
const deleteBoard = $("#deleteBoard").dialog({
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
	
/* 게시판 답변 */
const replyBoard = $("#replyBoard").dialog({
	    autoOpen: false,
	    modal: true,
	    width: 800,
	    height: 500,
	    buttons: {
	        "등록":dialogreply,
	        Close : function() {
	            $(this).dialog("close");
	        }
	    }
	});
/*--------------------------------------------실행부----------------------------------------------*/
/* 글 상세 패치 코드 */
function boardDetail(boardid) {
    const boardId = $("#boardId");
    const boardTitle = $("#boardTitle");
    const boardContents = $("#boardContents");
    const boardEmail = $("#boardEmail");
    const boardReg_date = $("#boardReg_date");
    const boardView_count = $("#boardView_count");

    const param = {
        boardid: boardid
    };

    $.ajax({
        type: "POST",
        url: "<c:url value='/board/view'/>",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(param),
        dataType: "json",
        success: function(json) {
            boardId.text(json.boardid);
            boardTitle.text(json.title);
            boardContents.text(json.contents);
            boardEmail.text(json.email);
            boardReg_date.text(json.reg_date);
            boardView_count.text(json.view_count);
            $("#detailBoard").dialog("open");
        },
        error: function(xhr, status, error) {
            console.error("AJAX 요청 중 오류 발생: " + error);
        }
    });
	
	return false;
	
}

//등록하기
function dialogInsert() {

    const insetEmail = "${loginMember.email}";
    
    const param = {
    	email: insetEmail,
        title: inTitle.value,
        contents: inContents.value,

    };
    
    $.ajax({
        type: "POST",
        url: "<c:url value='/board/insert'/>",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(param),
        dataType: "json",
        success: function(json) {
            alert(json.message);
            insertBoard.dialog("close");
        },
    });
}

//수정하기
function dialogUpdate() {
	
	const boardid2 = $("#boardId").text();
    const title2 = $("#updateBoardTitle").val();
    const contents2 = $("#updateBoardContents").val();
    const email2 = $("#updateBoardEmail").val(); 
   
    const param = {
   		boardid: boardid2,
        title: title2,
        contents: contents2,
        email: email2,
    };
    
    
    $.ajax({
        type: "POST",
        url: "<c:url value='/board/update'/>",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(param),
        dataType: "json",
        success: function(json) {
            alert(json.message);
            updateBoard.dialog("close");

            // 수정이 성공한 경우에 업데이트된 내용을 화면에 반영
            $("#boardTitle").text(title2);
            $("#boardContents").text(contents2);
        },
    });
}

//삭제하기
function dialogDelete() {

    const deleteBoardid = $("#boardId").text();
    
    const param = {
		boardid : deleteBoardid
    };
    
    $.ajax({
        type: "POST",
        url: "<c:url value='/board/delete'/>",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(param),
        dataType: "json",
        success: function(json) {
            alert(json.message);
            deleteBoard.dialog("close");
        },
    });
}


//체크 삭제
document.querySelector("#deleteBtn").addEventListener("click", async (e) => {
    e.preventDefault();

    // 게시판 목록 테이블
    const boardList = document.querySelector("#boardList");
    let ids = [];

    // 선택한 체크 박스 항목들
    const items = document.querySelectorAll(".item:checked");

    console.log("Selected items: ", items);

    if (items.length === 0) {
        alert("삭제할 항목을 선택하세요~~");
        return false;
    }

    if (!confirm("삭제 하시겠습니까?")) {
        return false;
    }
    
 // 선택한 체크박스 항목들의 값을 배열에 추가하고 행 숨김 처리
    items.forEach(item => {
        ids.push(item.value);
        const row = item.closest('tr');
        row.classList.add('hide');
    });

    // 서버로 전송할 파라미터
    const param = {
        ids: ids,
        boardid: document.querySelector("#boardList tr:last-child td:first-child input[type='checkbox']").value
    };

    async function deletesBoard(boardid) {
        try {
            const response = await fetch("<c:url value='/board/checkDelete'/>", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json; charset=UTF-8",
                },
                body: JSON.stringify(param),
            });

            const json = await response.json();

            if (json.status) {
            	alert(json.message);
                // 부모 및 자식 항목 삭제
                const itemsToDelete = [boardid, ...Array.from(items).filter(item => item.dataset.pid === boardid).map(item => item.value)];
                for (const itemToDelete of itemsToDelete) {
                	const item = document.querySelector(`.item[value="${itemToDelete}"]`);
                    if (item) {
                        const row = item.closest('tr');
                        boardList.removeChild(row);
                    }
                }

                // 서버에서 삭제된 항목들을 다시 표시
                const boardItem = document.querySelector("#boardItem");
                for (const board of json.boardList) {
                	const existingBoardItem = document.querySelector(`#boardid[value="${board.boardid}"]`);
                	
                    // 삭제되지 않은 항목만 표시
                    if (board.delete_yn === 'N') {
                        const newBoardItem = boardItem.cloneNode(true);

                        newBoardItem.querySelector("#boardid").innerText = board.boardid;
                        newBoardItem.querySelector("#title").innerText = board.title;
                        newBoardItem.querySelector("#email").innerText = board.email;
                        newBoardItem.querySelector("#reg_date").innerText = board.reg_date;
                        newBoardItem.querySelector("#view_count").innerText = board.view_count;

                        newBoardItem.style.display = "";
                        boardList.appendChild(newBoardItem);
                    }
                }
            } else {
                alert(json.message);
            }
        } catch (error) {
            console.error("Error:", error);
            alert("서버와 통신 중 오류가 발생했습니다.");
        }
    }

    for (const item of items) {
        ids.push(item.value);
        const row = item.closest('tr');
        row.classList.add('hide');
        deletesBoard(item.value);
    }

    return false;
});


//답변 등록하기
function dialogreply() {

    const replyEmail = "${loginMember.email}";
    const boardid3 = $("#boardId").text();
    
    const param = {
    	boardid: boardid3,
    	email: replyEmail,
        title: replyTitle.value,
        contents: replyContents.value,

    };
    
    $.ajax({
        type: "POST",
        url: "<c:url value='/board/reply'/>",
        contentType: "application/json; charset=UTF-8",
        data: JSON.stringify(param),
        dataType: "json",
        success: function(json) {
            alert(json.message);
            replyBoard.dialog("close");
            
            //이메일 발송 요청
            sendEmail(replyEmail);
        },
    });
}
/*--------------------------------------------기타------------------------------------------------------*/
function jsPageNo(pageNo){
	document.querySelector("#pageForm > #pageNo").value = pageNo;
	document.querySelector("#pageForm").submit();
}

//체크박스 전체 선택
function selectAll(selectAll){
	const checkboxes
	=	document.querySelectorAll(".item");
	
	checkboxes.forEach((checkbox) => {
		checkbox.checked = selectAll.checked;
	})
	$("#checkItem").prop("checked", false);
}

//체크박스 하나 취소 전체 선택 해제
function checkSelectAll(){
	//전체 체크 박스
	const checkboxes = 
		document.querySelectorAll('input[name="item"]');
	//선택 체크박스
	const checked = 
		document.querySelectorAll('input[name="itme"]:checked');
	//select all 체크박스
	const selectAll = 
		document.querySelector('input[name="selectall"]');
	
	//체크박스 하나라도 선택 해제되면 전체 선택 해제
	if(checkboxes.length === checked.length){
		selectAll.checked = true;
	}else{
		selectAll.checked = false;
	}
}
</script>
</body>
</html>