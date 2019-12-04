package com.yangtengfei.pay.service;

import com.alibaba.fastjson.JSON;
import com.yangtengfei.pay.bean.Card;
import com.yangtengfei.pay.payconst.BankTypeEnum;
import com.yangtengfei.pay.payconst.PayTypeEnum;
import com.yangtengfei.pay.util.CalendarUtil;
import com.yangtengfei.pay.util.DateUtil;
import com.yangtengfei.pay.view.CardView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class DardDateService {

    @Autowired
    private CardService cardService;

    public List<CardView> findCardViewList() {
        List<CardView> cardViewList = new ArrayList<>();
        //获取卡的信息
        List<Card> cards = cardService.findAll();
        //当前日期
        Calendar calendar = Calendar.getInstance();
        if (!CollectionUtils.isEmpty(cards)) {
            cards.stream().forEach(card -> {
                CardView cardView = new CardView();
                BeanUtils.copyProperties(card,cardView);
                //设置银行类型
                cardView.setBank(BankTypeEnum.getName(card.getBankType()));
                //设置还款类型
                cardView.setPayType(PayTypeEnum.getName(card.getPayType()));
                //是否是固定还款日
                int isFixDate = card.getIsfixDate();
                //还款日
                if(isFixDate == 0){
                    fixDateHandler(cardView);
                }else{
                    notFixDateHandler(cardView);
                }

                //是否能出钱
                //距离下个账单日的时间大于20天就可以出钱
                int subNexAccountDay = cardView.getSubNexAccountDay();
                if(subNexAccountDay < 29 && subNexAccountDay >20  && "信用卡".equals(cardView.getPayType())){
                    cardView.setIsgetMoney(true);
                }

                int subPayDay =  cardView.getSubPayDay();
                if (subPayDay <= 3 & subPayDay >= 0) {
                    cardView.setIsemergent(true);
                }
                cardViewList.add(cardView);

                cardViewList.sort((o1, o2)->o1.getAccountDate().compareTo(o2.getAccountDate()));
            });
        }
        return cardViewList;
    }
    private void fixDateHandler(CardView cardView){
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        //账单日期accountDate
        int accountDate = cardView.getAccountDate();
        Calendar calendarAccountDate = Calendar.getInstance();
        CalendarUtil.setSpecialDay(calendarAccountDate, accountDate);
        cardView.setAccountDateStr(DateUtil.calendarToString(calendarAccountDate, DateUtil.YYYY_MM_DD));

        //还款日期 payDay
        //CalendarUtil.setSpecialDay(calendar, card.getAccountDate());
        int payDate = cardView.getPayDate();
        Calendar calendarPayDate = Calendar.getInstance();
        CalendarUtil.setSpecialDay(calendarPayDate, payDate);
        cardView.setPayDateStr(DateUtil.calendarToString(calendarPayDate, DateUtil.YYYY_MM_DD));

        //存钱日期 putMonyDay
        //存钱日期再还款前一天
        CalendarUtil.addDay(calendarPayDate, 0);
        Calendar calendarPutDate = calendarPayDate;
        cardView.setPutMonyDayStr(DateUtil.calendarToString(calendarPutDate, DateUtil.YYYY_MM_DD));

        //距离还款日期 subPayDay
        int subPayDay = subPayDay(calendarPutDate);
        cardView.setSubPayDay(subPayDay);

        //下个账单日
        Calendar nextAccount = Calendar.getInstance();
        if(accountDate > currentDay){
            CalendarUtil.setSpecialDay(nextAccount, accountDate);
        }else{
            CalendarUtil.addMonth(nextAccount, 1);
            CalendarUtil.setSpecialDay(nextAccount, accountDate);
        }
        cardView.setNextAccountDateStr(DateUtil.calendarToString(nextAccount, DateUtil.YYYY_MM_DD));

        //距离下个账单日的时间
        int subNextAccountDay = subNextAccountDay(nextAccount);
        cardView.setSubNexAccountDay(subNextAccountDay);
    }
    private void notFixDateHandler(CardView cardView){
        int accountDate = cardView.getAccountDate();
        Calendar calendarAccountDate = Calendar.getInstance();
        //当前所在天数
        int currentDay = calendarAccountDate.get(Calendar.DAY_OF_MONTH);
        CalendarUtil.setSpecialDay(calendarAccountDate, accountDate);
        cardView.setAccountDateStr(DateUtil.calendarToString(calendarAccountDate, DateUtil.YYYY_MM_DD));

        //还款日期 payDay
        int payDate = cardView.getPayDate();
        if(accountDate > currentDay){
            CalendarUtil.addMonth(calendarAccountDate, -1);
        }
        CalendarUtil.addDay(calendarAccountDate, payDate);
        Calendar calendarPayDate = calendarAccountDate;
        cardView.setPayDateStr(DateUtil.calendarToString(calendarPayDate, DateUtil.YYYY_MM_DD));




        //存钱日期 putMonyDay
        //存钱日期再还款前一天
        CalendarUtil.addDay(calendarPayDate, 0);
        Calendar calendarPutDate = calendarPayDate;
        cardView.setPutMonyDayStr(DateUtil.calendarToString(calendarPutDate, DateUtil.YYYY_MM_DD));

        //距离还款日期 subPayDay
        int subPayDay = subPayDay(calendarPutDate);
        cardView.setSubPayDay(subPayDay);

        //下个账单日
        Calendar nextAccount = Calendar.getInstance();
        if(accountDate > currentDay){
            CalendarUtil.setSpecialDay(nextAccount, accountDate);
        }else{
            CalendarUtil.addMonth(nextAccount, 1);
            CalendarUtil.setSpecialDay(nextAccount, accountDate);
        }
        cardView.setNextAccountDateStr(DateUtil.calendarToString(nextAccount, DateUtil.YYYY_MM_DD));

        //距离下个账单日的时间
        int subNextAccountDay = subNextAccountDay(nextAccount);
        cardView.setSubNexAccountDay(subNextAccountDay);
    }


    private int subPayDay(Calendar calendarPutDate){
        Calendar calendar = Calendar.getInstance();
        int subAccountDay = (int) ((calendarPutDate.getTimeInMillis() - calendar.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        return subAccountDay;
    }


    private int subNextAccountDay(Calendar nextAccount){
        Calendar calendar = Calendar.getInstance();
        int subAccountDay = (int) ((nextAccount.getTimeInMillis() - calendar.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        return subAccountDay;
    }
}
