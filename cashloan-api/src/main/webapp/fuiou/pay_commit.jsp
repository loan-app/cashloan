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
	<form name="apiPay" method="post" action="/fuiou/payCommit.htm">
		<table width="70%" cellspacing="0" border="0" cellspacing="1">
			<tr>
				<td class="info_title">代付付款</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="1">
					    <tr>
							<td width="300" class="bg_gray" align="right">版本号：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;1.0</td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">帐号：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="accntno" size='30' maxlength='100'
								value='6212261904006115311' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">帐号名称：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
									type="text" name="accntnm" size='30' maxlength='100' value='张三' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">交易金额(元)(AMT)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="amt" size='30' maxlength='100' value='5' /></td>
						</tr>

						 <tr>
							<td width="300" class="bg_gray" align="right">企业流水号(entseq)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="entseq" size='30' maxlength='100' value='entseq' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">备注(memo)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
								type="text" name="memo" size='50' maxlength='100' value='memo' /></td>
						</tr>
						<tr>
							<td width="300" class="bg_gray" align="right">手机号(mobile)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<input
									type="text" name="mobile" size='30' maxlength='30' value='15388888888' /></td>
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