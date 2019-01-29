<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>API(3.0)手机支付协议绑定</title>
<link href="../styles/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
table {
	margin: 0 auto;
}
</style>
</head>
<body>
	<br />
	<form name="apiPay" method="post" action="/fuiou/bindMsg.htm" target="_blank">
		<table width="70%" cellspacing="0" border="0" cellspacing="1">
			<tr>
				<td class="info_title">PY77新协议绑定发送短信</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="1">
						<tr>
							<td width="300" class="bg_gray" align="right">客户编号(USERID)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="userid" size='30' value='xj123456' /></td>
						</tr>
							<tr>
							<td width="300" class="bg_gray" align="right">账户名称(ACCOUNT)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="account" size='30' maxlength='100'
								value='孙悟空' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">银行卡号(CARDNO)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="cardno" size='30' maxlength='100'
								value='3568891134526417' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">证件类型(IDTYPE)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<select
								name="idtype">
									<option selected value="0">身份证</option>
							</select></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">证件号(IDCARD)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="idcard" size='30' maxlength='100'
								value='420116199001011234' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">手机号码(MOBILENO)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="mobileno" size='30' maxlength='100'
								value='13888888888' /></td>
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