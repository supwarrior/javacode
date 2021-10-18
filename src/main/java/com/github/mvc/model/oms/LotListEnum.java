package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */
public enum LotListEnum {

    DETAIL_INFO(0,"该功能模块用于查询查看选中Lot的详细信息","点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“Detail Info”按钮。" +
            "2. 点击“Detail Info”按钮后，成功跳转到“Lot Information ”界面。页面默认在“Base Information”界面，页面展示该Lot的基础信息和状态信息，该页面详细操作功能介绍见“Lot”功能模块介绍。"),
    BANK_IN(1,"该功能模块对选中的lot执行Bank in(入库)操作","1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“Bank in”按钮。" +
            "2. 点击“Bank in”按钮后，成功跳转到“Bank in Confirm”界面。点击“Confirm ”确认Bank in或者点击“Cancel”取消操作"),
    BANK_IN_CANCEL(2,"该功能模块对选中的lot执行取消Bank in(入库)操作","1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“Bank in Cancel”按钮，可以对已经执行Bank in的lot取消Bank in操作。" +
            ""),
    LOT_START_CANCEL(3,"该功能对Lot执行Lot Start Cancel的操作","1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“Lot Start Cancel”按钮。" +
            "2. 点击“Lot Start Cancel”按钮后，成功跳转到“Lot Start ”界面 ，该页面详细操作功能介绍见“Lot Start”功能模块介绍。"),
    NEW_RUN_CARD(4,"该功能对于lot进行新建Run Card的操作"," 1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“New Run Card”按钮，跳转到”Run Card List”功能的界面"),
    PSM_LIST(5,"该功能对Lot分/合批进行设定","1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“PSM List”按钮，跳转到”PSM List”功能的界面，详细见Lot主模块下的功能介绍该功能对Lot分/合批进行设定"),
    DOC_SETUP(6,"该功能对Lot进行DOC设定","1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“DOC SetUp”按钮，跳转到”DOC SetUp”功能的界面，详细见Lot主模块下的功能介绍"),
    WIP_LOT_RESET(7,"该功能对Lot进行操作重新进站","1. 点击“MISC”下的“Lot List”菜单按钮，加载出“Lot List”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的Lot，选中一条Lot记录，点击“WIP Lot Reset”按钮2. 跳出到WIP Lot Reset的确认界面，点击“Yes”确认后，Lot会重新进站");

    private String description;

    private String details;

    private int index;

    LotListEnum(int index, String description, String details) {
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
