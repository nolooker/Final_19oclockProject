<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>19시 :: 찜 리스트</title>
</head>

<body>
	<!--전체 wrap-->
	<div id="chat-wrap">

		<!------------------------ [chat-side]------------------------------------->
		<jsp:include page="/WEB-INF/views/chat/chat-side.jsp" />
		<!------------------------ [chat-side]------------------------------------->

		<!------------------------ content 시작 ------------------------------------->
		<div class="content">


			<!-- heartList가 not null -->
			<c:if test="${not empty heartList }">
				<!-- 찜한 서비스 시작-->
				<div class="content-heart">
					<div id="content-title">내가 찜한 서비스</div>
					<div id="heart-list">
						<ul>
							<c:forEach items="${heartList }" var="s">
								<li class="list">
									<div class="list-wrap">
										<div id="service-preview">
											<p id="name">[${s.brandName}] ${s.STitle }</p>
											<p id="preview">제공자: ${s.MId }</p>
											<p id="preview">내용: ${s.SContent}</p>
											<p id="preview">가격: ${s.SPrice } 평점: ${s.SRate }</p>
											<%-- <span class="start-chat"><a
												href="/startChat.do?sNo=${s.SNo }&myId=${sessionScope.loginMember.MId }&yourId=${s.MId}"><u>문의하기</u></a></span> --%>
											<a href="#"
												onclick="startChat('${s.SNo }','${sessionScope.loginMember.MId }','${s.MId}','${sessionScope.loginMember.MNo }');">문의하기</a>

											<!-- 문의하기 버튼 누르면 사라지게 -->
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>
			<!-- heartList가 not null -->

			<!-- heartList가 null -->
			<c:if test="${empty heartList}">
				<div class="empty-page">
					<div id="content-title">내가 찜한 서비스</div>
					<div id="empty-content">
						<br> <br> <br> <br> <br> <br> <img
							src="/img/icon/exclamation_black.png">
						<h3>
							아직 찜한 서비스가 <br>없습니다!
						</h3>
						<a href="#"><u>나에게 맞는 서비스 검색 ></u></a>
					</div>
				</div>
			</c:if>
			<!-- heartList가 null -->

			<!-- 찜한 서비스 끝-->
		</div>
		<!------------------------ content 끝 ------------------------------------->

	</div>
	<!-- chat-wrap 끝-->

	<script>
		function startChat(sNo, userId, freeId,mNo) {
			$.ajax({
				url : "/makeRoom.do",
				type : "post",
				async : false,
				data : {
					sNo : sNo,
					userId : userId,
					freeId : freeId,
					mNo:mNo
				},
				success : function(data) {
					location.href = "/startChat.do?sNo=" + sNo + "&userId="
							+ userId + "&freeId=" + freeId
				},
				error : function() {

				}
			});
		}
	</script>
</body>
</html>