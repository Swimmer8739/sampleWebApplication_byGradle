<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>

	<html:form action="/logout">
		<html:submit value="ログアウト" />
	</html:form>
	<div>
		ようこそtestさん！
	</div>

	<hr/><br><br>


	<html:form action="/battle">


		<table border="1">
			<tr>
				<th></th>	<th>1</th>	<th>2</th>	<th>3</th>	<th>4</th>	<th>5</th>	<th>6</th>	<th>7</th>	<th>8</th>
			</tr>

			<tr>
				<td>1</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>2</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>3</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>4</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>5</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>6</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>7</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>
			<tr>
				<td>8</td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>	<td></td>
			</tr>

		</table>

	</html:form>

</body>
</html:html>