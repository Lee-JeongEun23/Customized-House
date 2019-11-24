<%@page import="kr.or.bit.dto.Sale"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<style type="text/css">
h5{
font-family: 'Jua', sans-serif;
}
a.btn :hover {
   color: #fff;
   background-color: #ff6863;
   border-color: #ff6863;
   border: 0.5px solid #eee;
}

a.btn  {
   color: #ff6863;
   background-color: #eee;
   border-color: #eee;
   border: 2px solid #eee;
   padding: 1rem 1rem;
}

.teamskillbar {
	position: relative;
	display: block;
	margin-bottom: 15px;
	width: 100%;
	background: #f2f2f2;
	height: 10px;
	-webkit-transition: 0.4s linear;
	transition: 0.4s linear;
	-webkit-transition-property: width, background-color;
	transition-property: width, background-color;
}

.teamskillbar h6 {
	position: absolute;
	top: -25px;
	left: 0;
}

.teamskillbar-bar {
	height: 10px;
	width: 0px;
	background: #ff6863;
	position: absolute;
	left: 0px;
	top: 0px;
}
.slick-items, .detail{
text-align: center;
}
</style>
<%ArrayList<Sale> list = (ArrayList<Sale>) request.getAttribute("saleList");
ArrayList<String> priceList=new ArrayList<String>();
String stringPrice=null;

	for(Sale listString : list){
		//System.out.println(listString.toString());
		double num = 79830;
		int price= listString.getPrice();
		double price2= num /price * 100;
		stringPrice = Double.toString(price2)+ "%";
		priceList.add(stringPrice);
		//System.out.println(stringPrice);
		//System.out.println(79830 / price *100); //79830/sale.price*100
	}
	for(String st : priceList){
		System.out.println(st);
	}
 	int listLength = priceList.size()-1;
 	//System.out.println("listLength: " + listLength);
 	System.out.println("stringPrice: " + stringPrice);
 	
%>
<h6>300개 이상의 매물</h6>
<p style="font-size: 13px">거래 가격의 비율이 높을 수록 평균 가격보다 낮습니다.</p>
<br>
<div class="row">
	<div class="col-lg-7" id="items">
 <form action="SelectaptListService.d4b" method="post">
 <c:set var="priceList" value="priceList"></c:set>
 <c:set var="stringPrice" value="stringPrice"></c:set>
 <c:set var="listLength" value="listLength"></c:set>
 <c:set var="sale" value="list"></c:set>
<c:forEach var="sale" items="<%=list%>" varStatus="status">
<%--  <c:forEach var="i" begin="0" end="<%=listLength %>" step="1"> --%>
		<!-- items inner -->
		<div class="row">
			<div class="col-lg-1"></div>
			<div class="col-lg-7">
				<div class="slick-items">
					<div>
						<img src="./images/xi1.jpg" alt="New York" width="500" height="220">
					</div>
					<div>
						<img src="./images/xi2.jpg" alt="New York" width="500" height="220">
					</div>
					<div>
						<img src="./images/xi3.jpg" alt="New York" width="500" height="220">
					</div>
				</div>
			</div>
			<!-- 내용 -->
			<div class="col-lg-4">
			<div class="detail" style="text-align: center;">
				<h5>${sale.aptName}</h5>
				<input type="text" class="form-control"	value="전용면적   ${sale.aptSize}㎡">			
					<br>
					<div class="skill_bar sm-m-top-50">
						<div class="teamskillbar clearfix m-top-20" data-percent="<%=stringPrice %>">>
							<h6>거래 가격 : ${sale.price}</h6>
							<div class="teamskillbar-bar"></div>
						</div>
						<!-- End Skill Bar -->

						<div class="teamskillbar clearfix m-top-50" data-percent="30%">
							<h6>교통 편리함</h6>
							<div class="teamskillbar-bar"></div>
						</div>
						<!-- End Skill Bar -->
					</div>						
				 <a class="btn" href="SaleDetail.jsp">See the Details</a>		
				 </div>
			</div>
		</div>
		<hr>
	</c:forEach>	
	</form>
	</div>

	<!-- map -->
	<div class="col-lg-5">
		<div id="map" style="width: 100%; height: 100%;"></div>
	</div>

</div>

<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level : 3
	// 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 마커가 표시될 위치입니다 
	var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		position : markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
	// marker.setMap(null);
$(function(){

    
	$('.slick-items').slick({
		autoplay : true,
		dots : true,
		speed : 300 /* 이미지가 슬라이딩시 걸리는 시간 */,
		infinite : true,
		autoplaySpeed : 3000 /* 이미지가 다른 이미지로 넘어 갈때의 텀 */,
		arrows : true,
		slidesToShow : 1,
		slidesToScroll : 1,
		fade : false
	});

});
	
</script>
