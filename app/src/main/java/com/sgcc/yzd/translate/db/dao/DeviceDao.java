package com.sgcc.yzd.translate.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sgcc.yzd.translate.db.entity.Device;
import com.sgcc.yzd.translate.db.entity.Instruction;

import java.util.List;

@Dao
public interface DeviceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDevice(Device device);

    @Query("SELECT * FROM device WHERE device_id == :deviceId AND device_id IS NOT NULL")
    Device queryDeviceId(Integer deviceId);

    @Query("SELECT * FROM device")
    List<Device> queryAllDevices();
}
