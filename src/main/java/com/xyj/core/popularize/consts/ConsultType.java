package com.xyj.core.popularize.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsultType implements EnumType{

    @SerializedName("0")
    PRE_SALE(0, "售前咨询"),

    @SerializedName("1")
    AFTER_SALE(1, "售后咨询"),

    @SerializedName("2")
    COOPERATION(2, "合作"),

    @SerializedName("3")
    TRIAL(3, "试用"),

    @SerializedName("4")
    DEMONSTRATION(4, "演示");

    private int    id;
    private String description;

    public static ConsultType getById(int id){
        for(ConsultType type : ConsultType.values()){
            if(type.id == id){
                return type;
            }
        }

        return PRE_SALE;
    }
}
