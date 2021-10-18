package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */
public enum LotStartEnum {

    SELECT_TO_LOT_START(0,"该操作可将从SPS生成的Product Order形成的批次时得到Lot ID，Product ID，count等信息,关联从Source Bank接受的原物料信息，选择Carrier，下线到Process中","" +
            "1.点击“Lot Start”菜单按钮，加载出“Product Order Information”界面，输入查询条件，查询出符合查询条件的Product Orders，选中Lot，点击“Select To Lot Start”按钮。" +
            "2. 点击“Select To Lot Start”按钮后，成功跳转到“Product Order Information”界面。页面展示Product Order基本信息，点击“Refresh”按钮，加载从物料房Material Receive的该Product的Source Lot，选中一个批次，根据查询条件选择carrier。" +
            "3. 输入查询条件，查询选择载具，点击“Confirm”按钮。" +
            "4.点击按钮，填写数量，点击“Confirm”按钮。" +
            "5.提交成功后，Carrier的槽位会按照填写数量进行装载，点击“Confirm”按钮。"),
    LOT_START_CANCEL(1,"该操作可对已经进行过Lot Start的产品批次进行取消操作，归还数量到产品批次。","" +
            "1.点击“Lot Start”菜单按钮，加载出“Product Order Information”界面，点击“Lot Start Cancel”按钮，成功跳转到Lot Start Cancel界面。" +
            "2.输入选择查询条件，点击“Search”按钮，选中需要进行Lot Start Cancel操作的批次，点击“Lot Start Cancel”按钮，跳转到Lot Start Cancel二级界面。" +
            "3．Lot Start Cancel二级界面展示该批次之前进行Lot Start的供应商批次，选中供应商批次，点击“Lot Start Cancel”按钮。");

    private String description;

    private String details;

    private int index;

    LotStartEnum(int index, String description, String details) {
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
