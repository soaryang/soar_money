<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker Spring MVC 之 表单提交</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style>
	input {
		width:300px;
		height:40px;
		font-weight:bold;
		font-size:15pt;
	}
	select {
		width:300px;
		height:40px;
		font-weight:bold;
		font-size:15pt;
	}
	</style>
    <script type="text/javascript">
	
	    function init() {			
			var select = document.getElementById("bankType");  
			for (var i = 0; i < select.options.length; i++){  
				if (select.options[i].value == ${card.bankType}){  
					select.options[i].selected = true;  
					break;  
				}  
			}
			
			var select2 = document.getElementById("payType");  
			for (var i = 0; i < select2.options.length; i++){  
				if (select2.options[i].value == ${card.payType}){  
					select2.options[i].selected = true;  
					break;  
				}  
			}
			var select3 = document.getElementById("isfixDate");  
			for (var i = 0; i < select3.options.length; i++){  
				if (select3.options[i].value == ${card.isfixDate}){  
					select3.options[i].selected = true;  
					break;  
				}  
			}
			
			var select4 = document.getElementById("isOpenCard");  
			for (var i = 0; i < select4.options.length; i++){  
				if (select4.options[i].value == ${card.isOpenCard}){  
					select4.options[i].selected = true;  
					break;  
				}  
			}
        }
        //默认提交状态为false
        var commitStatus = false;
        function dosubmit(){
            if(commitStatus==false){
                //提交表单后，讲提交状态改为true
                commitStatus = true;
                return true;
            }else{
                return false;
            }
        }
		function doChange(){
			if(document.getElementById("isfixDate").value==1){
				document.getElementById("payDate").value = "";
				document.getElementById("payDate").disabled=true; 
			}else{
				document.getElementById("payDate").disabled=false; 
			}
		}
    </script>
</head>
<body onload="init()">
<div>用户登录表单</div>
<form name="frmLogin"  onsubmit="return dosubmit()" action="/yangtengfei12345678/update" method="post">
    卡片名称: <input type="text" name="cardName" value="${card.cardName}"><br/>
    卡号: <input type="text" name="cardNo" value="${card.cardNo}"><br/>
    银行类型  <select name="bankType" id="bankType">
    <option value="0">中国工商银行</option>
    <option value="1">中国农业银行</option>
    <option value="2">中国银行</option>
    <option value="3">中国建设银行</option>
    <option value="4">交通银行</option>
    <option value="5">中信银行</option>
    <option value="6">中国光大银行</option>
    <option value="7">华夏银行</option>
    <option value="8">中国民生银行</option>
    <option value="9">招商银行</option>
    <option value="10">兴业银行</option>
    <option value="11">广发银行</option>
    <option value="12">平安银行</option>
    <option value="13">上海浦东发展银行</option>
    <option value="14">恒丰银行</option>
    <option value="15">浙商银行</option>
    <option value="16">渤海银行</option>
    <option value="17">支付宝</option>
    <option value="18">民生银行</option>
    <option value="19">花旗银行</option>
    <option value="20">中国邮政储蓄银行</option>
    <option value="21">京东</option>
</select><br/>
    还款类型 <select name="payType" id="payType">
                <option value="0">房贷</option>
                <option value="1">信用卡</option>
                <option value="2">蚂蚁花呗</option>
                <option value="3">京东白条</option>
            </select><br/>

    账单日期：<input type="text" name="accountDate" value="${card.accountDate !""}"><br/>
    是不是固定还款日期：
	<select name="isfixDate" id="isfixDate"  onchange="doChange()">
        <option value="0">是</option>
        <option value="1">否</option>
    </select>
	<br/>
    是否开卡：
    <select name="isOpenCard" id="isOpenCard">
        <option value="0">否</option>
        <option value="1">是</option>
    </select>
    <br/>
    还款日期： 
		<#if card.isfixDate==1>
			<input type="text" name="payDate" id="payDate" value="${card.payDate !""}" disabled="disabled"/>
		<#else>
			<input type="text" name="payDate" id="payDate" value="${card.payDate !""}"/>
		</#if>
	
	<br/>
    <input type="submit" value="提交" id="submit">

    <input type="hidden" name="id" value="${card.id}"/>
</form>
</body>
</html>
