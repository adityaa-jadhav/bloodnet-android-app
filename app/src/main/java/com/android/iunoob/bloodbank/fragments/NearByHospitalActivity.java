package com.android.iunoob.bloodbank.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.iunoob.bloodbank.R;

public class NearByHospitalActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.near_by_hospitals, container, false);
        getActivity().setTitle("Upcoming Blood Drives");

        // Find the buttons by their IDs
        Button button1 = rootView.findViewById(R.id.button1);
        Button button2 = rootView.findViewById(R.id.button2);
        Button button3 = rootView.findViewById(R.id.button3);
        Button button4 = rootView.findViewById(R.id.button4);
        Button button5 = rootView.findViewById(R.id.button5);

        // Set click listeners for each button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation for the first button
                navigateToLocation("KEM Hospital, Parel");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation for the second button
                navigateToLocation("Fortis Hospital, Mulund(W)");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation for the third button
                navigateToLocation("DG Ruparel College, Mahim");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation for the fourth button
                navigateToLocation("Dadar Station, Main Bridge");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle navigation for the fifth button
                navigateToLocation("AIMS Hospital, Dombivli(E)");
            }
        });

        return rootView;
    }

    // Method to navigate to a specific location using its name
    private void navigateToLocation(String locationName) {
        // You can replace this with your actual navigation logic
        // For example, launching Google Maps with the location name
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationName));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps"));
        //startActivity(mapIntent);

        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager != null && mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent);
        }
    }
}