package com.example.shadrak.expensestracker;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface API {

    String Base_URL = "http://192.168.1.110:5000/";

    @POST("invoice_upload")
    Call<ResultData> getresult(@Body RequestData requestData);

}
