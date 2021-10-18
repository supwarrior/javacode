package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */
public enum BankOperationEnum {

    LOT_INFO(0,"查看储存仓下的批次详细信息","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。;2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条Lot，点击“ Lot Info”按钮。 ;3. 点击“ Lot Info”按钮后，页面跳转到“Lot Information Menu”，界面展示批次详细信息，批次相关操作详见批次操作说明。"),
    MATERIAL_RECEIVE(1,"接受原材料批次到某指定仓库","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，点击“Material Receive”按钮，弹出“Material Receive”界面。3．在弹出的“Material Receive”界面中填写或选择Lot ID,Sub Lot Type,数量，产品ID等信息，点击提交，提交成功后，批次列表中最后一页最后一条记录即为新接受批次信息，批次状态为completed。"),
    MATERIAL_PREPARE(2,"原材料批次和晶舟做关联，并指定晶圆数量","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条Lot，点击“ Material Prepare”按钮。 3.成功跳转到“Material Prepare界面”，在此界面选择Lot Type，Sub Lot Type，查询选择需要关联晶舟，填写此次准备数量，添加到右边晶舟槽位，确认提交，提交成功后，可在批次列表中的最后一页最后一条记录中查看晶舟信息。"),
    SHIP(3,"对Bank中的此次进行出货","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条支持出货的Bank记录（Bank信息中Ship字段为Yes），加载出该储存库中的原材料批次，选中一条状态为“Completed”的Lot，点击“ Ship”按钮。3.成功跳转“Ship Confirm”界面，确认信息无误后，点击“Confirm”，确认出货，出货成功后，该批次的Status应变为SHIPPED。"),
    MATERIAL_RETURN(4,"将位置在仓库的原材料批次返还或返还部分数量","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条Lot，点击“ Material Return”按钮。3．成功跳转到“Material Return”界面，填写或选择Lot ID,Sub Lot Type,数量，产品ID等信息，点击提交，提交成功后，批次列表中的最后一页最后一条记录即为做过返还供应商批次操作的批次，数量减少返还数量，完全返还情况下，批次信息清除，不再存在。"),
    MATERIAL_PREPARE_CANCEL(5,"逻辑取消之前为原材料批次所做的准备","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条之前做过Material Prepare操作的Lot（批次信息有晶舟信息），需在MISC模块下的Wafer Slot Change功能里释放晶舟关联的批次信息，完成此操作后，Bank下的该批次的晶舟信息被清除，点击“ Material Prepare Cancel”按钮。3．跳转Material Prepare Cancel界面，确认信息无误后，点击“Confirm”按钮，提交成功后，页面跳转到Bank界面，可在批次列表中的最后一页倒数两条记录发现原批次状态变为EMPTIED，且生成一条新的批次信息。"),
    MOVE_BANK(6,"使用于生产过程中一个仓库中的批次需要移动到符合条件的另一个仓库时","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条Lot，点击“Move Bank ”按钮。3.跳转到“Move Bank”界面，此界面展示当前批次基本信息及所在仓库ID，可下拉选择或在页面点击“Search”按钮，查询选择目标仓库，点击“Confirm”进行提交，操作成功后，即可在目标仓库下查询到该批次信息。"),
    SHIP_CANCEL(7,"对Bank中已经进出货操作的批次进行取消出货操作","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条状态为Shipped的Lot，点击“Ship Cancel ”按钮。3．成功跳转“Ship Cancel Confirm”界面，确认信息无误后，点击“Confirm”，确认取消发货，取消发货成功后，该批次的Status应变为Completed。"),
    BANK_HOLD(8,"对Bank中的批次进行暂停","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条Lot，点击“Bank Hold ”按钮。3. 成功跳抓到“Bank Hold”操作界面，选择原因代码，点击“Confirm”按钮，确认提交，提交成功后，批次的状态变为ONHOLD，此状态下的批次不可进行Ship,Move Bank,Material Prepare等操作。"),
    BANK_HOLD_RELEASE(9,"可释放仓库中暂停状态的批次","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2. 选中一条Bank记录，加载出该储存库中的原材料批次，选中一条状态为ON HOLD的Lot，点击“Bank Hold Release ”按钮。3.成功跳转“Bank Hold Release”操作界面，选择原因代码，点击“Confirm”按钮，确认提交，提交成功后，批次的状态发生改变。"),
    CDA(10,"可用于进行客制化仓库属性信息","1.点击“Bank”菜单按钮，加载出界面，在“Bank ID”文本框输入Bank ID信息，查询出符合查询条件的Bank。2.点击“CDA”按钮，成功跳转到“Customized Defined Attribute Information”界面，可在界面进行新增，删除，更新操作，点击“add”按钮，弹出Customized Defined Attribute Information 信息输入界面，填写相关信息，点击“提交”，即可新增一条信息。");

    private String description;

    private String details;

    private int index;

    BankOperationEnum(int index, String description, String details) {
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
