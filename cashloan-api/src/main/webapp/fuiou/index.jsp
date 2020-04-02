<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>支付测试页面</title>
<link href="../styles/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
table {
	margin: 0 auto;
}
</style>
</head>
<body>
	<br />
		<table width="70%" cellspacing="0" border="0" cellspacing="1">
			<tr>
				<td class="info_title">支付订单操作页面</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="1">
						<tr>
							<td width="200" class="bg_gray" align="right">发送验证码：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="new_protocol_bindMsg.jsp">发送验证码</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">绑卡：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="new_protocol_bindCommit.jsp">操作绑卡</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">查询绑卡：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="new_protocol_query.jsp">查询绑卡</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">解除绑卡：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="new_protocol_unbind.jsp">解除绑卡</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">协议支付：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="new_protocol_order.jsp">协议支付</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">卡bin查询：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="query_card_bin.jsp">卡bin查询</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">订单结果查询接口(富友订单号)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="query_order.jsp">富友订单号查询</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">订单结果查询接口(商户订单号)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="query_order_2.jsp">商户订单号查询</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">付款（代收付）：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="pay_commit.jsp">付款（代收付）</a></td>
						</tr>
						<tr>
							<td width="200" class="bg_gray" align="right">付款查询(商户订单号)：&nbsp;&nbsp;</td>
							<td class="bg_yellow2" align="left">&nbsp;&nbsp;<a style="text-decoration: underline" href="pay_query.jsp">付款查询</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>