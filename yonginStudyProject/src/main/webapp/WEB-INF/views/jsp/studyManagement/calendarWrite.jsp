<!------ JSP 설정(시작) ------>
<%@ page language="java" contentType="text/HTML;charset=UTF-8" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!------ JSP 설정(끝) ------>

<!DOCTYPE html>
<html>
<head>
<!---- 순서 다르면 오류 ---->
<!-- 자원 불러오기(공통) : 순서  1(필수)-->
<jsp:include page="../common/resources.jsp"></jsp:include>

<!-- 헤더 불러오기 : 순서 2(헤더 필요없는 곳은 주석처리) -->
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>

<!-- 해당 페이지 js 호출 : 순서 3(다른 페이지 js 호출 금지)-->
<script type="text/javascript" src="/resources/js/studyManagement/calendarWrite.js"></script>
<script>
var typeSxnList = [
    <c:forEach var="result" items="${calendarType}" varStatus="status">
        {codeId:"${result.codeId}", codeValue:"${result.codeValue}", codeExtraData:"${result.codeExtraData}"}<c:if test="${!status.last}">,</c:if>
    </c:forEach>
    ];
var typeSxnMap = {};
typeSxnList.forEach(function(n){
	typeSxnMap[n.codeId] = [n.codeValue, n.codeExtraData];
});
</script>
<style>
body {
	max-height: 250px;
	overflow-x:hidden;
	overflow-y:hidden;
}
button {
	margin-top:10px; 
	padding:7px 9px 11px 9px; 
	width: calc(50% - 1px); 
	background:none; 
	border:none;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="typeHeaderColor" style="width:100%; height:10px;"></div>
<div style="margin-left:1%; margin-right:1%">
	<form id="calendarForm" name="calendarForm">
    <i class="fa fa-close" style="float:right; font-size:1.7rem; cursor:pointer;" onclick="closeWriteModal();"></i>
    <div style="margin-top:1%;">
      <div style="float:left; width:40%; margin-top:2%">
      <i id="typeColor" class="fa fa-circle" style="font-size:15px;margin-right:10px;"></i>
      <select id="type" name="type" data-ax-path="type" onmouseup="mouseUp();" style="width:100px; height:32px; margin-right:10%">
        <c:forEach var="result" items="${calendarType}" varStatus="status">
			<option value="<c:out value='${result.codeId}'/>" ><c:out value='${result.codeValue}'/>
		</c:forEach>
      </select>
      </div>
      
      <div style="float:right; width:60%;height:32px;">
        <i class="fa fa-group" style="font-size:1.5rem"></i> <span id="userInfoSpan" style="font-size:1.0rem;"></span>
      </div>
      
      
    </div>
  
    <table style="width:100%">
      <tr>
        <td colspan="5">
          <input type="text" class="form-control" id="title" name="title" data-ax-path="title" placeholder="제목" maxlength="30"/>
        </td>
      </tr>
      <tr>
        <td colspan="5">
          <textarea class="form-control" name="content" id="content" data-ax-path="content" placeholder="내용" maxlength="100"></textarea>
        </td>
      </tr>
      <tr>
        <td style="width:30%;">
          <input type="date" class="form-control" name="startDt" id="startDt" data-ax-path="startDt" placeholder="종료일" max="9999-12-31" maxlength="10" data-ax5formatter="date"/>
        </td>
        <td style="width:18%;">
          <input type="text" id="startHm" name="startHm" class="form-control" data-ax-path="startHm" data-ax5formatter="time" maxlength="5" placeholder="hh:mm"/>
        </td>
        <td style="width:4%;">
           &nbsp;~
        </td>
        <td style="width:30%;">
          <input type="date" class="form-control" name="endDt" id="endDt" placeholder="종료일" data-ax-path="endDt" max="9999-12-31" maxlength="10" data-ax5formatter="date"/>
        </td>
        <td style="width:18%;">
          <input type="text" class="form-control" id="endHm" name="endHm" data-ax-path="endHm" data-ax5formatter="time" maxlength="5" placeholder="hh:mm"/>
        </td>
      </tr>
    </table>
    </form>
</div>
<div style="text-align:center; position: fixed; bottom:0; width:100%; border-top: 1px solid #e5e5e5;">
  <button type="button" id="saveCalendarBtn" style="float:left; width:50%" onclick="saveSchedule();"><i class="fa fa-edit"></i>  저장</button>
  <button type="button" id="closeCalendarBtn" style="float:right; width:50%" onclick="closeWriteModal();"><i class="fa fa-times-circle"></i>  닫기</button>
</div>
</body>
</html>