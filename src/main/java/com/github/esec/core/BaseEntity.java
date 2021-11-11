package com.github.esec.core;

import com.github.annotation.DefaultValue;
import com.github.common.cons.CodeEnum;
import com.github.common.util.DateUtils;
import com.github.ddd.jpa.CoreHelperImpl;
import com.github.mycim.common.support.OmsBizConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
@Slf4j
public class BaseEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = SnowflakeIDWorker.STRATEGY_REFERENCE)
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 64)
    private String id;


    @PrePersist
    private void defaultValue() {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        if (declaredFields.length > 0) {
            Arrays.stream(declaredFields).forEach(field -> {
                DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
                if (null != defaultValue) {
                    ReflectionUtils.makeAccessible(field);
                    Object value = ReflectionUtils.getField(field, this);
                    if (null == value) {
                        Type genericType = field.getGenericType();
                        CoreHelperImpl.FiledTypeEnum fileType = CoreHelperImpl.FiledTypeEnum.getFileType(genericType.getTypeName());
                        Assert.isTrue(null != fileType, "Filed Type is null.");
                        try {
                            switch (defaultValue.type()) {
                                case OTHER:
                                    switch (fileType) {
                                        case CORE_STRING:
                                            ReflectionUtils.setField(field, this, defaultValue.stringValue());
                                            break;
                                        case CORE_INT:
                                            ReflectionUtils.setField(field, this, defaultValue.intValue());
                                            break;
                                        case CORE_DOUBLE:
                                            ReflectionUtils.setField(field, this, defaultValue.doubleValue());
                                            break;
                                        case CORE_BOOLEAN:
                                            ReflectionUtils.setField(field, this, defaultValue.boolValue());
                                            break;
                                        case CORE_CHAR:
                                            ReflectionUtils.setField(field, this, defaultValue.charValue());
                                            break;
                                        case CORE_LONG:
                                            ReflectionUtils.setField(field, this, defaultValue.longValue());
                                            break;
                                        case CORE_SHORT:
                                            ReflectionUtils.setField(field, this, defaultValue.shortValue());
                                            break;
                                        case CORE_TIME:
                                            ReflectionUtils.setField(field, this, new Timestamp(System.currentTimeMillis()));
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case CURRENT_TIME:
                                    switch (fileType) {
                                        case CORE_TIME:
                                            ReflectionUtils.setField(field, this, new Timestamp(System.currentTimeMillis()));
                                            break;
                                        case CORE_STRING:
                                            ReflectionUtils.setField(field, this, DateUtils.dateToString(new Date(), DateUtils.PATTEN_DATE_FORMAT_DATETIME));
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                case INIT_TIME:
                                    switch (fileType) {
                                        case CORE_TIME:
                                            Timestamp initTime = new Timestamp(Objects.requireNonNull(DateUtils.stringToDate(OmsBizConstants.OMS_TIMESTAMP_NIL_OBJECT_STRING, DateUtils.PATTEN_DATE_FORMAT_DATETIME)).getTime());
                                            ReflectionUtils.setField(field, this, initTime);
                                            break;
                                        case CORE_STRING:
                                            ReflectionUtils.setField(field, this, OmsBizConstants.OMS_TIMESTAMP_NIL_OBJECT_STRING);
                                            break;
                                        default:
                                            break;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            String message = String.format("Set Default value failed.Check the value is right -> [%s : %s]",
                                    this.getClass().getSimpleName(), field.getName());
                            log.error("conversion default value : {}", message);
                            throw new RuntimeException(CodeEnum.SYSTEM_ERROR.code());
                        }
                    }
                }
            });

        }
    }
}
