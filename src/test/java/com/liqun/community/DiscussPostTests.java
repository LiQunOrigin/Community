package com.liqun.community;

import com.liqun.community.entity.DiscussPost;
import com.liqun.community.service.DiscussPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community
 * @className: DiscussPostTests
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/22 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class DiscussPostTests {
    //���Է�������
    @Test
    public void testSaveDiscussPost() {
        DiscussPostService discussPostService = new DiscussPostService();
        DiscussPost post = new DiscussPost();
        post.setUserId(111);
        post.setTitle("Hello");
        post.setContent("���˱���");
        discussPostService.addDiscussPost(post);
        System.out.println(post.getId());
    }


}
