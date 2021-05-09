package com.andsf.sampleproject.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "ProductDetails")
public class ProductsDetailsToDB {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int product_id;

    @ColumnInfo(name = "ProductName")
    private String productname;

    @ColumnInfo(name = "ProductDescription")
    private String productdes;

    @ColumnInfo(name = "Quantity")
    private String Quantity;

    @ColumnInfo(name = "Price")
    private String price;

    @ColumnInfo(name = "PhoneNumber")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductdes() {
        return productdes;
    }

    public void setProductdes(String productdes) {
        this.productdes = productdes;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}