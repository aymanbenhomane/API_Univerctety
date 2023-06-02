package com.example.apiuniversity;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UniversitiesService {

    private static final String BASE_URL = "http://universities.hipolabs.com/";

    public interface Callback<T> {
        void onSuccess(T response);
        void onError(String message);

        void onError(Throwable error);
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private UniversitiesAPI universitiesApi = retrofit.create(UniversitiesAPI.class);

    public void getUniversities(String country, final Callback<List<University>> callback) {
        Call<List<University>> call = universitiesApi.getUniversities(country);
        call.enqueue(new retrofit2.Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {
                if (response.isSuccessful()) {
                    List<University> universities = response.body();
                    callback.onSuccess(universities);
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }
        });
    }
}

