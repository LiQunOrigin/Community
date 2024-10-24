package com.liqun.community;

import com.liqun.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community
 * @className: MailTests
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/18 17:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;


    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void testSendMail() {
        mailClient.sendMail("2915132212@qq.com","Test","Welcome");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username","sunday");
        String content = templateEngine.process("/mail/demo",context);
        mailClient.sendMail("2915132212@qq.com","Test",content);
    }
}
