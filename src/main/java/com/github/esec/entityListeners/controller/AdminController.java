package com.github.esec.entityListeners.controller;

import com.github.common.util.ValidatedUtils;
import com.github.esec.param.EquipmentFurnaceLayoutRecipeHistorySearchParam;
import com.github.esec.person.service.PersonPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private PersonPrivilegeService personPrivilegeService;

    @GetMapping("/test")
    @Transactional
    public void test() throws Exception {
        final Admin article = new Admin();
        article.setFullName("article");
        entityManager.persist(article);

        personPrivilegeService.checkPersonHavePermissionInq("","");
    }

    @PostMapping("/valid")
    public void testValid(@RequestBody EquipmentFurnaceLayoutRecipeHistorySearchParam equipmentFurnaceLayoutRecipeHistorySearchParam) {
        ValidatedUtils.checkParam(equipmentFurnaceLayoutRecipeHistorySearchParam);
    }
}
