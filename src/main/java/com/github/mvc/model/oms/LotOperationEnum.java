package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */
public enum LotOperationEnum {

    /**
     * 批次暂停，将批次暂停在当站，使其无法进入下站
     */
    LOT_HOLD(0,"暂停","该操作使用于批次出现特殊情况，需暂停生产，对批次进行暂停，使其不能进行过账等操作," +
            "1．点击“Lot”菜单按钮，加载出“Lot Information Menu”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的批次，点击“Lot Hold”按钮，成功跳转到“Lot Hold”界面。" +
            "2．跳转到“Lot Hold”界面后，页面展示Lot ID，Process Flow ID 等信息，选择部门，区域，原因代码，紧急程度，点击“Confirm”按钮。" +
            "3．提交成功后，系统给出成功提示，并且批次状态发生改变，变为“ONHOLD”。"),

    /**
     * 取消暂停
     */
    RELEASE(1,"取消暂停","该操作用于对批次下的暂停记录进行取消暂停," +
            "1. 点击“Lot”菜单按钮，加载出“Lot Information Menu”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的批次，点击“Release”按钮，成功跳转到“Lot Hold List”界面。" +
            "跳转到“Lot Hold List”界面后，页面展示Lot Hold操作记录信息，选择要Release的记录，点击“Hold Release”按钮。" +
            "3.跳转到“Lot Hold Release”界面，选择原因代码，点击“Confirm”按钮。" +
            "4.提交成功后，系统给出成功提示，在该批次下的所有Lot Hold List 都Release成功后，批次状态发生改变，变为“Waiting”。"),

    /**
     * 分批，从当前批次中分出一个子批
     * 该操作用于生产过程中将一个母批中的一片Wafer或者多片Wafer分出一个子批
     */
    SPLIT(2,"分批","该操作用于生产过程中将一个母批中的一片Wafer或者多片Wafer分出一个子批," +
            ". 点击“Lot”菜单按钮，加载出“Lot Information Menu”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的批次，点击“Split”按钮，成功跳转到“Spilt Lot”界面。" +
            "2.跳转到“Spilt Lot”界面后，页面展示批次Wafer ID，晶舟等信息，选择一个或多个槽位的Wafer，点击“Confirm”按钮。" +
            "3. 提交成功后，系统给出成功提示，跳转到“Lot Spilt Result”界面，界面展示分出的子批的Lot ID和所关联的载具信息，点击“Confirm”按钮。" +
            "4.提交成功后，页面跳转到“Lot Information Menu”界面，Wafer Qty减少分批出的Wafer数。"),

    /**
     * 合批，将分出来的子批合并到母批中
     */
    MERGE(3,"合批","该操作使用于将分出来的子批合并到他的母批中去," +
            "1. 点击“Lot”菜单按钮，加载出“Lot Information Menu”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的母批（合批时选择母批，带出其相关子批，选择子批，带出母批，但是只能使用母批去合子批），点击“Merge”按钮，成功跳转到“Lot Merge for Wafer Lot”界面。" +
            "2.跳转到“Lot Merge for Wafer Lot”界面后，页面展示Lot ID，Status，Process Flow ID，数量等，确认信息无误后，选择此次需要合批的子批，点击“Confirm”按钮。" +
            "3. 提交成功后，跳转到“Lot Merge Beyond Lot Family Confirm”界面，界面展示目标批和被合批的Lot ID信息及被合批的Wafer的槽位信息，点击“Confirm”按钮。" +
            "4.提交成功后，页面跳转到“Lot Information Menu”界面，Wafer Qty增加合批进来的Wafer数。"),

    /**
     * 站点操作历史
     */
    OPERATION_HISTORY(4,"操作历史","该操作用于查看批次在各个站点的操作历史," +
            "1. 点击“Lot”菜单按钮，加载出“Lot Information Menu”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的批次，点击“Operation History”按钮，成功跳转到“Operation Selection For History”界面。" +
            "2.跳转到“Operation Selection For History”界面后，页面展示该批次所经历的站点信息，选择 一个站点，点击“select”按钮，跳转到“Operation History”界面。" +
            "3.跳转到“Operation History”界面后，页面展示历史记录信息，包括事件报告时间，操作者ID，操作类型，流程ID，站点ID等信息。" +
            "4.选中一条操作记录，点击“Detail Info”按钮，跳转到“Operation History Detail Information”界面，即可查看该操作记录相关详细信息，包括批次信息，流程信息，设备信息，其他信息。" +
            "5. 选中一条操作记录，点击“Measurement”按钮，跳转到“Measurement Data History”界面 6. 选中一条操作记录，点击“Process History”按钮，跳转到“Measurement Data History”界面。"),

    /**
     * 跳站
     */
    SKIP(5,"跳站","该操作用于需将当前批次进行跳跃站点时，可跳过某一站点或多个站点，直接进入目标站点，以此站点为界，可往此站后站跳，亦可往前站跳，" +
            "1. 点击“Lot”菜单按钮，加载出“Lot Information Menu”界面，输入查询条件，点击“Search”按钮，查询出符合查询条件的批次，点击“Skip”按钮，成功跳转到“Skip”界面。" +
            "2.跳转到“Skip”界面后，根据实际情况，以当前站点为界，在流程中，目标站点在此站点之前，点击“Backward”按钮， 目标站点在此站点之后，点击“Forward”按钮。" +
            "3.点击“Backward”或“Forward”按钮后，成功跳转到“Operation Selection”界面，页面展示符合选择的站点，选中站点，点击“Select”按钮。" +
            "4.选择成功后，成功加载选择站点的信息，确认信息无误后，点击“Confirm”按钮。" +
            "5.提交成功后，系统给出成功提示，该批次的Operation ID变为跳站时选择的目标站点。");

    private String description;

    private String details;

    private int index;

    LotOperationEnum(int index, String description, String details) {
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
