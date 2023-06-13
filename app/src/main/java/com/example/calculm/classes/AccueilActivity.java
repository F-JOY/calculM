package com.example.calculm.classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.calculm.R;

public class AccueilActivity extends AppCompatActivity {
   Button module;
   Button ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);
        databaseModul db = new databaseModul( this,"moyenne",null,5) ;
        module= (Button) findViewById(R.id.Mod);
        module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccueilActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });
        ref=(Button) findViewById(R.id.ref);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.dlt(" ");
               Toast T= Toast.makeText(AccueilActivity.this, "Données Supprimer", Toast.LENGTH_SHORT);
               T.setGravity(Gravity.TOP|Gravity.CENTER,20,30);
               T.show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}