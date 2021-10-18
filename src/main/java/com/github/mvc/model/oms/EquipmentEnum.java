package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */

public enum EquipmentEnum {

    WHATS_NEXT(0,"该操作用于查看该设备下可以进行生产的批次，以便进行接下来操作","" +
            "1. 点击“Equipment”菜单按钮，加载“Equipment Information”界面，查询或者选择机台，加载该设备总体信息，页面默认在“General”界面，点击“what's next”按钮。" +
            "2．成功跳转到“Equipment Information\\ what's next”界面，该页面展示Wip Lots信息。" +
            "3.点击“Next”，跳转到“Equipment Information/ what's Next/Select Lot List”界面，选择可以在本设备加工的批次，点击“Move In Reserve”按钮。" +
            "4.页面跳转到“Move In Reserve”页面，页面展示批次和端口信息，选择一条Carrier Assignment信息，点击“Edit”按钮，跳转“Edit for Carrier Assignment”界面，确认填写信息无误后，点击“Confirm”按钮，页面跳转到“Move In Reserve”页面，点击“Confirm”按钮。" +
            "5.提交成功后，选择的端口就添加了Carrier信息，点击“Confirm”按钮，预约成功。"),
    MOVE_IN_RESERVE_CANCEL(1,"该功能用于取消预约的操作","1. 点击“Equipment”菜单按钮，加载“Equipment Information”界面，查询或者选择机台，加载该设备总体信息，页面默认在“General”界面，点击“Move In Reserve Cancel”按钮。" +
            "2．跳转到“Move In Reservation List”界面，该界面展示该机台的端口的预约信息，选中一条信息，点击“Reserve Cancel”按钮，对此次预约进行取消操作。" +
            ""),
    STATUS_CHANGE(2,"该功能用来改变机台的E10状态。","1. 点击“Equipment”菜单按钮，加载“Equipment Information”界面，查询或者选择机台，加载该设备总体信息，页面默认在“General”界面，点击“Status Change”按钮。" +
            "2.选择需要修改的E10状态，系统会带出该状态下的子状态，选择一条子状态，点击“Confirm”。" +
            "3.提交成功后，系统给出成功提示，并且机台E10状态修改成功。"),
    PORT_STATUS_RECOVER(3,"",""),
    EQUIPMENT_HISTORY(4,"","");


    private String description;

    private String details;

    private int index;

    EquipmentEnum(int index, String description, String details) {
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
