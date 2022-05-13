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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final int PHOTOCODE = 2;
    private static final int LEITORCODE = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtTel = findViewById(R.id.txtTelefone);
        TextView txtEmail = findViewById(R.id.txtEmail);
        ImageView foto = findViewById(R.id.fotoPerfil);
        Button btnLeitor = findViewById(R.id.btnLeitor);

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

        btnLeitor.setOnClickListener((view) -> {
            Intent leitorIntent = new Intent("com.google.zxing.client.android.SCAN");
            startActivityForResult(leitorIntent, LEITORCODE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView foto = findViewById(R.id.fotoPerfil);
        if(requestCode == PHOTOCODE && resultCode == Activity.RESULT_OK){
            Bitmap fotoBit = (Bitmap) data.getExtras().get("data");
            foto.setImageBitmap(fotoBit);
        }

        if(requestCode == LEITORCODE && resultCode == RESULT_OK){
            TextView txtRes = findViewById(R.id.resPadrao);
            TextView txtTipo = findViewById(R.id.tipoLidoPadrao);
            TextView scanResult = findViewById(R.id.txtResultadoLeitor);
            TextView formatResult = findViewById(R.id.txtTipoLeitor);

            String contents = data.getStringExtra("SCAN_RESULT");

            String format = data.getStringExtra("SCAN_RESULT_FORMAT");
            Log.d("Retorno", contents);
            Log.d("Retorno", format);
            txtRes.setVisibility(View.VISIBLE);
            txtTipo.setVisibility(View.VISIBLE);
            scanResult.setText(contents);
            formatResult.setText(format);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}