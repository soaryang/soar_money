package com.yangtengfei.pay.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "t_app")
public class Card {

    /*@Id
    @GeneratedValue
    private int id;*/

    @Id
    private String id;

    @Field
    private String cardName;

    @Field
    private String cardNo;

    @Field
    private Integer bankType = 0;

    //固定还款日 0是 1否
    @Field
    private Integer isfixDate = 0;

    @Field
    private Integer isOpenCard = 0;

    @Field
    private Integer payType = 0;

    @Field
    private Integer payDate = 0;

    //账单日
    @Field
    private Integer accountDate = 0;

    @Field
    private Date createDate = new Date();

    @Field
    private Date updateDate = new Date();

    @Field
    private int deleteFlg = 0;
}
