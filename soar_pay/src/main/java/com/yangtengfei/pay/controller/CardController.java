package com.yangtengfei.pay.controller;

import com.yangtengfei.pay.bean.Card;
import com.yangtengfei.pay.service.CardService;
import com.yangtengfei.pay.service.DardDateService;
import com.yangtengfei.pay.view.CardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private DardDateService cardDateService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response){
        return "insert";
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
    //@IpLimit(ip="127.0.0.1")
    public String findById(HttpServletRequest request, HttpServletResponse response){
        return "123123";
    }

    @PostMapping(value = "/save")
    //@IpLimit(ip="127.0.0.1")
    public ModelAndView saveCard(@ModelAttribute Card card){
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

        newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "房贷".equals(item.getPayType())).collect(Collectors.toList()));

        newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "蚂蚁花呗".equals(item.getPayType())).collect(Collectors.toList()));

        newCardViewList.addAll(cardViewList.stream().filter(item -> item.getIsOpenCard()==1).filter(item -> "信用卡".equals(item.getPayType())).collect(Collectors.toList()));

        List<CardView> isNotOpenCardViewList = cardViewList.stream().filter(item -> item.getIsOpenCard()==0).collect(Collectors.toList());

        newCardViewList.addAll(isNotOpenCardViewList);

        return newCardViewList;
    }
}
