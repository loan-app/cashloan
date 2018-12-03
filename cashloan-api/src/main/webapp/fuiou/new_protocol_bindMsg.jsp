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
							<td width="300" class="bg_gray" align="right">版本号(VERSION)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;1.0</td>
						</tr>
						 <tr>
							<td width="300" class="bg_gray" align="right">商户编号(MCHNTCD)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;
							<input type="text" name="mchntcd" size='30' value='0002900F0096235' /></td>
						</tr>
						 <tr>
							<td width="300" class="bg_gray" align="right">商户密钥(key)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;
							<input type="text" name="key" size='100' value='5old71wihg2tqjug9kkpxnhx9hiujoqj' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">客户编号(USERID)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="userid" size='30' value='66666666' /></td>
						</tr>
						 <tr>
							<td width="300" class="bg_gray" align="right">交易请求日期(TRADEDATE)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;
							<input type="text" name="tradedate" size='30' value='' /></td>
						</tr>
						 <tr>
							<td width="300" class="bg_gray" align="right">流水号(MCHNTSSN)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;
							<input type="text" name="mchntssn" size='30' value='' /></td>
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
							<td width="200" class="bg_gray" align="right">CVN：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="cvn" size='30' maxlength='100'
								value='123' /></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">有效期(YYMM)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="exp" size='30' maxlength='100'
								value='1129' /></td>
						</tr>
						<!-- <tr>
							<td width="300" class="bg_gray" align="right">短信验证码(MSGCODE)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="msgcode" size='30' maxlength='100'
								value='0000' /></td>
						</tr> -->
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