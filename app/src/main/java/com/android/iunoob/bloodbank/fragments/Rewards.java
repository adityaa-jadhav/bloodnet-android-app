package com.android.iunoob.bloodbank.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.CardView;

import com.android.iunoob.bloodbank.R;
import com.android.iunoob.bloodbank.coupons.BoatActivity;
import com.android.iunoob.bloodbank.coupons.FasttrackActivity;
import com.android.iunoob.bloodbank.coupons.LenskartActivity;
import com.android.iunoob.bloodbank.coupons.McD;
import com.android.iunoob.bloodbank.coupons.SpotifyActivity;
import com.android.iunoob.bloodbank.coupons.SwiggyinstamartActivity;
import com.android.iunoob.bloodbank.coupons.ZeptoActivity;
import com.android.iunoob.bloodbank.coupons.ZomatoActivity;
import com.android.iunoob.bloodbank.viewmodels.DonorData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Rewards extends Fragment implements View.OnClickListener  {

    private TextView totalDonateTextView;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseUser fuser;
    private DatabaseReference db_ref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Rewards");

        mAuth = FirebaseAuth.getInstance();
        fuser = mAuth.getCurrentUser();
        db_ref = FirebaseDatabase.getInstance().getReference("donors");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rewards, container, false);

        totalDonateTextView = view.findViewById(R.id.totaldonate);

        // Find each CardView by its ID and set a click listener
        CardView imageCard1 = view.findViewById(R.id.imageCard1);
        imageCard1.setOnClickListener(this);

        CardView imageCard2 = view.findViewById(R.id.imageCard2);
        imageCard2.setOnClickListener(this);

        CardView imageCard3 = view.findViewById(R.id.imageCard3);
        imageCard3.setOnClickListener(this);

        CardView imageCard4 = view.findViewById(R.id.imageCard4);
        imageCard4.setOnClickListener(this);

        CardView imageCard5 = view.findViewById(R.id.imageCard5);
        imageCard5.setOnClickListener(this);

        CardView imageCard6 = view.findViewById(R.id.imageCard6);
        imageCard6.setOnClickListener(this);

        CardView imageCard7 = view.findViewById(R.id.imageCard7);
        imageCard7.setOnClickListener(this);

        CardView imageCard8 = view.findViewById(R.id.imageCard8);
        imageCard8.setOnClickListener(this);

        // Retrieve total donation count from Firebase
        retrieveTotalDonate();

        return view;
    }

    @Override
    public void onClick(View v) {
        // Handle click events for each CardView
        switch (v.getId()) {
            case R.id.imageCard1:
                // Handle click for imageCard1
                if (TotalDonate >= 0) {
                    startActivity(new Intent(getContext(), McD.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard2:
                // Handle click for imageCard2
                if (TotalDonate >= 0) {
                    startActivity(new Intent(getContext(), BoatActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard3:
                // Handle click for imageCard3
                if (TotalDonate >= 1) {
                    startActivity(new Intent(getContext(), FasttrackActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard4:
                // Handle click for imageCard4
                if (TotalDonate >= 2) {
                    startActivity(new Intent(getContext(), ZomatoActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard5:
                // Handle click for imageCard5
                if (TotalDonate >= 3) {
                    startActivity(new Intent(getContext(), SpotifyActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard6:
                // Handle click for imageCard6
                if (TotalDonate >= 4) {
                    startActivity(new Intent(getContext(), LenskartActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard7:
                // Handle click for imageCard7
                if (TotalDonate >= 5) {
                    startActivity(new Intent(getContext(), ZeptoActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageCard8:
                // Handle click for imageCard8
                if (TotalDonate >= 6) {
                    startActivity(new Intent(getContext(), SwiggyinstamartActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Unlock this coupon by donating blood!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private int TotalDonate = 0;

    private void retrieveTotalDonate() {
        progressDialog.show();

        Query query = db_ref.child(fuser.getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                if (dataSnapshot.exists()) {
                    DonorData donorData = dataSnapshot.getValue(DonorData.class);
                    if (donorData != null) {
                        TotalDonate = donorData.getTotalDonate();
                        totalDonateTextView.setText("Total Donate: " + TotalDonate);
                    } else {
                        totalDonateTextView.setText("Total Donate: 1");
                    }
                } else {
                    totalDonateTextView.setText("Total Donate: 0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Failed to retrieve total donation count", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
