package com.yangtengfei.pay.service;

import freemarker.template.Template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class FreemarkUtilService {


    //邮件的发送者
    @Value("cardList.ftl")
    private String payFreemarker;

    @Autowired
    private FreeMarkerConfigurer configurer;


    public String getPayCotent(Object object){
        String text = "";
        try {
            Template template = configurer.getConfiguration().getTemplate(payFreemarker);
            Map<String, Object> model = new HashMap<>();
            model.put("list", object);
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}
