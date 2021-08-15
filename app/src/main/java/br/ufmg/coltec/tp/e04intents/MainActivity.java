package br.ufmg.coltec.tp.e04intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMyProfile = findViewById(R.id.btn_profile);
        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnScanCode = findViewById(R.id.btn_scan_code);
        btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //testa se o código solicitado é 0, como foi setado na linha 31
        //
        if(requestCode == 0){
            if(resultCode == RESULT_OK){ //testa se a execução ocorreu corretamente
                // armazena  que foi codificado
                String contentsEncoded = data.getStringExtra("SCAN_RESULT");
                // armazena o tipo de código (numero, QRCOde, etc)
                String codeFormat = data.getStringExtra("SCAN_RESULT_FORMAT");
            }
        }
    }
}
