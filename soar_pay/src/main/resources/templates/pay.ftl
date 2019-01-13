<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<body>
<div>
		<table cellpadding="0" cellspacing="0" style="border: 1px solid red;width: 100%;">
			<tr>
				<td style="border: 1px solid red;">卡片名称</td>
				<td style="border: 1px solid red;">${card.cardName}</td>
			</tr>
			<tr>
				<td style="border: 1px solid red;">卡号</td>
				<td style="border: 1px solid red;">${card.cardNo}</td>
			</tr>
			<tr>
				<td style="border: 1px solid red;">银行</td>
				<td style="border: 1px solid red;">${card.bank}</td>
			</tr>
			<tr>
				<td style="border: 1px solid red;">还钱种类</td>
				<td style="border: 1px solid red;">${card.payType}</td>
			</tr>
			<#if card.payType!="蚂蚁花呗" && card.payType!="房贷">
			<tr>
				<td style="border: 1px solid red;">每月账单日期</td>
				<td style="border: 1px solid red;">
					${card.accountDate}
				</td>
			</tr>
			</#if>
			<tr>
				<td style="border: 1px solid red;">每月还款日</td>
				<td style="border: 1px solid red;">
					<#if card.isfixDate==0>
						${card.payDate}
					<#else>
						账单后20天
					</#if>
				</td>
			</tr>
			<tr>
				<td style="border: 1px solid red;">还款日期</td>
				<td style="border: 1px solid red;">${card.payDay}</td>
			</tr>
			<tr>
				<td style="border: 1px solid red;">存钱日期</td>
				<td style="border: 1px solid red;">${card.putMonyDay}</td>
			</tr>
                        <tr>
                                <td style="border: 1px solid red;">当前日期</td>
                                <td style="border: 1px solid red;">${.now?string('yyyy-MM-dd')}</td>
                        </tr>
			<tr>
				<td style="border: 1px solid red;">距离存钱日期</td>
				<td style="border: 1px solid red;" bgcolor="#FF0000">${card.subPayDay}天</td>
			</tr>
			<#if card.payType!="蚂蚁花呗" && card.payType!="房贷">
			<tr>
				<td style="border: 1px solid red;">下个账单日期</td>
				<td style="border: 1px solid red;">
					${card.accountDay}
				</td>
			</tr>
			<tr>
				<td style="border: 1px solid red;">距离账单日</td>
				<td style="border: 1px solid red;">
					${card.subAccountDay}天
				</td>
			</tr>
			</#if>
	</table>
</div>
</body>
</html>
