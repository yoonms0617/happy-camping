/**
 * 
 */
/*
$(function(){
	qnaInsert()
})
*/

function qnaInsert(ino)
{
		alert(1)
		
	   alert("ino="+ino)
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
	alert(2);
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
	alert("취소");
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
	alert("디테일");
	$.ajax({
		type: 'post',
		url: "item_qna_detail.do",
		data:{'qano':qano},
		success: function(response)	{
			$('#print').html(response)
		}
		
	})
}

let i = 0;
let u = 0;

function qnaDel11(qano){
		let pwd = $('#del_pwd').val();
		if(pwd.trim()===""){
			$('#del_pwd').focus()
			return;
		}
		let no = $('#del').attr("data-no");
		$.ajax({
			type : 'post',
			url : 'item_qna_delete.do',
			data : {"no":no, "pwd":pwd},
			success : function(result){
				let res = result.trim();
				if(res === 'yes'){
					location.href="item_qna_list.do"
				}else{
					alert("비밀번호가 틀립니다.\n다시 입력해주세요")
					$('#del_pwd').val("");
					$('#del_pwd').focus();
				}
			}
		})
}

function qnaDel()
{
	alert(삭제);
	let formData=$('#del_frm').serialize();
	$.ajax({
		type: 'post',
		url: "item_qna_delete.do",
		data:formData,
		success: function(response){
			$('#print').html(response)
		}
	})	
}





