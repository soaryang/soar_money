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
    </script>
</head>
<body>
<div>用户登录表单</div>
<form name="frmLogin"  onsubmit="return dosubmit()" action="/yangtengfei12345678/save" method="post">
    卡片名称: <input type="text" name="cardName"><br/>
    卡号: <input type="text" name="cardNo"><br/>
    银行类型  <select name="bankType">
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
    <option value="22">北京银行</option>
</select><br/>
    还款类型 <select name="payType">
                <option value="0">房贷</option>
                <option value="1">信用卡</option>
                <option value="2">蚂蚁花呗</option>
                <option value="3">京东白条</option>
            </select><br/>

    账单日期：<input type="text" name="accountDate"><br/>
    是不是固定还款日期：
    <select name="isfixDate">
        <option value="0">是</option>
        <option value="1">否</option>
    </select>
    <br/>
    是否开卡：
    <select name="isOpenCard">
        <option value="0">否</option>
        <option value="1">是</option>
    </select>
    <br/>
    还款日期：<input type="text" name="payDate"><br/>
    <input type="submit" value="提交" id="submit">
</form>
</body>
</html>
