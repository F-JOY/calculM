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

public class update3 extends AppCompatActivity {
    EditText n3,c3,td3,tp3,ex3;
    TextView m3;
    Button rt3,up3,calculer;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update3);
        databaseModul db = new databaseModul(this,"moyenne",null,5);
        n3=(EditText) findViewById(R.id.upnm3);
        c3=(EditText) findViewById(R.id.upcf3);
        td3=(EditText)findViewById(R.id.uptd3);
        tp3=(EditText)findViewById(R.id.uptp3);
        ex3=(EditText) findViewById(R.id.upex3);
        m3=(TextView)findViewById(R.id.upmoy3);
        rt3=(Button)findViewById(R.id.uprt3);
        up3=(Button) findViewById(R.id.up3);
        final int[] cpt={0};
        calculer=(Button)findViewById(R.id.upclc3);
        getintentdata();
        rt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update3.this,ModuleActivity.class);
                startActivity(intent);
            }
        });
        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cpt[0]++;
                if (TextUtils.isEmpty(n3.getText())){
                    n3.setText(name);
                }else{
                    if (TextUtils.isEmpty(c3.getText())){
                        Toast.makeText(update3.this, "saiser coefficient ", Toast.LENGTH_SHORT).show();

                    }else{
                        if (TextUtils.isEmpty(td3.getText())){
                            Toast.makeText(update3.this, "saiser note td ", Toast.LENGTH_SHORT).show();

                        }else{
                            if (TextUtils.isEmpty(tp3.getText())){
                                Toast.makeText(update3.this, "saiser note tp ", Toast.LENGTH_SHORT).show();

                            }else{
                                if (TextUtils.isEmpty(ex3.getText())){
                                    Toast.makeText(update3.this, "saiser note EMD", Toast.LENGTH_SHORT).show();

                                }else{
                                    float Td = Float.parseFloat(td3.getText().toString());
                                    float Tp = Float.parseFloat(tp3.getText().toString());
                                    float Emd = Float.parseFloat(ex3.getText().toString());
                                    float Moyenne ;


                                    if(Td>20 || Td<0){
                                        Toast.makeText(update3.this,"errure:td invalide",Toast.LENGTH_SHORT).show();

                                        td3.setText(" ");
                                    }else{
                                        if(Tp>20 || Tp<0){
                                            Toast.makeText(update3.this,"errure:tp invalide",Toast.LENGTH_SHORT).show();

                                            tp3.setText(" ");
                                        }else{
                                            if(Emd>20 || Emd<0){
                                                Toast.makeText(update3.this,"errure:emd invalide",Toast.LENGTH_SHORT).show();

                                                ex3.setText(" ");
                                            }else{
                                                Moyenne = ( Td+ Tp + (Emd * 2)) / 4;
                                                m3.setText(String.valueOf(Moyenne));
                                            }
                                        }} } } } } }


            }
        });
        up3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpt[0]==0){
                    Toast.makeText(update3.this, "Calculer", Toast.LENGTH_SHORT).show();
                }else {
                if (TextUtils.isEmpty(n3.getText())){
                    Toast.makeText(update3.this, "clicker Calculer", Toast.LENGTH_SHORT).show();
                    n3.setText(name);
                }else{
                    if (TextUtils.isEmpty(c3.getText())){
                        Toast.makeText(update3.this, "clicker Calculer", Toast.LENGTH_SHORT).show();

                    }else{
                        if (TextUtils.isEmpty(td3.getText())){
                            Toast.makeText(update3.this, "clicker Calculer", Toast.LENGTH_SHORT).show();

                        }else{
                            if (TextUtils.isEmpty(tp3.getText())){
                                Toast.makeText(update3.this, "clicker Calculer", Toast.LENGTH_SHORT).show();

                            }else{
                                if (TextUtils.isEmpty(ex3.getText())){
                                    Toast.makeText(update3.this, "clicker Calculer", Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(update3.this, "le nom ne peut pas etre modifier", Toast.LENGTH_SHORT).show();
                                    n3.setText(name);
                                    db.updatedata(n3.getText().toString(), Integer.parseInt(c3.getText().toString()), Float.parseFloat(m3.getText().toString()));
                                    Toast.makeText(update3.this, "la moyenne est Modifier", Toast.LENGTH_SHORT).show();
                                }
                            } } } } }}
        });
    }
    void getintentdata(){
        name=getIntent().getStringExtra("nom");
        n3.setText(name);
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}