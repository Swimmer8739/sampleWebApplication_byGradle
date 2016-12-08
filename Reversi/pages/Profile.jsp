<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
	<html:form action="/logout">
		<html:submit value="ログアウト" />
	</html:form>
	<div>
		ようこそ<bean:write name="LoginForm" property="name" />さん！
	</div>

	<hr/><br><br>

	<h6>対戦成績</h6>
	<br>
	<table border="1">
		<tr>
			<td>総対戦回数</td>
			<td>10</td>
		</tr>
		<tr>
			<td>勝利回数</td>
			<td>5</td>
		</tr>
		<tr>
			<td>勝率</td>
			<td>50%</td>
		</tr>
	</table>

	<html:form action="/gamesetup">
		<html:submit value="対戦設定画面へ" />
	</html:form>

</body>
</html>

