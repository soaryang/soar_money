package com.yangtengfei.pay.service;


import com.yangtengfei.pay.bean.Card;
import com.yangtengfei.pay.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card findCardById(String id){
        return cardRepository.findOne(id);
    }

    public void save(Card card){
        cardRepository.save(card);
    }

    public List<Card> findAll(){
        return cardRepository.findAll();
    }
}
