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

public class tdemd extends AppCompatActivity {
    private EditText tdtp;
    private EditText emd;
    private TextView moy;
    private EditText nom;
    private EditText coef;
    Button back;
    Button enregistre;
    Button calculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseModul db = new databaseModul( this,"moyenne",null,5) ;
        setContentView(R.layout.activity_tdemd);
        nom=(EditText) findViewById(R.id.nm);
        coef = (EditText) findViewById(R.id.cf) ;
        tdtp =(EditText) findViewById(R.id.td);
        emd =(EditText) findViewById(R.id.ex);
        moy=(TextView) findViewById(R.id.my);
        back = (Button) findViewById(R.id.rt);
        enregistre =(Button) findViewById(R.id.sv);
        calculer=(Button) findViewById(R.id.calc);
        final int[] cpt = {0};
        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpt[0]==0){
                    Toast.makeText(tdemd.this, "Calculer", Toast.LENGTH_SHORT).show();
                }else{
                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(tdemd.this,"click calculer",Toast.LENGTH_SHORT).show();

                }else{
                    if(TextUtils.isEmpty(coef.getText())){
                        Toast.makeText(tdemd.this,"click calculer",Toast.LENGTH_SHORT).show();

                    }else{
                        if(TextUtils.isEmpty(tdtp.getText())){
                            Toast.makeText(tdemd.this,"click calculer",Toast.LENGTH_SHORT).show();

                        }else {
                            if (TextUtils.isEmpty(emd.getText())){
                                Toast.makeText(tdemd.this,"click calculer",Toast.LENGTH_SHORT).show();

                            }else{
                                db.insert(nom.getText().toString(), Integer.parseInt(coef.getText().toString()), Float.parseFloat(moy.getText().toString()));
                                Toast.makeText(tdemd.this,"enregistrer",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(tdemd.this,ModuleActivity.class);
                                startActivity(intent);
                            } } } }}



            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tdemd.this,ModuleActivity.class);
                startActivity(intent);
            }
        });
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpt[0]++;
                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(tdemd.this,"Saiser nom module",Toast.LENGTH_LONG).show();

                }else{
                    if(TextUtils.isEmpty(coef.getText())){
                        Toast.makeText(tdemd.this,"saiser coefficient",Toast.LENGTH_LONG).show();

                    }else{
                        if(TextUtils.isEmpty(tdtp.getText())){
                            Toast.makeText(tdemd.this,"saiser note td ou tp",Toast.LENGTH_SHORT).show();

                        }else {
                            if (TextUtils.isEmpty(emd.getText())){
                                Toast.makeText(tdemd.this,"saiser note EMD",Toast.LENGTH_SHORT).show();

                            }else{
                                float Tdtp = Float.parseFloat(tdtp.getText().toString());
                                float Emd = Float.parseFloat(emd.getText().toString());
                                float Moyenne ;

                                if(Tdtp>20 || Tdtp<0){
                                    Toast.makeText(tdemd.this,"errure:note invalide",Toast.LENGTH_SHORT).show();

                                    tdtp.setText(" ");
                                }else {
                                    if(Emd>20 || Emd<0){
                                        Toast.makeText(tdemd.this,"errure:emd invalide",Toast.LENGTH_SHORT).show();

                                        emd.setText(" ");
                                    }else{
                                        Moyenne = ( Tdtp + (Emd * 2)) / 3;
                                        moy.setText(String.valueOf(Moyenne));
                                    }}
                            } } } }

            }
        });
    }



    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}