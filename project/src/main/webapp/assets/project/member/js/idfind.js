
 $( function() {
    $( "#tabs" ).tabs();
    // 아이디 찾기 => 전화번호 
	$('#idfind-btn1').click(function(){
		   let pname =$('#pname').val();
	       let tel=$('#tel').val();
	       if(pname.trim()=="")
	       {
	          $('#pname').focus();
	          return
	       }
	       
	       if(tel.trim()=="")
	       {
	          $('#tel').focus();
	          return
	       }
	       
	       $.ajax({
			  type:'post',
			  url: '/member/idtelfind.do',
			  data :{"name":pname,"tel":tel},
			  
			  success:function(response)
			  {
				  let res=response.trim();
				  
				  var oMsg=$('#err-msg1')
				  if(res=='NO')
				  {
					showErrorMsg(oMsg,"이름 또는 휴대번호를 다시 확인해주세요");     
					return; 
				  }
				  else 
				  {
					showSuccessMsg(oMsg,res);
				  }
				}
			})  
	    });	
    // 아이디 찾기 => 이메일 
	$('#idfind-btn2').click(function(){
		   let ename =$('#ename').val();
	       let email=$('#email').val();
	       console.log("name2"+ename);
	       console.log("email2"+email);
	       if(ename.trim()=="")
	       {
	          $('#ename').focus();
	          return
	       }
	       
	       if(email.trim()=="")
	       {
	          $('#email').focus();
	          return
	       }
	       
	       $.ajax({
			  type:'post',
			  url: '/member/ideamilfind.do',
			  data :{"name":ename,"email":email},
			  
			  success:function(response)
			  {
				  let res=response.trim();
				  console.log("res2"+res)
				  
				  var oMsg=$('#err-msg2')
				  if(res=='NO')
				  {
					console.log("no입니다")
					showErrorMsg(oMsg,"이름 또는 휴대번호를 다시 확인해주세요");     
					return; 
				  }
				  else 
				  {console.log("yes입니다")
					showSuccessMsg(oMsg,res);
					
				  }
				}
			})  
	    });
})

function showErrorMsg(oMsg,message){
 oMsg.attr('class', 'text-danger');
 oMsg.html(message);
 oMsg.show();
}
function showSuccessMsg(oMsg,message){
 oMsg.attr('class', 'text-success');
 oMsg.html(message);
 oMsg.show();
} 