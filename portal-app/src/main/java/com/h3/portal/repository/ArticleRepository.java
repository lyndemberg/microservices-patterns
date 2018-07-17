package com.h3.portal.repository;

import com.h3.portal.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article,String> {
    public List<Article> findAllByTitle(String title);
}
