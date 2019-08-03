package com.yangtengfei.pay.controller;

import com.alibaba.fastjson.JSON;
import com.yangtengfei.pay.bean.Card;
import com.yangtengfei.pay.limit.ip.IpLimit;
import com.yangtengfei.pay.service.CardService;
import com.yangtengfei.pay.service.DardDateService;
import com.yangtengfei.pay.service.MailService;
import com.yangtengfei.pay.view.CardView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/yangtengfei12345678")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private DardDateService cardDateService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response){
        return "insert";
    }


    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String mail(HttpServletRequest request, HttpServletResponse response){
        try{
            List<CardView> cardViewList = cardDateService.findCardViewList();
            for(CardView cardView:cardViewList){
                if(cardView.isIsemergent() && cardView.getIsOpenCard()==1){
                    mailService.sendSimpleMail(cardView);
                }
            }
        }catch (Exception e){
            log.error("excutePayScheduler error",e);
        }

        return "success";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable String id){
        ModelAndView view = new ModelAndView("update");
        Card card = cardService.findCardById(id);
        view.addObject("card",card);
        return view;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("cardList");
        List<CardView> cardViewList = cardDateService.findCardViewList();


        view.addObject("list",findCardViewList(cardViewList));
        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @IpLimit(ip="127.0.0.1")
    public String findById(HttpServletRequest request, HttpServletResponse response){
        return "123123";
    }

    @PostMapping(value = "/save")
    //@IpLimit(ip="127.0.0.1")
    public ModelAndView saveCard(@ModelAttribute Card card){
        log.info("card:{}", JSON.toJSONString(card));
        cardService.save(card);
        ModelAndView view = new ModelAndView("cardList");
        List<CardView> cardViewList = cardDateService.findCardViewList();
        view.addObject("list",findCardViewList(cardViewList));
        return view;
    }

    @PostMapping(value = "/update")
    //@IpLimit(ip="127.0.0.1")
    public ModelAndView update(@ModelAttribute Card card){

        Card temp= cardService.findCardById(card.getId());
        if(temp!=null){
            cardService.save(card);
        }
        ModelAndView view = new ModelAndView("cardList");
        List<CardView> cardViewList = cardDateService.findCardViewList();
        view.addObject("list",findCardViewList(cardViewList));
        return view;
    }

    private List<CardView> findCardViewList(List<CardView> cardViewList){
        //已经开卡的
        List<CardView> newCardViewList = new ArrayList<>();

        //newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "房贷".equals(item.getPayType())).collect(Collectors.toList()));

        //newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "蚂蚁花呗".equals(item.getPayType())).collect(Collectors.toList()));

        //newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "京东白条".equals(item.getPayType())).collect(Collectors.toList()));

        //newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "信用卡".equals(item.getPayType())).collect(Collectors.toList()));

        newCardViewList.addAll(cardViewList.stream().filter(item -> item.isIsemergent()==true).collect(Collectors.toList()));

        newCardViewList.addAll(cardViewList.stream().filter(item -> item.isIsemergent()==false).collect(Collectors.toList()));


        List<CardView> isNotOpenCardViewList = cardViewList.stream().filter(item -> item.getIsOpenCard()==0).collect(Collectors.toList());

        newCardViewList.addAll(isNotOpenCardViewList);

        return newCardViewList;
    }
}
