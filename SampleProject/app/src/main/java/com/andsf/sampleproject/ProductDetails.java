package com.andsf.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andsf.sampleproject.database.AppDatabase;
import com.andsf.sampleproject.database.ProductsDetailsToDB;
import com.andsf.sampleproject.databinding.ActivityProductDetailsBinding;

public class ProductDetails extends AppCompatActivity {

    String pname,pp,pdes,pquantity;
    private ActivityProductDetailsBinding activityProductDetailsBinding;
    public static AppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityProductDetailsBinding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = activityProductDetailsBinding.getRoot();
        setContentView(view);
        setTitle("Product Details");
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"ProductDetails").allowMainThreadQueries().build();

        activityProductDetailsBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((activityProductDetailsBinding.productname.getText().toString().isEmpty() && activityProductDetailsBinding.editTextTextMultiLine.getText().toString().isEmpty() &&activityProductDetailsBinding.quantity.getText().toString().isEmpty() && activityProductDetailsBinding.price.getText().toString().isEmpty())){

                    Toast.makeText(ProductDetails.this,"Enter All details",Toast.LENGTH_LONG).show();
                }
               else if(activityProductDetailsBinding.productname.getText().toString().isEmpty()) {
                    Toast.makeText(ProductDetails.this,"Enter product name",Toast.LENGTH_LONG).show();

                }
                else if(activityProductDetailsBinding.editTextTextMultiLine.getText().toString().isEmpty()) {
                    Toast.makeText(ProductDetails.this,"Enter product description",Toast.LENGTH_LONG).show();

                }
                else if(activityProductDetailsBinding.quantity.getText().toString().isEmpty()) {
                    Toast.makeText(ProductDetails.this,"Enter quantity of the product",Toast.LENGTH_LONG).show();

                }
               else if(activityProductDetailsBinding.price.getText().toString().isEmpty()) {
                    Toast.makeText(ProductDetails.this,"Enter price of the product",Toast.LENGTH_LONG).show();

                }
               else {
                    pname = activityProductDetailsBinding.productname.getText().toString();
                    pdes = activityProductDetailsBinding.editTextTextMultiLine.getText().toString();
                    pquantity = activityProductDetailsBinding.quantity.getText().toString();
                    pp = activityProductDetailsBinding.price.getText().toString();

                    ProductsDetailsToDB productsDetailsToDB = new ProductsDetailsToDB();
                    productsDetailsToDB.setProductname(pname);
                    productsDetailsToDB.setProductdes(pdes);
                    productsDetailsToDB.setQuantity(pquantity);
                    productsDetailsToDB.setPrice(pp);
                    productsDetailsToDB.setPhoneNumber("8333020263");
                    myAppDatabase.productDao().addproduct(productsDetailsToDB);
                    Toast.makeText(getApplicationContext()," Saved the Product successfully",Toast.LENGTH_LONG).show();
                    activityProductDetailsBinding.productname.setText("");
                    activityProductDetailsBinding.editTextTextMultiLine.setText("");
                    activityProductDetailsBinding.quantity.setText("");
                    activityProductDetailsBinding.price.setText("");
                }

            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            // startActivity(new Intent(this,AddMoney.class));
            Intent i = new Intent(this,ProductPage.class);
            startActivity(i);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        Intent i = new Intent(ProductDetails.this,ProductPage.class);
        startActivity(i);
        finish();


    }
}