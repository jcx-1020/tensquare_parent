package com.tensquare.search.service;


import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 文章业务逻辑层
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;


    /**
     * 增加文章
     * @param article
     */
    public void save(Article article){
        articleDao.save(article);
    }

    /**
     * 根据关键字搜索文章
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        return articleDao.findByTitleOrContentLike(key,key,pageable);
    }
}
