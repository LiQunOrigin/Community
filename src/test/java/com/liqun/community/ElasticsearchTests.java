package com.liqun.community;

import com.liqun.community.dao.DiscussPostMapper;
import com.liqun.community.dao.elasticsearch.DiscussPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community
 * @className: ElasticsearchTests
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/31 12:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ElasticsearchTests {
    @Autowired
    private DiscussPostMapper mapper;

    @Autowired
    private DiscussPostRepository repository;

    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void testInsert() {
        repository.save(mapper.selectDiscussPostById(241));
        repository.save(mapper.selectDiscussPostById(242));
        repository.save(mapper.selectDiscussPostById(243));
    }


}
