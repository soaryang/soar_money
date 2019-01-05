package com.yangtengfei.pay.limit.ip;

public class IpLimitException extends Exception {

    public IpLimitException(){
        super("ip不能访问");
    }

    public IpLimitException(String message){
        super(message);
    }
}
