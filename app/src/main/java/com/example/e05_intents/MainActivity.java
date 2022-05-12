package com.example.e05_intents;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PHOTOCODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtTel = findViewById(R.id.txtTelefone);
        TextView txtEmail = findViewById(R.id.txtEmail);
        ImageView foto = findViewById(R.id.fotoPerfil);

        txtTel.setOnClickListener((view) -> {
            String telefone = "tel:" + txtTel.getText();
            Uri uri = Uri.parse(telefone);
            Intent telIntent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(telIntent);
        });

        txtEmail.setOnClickListener((view) -> {
            Uri uri = Uri.parse("mailto:" + txtEmail.getText());
            Intent emIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(emIntent);
        });

        foto.setOnClickListener((view) -> {
            Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(fotoIntent, PHOTOCODE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView foto = findViewById(R.id.fotoPerfil);
        if(requestCode == PHOTOCODE && resultCode == Activity.RESULT_OK){
            Bitmap fotoBit = (Bitmap) data.getExtras().get("data");
            foto.setImageBitmap(fotoBit);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}