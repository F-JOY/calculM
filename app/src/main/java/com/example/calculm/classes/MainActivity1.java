package com.example.calculm.classes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.calculm.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
 EditText nom;
 EditText coef;
 EditText examen;
 TextView moy;
 Button rt;
 Button save;
 Button calculer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        databaseModul db = new databaseModul( this,"moyenne",null,5) ;
        nom=(EditText) findViewById(R.id.nm);
        coef=(EditText) findViewById(R.id.cf);
        examen=(EditText) findViewById(R.id.ex);
        moy=(TextView) findViewById(R.id.my);
        rt=(Button) findViewById(R.id.rt);
        save=(Button) findViewById(R.id.sv);
        calculer=(Button) findViewById(R.id.calc);
        final int[] cpt = {0};
        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity1.this,ModuleActivity.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpt[0]==0){
                    Toast.makeText(MainActivity1.this, "Calculer", Toast.LENGTH_SHORT).show();
                }else{
                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(MainActivity1.this,"Click Calculer",Toast.LENGTH_LONG).show();

                }else{if (TextUtils.isEmpty(coef.getText())){
                    Toast.makeText(MainActivity1.this,"Click Calculer",Toast.LENGTH_LONG).show();


                }else{if(TextUtils.isEmpty(examen.getText())){
                    Toast.makeText(MainActivity1.this,"Click Calculer",Toast.LENGTH_LONG).show();
                }else{
                db.insert(nom.getText().toString(), Integer.parseInt(coef.getText().toString()), Float.parseFloat(moy.getText().toString()));
                Toast.makeText(MainActivity1.this,"enregistrer",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity1.this,ModuleActivity.class);
                startActivity(intent);
            }}}}}
        });
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpt[0]++;
                float Emd;
                float moyenne ;
                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(MainActivity1.this,"Saiser Nom module",Toast.LENGTH_LONG).show();

                }else{if (TextUtils.isEmpty(coef.getText())){
                    Toast.makeText(MainActivity1.this,"Saiser coefficient",Toast.LENGTH_LONG).show();


                }else{if(TextUtils.isEmpty(examen.getText())){
                    Toast.makeText(MainActivity1.this,"Saiser EMD",Toast.LENGTH_LONG).show();
                }else{
                    Emd=Float.parseFloat(examen.getText().toString());
                    if( Emd>20 || Emd<0 ){
                        Toast.makeText(MainActivity1.this,"errure: emd invalide",Toast.LENGTH_SHORT).show();
                        examen.setText(" ");
                    }else{
                        moyenne=Emd;
                        moy.setText(String.valueOf(moyenne));
                    }}}}
            }
        });

    }



    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}