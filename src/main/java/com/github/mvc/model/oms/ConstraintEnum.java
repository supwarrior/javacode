package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */

public enum ConstraintEnum {

    ADD(0,"",""),
    EDIT(1,"该操作用于选中特定的一条Constraint进行编辑操作",""),
    CANCEL(2,"该操作用于选中特定的一条Constraint进行取消操作",""),
    EXPORT_DATA(3,"",""),
    REGISTER_EXCEPTION(4,"",""),
    EXCEPTION_LOT_LIST(5,"",""),
    HISTORY(6,"","");

    private String description;

    private String details;

    private int index;

    ConstraintEnum(int index, String description, String details) {
        this.index = index;
        this.description = description;
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }

    public int getIndex() {
        return index;
    }
}
