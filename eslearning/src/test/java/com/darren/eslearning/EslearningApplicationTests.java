package com.darren.eslearning;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EslearningApplicationTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void contextLoads() {

    }
    @Test
    public void allMatchSearchTest() throws IOException {
        // 请求对象的设置（索引和type）
        SearchRequest request = new SearchRequest("book");
        request.types("computer");

        // 查询条件对象的设置
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.fetchSource(new String[]{"author","price","title"},null);

        // 查询条件对象放入请求对象中
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request);
        System.out.println(response);

        // 获取结果集中的结果
        SearchHits searchHits = response.getHits();
        if(null != searchHits){
            SearchHit[] results = searchHits.getHits();
            for(SearchHit result: results){
                System.out.println(result.getSourceAsMap());//hits.hits._source
            }
        }
    }

    @Test
    public void testPaginating() throws IOException {
        SearchRequest request = new SearchRequest("book");
        request.types("computer");

        // 分页
        int page = 0;
        int size = 2;
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(page);
        builder.size(size);
        builder.query(QueryBuilders.matchAllQuery());
        builder.fetchSource(new String[]{"author","price","title"},null);

        // 查询条件对象放入请求对象中
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request);
        System.out.println(response);

        // 获取结果集中的结果
        SearchHits searchHits = response.getHits();
        if(null != searchHits){
            SearchHit[] results = searchHits.getHits();
            for(SearchHit result: results){
                System.out.println(result.getSourceAsMap());//hits.hits._source
            }
        }
    }

    @Test
    public void testMultiMatchQuery() throws IOException {
        SearchRequest request = new SearchRequest("book");
        request.types("computer");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("设计模式","title","author").minimumShouldMatch("50%"));
        builder.fetchSource(new String[]{"author","price","title"},null);

        // 查询条件对象放入请求对象中
        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request);
        System.out.println(response);

        // 获取结果集中的结果
        SearchHits searchHits = response.getHits();
        if(null != searchHits){
            SearchHit[] results = searchHits.getHits();
            for(SearchHit result: results){
                System.out.println(result.getSourceAsMap());//hits.hits._source
            }
        }
    }

}
