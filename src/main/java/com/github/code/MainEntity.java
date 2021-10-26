package com.github.code;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;


/**
 * @author 康盼Java开发工程师
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class MainEntity extends BaseEntity {

    private static final long serialVersionUID = 7872938649139372825L;
}
