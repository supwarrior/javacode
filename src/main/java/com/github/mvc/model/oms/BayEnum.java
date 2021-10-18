package com.github.mvc.model.oms;

/**
 * @author 康盼Java开发工程师
 */
public enum BayEnum {

    WHATS_NEXT(0,"What's next为对Lot进行优先级排序，展示给用户按照各种算法逻辑计算出的该设备生产批次的优先级的列表",
            "1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay的信息，选中一条Bay，界面下方即可加载显示出Bay Status信息。\n" +
                    "2.在Bay ID查询输入框中输入信息，点击“Filter”按钮，查询想要的Bay，选中该Bay下需要进行What's Next操作的设备（此步操作针对于设备，所以需选择Eqp Type字段有值的记录），点击“What's Next”按钮。\n" +
                    "3.点击“What's Next”按钮，页面成功跳转到Equipment Information  What's Next界面，该页面展示给用户按照各种算法逻辑计算出的该设备生产批次的优先级的列表。可在此界面选中批次进行Next，Monitor Lots，PM Lot Start,Sorter Job Create的操作。\n"),
    STATION_INFO(1,"该操作用于需要查看Bay下的站点的详细信息， 并可在跳转后的Stocker界面对该Stocker进行操作","1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay的信息，选中一条Bay，界面下方即可加载显示出Bay Status信息。\n" +
            "在Bay ID查询输入框中输入信息，点击“Filter”按钮，查询想要的Bay，选中该Bay下需要进行Station Info操作的Station（此步操作针对于Station，所以需选择Station Type字段有值的记录），点击“Station Info”按钮。\n" +
            "3.点击“Station Info”按钮，页面成功跳转到Stocker界面，该页面展示给用户上一步操作中选中的Station的详细信息。可在此界面进行Stock Out，Stock In,Where Next,Status Change, Inventory UpLoad，NPW Reserve,Inter Bay Xfer,CDA,Alarm List的操作。\n" +
            ""),
    EQUIPMENT_INFO(2,"","1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay的信息，选中一条Bay，界面下方即可加载显示出Bay Status信息。\n" +
            "2.在Bay ID查询输入框中输入信息，点击“Filter”按钮，查询想要的Bay，选中该Bay下需要进行Equipment Info操作的设备（此步操作针对于设备，所以需选择Eqp Type字段有值的记录），点击“Equipment Info”按钮。\n" +
            "3. 点击“Equipment Info”按钮，页面成功跳转到Equipment界面，该页面展示给用户上一步操作中选中的Equipment的详细信息。可在此界面进行Dispatch，Reserve Info，Status Change等操作。\n" +
            ""),
    TRANSFER_JOB_STATUS(3,"该操作用来转变Job的状态","1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay的信息，选中一条Bay，界面下方即可加载显示出Bay Status信息。\n" +
            "2.在Bay ID查询输入框中输入信息，点击“Filter”按钮，查询想要的Bay，选中该Bay下需要进行Transfer Job Status操作的设备或Station，点击“Transfer Job Status”按钮。\n" +
            ""),
    AUTO_STATION(4,"该操作用于快捷选择Station Type为auto的Station进行接下来的操作"," 1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay的信息，点击“Auto Station”按钮。 \n" +
            "2．点击“Auto Station”按钮后，弹出Station Selection界面，选择一条Auto Station，点击“Select”按钮，跳转“Stocker”界面。 \n" +
            ""),
    MANUAL_SHELF(5,"普通的货架，该操作用于快捷选择Station Type为shelf的Station进行接下来的操作","1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay的信息，点击“Manual Shelf”按钮 \n" +
            "2．点击“Manual Shelf”按钮后，弹出Station Selection界面，选择一条Station，点击“Select”按钮，跳转“Stocker”界面。 \n" +
            ""),
    RETICLE_SHELF(6,"光罩货架，该操作用于快捷选择Station Type为ReticleShelf的Station进行接下来的操作","1.点击“Bay”菜单，加载Bay Menu界面，界面上方为Bay Information,用于展示Bay息，点击“Reticle Shelf”按钮。 \n" +
            "2. 点击“Reticle Shelf”按钮后，弹出Station Selection界面，选择一条Station，点击“Select”按钮，跳转“Stocker”界面。" +
            "");

    private String description;

    private String details;

    private int index;

    BayEnum(int index, String description, String details) {
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
