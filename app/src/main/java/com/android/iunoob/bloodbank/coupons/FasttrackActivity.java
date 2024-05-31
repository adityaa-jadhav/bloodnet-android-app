package com.android.iunoob.bloodbank.coupons;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.iunoob.bloodbank.R;



public class FasttrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasttrack);

        Button redeemButton = findViewById(R.id.button6);
        Button copyButton = findViewById(R.id.copyButton);
        final TextView voucherCodeTextView = findViewById(R.id.voucherCodeTextView);

        // Handle Redeem button click
        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect user to the website
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fastrackeyewear.com")));
            }
        });

        // Handle Copy button click
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Copy voucher code to clipboard
                String voucherCode = voucherCodeTextView.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Voucher Code", voucherCode);
                clipboard.setPrimaryClip(clip);

                // Show a toast message to indicate successful copying
                Toast.makeText(getApplicationContext(), "Coupon code copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

    }
}