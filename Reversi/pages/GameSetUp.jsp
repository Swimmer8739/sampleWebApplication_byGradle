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
	<form action="/logout" method="POST">
		<html:submit value="ログアウト" />
	</form>
	<div>
		ようこそtestさん！
	</div>

	<hr/><br><br>

	<h4>ゲーム設定</h4>
	<html:form action="/gamestart">
		<table border="1">
			<tr>
			</tr>

			<tr>
				<td>プレイヤー１</td>
				<td>
					<html:select property="player1" name="player1" value="human">
					<html:option value="human">人間</html:option>
					<html:option value="AI_1">AI:Level1</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>プレイヤー２</td>
				<td>
					<html:select property="player2" name="player2" value="AI_1">
					<html:option value="human">人間</html:option>
					<html:option value="AI_1">AI:Level1</html:option>
					</html:select>
				</td>
			</tr>
		</table>
		<html:submit value="対戦開始！" />
	</html:form>

</body>
</html>