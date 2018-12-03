<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PY77支付协议卡查询</title>
<link href="../styles/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
table {
	margin: 0 auto;
}
</style>
</head>
<body>
	<br />
	<form name="apiPay" method="post" action="/fuiou/bindQuery.htm" target="_blank">
		<table width="70%" cellspacing="0" border="0" cellspacing="1">
			<tr>
				<td class="info_title">API(3.0)手机支付协议卡查询</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="1">
					    <tr>
							<td width="300" class="bg_gray" align="right">版本号(VERSION)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;1.0</td>
						</tr>
					    <tr>
							<td width="300" class="bg_gray" align="right">商户编号(MCHNTCD)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;
							<input type="text" name="mchntcd" size='30' value='0002900F0096235' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">客户编号(USERID)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;
							<input type="text" name="userid" size='30' value='66666666' /></td>
						</tr>
						<tr>
							<td height="50">&nbsp;</td>
							<td><input type="submit" name="Submit" value="确 定" />&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" name="Submit2" value="重 置" />&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" name="Submit3" value="返回" onclick="javascript:history.go(-1)"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>