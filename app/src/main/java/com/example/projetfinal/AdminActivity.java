package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private Button btnManageClients,btnManageProducts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnManageClients = findViewById(R.id.btnManageClients);
        btnManageProducts = findViewById(R.id.btnManageProducts);
        btnManageClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainClientActivity.class);
                startActivity(intent);
            }
        });
        btnManageProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
