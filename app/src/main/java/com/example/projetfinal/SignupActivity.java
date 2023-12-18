package com.example.projetfinal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

public class SignupActivity extends AppCompatActivity {

    private EditText editSignupLogin, editSignupPassword, editSignupRole;
    private Button btnSignup;
    private TextView loginTextView;  // TextView for linking to login activity


    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editSignupLogin = findViewById(R.id.loginEditText);
        editSignupPassword = findViewById(R.id.passwordEditText);
        editSignupRole = findViewById(R.id.roleEditText);
        btnSignup = findViewById(R.id.SignupButton);
        loginTextView = findViewById(R.id.LoginTextView);

        dbHelper = new DatabaseHelper(this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser();
            }
        });
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when the TextView is clicked
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();  // Close the signup activity to prevent going back
            }
        });
    }

    private void signupUser() {
        String login = editSignupLogin.getText().toString().trim();
        String password = editSignupPassword.getText().toString().trim();
        String role = editSignupRole.getText().toString().trim();

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password) || TextUtils.isEmpty(role)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Check if the login already exists
        if (isLoginExists(db, login)) {
            Toast.makeText(this, "Login already exists. Choose a different one.", Toast.LENGTH_SHORT).show();
            return;
        }

        // If the login doesn't exist, proceed with the signup
        ContentValues values = new ContentValues();
        values.put("login", login);
        values.put("password", password);
        values.put("role", role);

        long newRowId = db.insert("Compte", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            // Redirect to the login screen after successful signup
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();  // Close the signup activity to prevent going back
        } else {
            Toast.makeText(this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private boolean isLoginExists(SQLiteDatabase db, String login) {
        String[] columns = {"id"};
        String selection = "login=?";
        String[] selectionArgs = {login};
        Cursor cursor = db.query("Compte", columns, selection, selectionArgs, null, null, null);

        boolean loginExists = cursor.moveToFirst();
        cursor.close();

        return loginExists;
    }
}
