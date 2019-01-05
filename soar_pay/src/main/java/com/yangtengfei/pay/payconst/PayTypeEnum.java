package com.yangtengfei.pay.payconst;

public enum PayTypeEnum {
    CREDIT_CARD("信用卡", 1),
    ZHIFUBAO("蚂蚁花呗", 2),
    HOUSE_PAY("房贷", 0);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private PayTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (PayTypeEnum c : PayTypeEnum.values()) {
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