package com.example.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {

    EditText libelle_input, description_input, photo_input, prix_input, quantite_input;
    Button add_button, row_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add);

        libelle_input = findViewById(R.id.libelle_input);
        description_input = findViewById(R.id.description_input);
        photo_input = findViewById(R.id.photo_input);
        prix_input = findViewById(R.id.prix_input);
        quantite_input = findViewById(R.id.quantite_input);
        add_button = findViewById(R.id.add_button);
        row_button = findViewById(R.id.row_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddProductActivity.this);
                myDB.addProduct(libelle_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        photo_input.getText().toString().trim(),
                        Double.valueOf(prix_input.getText().toString().trim()),
                        Integer.valueOf(quantite_input.getText().toString().trim()));
                Intent intent = new Intent(AddProductActivity.this, MainProductActivity.class);
                startActivity(intent);
            }
        });

        // Row button click listener
        row_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the "Consulter" button click and navigate to MainActivity
                Intent intent = new Intent(AddProductActivity.this, MainProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
