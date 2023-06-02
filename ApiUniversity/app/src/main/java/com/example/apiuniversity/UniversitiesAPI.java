package com.example.apiuniversity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UniversitiesAPI {
    @GET("search")
    Call<List<University>> getUniversities(@Query("country") String country);
}