<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Who</title>
</head>
<body>
	<html:form action="/login" method="POST">
		<table border="0">
			<html:errors />

			<tr>
				<th></th>
				<th></th>
			</tr>

			<tr>
				<td>名前 ：</td>
				<td><html:text property="name" size="20" maxlength="30" /></td>
			</tr>
			<tr>
				<td>パスワード：</td>
				<td><html:password property="password" size="20" maxlength="30" /></td>
			</tr>

			<tr>
				<td><html:submit value="OK" /></td>
				<td></td>
			</tr>

		</table>
	</html:form>
</body>
</html:html>