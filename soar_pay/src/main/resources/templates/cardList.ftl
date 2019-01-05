<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker Spring MVC 之 表单提交</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        td{
            height: 30px;
        }
    </style>
</head>
<body>
    <table cellpadding="0" cellspacing="0" style="border: 1px solid red;width: 80%;">
            <thead>
                <td style="border: 1px solid red;">卡片名称</td>
                <td style="border: 1px solid red;">卡号</td>
                <td style="border: 1px solid red;">银行</td>
                <td style="border: 1px solid red;">还钱种类</td>
				<td style="border: 1px solid red;">每月账单日期</td>
				<td style="border: 1px solid red;">每月还款日</td>
                <td style="border: 1px solid red;">还款日期</td>
                
                <td style="border: 1px solid red;">存钱日期</td>
				<td style="border: 1px solid red;">距离还款日</td>
                <td style="border: 1px solid red;">下个账单日期</td>
                <td style="border: 1px solid red;">距离账单日</td>
                <td style="border: 1px solid red;">是否能出钱</td>
            </thead>
            <#list list as card>
                <#if card.isemergent>
                    <tr style="background-color: lightcoral">
                <#elseif card.isgetMoney>
                    <tr style="background-color: green;color: white;">
                <#else>
                    <tr>
                </#if>
                    <td style="border: 1px solid red;">${card.cardName}</td>
                    <td style="border: 1px solid red;">${card.cardNo}</td>
                    <td style="border: 1px solid red;">${card.bank}</td>
                    <td style="border: 1px solid red;">${card.payType}</td>
					<td style="border: 1px solid red;">
						<#if card.payType=="蚂蚁花呗" || card.payType=="房贷">
							花呗，房贷只有还款日
						<#else>
							${card.accountDate}
						</#if>
					</td>
					<td style="border: 1px solid red;">
						<#if card.isfixDate==0>
							${card.payDate}
						<#else>
							账单后20天
						</#if>
					</td>
                    <td style="border: 1px solid red;">${card.payDay}</td>
                    <td style="border: 1px solid red;">${card.putMonyDay}</td>
					<td style="border: 1px solid red;">${card.subPayDay}天</td>
                <td style="border: 1px solid red;">
                    <#if card.payType=="蚂蚁花呗" || card.payType=="房贷">
                    <#else>
                        ${card.accountDay}
                    </#if>
                </td>
                <td style="border: 1px solid red;">
                    <#if card.payType=="蚂蚁花呗" || card.payType=="房贷">
                    <#else>
                        ${card.subAccountDay}天
                    </#if>
                </td>
                <td style="border: 1px solid red;">
                <#if card.payType=="蚂蚁花呗" || card.payType=="房贷">
                <#elseif card.isgetMoney>
                    可以
                <#else>
                 </#if>
                </td>
                </tr>
            </#list>
    </table>
</body>

</html>
