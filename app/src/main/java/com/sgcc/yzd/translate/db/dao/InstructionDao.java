package com.sgcc.yzd.translate.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sgcc.yzd.translate.db.entity.Device;
import com.sgcc.yzd.translate.db.entity.Instruction;

import java.util.List;

@Dao
public interface InstructionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInstruction(Instruction instruction);

    @Delete
    void deleteInstruction(Instruction instruction);

    @Query("SELECT * FROM instruction")
    List<Instruction> queryAllInstructions();

    @Query("SELECT * FROM instruction WHERE" +
            " :message LIKE '%' || key_words || '%' AND key_words IS NOT NULL")
    Instruction queryInstruction(String message);

}
