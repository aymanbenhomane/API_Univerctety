package com.example.apiuniversity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;
public class UniversitiesVolleyService {
    private static final String BASE_URL = "http://universities.hipolabs.com/";
    private RequestQueue requestQueue;

    public UniversitiesVolleyService(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getUniversities(String country, final VolleyCallback<List<University>> callback) {
        String url = BASE_URL+"search?country="+country;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Type universityListType = new TypeToken<List<University>>(){}.getType();
                        List<University> universities = gson.fromJson(response.toString(), universityListType);
                        callback.onSuccess(universities);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    public interface VolleyCallback<T> {
        void onSuccess(T result);
        void onError(VolleyError error);
    }
}
