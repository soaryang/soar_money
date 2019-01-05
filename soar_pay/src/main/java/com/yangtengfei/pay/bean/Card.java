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
    private int bankType;

    @Field
    private int isfixDate;

    @Field
    private int isOpenCard;

    @Field
    private int payType;

    @Field
    private int payDate;

    @Field
    private int accountDate;

    @Field
    private Date createDate = new Date();

    @Field
    private Date updateDate = new Date();

    @Field
    private int deleteFlg;
}
