package com.jmmy.jmmyapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insert(DataEntity dataEntity);

    @Delete
    void delete(DataEntity dataEntity);

    @Update
    void update(DataEntity dataEntity);

    @Query("SELECT * FROM jmmy_db")
    List<DataEntity> getDateEntity();

    @Query("SELECT * FROM jmmy_db WHERE id = :id")
    DataEntity getDateEntityById(int id);

    @Query("SELECT * FROM jmmy_db WHERE name = :name")
    DataEntity getDateEntityByName(String name);

}
