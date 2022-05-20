package com.example.e05intents;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int FOTO_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton alterafoto = findViewById(R.id.foto);

        alterafoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent,FOTO_CODE);
            }
        });

        TextView text = findViewById(R.id.telefone);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse ("tel:33181920");
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });




    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = findViewById(R.id.foto);

        if (requestCode == FOTO_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }


}