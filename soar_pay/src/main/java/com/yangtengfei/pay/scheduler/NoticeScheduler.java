package com.yangtengfei.pay.scheduler;

import com.alibaba.fastjson.JSON;
import com.yangtengfei.pay.bean.Card;
import com.yangtengfei.pay.service.CardService;
import com.yangtengfei.pay.service.DardDateService;
import com.yangtengfei.pay.service.MailService;
import com.yangtengfei.pay.util.DateUtil;
import com.yangtengfei.pay.view.CardView;
import com.yangtengfei.pay.view.PayInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
@Configuration
@EnableScheduling
public class NoticeScheduler {

    @Autowired
    private CardService cardService;

    @Autowired
    private MailService mailService;

    @Autowired
    private DardDateService cardDateService;

    @Scheduled(cron="0 0 9,15 * * ?")
    public void excutePayScheduler() {
        try{
            List<CardView> cardViewList = cardDateService.findCardViewList();
            for(CardView cardView:cardViewList){
                if(cardView.isIsemergent()){
                    mailService.sendSimpleMail(cardView);
                }
            }
        }catch (Exception e){
            log.error("excutePayScheduler error",e);
        }
        //mailService.sendSimpleMail(cardDateService.findCardViewList());
       /* Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<Card> cards = cardService.findAll();
        Calendar payCalendar =  Calendar.getInstance();
        for(Card card:cards){
            try {
                Thread.sleep(5000);
                int payDay = card.getPayDate();
                int subDay =0;
                if(payDay< day){
                    //计入到下个月
                    Calendar calendarTemp  = Calendar.getInstance();
                    calendarTemp.add(Calendar.MONTH,1);
                    calendarTemp.set(Calendar.DAY_OF_MONTH,payDay);
                    subDay =(int)(calendarTemp.getTimeInMillis() - calendar.getTimeInMillis())/(1000*60*60*24);
                    payCalendar = calendarTemp;
                }else{
                    subDay = payDay - day;
                    payCalendar = calendar;
                    payCalendar.set(Calendar.DAY_OF_MONTH,payDay);
                }
                if(subDay<=6 & subDay>=1){

                    PayInfo payInfo = new PayInfo();
                    payInfo.setCardName(card.getCardName());
                    payInfo.setPayDate(DateUtil.calendarToString(payCalendar,DateUtil.YYYY_MM_DD));
                    mailService.sendSimpleMail(payInfo);
                    System.out.print("day:"+JSON.toJSONString(card.getCardName()));
                }
            }catch (Exception e){
                log.error("send Message error",e);
            }
        }*/
    }
}
