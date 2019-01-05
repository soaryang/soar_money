package com.yangtengfei.pay.view;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class CardView {

    private String id;

    private String cardName;

    //卡号
    private String cardNo;

    //银行
    private String bank;

    //什么类型的还款
    private String payType;

    //还款日
    private Integer payDate;

    //存钱日期
    private String putMonyDay;

    //账单日
    private Integer accountDate;

    //距离还款时间
    private Integer subPayDay;

    //还款日期
    private String payDay;

    //账单日（string）
    private String accountDay;

    //距离下个账单日
    private int subAccountDay;

    private boolean thisMonthPayDate;

    private boolean thisMonthAccountDate;

    private boolean  isemergent;

    private boolean isgetMoney;

    private int isfixDate;
    private int isOpenCard;

}
