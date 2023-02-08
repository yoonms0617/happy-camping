<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><!-------------------- 1 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--------------------  2-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-------------------- 3 -->  
<jsp:include page="/happy/fragments/head.jsp" flush="false" /> <!--------------------4  -->
<jsp:include page="/happy/fragments/header.jsp" flush="false" />  <!-------------------- 5 -->



<link rel="stylesheet" href="http://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"><!--------------------  6-->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-------------------- 7 -->



<script type="text/javascript">
$( function() {
    $( "#tabs" ).tabs();
})
</script>


<body>
<!----------------------------------상품상세보기 상단 ---------------------------- -->
<div class="album py-3">
  <div class="container" style="max-width: 1200px">
    <div  class="mb-4">
       <h4>상품 상세보기</h4>
    </div>
  <div style="height: 5px"></div>
  <div class="detailArea" >
    <div class="pe-sm-5"style="width:50%;  float:left "> 
      <img src="${vo.image }" style="width:100%">
    </div>
    <div class="infoArea">
    <div style="height: 50px"></div>
      <table>
          <tr>
            <p class="fs-4">${vo.name }</p>
          </tr>
          <c:if test="${vo.sale!=0 }">
          <tr>
             <th class="text-danger pe-5"  rowspan="2"><font size="6">${vo.sale }%</font></th>
             <th class="fs-4 pe-5"><p  style="color: gray">기존 판매가</p></th> 
             <th class="fs-4 pe-5"><p >판매가</p></th>
          </tr>
          <tr> <!--   style="border-bottom: 1px solid black" -->
           <td class="text-decoration-line-through">
              <p class="fs-5"style="color: gray">
              <fmt:formatNumber value="${vo.price}" pattern="#,###"/>원</p>
            </td>
            <td class="fs-5">
              <p><fmt:formatNumber value="${vo.price * ((100 - vo.sale) * 0.01)}" pattern="#,###"/>원</p>
            </td>
           </tr>
          </c:if>
          
       
          
          <c:if test="${vo.sale==0 }">
           <tr class="pt-sm-5 ">
	           <th>판매가&nbsp;: </th>
           	   <td class="pt-sm-2"><fmt:formatNumber value="${vo.price}" pattern="#,###"/>원</td>
          </tr>
          </c:if>
          
          <tr class="pt-sm-5 ">
           <th>브랜드&nbsp;: </th>
           <td class="pt-sm-2">${vo.brand }</td>
          </tr>
          <tr>
            <th>적립금&nbsp;:</th>
            <td class="pt-sm-3"><p><fmt:formatNumber value="${vo.price *0.005}" pattern="#,###"/>원</p></td>
          </tr>
          <tr>
            <th class="pe-2"rowspan="2">배송비&nbsp;:</th>
            <td>
              <select>
                <option selected>주문시 결제(선결제)&nbsp;</option>
                <option> 수령시 결제(착불)</option>
              </select>
            </td>
          </tr>
          <tr  >
           <th>3,000원 &nbsp; (50,000원 이상 구매 시 무료)</th>
          </tr>
          <tr style="height:30px"></tr>
          <tr style="border-bottom: 1px solid black"></tr>
          <tr style="height:30px"></tr>
          <tr>
            <th colspan="3">
                <div class="d-flex justify-content-center">
                    <div>
                        <button type="button"style="height:50px ;width:160px;" class="btn btn-sm btn-dark me-1">구매하기</button>
                    </div>
                    <div>
                        <button type="button"style="height:50px;width:160px;" class="btn btn-sm btn-outline-secondary me-1">장바구니</button>
                    </div>
                    <div id="like-btn">
<%--                        <button type="button"style="height:50px;width:160px;" class="btn btn-sm btn-outline-secondary">좋아요</button>--%>
                    </div>
                </div>
            </th>
          </tr>
      </table>
    </div>
  </div>
  <div style="clear: both"></div>
  
  <!----------------------------------상품상세보기 하단 메뉴 ---------------------------- -->
  <div style="height:30px"></div>
  <div style="border-bottom: 0.2px solid black"></div>
  <div id="tabs">
				<ul>
					<li><a href="#tabs-1" style="height:50px ;width:160px;">상품 상세 정보</a></li>
			        <li><a href="#tabs-2">상품 구매 안내</a></li> 
			        <li><a href="#tabs-3">상품 사용 후기</a></li> 
			        <li><a href="#tabs-4">상품 Q&A</a></li>
				</ul>
				<!------ 상품 상세정보 ------>
				<div id="tabs-1">
					<div style="height:30px"></div>
					<c:forEach var="descript" items="${descript }">
						<img src="${descript }" style="width:100%">
					</c:forEach>
<!-- 					 <div style=""> -->
<%-- 				       <img src="${vo.description }" style="width:100%"></div> --%>
<!-- 				     </div> -->

				</div>
				<!------ 상품 구매안내 ------>
				<div id="tabs-2" > <!-- 상품 구매 안내 -->
			       <div style="height: 50px"></div>
			        <ul>
			        <li style="font-size:30"><b>상품 결제 정보</b></li>
			        <p>상품의 결제는 신용카드 / 현금 / 적립금 / 쿠폰으로 결제가 가능합니다.<br><br>
			          고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도 있습니다.<br>
			         확인과정에서 도난 카드의 사용이나 타인 명의의 주문등 정상적인 주문이 아니라고 판단될 경우 임의로 주문을 보류 또는 취소할 수 있습니다.<br><br> 
			            무통장 구매시 상품 구매 대금은 신한은행 () 직접 입금해주시면 됩니다. (일부제품 무통장주문 불가)  <br>
			            주문시 입력한 입금자명과 실제입금자의 성명이 반드시 일치하여야 하며, 2일 이내로 입금을 하셔야 하며 입금되지 않은 주문은 자동취소 됩니다.<br><br>
			            사용은 주문금액 총액 10만원 이상 구매시 부터 적립하신 금액 한도내에서 제한없이 결제가 가능합니다.(공지사항 참조)</p>
			         </ul>
			         <div style="height: 50px"></div>
			         <ul>
			       <li style="font-size:30"><b>배송정보</b></li>
			        <p>배송 안내 : 근무일 기준(월-금) 오후 2시 이전 주문 시 당일배송 (매장 재고 있는 제품에 한함)<br><br>
			            배송 방법 : 택배 (한진택배)<br><br>
			         배송 지역 : 전국지역<br><br>
			            배송 비용 : 3,000원 / 5만원 이상 구매 시 무료<br>
			           (제주 및 도서산간지역은 추가운임 청구)<br><br>
			           (부피가 큰 상품이나 여러개의 박스로 나누어 발송된 상품의 경우 교환/반품 시 추가비용이 청구될 수 있습니다)<br><br>
			           배송 기간 : 발송 후 1일 ~ 3일 (영업일기준)<br><br>
			           ※ 네이버페이 주문의 경우 도서, 산간, 오지, 일부지역(제주) 등은 배송비 추가를 위하여 연락드릴 수 있습니다.<br><br>
			           ※ 캠핑리스트는 오프라인매장과 동시에 판매중으로 예고없이 제품이 품절될 수 있습니다. 주문하신 제품이 품절 되었을 경우 미리 연락드리겠습니다.<br><br>
			       </p>
			         </ul>
			         <div style="height: 50px"></div>
			         <ul>
			       <li style="font-size:30"><b>교환 및 반품정보</b></li>
			        <p><b style="font-size: 20;color: gray">- 교환 및 반품이 가능한 경우</b><br><br>
			            <font style="color: red">제품을 개봉하지 않은 배송상품의 교환/반품 신청은 상품 수령 후 7일 이내</font> 요청 가능합니다.<br><br>
			         구매자의 단순변심에 의한 교환/반품에 따른 <font style="color: red">배송비(교환의 경우 왕복배송비)는 고객님께 부담</font>해주셔야 하며, 재판매가 가능한 상태의 제품이어야 합니다.<br><br>
			            무료배송 상품인 경우 반품시, 최초 배송료를 포함한 왕복 배송비가 부과될 수 있습니다.<br><br>
			           <font style="color: red"> 부피가 큰 상품이나 여러개의 박스로 나누어 발송된 상품의 경우 추가비용이 청구</font>될 수 있습니다.<br><br>
			           도서/산간지역 및 주문제작, 설치 상품 등의 경우, 기본 배송료 외에 반품시 추가 배송료가 발생될 수 있습니다.<br><br>
			         상품의 불량 및 오배송의 사유로 발생되는 교환/반품의 배송비용은 판매자가 부담합니다.<br><br>
			           ※ 텐트 및 기어류는 펼쳐보거나 설치를 하실 경우 단순변심으로 인한 교환 및 반품이 불가하오니 신중한 구매 부탁드립니다.<br><br>
			           ※ 텐트 및 기어류는 필드에서 사용하는 제품의 특성상 필드에서의 사용후에는 불량이 발견되더라도 교환 및 환불이 불가한 경우가 있습니다. 실내에서의 제품 검수 후 사용을 부탁드립니다.
			          
			          
			          <div style="height: 50px"></div>
			          <p><b style="font-size: 20;color: gray">-교환 및 반품이 불가능한 경우</b><br><br>
			        <font style="color: red">제품 포장을 개봉하였거나 포장이 훼손</font>되어 상품의 가치가 현저히 감소된 경우<br><br>
			        단순변심으로 상품 수령 후, <font style="color: red">교환/반품 가능기간 7일을 초과한 경우</font><br><br>
			       표시/광고와 다른 사실을 <font style="color: red">인지한 날로부터 30일을 초과한 경우</font><br><br>
			         반품 요청(상품 수령 후 3개월)기간이 지난 경우<br><br>
			         <font style="color: red">상품의 택(TAG) 제거/라벨 및 상품 훼손</font>으로 상품의 가치가 현저히 감소된 경우<br><br>
			         상품 및 구성품을 분실하였거나 취급 부주의로 인한 파손/고장/오염된 경우<br><br>
			         고객님의 사용, 시간경과, 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우 (예: 계절상품, 식품, 화장품 등)<br><br>
			         주문 확인 후 상품제작이 들어가는 주문제작상품 또는 고객님의 요청에 의해 상품사양이 변경된 경우(제작이 시작된 이후에는 취소 및 교환/반품이 불가합니다)<br><br>
			         제품을 개봉하여 장착한 이후 단순변심의 경우 복제가 가능한 상품의 포장 등을 훼손한 경우<br><br>
			         상품의 시리얼 넘버 유출로 내장된 소프트웨어의 가치가 감소한 경우<br><br>
			         ※ 고객님의 마음이 바뀌어 교환, 반품을 하실 경우 상품반송 비용은 고객님께서 부담하셔야 합니다.(색상 교환, 사이즈 교환 등 포함)<br><br>
			         ※ 부피가 큰 상품이나 여러개의 박스로 나누어 발송되는 상품의 경우 별도의 배송비가 책정되며, 교환/반품 시 추가비용이 청구될 수 있습니다.</p>
			       </p>
			      </ul>
			   </div>

				
				<!--------------------------------------- 상품 사용후기------------------------------------------->
		<div id="tabs-3" >
		<div class="mt-3">
			<div id="review-list"></div>
                    <div class="form-floating">
                        <textarea id="review-form" onkeydown="resize(this)" class="form-control"
                                  onkeyup="resize(this)" style="width: 100%; min-height: 5rem; overflow-y: hidden; resize: none;"></textarea>
                        <label for="review-form">댓글을 남겨보세요</label>
                        <div class="mt-1 d-flex justify-content-end">
                            <button class="btn btn-sm btn-primary" id="review-write" onclick="writeReview('${sessionScope.mid}')">등록</button>
                        </div>
                    </div>
                </div>
		</div>
		
		
		<!-- ---------------------상품 Q&A (count내용 추가할 예정) ------------------------------>
		
  <div id="tabs-4" > 
	<div class="wrapper row3">
  <main class="container clear"> 
    <h2 class="sectiontitle">상품 QnA</h2>
	<div style="height: 5px"></div>
	<div style="height: 550px" >
	<table class = "table">
	  <tr>
	    <td>
	      <span  class="btn btn-sm btn-danger" onclick="qnaInsert(${vo.ino })">문의</span>
	    </td>
	  </tr>
	</table>
	
	<div id="print">
	  <jsp:include page="item_qna_list.jsp">
	    <jsp:param value="${vo.ino }" name="ino"/>
	  </jsp:include>
	</div>
	
	</div>
	
  </main>
</div>
  </div> <!--tabs$ -->
	  
  </div>
 </div>
</div>
<div style="height :40px;"></div>


  <jsp:include page="/happy/fragments/footer.jsp" flush="false" /> <!-------------------- 1 -->
  <jsp:include page="/happy/fragments/common-script.jsp" flush="false" /> <!-------------------- 2 -->
<script rel="script" src="/assets/project/item/js/item_review.js"></script>
<script rel="script" src="/assets/project/item/js/item_qna.js"></script>

  <script src="assets/project/item/js/main.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script> <!--------------------3 -->
  
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script> <!-------------------- 4-->
<script rel="script" src="/assets/project/item/js/item_like.js"></script>

</body>
</html>