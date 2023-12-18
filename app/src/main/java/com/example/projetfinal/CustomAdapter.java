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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList product_id, product_libelle, product_description, product_photo, product_prix, product_quantite;

    CustomAdapter(Activity activity, Context context, ArrayList product_id, ArrayList product_libelle,
                  ArrayList product_description, ArrayList product_photo, ArrayList product_prix,
                  ArrayList product_quantite) {
        this.activity = activity;
        this.context = context;
        this.product_id = product_id;
        this.product_libelle = product_libelle;
        this.product_description = product_description;
        this.product_photo = product_photo;
        this.product_prix = product_prix;
        this.product_quantite = product_quantite;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.product_id_txt.setText(String.valueOf(product_id.get(position)));
        holder.product_libelle_txt.setText(String.valueOf(product_libelle.get(position)));
        holder.product_description_txt.setText(String.valueOf(product_description.get(position)));
        holder.product_photo_txt.setText(String.valueOf(product_photo.get(position)));
        holder.product_prix_txt.setText(String.valueOf(product_prix.get(position)));
        holder.product_quantite_txt.setText(String.valueOf(product_quantite.get(position)));

        // RecyclerView onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateProductActivity.class);
                intent.putExtra("id", String.valueOf(product_id.get(position)));
                intent.putExtra("libelle", String.valueOf(product_libelle.get(position)));
                intent.putExtra("description", String.valueOf(product_description.get(position)));
                intent.putExtra("photo", String.valueOf(product_photo.get(position)));
                intent.putExtra("prix", String.valueOf(product_prix.get(position)));
                intent.putExtra("quantite", String.valueOf(product_quantite.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView product_id_txt, product_libelle_txt, product_description_txt,
                product_photo_txt, product_prix_txt, product_quantite_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id_txt = itemView.findViewById(R.id.product_id_txt);
            product_libelle_txt = itemView.findViewById(R.id.product_libelle_txt);
            product_description_txt = itemView.findViewById(R.id.product_description_txt);
            product_photo_txt = itemView.findViewById(R.id.product_photo_txt);
            product_prix_txt = itemView.findViewById(R.id.product_prix_txt);
            product_quantite_txt = itemView.findViewById(R.id.product_quantite_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            // Animate RecyclerView
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
