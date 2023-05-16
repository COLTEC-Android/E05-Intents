package com.example.e05_intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PHOTO_CODE = 1;
    private static final int SCAN_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnEditPhoto = findViewById(R.id.btnEdit);
        TextView email = findViewById(R.id.profileMail);
        TextView phone = findViewById(R.id.profileTel);

        Button btnScan = findViewById(R.id.btn_scan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanIntent = new Intent("com.google.zxing.client.android.SCAN");

                startActivityForResult(scanIntent, SCAN_CODE);
            }
        });


        btnEditPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(photoIntent, PHOTO_CODE);

            }
        });


        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri callUri = Uri.parse("tel:"+phone.getText().toString());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, callUri);

                startActivity(callIntent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PHOTO_CODE && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            ImageView ivPhoto = this.findViewById(R.id.imgProfile);
            ivPhoto.setImageBitmap(photo);
        }

        if(requestCode == SCAN_CODE && resultCode == RESULT_OK){
            String code = data.getStringExtra("SCAN_RESULT");

            TextView lblCode = this.findViewById(R.id.lbl_code);
            lblCode.setText(code);
        }

    }
}