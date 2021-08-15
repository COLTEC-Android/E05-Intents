package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MyProfileActivity extends Activity {

    private static int PHOTO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Button btnChangePhoto = findViewById(R.id.btn_change_photo);
        btnChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, PHOTO_CODE);
            }
        });

        Button btnCallMe = findViewById(R.id.btn_phone_number);
        btnCallMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriPhoneNumber = Uri.parse("tel: 9999999999999");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, uriPhoneNumber);
                startActivity(callIntent);
            }
        });

        Button btnSendEmail = findViewById(R.id.btn_email_address);
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmailIntent = new Intent();
                sendEmailIntent.setAction(Intent.ACTION_SEND);
                sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, "2020954820@teiacoltec.org");
                sendEmailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, Fernanda!");
                sendEmailIntent.setType("text/plain");

                Intent share = Intent.createChooser(sendEmailIntent, null);
                startActivity(share);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        ImageView imageView = findViewById(R.id.profile_photo);

        //testa se o código passado como parâmetro é igual ao codigo da foto
        // e se a execução ocorreu corretamente
        if(requestCode == PHOTO_CODE && resultCode == Activity.RESULT_OK){
            Bitmap profilePhoto = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(profilePhoto);
        }
    }
}