package com.zsp.zspoasearch;

import com.alibaba.fastjson.JSON;
import com.zsp.zspoasearch.config.ElasticSearchConfig;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class ZspoaSearchApplicationTests {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Test
    public void searchAgg() throws IOException {
//            创造检索请求
        SearchRequest searchRequest =new SearchRequest();
//           指定索引
        searchRequest.indices("usertable");
//          封装条件
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
//           给builder里面放检索条件
        searchSourceBuilder.query(QueryBuilders.matchQuery("name","dasdas"));
//            放入聚合查询条件
        TermsAggregationBuilder ageAgg= AggregationBuilders.terms("ageAgg").field("age").size(10);
        searchSourceBuilder.aggregation(ageAgg);
        AvgAggregationBuilder ageAvg = AggregationBuilders.avg("ageAvg").field("age");
        searchSourceBuilder.aggregation(ageAvg);
        System.out.println(searchSourceBuilder);
//           放入搜索资源
        searchRequest.source(searchSourceBuilder);
//           执行查找
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(searchResponse);
//        可以将其封装成map
//        Map map = JSON.parseObject(searchResponse.toString(), Map.class);
//        System.out.println(map);
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();

            JsonRootBean jsonRootBean = JSON.parseObject(sourceAsString, JsonRootBean.class);
            System.out.println(jsonRootBean);
        }
//        获取聚合信息
        Aggregations aggregations = searchResponse.getAggregations();
        Aggregation ageAvg1 = aggregations.get("ageAvg");
        System.out.println(ageAvg1.getMetaData());
    }
    @Test
    public void searchData() throws IOException {
//      1、  创造检索请求
        SearchRequest searchRequest =new SearchRequest();
//          指定索引
        searchRequest.indices("usertable");
//        2、封装条件
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name","dasdas"));

        System.out.println(searchSourceBuilder);
//        3、放入搜索资源
        searchRequest.source(searchSourceBuilder);
//          4、执行查找
        SearchResponse search = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(search);
    }
    @Test
    public void indexData() throws IOException {
        IndexRequest indexRequest= new IndexRequest("users");
        indexRequest.id("1");
        User user =new User();
        user.setUsername("asdasd");
        user.setPassword("asdsad");
        indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);
        System.out.println(index);
    }
    @Data
    class User{
        String username;
        String password;
    }
    @Test
    void contextLoads() {
        System.out.println(restHighLevelClient);
    }

    /**
     * Copyright 2021 bejson.com
     */

    @Data
    public static class JsonRootBean {

        private String name;
        private int age;
        private String address;
        private long balance;
        private String gender;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }
        public int getAge() {
            return age;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }
        public long getBalance() {
            return balance;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
        public String getGender() {
            return gender;
        }

    }
}
