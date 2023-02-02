var submitFlag = false;

$(function () {
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