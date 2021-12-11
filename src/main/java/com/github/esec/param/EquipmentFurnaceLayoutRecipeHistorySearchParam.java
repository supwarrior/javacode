package com.github.esec.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EquipmentFurnaceLayoutRecipeHistorySearchParam {

    @NotBlank
    private String equipmentId;
}
