package com.h3.portal.service;

import com.h3.portal.model.Article;
import com.h3.portal.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public List<Article> buscarPorTitulo(String titulo){
        List<Article> lista = articleRepository.findAllByTitle(titulo);
        return lista;
    }
}
