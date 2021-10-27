package com.github.ddd.domainObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author 康盼Java开发工程师
 */
@Getter
@Setter
@MappedSuperclass
@ToString
public class BaseEntity implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;
}