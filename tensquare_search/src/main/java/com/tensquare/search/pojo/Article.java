package com.tensquare.search.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章实体类
 */

@Document(indexName = "tensquare_article",type = "article")
public class Article implements Serializable {

    @Id
    private String id;
    /**
     * 是否索引，看该域是否能被搜索
     * 是否分词，表示搜索的时候是整体匹配还是单词匹配
     * 是否储存，是否在页面上显示
     */
    /**
     * 标题
     */
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 文章正文
     */
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 审核状态
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
