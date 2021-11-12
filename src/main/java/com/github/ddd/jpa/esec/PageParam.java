package com.github.ddd.jpa.esec;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.Predicate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageParam extends BasicParam {
    /**
     * 页码
     */
    private Integer page = 1;
    /**
     * 数量
     */
    private Integer size = 10;
    /**
     * count
     */
    @NotGeneratedMapParam
    private String countReplace;

    public Pageable of() {
        return PageRequest.of(page - 1, size);
    }

    public Pageable of(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

    public List<Predicate> predicate() {
        return Lists.newArrayList();
    }
}
