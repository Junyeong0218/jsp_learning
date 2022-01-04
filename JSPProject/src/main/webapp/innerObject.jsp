<%
	// built-in Object
	// request, response, pageContext, session, application, config, out, page
	// pageContext == page 내에서만 사용되는 저장소
	int a = 3;
	int b = 4;
%>
<%!
	public int add(int a, int b) {
		return a+b;
	}
%>

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
            		<td class="output" colspan="4"><%=add(a,b)%></td>
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