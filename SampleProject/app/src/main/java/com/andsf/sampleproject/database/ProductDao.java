package com.andsf.sampleproject.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.andsf.sampleproject.ModelClass;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM PRODUCTDETAILS")
    List<ProductsDetailsToDB> getAll();

    @Insert
    public void addproduct(ProductsDetailsToDB prodcutsDetailsToDB);

}
