<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通话详情修复</title>
<link href="../styles/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
table {
	margin: 0 auto;
}
</style>
</head>
<body>
	<br />
	<form name="h5pay" method="post" action="/voicecnt/fix.htm" target="_blank">
		<table width="70%" cellspacing="0" border="0" cellspacing="1">
			<tr>
				<td class="info_title">通话详情修复</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="1">
						<tr>
							<td width="200" class="bg_gray" align="right">报告的最大id：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="orderId" size='30' value='1' /></td>
						</tr>
						<tr>
							<td height="50">&nbsp;</td>
							<td><input type="submit" name="Submit" value="查询 "/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" name="Submit2" value="重 置" />&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" name="Submit3" value="返回" onclick="javascript:history.go(-1)"/></td>

						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>