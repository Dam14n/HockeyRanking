package com.wip.hockey.room;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.wip.hockey.room.database.AppDataBase;

public class RoomFactory {

    private static final String TAG = RoomFactory.class.toString();

    public static AppDataBase getAdapter(Context context) {
        return Room.databaseBuilder(context,
                AppDataBase.class, "database-name").build();
    }
}
