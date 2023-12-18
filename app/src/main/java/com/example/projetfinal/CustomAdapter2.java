package com.example.projetfinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList client_id, client_nom, client_prenom, client_email, client_tel;

    CustomAdapter2(Activity activity, Context context, ArrayList client_id, ArrayList client_nom,
                   ArrayList client_prenom, ArrayList client_email, ArrayList client_tel) {
        this.activity = activity;
        this.context = context;
        this.client_id = client_id;
        this.client_nom = client_nom;
        this.client_prenom = client_prenom;
        this.client_email = client_email;
        this.client_tel = client_tel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.client_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.client_id_txt.setText(String.valueOf(client_id.get(position)));
        holder.client_nom_txt.setText(String.valueOf(client_nom.get(position)));
        holder.client_prenom_txt.setText(String.valueOf(client_prenom.get(position)));
        holder.client_email_txt.setText(String.valueOf(client_email.get(position)));
        holder.client_tel_txt.setText(String.valueOf(client_tel.get(position)));

        // RecyclerView onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateClientActivity.class);
                intent.putExtra("id", String.valueOf(client_id.get(position)));
                intent.putExtra("nom", String.valueOf(client_nom.get(position)));
                intent.putExtra("prenom", String.valueOf(client_prenom.get(position)));
                intent.putExtra("email", String.valueOf(client_email.get(position)));
                intent.putExtra("tel", String.valueOf(client_tel.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return client_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView client_id_txt, client_nom_txt, client_prenom_txt, client_email_txt, client_tel_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            client_id_txt = itemView.findViewById(R.id.client_id_txt);
            client_nom_txt = itemView.findViewById(R.id.client_nom_txt);
            client_prenom_txt = itemView.findViewById(R.id.client_prenom_txt);
            client_email_txt = itemView.findViewById(R.id.client_email_txt);
            client_tel_txt = itemView.findViewById(R.id.client_tel_txt);
            mainLayout = itemView.findViewById(R.id.mainLayoutClient);
            // Animate RecyclerView
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}