package com.xyj.core.popularize.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConsultStatus implements EnumType{

    @SerializedName("0")
    HANDLED(0, "已处理"),

    @SerializedName("1")
    UNHANDLE(1, "未处理");

    private int    id;
    private String description;

    public static ConsultStatus getById(int id){
        for(ConsultStatus status : ConsultStatus.values()){
            if(status.id == id){
                return status;
            }
        }

        return UNHANDLE;
    }
}
