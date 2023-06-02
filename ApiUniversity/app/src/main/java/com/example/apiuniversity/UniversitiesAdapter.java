package com.example.apiuniversity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UniversitiesAdapter extends RecyclerView.Adapter<UniversitiesAdapter.ViewHolder> {

    private List<University> universities;

    public UniversitiesAdapter(List<University> universities) {
        this.universities = universities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.university_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        University university = universities.get(position);
        holder.textViewName.setText(university.getName());
        holder.textViewCountry.setText(university.getCountry());
    }

    @Override
    public int getItemCount() {
        return universities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewCountry = itemView.findViewById(R.id.text_view_country);
        }
    }
}
