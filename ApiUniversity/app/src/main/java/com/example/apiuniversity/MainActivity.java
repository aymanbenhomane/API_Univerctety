package com.example.apiuniversity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button API;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private List<University> universities = new ArrayList<>();
    private UniversitiesAdapter universitiesAdapter;
    private UniversitiesService universitiesService;
    private UniversitiesVolleyService universitiesVolleyService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner_country);
        recyclerView = findViewById(R.id.recycler_view);

        universitiesAdapter = new UniversitiesAdapter(universities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(universitiesAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        universitiesService = new UniversitiesService();
        universitiesVolleyService = new UniversitiesVolleyService(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String country = parent.getItemAtPosition(position).toString();

                universitiesService.getUniversities(country, new UniversitiesService.Callback<List<University>>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(List<University> result) {
                        universities.clear();
                        universities.addAll(result);
                        universitiesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });

                universitiesVolleyService.getUniversities(country, new UniversitiesVolleyService.VolleyCallback<List<University>>() {
                    @Override
                    public void onSuccess(List<University> result) {
                        universities.clear();
                        universities.addAll(result);
                        universitiesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire
            }
        });
    }
}
