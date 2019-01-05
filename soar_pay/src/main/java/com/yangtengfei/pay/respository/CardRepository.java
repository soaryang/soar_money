package com.yangtengfei.pay.respository;

import com.yangtengfei.pay.bean.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface CardRepository extends MongoRepository<Card,String> {
}