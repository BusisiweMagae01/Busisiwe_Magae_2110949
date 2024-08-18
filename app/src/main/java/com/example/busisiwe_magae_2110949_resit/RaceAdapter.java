package com.example.busisiwe_magae_2110949_resit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {

    private List<Race> races;

    public RaceAdapter(List<Race> races) {
        this.races = races;
    }

    @NonNull
    @Override
    public RaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.race_item, parent, false);
        return new RaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceViewHolder holder, int position) {
        Race race = races.get(position);
        holder.raceNameTextView.setText(race.getRaceName());
        holder.circuitTextView.setText(race.getCircuitName());
        holder.dateTextView.setText(race.getDate());
    }

    @Override
    public int getItemCount() {
        return races.size();
    }

    public static class RaceViewHolder extends RecyclerView.ViewHolder {
        TextView raceNameTextView;
        TextView circuitTextView;
        TextView dateTextView;

        public RaceViewHolder(@NonNull View itemView) {
            super(itemView);
            raceNameTextView = itemView.findViewById(R.id.raceNameTextView);
            circuitTextView = itemView.findViewById(R.id.circuitTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
