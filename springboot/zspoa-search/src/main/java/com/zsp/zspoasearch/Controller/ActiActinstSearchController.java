package com.zsp.zspoasearch.Controller;

import com.alibaba.fastjson.JSON;
import com.zsp.utils.R;
import com.zsp.zspoasearch.config.ElasticSearchConfig;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("search/acti")
public class ActiActinstSearchController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @GetMapping("/actinst/list")
    public R searchMemberList(){
//        1、建立搜索引擎
        SearchRequest searchRequest =new SearchRequest("act_actinst");
//        2、建立搜索对象
        SearchSourceBuilder sourceBuilder =new SearchSourceBuilder();
//        3、简历搜索工具
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(sourceBuilder);
        try {
            ArrayList<Map> list = new ArrayList<>();
            SearchResponse search = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            SearchHit[] hits = search.getHits().getHits();
            for (SearchHit hit : hits) {
                Map<String, Object> map = hit.getSourceAsMap();
                list.add((map));
            }
            return R.ok().put("actinstList", JSON.toJSON(list));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.error().put("actinstList",null);
    }
}
