package com.example.calculm.classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculm.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyviewHolder> {
     private Context context;
     private Cursor mcursor;
     private ArrayList m_nom,m_coef,m_moy;
     Activity act;
     int position;
     SQLiteDatabase sqLiteDatabase;
      View vpop;

     CustomAdapter(Activity act,Context context,ArrayList m_nom,ArrayList m_coef,ArrayList m_moy){
         this.act=act;
         this.context=context;
         this.m_nom=m_nom;
         this.m_coef=m_coef;
         this.m_moy=m_moy;

     }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.row,parent,false);
        vpop = inflater.inflate(R.layout.toas,null);
        return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {

     String nom=String.valueOf(m_nom.get(position));

        holder.m_nomtx.setText(String.valueOf(m_nom.get(position)));
        holder.m_nomtx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast t =Toast.makeText(context, "Glisser: -> supprimer| <- modifier", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.TOP|Gravity.CENTER,20,30);
                        t.setView(vpop);
                        t.show();
                  }
        });
        holder.m_coeftx.setText(String.valueOf(m_coef.get(position)));
        holder.m_coeftx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t =Toast.makeText(context, "Glisser: -> supprimer| <- modifier", Toast.LENGTH_LONG);
                t.setGravity(Gravity.TOP|Gravity.CENTER,20,30);
                t.setView(vpop);
                t.show();
            }
        });
        holder.m_moytx.setText(String.valueOf(m_moy.get(position)));
        holder.m_moytx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t =Toast.makeText(context, "Glisser: -> supprimer| <- modifier", Toast.LENGTH_LONG);
                t.setGravity(Gravity.TOP|Gravity.CENTER,20,30);
                t.setView(vpop);
                t.show();
            }
        });
        holder.itemView.setTag(nom);








    }

    @Override
    public int getItemCount() {
        return m_nom.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView m_nomtx,m_coeftx,m_moytx;
        LinearLayout mainLayout;
            Button del;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            m_nomtx=itemView.findViewById((R.id.m_nom));
            m_coeftx=itemView.findViewById((R.id.m_coef));
            m_moytx=itemView.findViewById((R.id.m_moy));
            mainLayout = itemView.findViewById(R.id.linearLayout);


        }
    }
}
