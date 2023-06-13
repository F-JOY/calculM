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

public class update2 extends AppCompatActivity {
    EditText n2,c2,td2,ex2;
    TextView m2;
    Button rt2,up2,calculer;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        databaseModul db = new databaseModul(this,"moyenne",null,5);
        n2=(EditText) findViewById(R.id.upnm2);
        c2=(EditText) findViewById(R.id.upcf2);
        td2=(EditText)findViewById(R.id.uptd2);
        ex2=(EditText) findViewById(R.id.upex2);
        m2=(TextView)findViewById(R.id.upmoy2);
        rt2=(Button)findViewById(R.id.uprt2);
        up2=(Button) findViewById(R.id.up2);
        calculer=(Button)findViewById(R.id.upclc2);
        final int [] cpt={0};
        getintentdata();
        rt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update2.this,ModuleActivity.class);
                startActivity(intent);
            }
        });
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpt[0]++;
                if (TextUtils.isEmpty(n2.getText())){
                    n2.setText(name);
                }else{
                    if(TextUtils.isEmpty(c2.getText())){
                        Toast.makeText(update2.this, "saiser coefficient", Toast.LENGTH_SHORT).show();
                    }else{
                        if(TextUtils.isEmpty(td2.getText())){
                            Toast.makeText(update2.this, "saiser note td ou tp", Toast.LENGTH_SHORT).show();
                        }else{
                            if(TextUtils.isEmpty(ex2.getText())){
                                Toast.makeText(update2.this, "saiser note EMD", Toast.LENGTH_SHORT).show();
                            }else{
                                float Tdtp = Float.parseFloat(td2.getText().toString());
                                float Emd = Float.parseFloat(ex2.getText().toString());
                                float Moyenne ;

                                if(Tdtp>20 || Tdtp<0){
                                    Toast.makeText(update2.this,"errure:note invalide",Toast.LENGTH_SHORT).show();

                                    td2.setText(" ");
                                }else {
                                    if(Emd>20 || Emd<0){
                                        Toast.makeText(update2.this,"errure:emd invalide",Toast.LENGTH_SHORT).show();

                                        ex2.setText(" ");
                                    }else{
                                        Moyenne = ( Tdtp + (Emd * 2)) / 3;
                                        m2.setText(String.valueOf(Moyenne));
                                    }} } } } }

            }
        });
        up2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpt[0]==0){
                    Toast.makeText(update2.this, "Calculer", Toast.LENGTH_SHORT).show();
                }else{
                if(TextUtils.isEmpty(n2.getText())){
                    Toast.makeText(update2.this, "clicker calculer", Toast.LENGTH_LONG).show();
                    n2.setText(name);
                }else{
                    if(TextUtils.isEmpty(c2.getText())){
                        Toast.makeText(update2.this, "clicker calculer", Toast.LENGTH_SHORT).show();

                    }else{
                        if(TextUtils.isEmpty(td2.getText())){
                            Toast.makeText(update2.this, "clicker calculer", Toast.LENGTH_SHORT).show();

                        }else{
                            if(TextUtils.isEmpty(ex2.getText())){
                                Toast.makeText(update2.this, "clicker calculer", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(update2.this, "le nom ne peut pas etre modifier", Toast.LENGTH_SHORT).show();
                                n2.setText(name);
                                db.updatedata(n2.getText().toString(), Integer.parseInt(c2.getText().toString()), Float.parseFloat(m2.getText().toString()));
                                Toast.makeText(update2.this, "la moyenne est Modifier", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }}

            }
        });

    }
    void getintentdata(){
        name=getIntent().getStringExtra("nom");
        n2.setText(name);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}