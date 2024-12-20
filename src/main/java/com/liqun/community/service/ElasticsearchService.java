package com.liqun.community.service;

import com.liqun.community.dao.elasticsearch.DiscussPostRepository;
import com.liqun.community.entity.DiscussPost;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @projectName: community
 * @package: com.liqun.community.service
 * @className: ElasticsearchService
 * @author: LiQun
 * @description: TODO
 * @data 2024/10/31 13:52
 */
@Service
public class ElasticsearchService {
    @Autowired
    private DiscussPostRepository discussPostRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void saveDiscussPost(DiscussPost post) {
        discussPostRepository.save(post);
    }

    public void deleteDiscussPost(int id) {
        discussPostRepository.deleteById(id);
    }

    public Page<DiscussPost> searchDiscussPost(String keyword, int current, int limit) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyword, "title", "content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(current, limit))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                )
                .build();
        return elasticsearchTemplate.queryForPage(searchQuery, DiscussPost.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                SearchHits searchHits = response.getHits();
                if (searchHits.getTotalHits() <= 0) {
                    return null;
                }
                List<DiscussPost> list = new ArrayList<>();
                for (SearchHit hit : searchHits) {
                    DiscussPost post = new DiscussPost();
                    String id = hit.getSourceAsMap().get("id").toString();
                    post.setId(Integer.valueOf(id));
                    post.setTitle((String) hit.getSourceAsMap().get("title"));
                    post.setContent((String) hit.getSourceAsMap().get("content"));
                    post.setCreateTime(new Date((Long) hit.getSourceAsMap().get("createTime")));
                    post.setScore(Double.valueOf(hit.getSourceAsMap().get("score").toString()));
                    post.setStatus(Integer.valueOf(hit.getSourceAsMap().get("status").toString()));
                    post.setCommentCount(Integer.valueOf(hit.getSourceAsMap().get("commentCount").toString()));

                    // 处理高亮显示的结果
                    HighlightField titleField = hit.getHighlightFields().get("title");
                    if (titleField != null) {
                        post.setTitle(titleField.fragments()[0].toString());
                    }
                    HighlightField contentField = hit.getHighlightFields().get("content");
                    if (contentField != null) {
                        post.setContent(contentField.fragments()[0].toString());
                    }
                    list.add(post);
                }
                return new AggregatedPageImpl(list, pageable,
                        searchHits.getTotalHits(),response.getAggregations(), response.getScrollId(),searchHits.getMaxScore());

            }
        });
    }

    public void updateDiscussPost(int id, String title, String content) {
        DiscussPost post = discussPostRepository.findById(id).orElse(null);
    }

}
