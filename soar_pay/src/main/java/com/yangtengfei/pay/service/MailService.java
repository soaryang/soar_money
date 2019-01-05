package com.yangtengfei.pay.service;


import com.yangtengfei.pay.view.CardView;
import com.yangtengfei.pay.view.PayInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private FreemarkUtilService freemarkUtilService;

    public void sendSimpleMail(List<CardView> cardViewList){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo("759620299@qq.com");//发送给谁
            helper.setSubject("【" +"还钱" + "】");//邮件标题
            helper.setText(freemarkUtilService.getPayCotent(cardViewList), true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }
    }
}
