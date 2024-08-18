package com.example.busisiwe_magae_2110949_resit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RaceScheduleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race_schedule, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRaces);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetching and parsing the race schedule data
        String raceXml = ((MainActivity) getActivity()).getDataFromApi(MainActivity.RACE_SCHEDULE_URL);
        List<Race> races = ((MainActivity) getActivity()).parseRaceSchedule(raceXml);

        // Setting up the adapter with parsed data
        RaceAdapter adapter = new RaceAdapter(races);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
