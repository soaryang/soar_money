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


    @Value("${mail.to}")
    private String mailto;



    @Autowired
    private FreemarkUtilService freemarkUtilService;

    public void sendSimpleMail(CardView cardView){
        try {

            String[] mailList = mailto.split(",");
            for(int i=0; i<mailList.length; i++){
                Thread.sleep(5000);
                log.info("【"+cardView.getBank()+"-"+cardView.getPayType() +"还钱" + "】"+"to"+mailList[i]);
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(from);
                helper.setTo(mailList[i]);//发送给谁
                helper.setSubject("【"+cardView.getBank()+"-"+cardView.getPayType() +"还钱" + "】");//邮件标题
                helper.setText(freemarkUtilService.getPayCotent(cardView), true);
                javaMailSender.send(mimeMessage);
            }
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }
    }
}
