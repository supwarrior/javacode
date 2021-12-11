package com.github.common.util;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.github.common.cons.CodeEnum;
import com.github.resource.I18n;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class ValidatedUtils {

    private static final Validator VALIDATOR = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory().getValidator();


    public static void check(boolean isRun, CodeEnum code) {
        if (isRun) {
            throw new RuntimeException(I18n.getChineseValueByKey(code.getCode()));
        }
    }

    public static void checkParam(Object object) {
        // step1. 验证参数是否为null
        check(Objects.isNull(object), CodeEnum.PARAM_CHECK_ERROR);
        // step3. 验证参数
        Set<ConstraintViolation<Object>> validateResult = VALIDATOR.validate(object);
        validateResult.forEach(objectConstraintViolation -> log.error(objectConstraintViolation.toString()));
        ValidatedUtils.check(CollectionUtils.isNotEmpty(validateResult),CodeEnum.PARAM_CHECK_ERROR);
    }

}
