package com.liqun.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.util
 * @className: MailClient
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/18 17:38
 */
@Component
public class MailClient {


    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender mailSender;


    //邮件发送者
    @Value("${spring.mail.username}")
    private String from;


    public void sendMail(String to,String subject,String content){
        try {
            //创建邮件对象
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            mailSender.send(helper.getMimeMessage());
        } catch (Exception e) {
            logger.error("发送邮件失败:" + e.getMessage());
        }
    }
}
