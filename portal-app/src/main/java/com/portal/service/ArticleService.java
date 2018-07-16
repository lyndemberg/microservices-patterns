package com.portal.service;

import com.portal.model.Article;
import com.portal.repository.ArticleRepository;
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
