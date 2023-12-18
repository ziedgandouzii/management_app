package com.example.projetfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogin, editPassword;
    private Button btnLogin;
    private TextView signupTextView;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLogin = findViewById(R.id.loginEditText);
        editPassword = findViewById(R.id.passwordEditText);
        btnLogin = findViewById(R.id.loginButton);
        signupTextView = findViewById(R.id.signUpTextView);


        dbHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when the TextView is clicked
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();  // Close the signup activity to prevent going back
            }
        });
    }

    private void loginUser() {
        String login = editLogin.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"id", "role"};
        String selection = "login=? AND password=?";
        String[] selectionArgs = {login, password};
        Cursor cursor = db.query("Compte", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // User authenticated
            @SuppressLint("Range") String role = cursor.getString(cursor.getColumnIndex("role"));

            // Redirect to the appropriate activity based on the role
            Intent intent;
            if ("admin".equals(role)) {
                intent = new Intent(LoginActivity.this, AdminActivity.class);
            } else {
                intent = new Intent(LoginActivity.this, AdminActivity.class);
            }

            startActivity(intent);
            finish();  // Close the login activity to prevent going back
        } else {
            Toast.makeText(this, "Incorrect login or password", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
    }
}
