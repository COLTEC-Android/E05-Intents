package com.example.e05_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int FOTO_CODE = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fotoBtn = findViewById(R.id.btn_photo);
        TextView telefone = findViewById(R.id.txt_telefone);
        TextView email = findViewById(R.id.txt_email);

        fotoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, FOTO_CODE);
            }
        });

        telefone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uritTell = Uri.parse("tel:31983127417");
                Intent intent = new Intent(Intent.ACTION_DIAL, uritTell);
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "2021953372@teiacoltec.org" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "calculator_feedback");
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.photo);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photoNew = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photoNew);
        }
    }

}