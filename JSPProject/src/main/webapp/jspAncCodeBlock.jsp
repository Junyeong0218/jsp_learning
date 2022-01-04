<%
	// 코드 블럭
	int x = 1;
	int y = 3;
%>
<!-- 코드블럭을 이용해 JSP 파일 내 선언된 지역변수를 출력하고 싶은 경우 아래와 같이 작성 -->
<%=y%>

<!-- 멤버 메소드를 정의하고 싶은 경우 그냥 선언하게되면 변환 과정에서 _jspService() 내에 
     선언하기 때문에 오류가 발생한다.
     오류 미발생을 위해서 코드 블럭 내에 !(느낌표) 를 같이 적어준다. -->
     
<%! 
	public int sum(int x, int y) {
		return x+y;
	}
%>

<!-- 페이지 지시자 블럭 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculator</title>
    <style>
    	input {
    	width: 50px;
    	height: 50px;
    	}
    	.output {
    	height: 50px;
    	background: #e9e9e9;
    	font-size: 24px;
    	font-weight: bold;
    	text-align: right;
    	padding: 0px 5px;
    	}
    </style>
</head>

<body>
    <div>
        <form action="calculator" method="post">
            <table>
            	<tr>
            		<td class="output" colspan="4">${x+y}</td>
            	</tr>
            	<tr>
            		<td><input type="submit" name="operator" value="CE"></td>
            		<td><input type="submit" name="operator" value="C"></td>
            		<td><input type="submit" name="operator" value="BS"></td>
            		<td><input type="submit" name="operator" value="/"></td>
            	</tr>
            	<tr>
            		<td><input type="submit" name="value" value="7"></td>
            		<td><input type="submit" name="value" value="8"></td>
            		<td><input type="submit" name="value" value="9"></td>
            		<td><input type="submit" name="operator" value="*"></td>
            	</tr>
            	<tr>
            		<td><input type="submit" name="value" value="4"></td>
            		<td><input type="submit" name="value" value="5"></td>
            		<td><input type="submit" name="value" value="6"></td>
            		<td><input type="submit" name="operator" value="-"></td>
            	</tr>
            	<tr>
            		<td><input type="submit" name="value" value="1"></td>
            		<td><input type="submit" name="value" value="2"></td>
            		<td><input type="submit" name="value" value="3"></td>
            		<td><input type="submit" name="operator" value="+"></td>
            	</tr>
            	<tr>
            		<td colspan="2"><input type="submit" name="value" value="0" style="width: 105px;"></td>
            		<td><input type="submit" name="dot" value="."></td>
            		<td><input type="submit" name="operator" value="="></td>
            	</tr>
            </table>
        </form>
    </div>
</body>

</html>