package com.android.travelapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.travelapp.R;
import com.google.android.material.textfield.TextInputLayout;

public class DeleteTourist extends AppCompatActivity {
    Button btnDelete, btnBack;
    TextInputLayout inpId_tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tourist);

        btnDelete = findViewById(R.id.btn_delete_tourist);
        btnBack = findViewById(R.id.btn_back_edit_tourist);

        inpId_tour = findViewById(R.id.tourID_find);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_tour = inpId_tour.getEditText().getText().toString();

                if (id_tour.isEmpty()){
                    Toast.makeText(DeleteTourist.this, "Your input values can't be left blank", Toast.LENGTH_LONG).show();
                } else {
                    
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteTourist.this, EditTourist.class);
                startActivity(intent);
            }
        });
    }
}
