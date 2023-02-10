
	
function qnaInsert(ino)
{
		
	   $.ajax({
		type: 'post',
		url:'item_qna_insert.do',
		data:{'ino':ino},
		success: function(response){
			 $('#print').html(response)
		}
	})
}
function qnaInsertOk()
{
	let formData=$('#qnaFrm').serialize();
	$.ajax({
		type: 'post',
		url: "item_qna_insert_ok.do",
		data:formData,
		success: function(response){
			$('#print').html(response)
		}
	})	
}
function qnaList(ino)
{
	$.ajax({
		type: 'post',
		url: "item_qna_list.do",
		data:{'ino':ino},
		success: function(response){
			$('#print').html(response)
		}
	})

}

function qnaDetail(qano){
	$.ajax({
		type: 'post',
		url: "item_qna_detail.do",
		data:{'qano':qano},
		success: function(response)	{
			$('#print').html(response)
		}
		
	})
}



function qnaUpdate(qano){
	$.ajax({
	type: 'post',
	url: "item_qna_update.do",
	data:{'qano':qano},
	success: function(response){
		$('#print').html(response)
	}		
	})

}

function qnaUpdateOk()
{

	let formData=$('#qnaUpFrm').serialize();
	$.ajax({
		type: 'post',
		url: "item_qna_update_ok.do",
		data:formData,
		success : function(response){
				let res = response.trim();
				if(res === 'no'){ // 비밀번호가 틀려서 수정이 안되는 경우
					alert("비밀번호를 다시 입력해 주세요.")
					$('#password').val("");
				  	$('#password').focus();
				  	
				}else{ // 비밀번호가 맞는 경우
					alert("수정되었습니다.")
				}
				$('#print').html(response)
			}
	})	
}

function qnaReinsert(qano){
	let formData=$('#qnaReins').serialize();
	$.ajax({
		type: 'post',
		url: "item_qna_reply_insert.do",
		data:formData,
		success : function(response){
				
				$('#print').html(response)
			}
	})	
}










