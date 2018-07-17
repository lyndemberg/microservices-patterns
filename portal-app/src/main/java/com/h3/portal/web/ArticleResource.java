package com.h3.portal.web;

import com.h3.portal.model.Article;
import com.h3.portal.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleResource {

    private final ArticleService articleService;

    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> buscarTodosPorTitulo(@RequestParam("title") String titulo){
        return ResponseEntity.ok().body(articleService.buscarPorTitulo(titulo));
    }

}
