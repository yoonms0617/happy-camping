var submitFlag = false;

$(function () {
	// id 저장 관련 내용
	var key = getCookie("key");
	    $("#userid").val(key); 
	     
	    // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
	    if($("#userid").val() != ""){ 
	        $("#saveId").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
	    }
	     
	    $("#saveId").change(function(){ // 체크박스에 변화가 있다면,
	        if($("#saveId").is(":checked")){ // ID 저장하기 체크했을 때,
	            setCookie("key", $("#userid").val(), 7); // 7일 동안 쿠키 보관
	        }else{ // ID 저장하기 체크 해제 시,
	            deleteCookie("key");
	        }
	    });
	     
	    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
	    $("#userid").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
	        if($("#saveId").is(":checked")){ // ID 저장하기를 체크한 상태라면,
	            setCookie("key", $("#userid").val(), 7); // 7일 동안 쿠키 보관
	        }
	    });


	
	// 로그인 관련 내용
    $('#login-btn').click(function () {
        const userid = $('#userid').val();
        const password = $('#password').val();

        if (userid === '') {
            $('#err-msg').text('아이디를 입력해 주세요.');
            $('#userid').focus();
            return;
        }
        if (password === '') {
            $('#err-msg').text('비밀번호를 입력해 주세요.');
            $('#password').focus();
            return;
        }
  
        $.ajax({
		  type:'post',
		  url: '/member/login.do',
		  data :{"userid":userid,"password":password},
		  
		  success:function(result)
		  {
			  let res=result.trim();
			  var oMsg=$('#err-msg')
			  if(res=='NOID')
			  {
				showErrorMsg(oMsg,"아이디가 존재하지 않습니다");     
				return;      	 
			  }
			  else if(res=='NOPWD')
			  {
				 showErrorMsg(oMsg,"비밀번호가 틀립니다");     
				return;  
			  }
			  else 
			  {
				location.href="../main.do";
			  }
			}
		})  
		
    });
    
});

function showErrorMsg(oMsg, message) {
    oMsg.attr('class', 'text-danger');
    oMsg.html(message);
    oMsg.show();
}
//////////////////////////////////////////////
	// 쿠키 저장하기 
	// setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
		
		
	}

	// 쿠키 삭제
	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}
     
	// 쿠키 가져오기
	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) { // 쿠키가 존재하면
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정 
				end = cookieData.length;
                console.log("end위치  : " + end);
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}