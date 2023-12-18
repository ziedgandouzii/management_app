package com.example.projetfinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProductActivity extends AppCompatActivity {

    EditText libelle_input, description_input, photo_input, prix_input, quantite_input;
    Button update_button, delete_button;

    String id, libelle, description, photo, prix, quantite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_update);

        libelle_input = findViewById(R.id.libelle_input2);
        description_input = findViewById(R.id.description_input2);
        photo_input = findViewById(R.id.photo_input2);
        prix_input = findViewById(R.id.prix_input2);
        quantite_input = findViewById(R.id.quantite_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // First, we call this
        getAndSetIntentData();

        // Set action bar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(libelle);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateProductActivity.this);
                libelle = libelle_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                photo = photo_input.getText().toString().trim();

                // Convert quantite to int
                int updatedQuantite = Integer.parseInt(quantite_input.getText().toString().trim());

                // Convert prix to double
                double updatedPrix = Double.parseDouble(prix_input.getText().toString().trim());

                myDB.updateData(id, libelle, description, photo, updatedPrix, updatedQuantite);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("libelle") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("photo") &&
                getIntent().hasExtra("prix") && getIntent().hasExtra("quantite")) {

            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            libelle = getIntent().getStringExtra("libelle");
            description = getIntent().getStringExtra("description");
            photo = getIntent().getStringExtra("photo");
            prix = getIntent().getStringExtra("prix");
            quantite = getIntent().getStringExtra("quantite");

            // Setting Intent Data
            libelle_input.setText(libelle);
            description_input.setText(description);
            photo_input.setText(photo);
            prix_input.setText(prix);
            quantite_input.setText(quantite);
            Log.d("stev", libelle + " " + description + " " + photo + " " + prix + " " + quantite);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + libelle + " ?");
        builder.setMessage("Are you sure you want to delete " + libelle + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateProductActivity.this);
                myDB.deleteOneProduct(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
