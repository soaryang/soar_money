package com.yangtengfei.pay.payconst;

public enum BankTypeEnum {
    ICBC("中国工商银行",0),
    AGRICULTURALBANKOFCHINA("中国农业银行",1),
    BANKOFCHINA("中国银行",2),
    CHINACONSTRUCTIONBANK("中国建设银行",3),
    BANKOFCOMMUNICATIONS("交通银行",4),
    CITICBANK("中信银行",5),
    CHINAEVERBRIGHTBANK("中国光大银行",6),
    HSBCBANK("华夏银行",7),
    CHINAMINSHENGBANK("中国民生银行",8),
    CHINAMERCHANTSBANK("招商银行",9),
    INDUSTRIALBANK("兴业银行",10),
    GUANGFABANK("广发银行",11),
    PINGANBANK("平安银行",12),
    SHANGHAIPUDONGDEVELOPMENTBANK("上海浦东发展银行",13),
    EVERGROWINGBANK("恒丰银行",14),
    ZHESHANGBANK("浙商银行",15),
    BOHAIBANK("渤海银行",16),
    ZHIFUBAO("支付宝",17),
    MINGSHENG("民生银行",18),
    HUAQI("花旗银行",19),
    YOUZHENGCHUXUYINHANG("中国邮政储蓄银行",20);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private BankTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (BankTypeEnum c : BankTypeEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}