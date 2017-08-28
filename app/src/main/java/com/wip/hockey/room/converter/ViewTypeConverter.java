package com.wip.hockey.room.converter;


import android.arch.persistence.room.TypeConverter;

import com.wip.hockey.fragment.ViewType;

public class ViewTypeConverter {
    @TypeConverter
    public ViewType fromIntToViewType(int value) {
        for ( ViewType type : ViewType.values()) {
            if ( type.ordinal() == value){
                return type;
            }
        }
        return null;
    }

    @TypeConverter
    public int viewTypeToInt(ViewType viewType) {
        return viewType.ordinal();
    }
}