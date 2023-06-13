package com.example.calculm.classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculm.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ModuleActivity extends AppCompatActivity {
  Button add;
  Button back;
   Button calculerMG;
   private static ImageView imgv;
   int[] images={R.drawable.bravo,R.drawable.sad};
   TextView mention;
    TextView MoyG;
    private AlertDialog dialog;
  private AlertDialog.Builder builder;
    RecyclerView recyclerview;
    ArrayList<String> m_nom;
    ArrayList<Integer> m_coef;
    ArrayList<Float> m_moy;
    databaseModul db;
    CustomAdapter cstma;
    ArrayList<Float> mcf;
    float Mg;
    int sumcoef=0;
    float sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        recyclerview = (RecyclerView) findViewById(R.id.rcv);
        db = new databaseModul( this,"moyenne",null,5) ;
        m_nom=new ArrayList<>();
        m_coef=new ArrayList<Integer>();
        m_moy=new ArrayList<>();
        mcf=new ArrayList<>();
        stordata();
        cstma = new CustomAdapter(ModuleActivity.this,this,m_nom,m_coef,m_moy);
        recyclerview.setAdapter(cstma);
        new ItemTouchHelper(item).attachToRecyclerView(recyclerview);
        new ItemTouchHelper(item2).attachToRecyclerView(recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(ModuleActivity.this));




        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View vpop = getLayoutInflater().inflate(R.layout.activity_tabl_module,null);
                Button fermer;
                TextView t1 = vpop.findViewById(R.id.t1);
                TextView t2 = vpop.findViewById(R.id.t2);
                TextView t3 = vpop.findViewById(R.id.t3);
                 fermer = vpop.findViewById(R.id.cls);
                t1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Intent intent = new Intent(ModuleActivity.this,MoyenneMActivity.class);
                        startActivity(intent);
                    }
                });
                t2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ModuleActivity.this,tdemd.class);
                        startActivity(intent);
                    }
                });
                t3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ModuleActivity.this,MainActivity1.class);
                        startActivity(intent);
                    }
                });
                builder = new AlertDialog.Builder(ModuleActivity.this);
                builder.setView(vpop);
                dialog=builder.create();
                dialog.show();
              fermer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        back = (Button)findViewById(R.id.acc);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModuleActivity.this,AccueilActivity.class);
                startActivity(intent);
            }
        });

        for(int i=0;i<m_coef.size();i++){
            sumcoef=sumcoef+m_coef.get(i);

        }
        for(int j =0;j<m_coef.size();j++){
            int a1=m_coef.get(j);
            float a2 =m_moy.get(j);
            mcf.add(a1*a2);

        }

        for(int i=0;i<mcf.size();i++){
            sum=sum+mcf.get(i);

        }


        float Mg=sum/sumcoef;
        imgv=(ImageView)findViewById(R.id.mv);
        calculerMG=(Button) findViewById(R.id.MG);
        mention=(TextView)findViewById(R.id.mnt);
        MoyG=(TextView) findViewById(R.id.mg);
        calculerMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MoyG.setText(String.valueOf(Mg));
               if(Mg>=10){
                   imgv.setImageResource(images[0]);
                   mention.setText("Admis,Bravo!!");
               }
               if(Mg<10){
                   imgv.setImageResource(images[1]);
                   mention.setText("Ajournée:(");
               }
            }
        });
    }
    @SuppressLint("Range")
    void stordata(){
        Cursor cur = db.readdata();
        if(cur.getCount() == 0){
            Toast.makeText(this,"Ajouter vos Modules",Toast.LENGTH_SHORT).show();
       }else{
            while (cur.moveToNext()){
                m_nom.add(cur.getString(0));
                m_coef.add(cur.getInt(1));
                m_moy.add(cur.getFloat(2));

            }
        }

    }
    String n;
    ItemTouchHelper.SimpleCallback item2= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            n= String.valueOf(viewHolder.itemView.getTag());
            View vpop = getLayoutInflater().inflate(R.layout.deleting,null);
            Button yes=vpop.findViewById(R.id.yes);
            Button no=vpop.findViewById(R.id.no);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deletrow(n);
                    Toast.makeText(ModuleActivity.this, "le module "+n+" est supprimer", Toast.LENGTH_SHORT).show();
                    m_nom.remove(viewHolder.getAdapterPosition());
                    m_coef.remove(viewHolder.getAdapterPosition());
                    m_moy.remove(viewHolder.getAdapterPosition());
                    cstma.notifyDataSetChanged();
                    imgv.setImageResource(R.drawable.white);
                    mention.setText(" ");
                    MoyG.setText("Moyenne=");
                    Intent intent=new Intent(ModuleActivity.this,ModuleActivity.class);
                    startActivity(intent);
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ModuleActivity.this,ModuleActivity.class);
                    startActivity(intent);
                }
            });
            builder = new AlertDialog.Builder(ModuleActivity.this);
            builder.setView(vpop);
            dialog=builder.create();
            dialog.show();




        }
    };

    ItemTouchHelper.SimpleCallback item= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
             n= String.valueOf(viewHolder.itemView.getTag());

            View vpop = getLayoutInflater().inflate(R.layout.activity_tabl_module,null);
            Button fermer;
            TextView t1 = vpop.findViewById(R.id.t1);
            TextView t2 = vpop.findViewById(R.id.t2);
            TextView t3 = vpop.findViewById(R.id.t3);
            fermer = vpop.findViewById(R.id.cls);
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ModuleActivity.this,update3.class);
                    intent.putExtra("nom",n);
                    startActivity(intent);
                }
            });
            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ModuleActivity.this,update2.class);
                    intent.putExtra("nom",n);
                    startActivity(intent);
                }
            });
            t3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ModuleActivity.this,update1.class);
                    intent.putExtra("nom",n);
                    startActivity(intent);
                }
            });
            builder = new AlertDialog.Builder(ModuleActivity.this);
            builder.setView(vpop);
            dialog=builder.create();
            dialog.show();
            fermer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ModuleActivity.this,ModuleActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });




        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}