package com.andsf.sampleproject.Newtork;

import com.andsf.sampleproject.BookResponse;
import com.andsf.sampleproject.ModelClass;
import com.andsf.sampleproject.ProductResponse;
import com.google.gson.JsonObject;


import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("getAllAvailableBooks")
    Call<BookResponse> getBooks();
    @POST("addNewProduct")
    Call<ProductResponse> addProductToServer(@Body ModelClass v);


}
