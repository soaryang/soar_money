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
import java.util.List;

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
        view.addObject("list",cardViewList);
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
    public String saveCard(@ModelAttribute Card card){
        cardService.save(card);
        return "success";
    }

    @PostMapping(value = "/update")
    //@IpLimit(ip="127.0.0.1")
    public String update(@ModelAttribute Card card){

        Card temp= cardService.findCardById(card.getId());
        if(temp!=null){
            cardService.save(card);
        }
        return "success";
    }
}
