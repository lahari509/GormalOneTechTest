package com.andsf.sampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andsf.sampleproject.Newtork.ApiClientWithHeaders;
import com.andsf.sampleproject.Newtork.ApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DisplayBooks extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    Retrofit retrofit;
    GetBookResultAdapter getBookResultAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_books);
        recyclerView = findViewById(R.id.recyclerview);
         retrofit = ApiClientWithHeaders.getRetrofitInstance(DisplayBooks.this);
        getBookList();
    }

    private void getBookList() {
        progressDialog = new ProgressDialog(DisplayBooks.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        apiInterface = retrofit.create(ApiInterface.class);
        Call<BookResponse> call = apiInterface.getBooks();
        call.enqueue(new Callback<BookResponse>() {
            BookResponse getBooksResponse;
            List<Results> getBookResult = new ArrayList<>();
            @Override
            public void onResponse(Call<BookResponse> call, retrofit2.Response<BookResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    getBooksResponse = response.body();
                    getBookResult = getBooksResponse.getResults();
                    if(getBookResult.size()>0) {
                        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(DisplayBooks.this);
                        recyclerView.setLayoutManager(mlayoutManager);
                        mlayoutManager.requestLayout();
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.addItemDecoration(new DividerItemDecoration(DisplayBooks.this,DividerItemDecoration.VERTICAL));
                        getBookResultAdapter = new GetBookResultAdapter(DisplayBooks.this, (ArrayList<Results>) getBookResult);
                        recyclerView.setAdapter(getBookResultAdapter);
                    }
                }
                else{
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DisplayBooks.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}