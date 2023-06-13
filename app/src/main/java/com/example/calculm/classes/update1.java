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

public class update1 extends AppCompatActivity {
            EditText n1,c1,ex1;
            TextView m1;
            Button rt1,up1,calculer;
            String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update1);
        databaseModul db = new databaseModul(this,"moyenne",null,5);
        n1=(EditText) findViewById(R.id.upnm1);
        c1=(EditText) findViewById(R.id.upcf1);
        ex1=(EditText) findViewById(R.id.upex1);
        m1=(TextView)findViewById(R.id.upmoy1);
        rt1=(Button)findViewById(R.id.uprt1);
        up1=(Button) findViewById(R.id.up1);
        final int[] cpt = {0};
        calculer=(Button)findViewById(R.id.upclc1);
           getintentdata();

        rt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update1.this,ModuleActivity.class);
                startActivity(intent);
            }
        });

        calculer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cpt[0]++;
                if(TextUtils.isEmpty(n1.getText())){
                    Toast.makeText(update1.this,"saiser le nouveau coeff",Toast.LENGTH_SHORT).show();
                    n1.setText(name);
                }else{
                    if(TextUtils.isEmpty(c1.getText())){
                        Toast.makeText(update1.this,"saiser le nouveau coeff",Toast.LENGTH_SHORT).show();

                    }else{
                        if(TextUtils.isEmpty(ex1.getText())){
                            Toast.makeText(update1.this,"saiser la nouvelle note",Toast.LENGTH_SHORT).show();

                        }else{
                            float Emd=Float.parseFloat(ex1.getText().toString());
                            float moyenne ;
                            if(Emd>20 || Emd<0){
                                Toast.makeText(update1.this,"errure: emd invalide",Toast.LENGTH_SHORT).show();
                                ex1.setText(" ");
                            }else{
                                moyenne=Emd;
                                m1.setText(String.valueOf(moyenne));
                            }
                        }

                    }
                }

            }
        });

        up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpt[0]==0){
                    Toast.makeText(update1.this, "Calculer", Toast.LENGTH_SHORT).show();
                }else{
                if(TextUtils.isEmpty(n1.getText())){
                    Toast.makeText(update1.this, "clicker calculer", Toast.LENGTH_LONG).show();
                    n1.setText(name);
                }else{
                    if(TextUtils.isEmpty(c1.getText())){
                        Toast.makeText(update1.this, "clicker calculer", Toast.LENGTH_LONG).show();

                    }else{
                        if (TextUtils.isEmpty(ex1.getText())){
                            Toast.makeText(update1.this, "clicker calculer", Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(update1.this, "le nom ne peut pas etre modifier", Toast.LENGTH_LONG).show();
                            n1.setText(name);
                            db.updatedata(name, Integer.parseInt(c1.getText().toString()), Float.parseFloat(m1.getText().toString()));
                            Toast.makeText(update1.this, "la moyenne est Modifier", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }}
        });
    }
    void getintentdata(){
        name=getIntent().getStringExtra("nom");
        n1.setText(name);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}