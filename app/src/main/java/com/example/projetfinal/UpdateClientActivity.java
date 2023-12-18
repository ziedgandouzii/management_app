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

public class UpdateClientActivity extends AppCompatActivity {

    EditText nom_input, prenom_input, email_input, tel_input;
    Button update_button, delete_button;

    String id, nom, prenom, email, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_update);

        nom_input = findViewById(R.id.nom_input2);
        prenom_input = findViewById(R.id.prenom_input2);
        email_input = findViewById(R.id.email_input2);
        tel_input = findViewById(R.id.tel_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        // First, we call this
        getAndSetIntentData();

        // Set action bar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nom);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // And only then we call this
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(UpdateClientActivity.this);
                nom = nom_input.getText().toString().trim();
                prenom = prenom_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                tel = tel_input.getText().toString().trim();

                myDB.updateData(id, nom, prenom, email, tel);
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
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nom") &&
                getIntent().hasExtra("prenom") && getIntent().hasExtra("email") &&
                getIntent().hasExtra("tel")) {

            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            prenom = getIntent().getStringExtra("prenom");
            email = getIntent().getStringExtra("email");
            tel = getIntent().getStringExtra("tel");

            // Setting Intent Data
            nom_input.setText(nom);
            prenom_input.setText(prenom);
            email_input.setText(email);
            tel_input.setText(tel);
            Log.d("stev", nom + " " + prenom + " " + email + " " + tel);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + nom + " ?");
        builder.setMessage("Are you sure you want to delete " + nom + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(UpdateClientActivity.this);
                myDB.deleteOneClient(id);
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
