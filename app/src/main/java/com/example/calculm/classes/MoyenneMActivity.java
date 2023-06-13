package com.example.calculm.classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.calculm.R;

public class MoyenneMActivity extends AppCompatActivity {
    private EditText td;
    private EditText tp;
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
        setContentView(R.layout.activity_main);
        databaseModul db = new databaseModul( this,"moyenne",null,5) ;
        nom=(EditText) findViewById(R.id.nm);
        coef = (EditText) findViewById(R.id.cf) ;
        td =(EditText) findViewById(R.id.td);
        tp =(EditText) findViewById(R.id.tp);
        emd =(EditText) findViewById(R.id.ex);
        moy=(TextView) findViewById(R.id.my);
        back = (Button) findViewById(R.id.rt);
        enregistre =(Button) findViewById(R.id.sv);
        calculer=(Button)findViewById(R.id.calc);
        final int[] cpt = {0};

        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpt[0]==0){
                    Toast.makeText(MoyenneMActivity.this, "Calculer", Toast.LENGTH_SHORT).show();
                }else{

                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(MoyenneMActivity.this,"click calculer",Toast.LENGTH_SHORT).show();

                }else{if(TextUtils.isEmpty(coef.getText())){
                    Toast.makeText(MoyenneMActivity.this,"click calculer",Toast.LENGTH_SHORT).show();

                }else{if (TextUtils.isEmpty(td.getText())){
                    Toast.makeText(MoyenneMActivity.this,"click calculer",Toast.LENGTH_SHORT).show();

                }else{
                    if (TextUtils.isEmpty(tp.getText())){
                        Toast.makeText(MoyenneMActivity.this,"click calculer",Toast.LENGTH_SHORT).show();

                    }else{
                        if (TextUtils.isEmpty(emd.getText())){
                            Toast.makeText(MoyenneMActivity.this,"click calculer",Toast.LENGTH_SHORT).show();

                        }else{
                            db.insert(nom.getText().toString(), Integer.parseInt(coef.getText().toString()), Float.parseFloat(moy.getText().toString()));
                            Toast.makeText(MoyenneMActivity.this,"enregistrer",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MoyenneMActivity.this,ModuleActivity.class);
                            startActivity(intent);
                        } } } } }}}





        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoyenneMActivity.this,ModuleActivity.class);
                startActivity(intent);
            }
        });
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   cpt[0]++;
                if(TextUtils.isEmpty(nom.getText())){
                    Toast.makeText(MoyenneMActivity.this,"Saiser Nom module ",Toast.LENGTH_LONG).show();

                }else{
                    if(TextUtils.isEmpty(coef.getText())){
                        Toast.makeText(MoyenneMActivity.this,"Saiser Coefficient ",Toast.LENGTH_LONG).show();

                    }else{
                        if(TextUtils.isEmpty(td.getText())){
                            Toast.makeText(MoyenneMActivity.this,"Saiser not td ",Toast.LENGTH_LONG).show();

                        }else{
                            if (TextUtils.isEmpty(tp.getText())){
                                Toast.makeText(MoyenneMActivity.this,"Saiser note tp ",Toast.LENGTH_LONG).show();

                            }else{
                                if(TextUtils.isEmpty((emd.getText()))){
                                    Toast.makeText(MoyenneMActivity.this,"Saiser note EMD ",Toast.LENGTH_LONG).show();

                                }else{
                                    float Td = Float.parseFloat(td.getText().toString());
                                    float Tp = Float.parseFloat(tp.getText().toString());
                                    float Emd = Float.parseFloat(emd.getText().toString());
                                    float Moyenne ;
                                    if(Td>20 || Td<0){
                                        Toast.makeText(MoyenneMActivity.this,"errure:td invalide",Toast.LENGTH_SHORT).show();

                                        td.setText(" ");
                                    }else{
                                        if(Tp>20 || Tp<0){
                                            Toast.makeText(MoyenneMActivity.this,"errure:tp invalide",Toast.LENGTH_SHORT).show();

                                            tp.setText(" ");
                                        }else{
                                            if(Emd>20 || Emd<0){
                                                Toast.makeText(MoyenneMActivity.this,"errure:emd invalide",Toast.LENGTH_SHORT).show();

                                                emd.setText(" ");
                                            }else{
                                                Moyenne = ( Td+ Tp + (Emd * 2)) / 4;
                                                moy.setText(String.valueOf(Moyenne));
                                            }
                                        }} } } } } }



            }
        });

    }


    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}