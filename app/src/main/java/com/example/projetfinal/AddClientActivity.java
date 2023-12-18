package com.example.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddClientActivity extends AppCompatActivity {

    EditText nom_input, prenom_input, email_input, tel_input;
    Button add_button, row_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_add);

        nom_input = findViewById(R.id.nom_input);
        prenom_input = findViewById(R.id.prenom_input);
        email_input = findViewById(R.id.email_input);
        tel_input = findViewById(R.id.tel_input);
        add_button = findViewById(R.id.add_button);
        row_button = findViewById(R.id.row_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(AddClientActivity.this);
                myDB.addClient(nom_input.getText().toString().trim(),
                        prenom_input.getText().toString().trim(),
                        email_input.getText().toString().trim(),
                        tel_input.getText().toString().trim());
                Intent intent = new Intent(AddClientActivity.this, MainClientActivity.class);
                startActivity(intent);
            }
        });

        // Row button click listener
        row_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Consulter" button click and navigate to MainActivity
                Intent intent = new Intent(AddClientActivity.this, MainClientActivity.class);
                startActivity(intent);
            }
        });
    }
}
