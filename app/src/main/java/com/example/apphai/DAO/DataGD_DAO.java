package com.example.apphai.DAO;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apphai.model.MonAn;

import java.util.List;

@Dao
public interface DataGD_DAO {
    @Insert
    void insertData(MonAn data);

    @Query("SELECT *  FROM monan ")
    List<MonAn> getListData();

    @Query("SELECT * FROM monan where resourceId=:rscId and name=:name and price=:gia ")
    List<MonAn> checkData(int rscId, String name, int gia);

    @Update
    void updateData(MonAn data);

    @Delete
    void deleteData(MonAn data);

//    @Query("SELECT COUNT(*) FROM dataGD where giaMua/giaHienTai < 0 ")
//    int getLai();
//
//    @Query("SELECT COUNT(*) FROM dataGD  ")
//    int getTong();
}
