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
//                Toast toast = Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT);
//                toast.show();
                Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
