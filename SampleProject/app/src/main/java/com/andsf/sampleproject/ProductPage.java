package com.andsf.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andsf.sampleproject.Newtork.ApiClientWithHeaders;
import com.andsf.sampleproject.Newtork.ApiInterface;
import com.andsf.sampleproject.database.AppDatabase;
import com.andsf.sampleproject.database.ProductsDetailsToDB;
import com.andsf.sampleproject.databinding.ActivityProductPageBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductPage extends AppCompatActivity {

    private static final String TAG = "product" ;
    private ActivityProductPageBinding activityProductPageBinding;
    public static AppDatabase myAppDatabase;
    List<ModelClass> list = new ArrayList<ModelClass>();
    ApiInterface apiInterface;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductPageBinding = ActivityProductPageBinding.inflate(getLayoutInflater());
        View view = activityProductPageBinding.getRoot();
        setContentView(view);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        retrofit = ApiClientWithHeaders.getRetrofitInstance(ProductPage.this);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "ProductDetails").allowMainThreadQueries().build();

        activityProductPageBinding.addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProductPage.this, ProductDetails.class);
                startActivity(i);
            }
        });

        activityProductPageBinding.syncProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ProductsDetailsToDB> productClassList = myAppDatabase.productDao().getAll();
                System.out.println("productClassList" + productClassList);
                if (productClassList != null && productClassList.size() > 0) {
                    for (int i = 0; i < productClassList.size(); i++) {

                                ModelClass modelClass = new ModelClass();
                                modelClass.setProduct_name(productClassList.get(i).getProductname());
                                modelClass.setProduct_desc(productClassList.get(i).getProductdes());
                                modelClass.setProduct_quantity(productClassList.get(i).getQuantity());
                                modelClass.setProduct_price(productClassList.get(i).getPrice());
                                modelClass.setUser_mobile_no(productClassList.get(i).getPhoneNumber());
                                list.add(modelClass);
                                syncapi(modelClass);


                        }
                }

            }
        });

    }

    private void syncapi(ModelClass modelClass) {
        Log.d(TAG,"API called");
        apiInterface = retrofit.create(ApiInterface.class);
        Call<ProductResponse> saveProducts = apiInterface.addProductToServer(modelClass);
        saveProducts.enqueue(new Callback<ProductResponse>() {

            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        ProductResponse productResponse = response.body();
                        Toast.makeText(ProductPage.this, productResponse.getResults().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }


            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductPage.this,t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            // startActivity(new Intent(this,AddMoney.class));
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        Intent i = new Intent(ProductPage.this,MainActivity.class);
        startActivity(i);
        finish();


    }
}