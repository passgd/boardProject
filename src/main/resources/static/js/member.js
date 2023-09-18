// 회원가입 버튼 클릭 시 다이얼로그 열기
$(document).ready(function () {
    var insertDialogDlg = $("#insertDialog");

    insertDialogDlg.dialog({
        autoOpen: false,
        modal: true,
        width: 800,
        height: 500,
        buttons: {
            "회원가입": function(){
                // 회원가입 버튼을 눌렀을 때 실행할 코드 추가
                insertDialogDlg.dialog("close");
            },
            "닫기": function () {
                $(this).dialog("close");
            }
        }
    });

    $("#insertButton").on("click", function(){
        insertDialogDlg.dialog("open");
    });
});

// 중복아이디 검사
 const checkEmail = (email, url) => {
	
	const param = {email: email.value};

	fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
		},
		body: JSON.stringify(param),
	})
	.then((response) => response.json())
	.then((json) => {
		alert(json.message);
		if(json.status){
			email.value = "";
			email.focus();
			existCheckEmail = false;
		}else{
			existCheckEmail = true;
		}
	});
}

// 회원가입 폼 제출 시
const insertDialog = (url) =>{
$("#mForm").on("submit", function (e) {
	if(!existCheckEmail){
		alert("아이디 중복을 확인 해주세요");
		existEmail.focus();
		 e.preventDefault();
		return;
	}
	const selectedGender = document.querySelector('input[name="gender"]:checked');
	const genderValue = selectedGender ? selectedGender.value : '';
	
	const param = {
			email: email.value,
			pwd: pwd.value,
			name: uname.value,
			birthday: birthday.value,
			phone: phone.value,
			gender: genderValue,
			address: address.value,
	};
	fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json; charset=UTF-8",
		},
		body: JSON.stringify(param),
	})
	.then((response) => response.json())
	.then((json) => {
		alert(json.message);
		insertDialogDlg.dialog("close");
		});
 	  });
    };
    
//로그아웃
const jsLogout = (url) =>{
    fetch(url, {
		method: "GET",
	})
	.then((response) => response.json())
	.then((json) =>{
		alert(json.message);
      });
}
