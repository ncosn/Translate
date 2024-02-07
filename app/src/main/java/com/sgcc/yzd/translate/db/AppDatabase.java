package com.sgcc.yzd.translate.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sgcc.yzd.translate.db.dao.DeviceDao;
import com.sgcc.yzd.translate.db.dao.InstructionDao;
import com.sgcc.yzd.translate.db.entity.Device;
import com.sgcc.yzd.translate.db.entity.Instruction;

@Database(entities = {
        Device.class,
        Instruction.class
}, version=1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase mInstance;
    /**
     * 数据库名称
     */
    private static String DATABASE_NAME = "jqr.db";
    public abstract DeviceDao deviceDao();
    public abstract InstructionDao instructionDao();


    public synchronized static AppDatabase getInstance(final Context context) {
        //Check condition
        if (mInstance == null) {
            mInstance = buildDatabase(context.getApplicationContext());
        }
        return mInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        return appDatabase;
    }
}
