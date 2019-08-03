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

    //账单日
    private Integer accountDate;

    //账单日期
    private String accountDateStr;

    //还款日
    private Integer payDate;

    //还款日期
    private String payDateStr;

    //存钱日期
    private String putMonyDayStr;

    //下个账单日
    private String nextAccountDateStr;


    //距离还款时间
    private Integer subPayDay;

    //还款日期
    private String payDay;

    //账单日（string）
    private String accountDay;

    //距离下个账单日
    private int subNexAccountDay;

    //固定还款日 0是 1否
    private Integer isfixDate = 0;

    //是否能出钱
    private boolean isgetMoney;

    private boolean thisMonthPayDate;

    private boolean thisMonthAccountDate;

    //是否紧急
    private boolean  isemergent;



    private int isOpenCard;

}
