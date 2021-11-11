package com.github.esec.entityListeners.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PersistenceContext
    protected EntityManager entityManager;

    @GetMapping("/test")
    @Transactional
    public void test() throws Exception {
        final Admin article = new Admin();
        article.setFullName("article");
        entityManager.persist(article);
        entityManager.flush();
    }
}
