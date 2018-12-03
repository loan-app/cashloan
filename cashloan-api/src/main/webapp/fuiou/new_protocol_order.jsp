<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PY77新协议支付下单</title>
<link href="../styles/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
table {
	margin: 0 auto;
}
</style>
</head>
<body>
	<br />
	<form name="apiPay" method="post" action="/fuiou/payment.htm">
		<table width="70%" cellspacing="0" border="0" cellspacing="1">
			<tr>
				<td class="info_title">PY77新协议支付下单</td>
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
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;0002900F0096235</td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">客户编号(USERID)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="userid" size='30' value='66666666' /></td>
						</tr>
							<tr>
							<td width="300" class="bg_gray" align="right">商户订单号(MCHNTORDERID)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="mchntorderid" size='30' value='' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">协议号(PROTOCOLNO)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="protocolno" size='30' maxlength='100'
								value='P100000000001' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">交易金额(分)(AMT)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="amt" size='30' maxlength='100' value='1' /></td>
						</tr>
						 <tr>
							<td width="300" class="bg_gray" align="right">USERIP(USERIP)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="userip" size='30' maxlength='100' value='127.0.0.1' /></td>
						</tr>
						 <tr>
							<td width="300" class="bg_gray" align="right">是否发短信(NEEDSENDMSG)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="needsendmsg" size='30' maxlength='100' value='1' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">回调地址(BACKURL)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="backurl" size='100' maxlength='1000' value='http://www.baidu.com' /></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">CVN：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="cvn" size='30' maxlength='100'
								value='' /></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">有效期(YYMM)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="exp" size='30' maxlength='100'
								value='' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">订单存活期(分钟)(ORDERALIVETIME)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="orderalivetime" size='30' maxlength='100'
								value='' /></td>
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