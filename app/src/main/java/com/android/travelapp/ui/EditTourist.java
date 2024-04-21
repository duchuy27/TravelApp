package com.android.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.travelapp.Adapter.touristAdapter;
import com.android.travelapp.Database.Database;
import com.android.travelapp.Model.touristModel;
import com.android.travelapp.R;

import java.util.ArrayList;

public class EditTourist extends AppCompatActivity {
    Button AddTour, DeleteTour;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tourist);

        AddTour = findViewById(R.id.btn_AddTour);
        DeleteTour = findViewById(R.id.btn_DeleteTour);

        database = new Database(this);
        getData();

        AddTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTourist.this, AddTourist.class);
                startActivity(intent);
            }
        });

        DeleteTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditTourist.this, DeleteTourist.class);
                startActivity(intent);
            }
        });
    }
    private void getData() {
        ArrayList<touristModel> listTourist = database.listTourist();
        if(listTourist.isEmpty()){
            Toast.makeText(EditTourist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        } else {
            RecycleViewAdapterProcess(listTourist);
        }
    }
    private void RecycleViewAdapterProcess(ArrayList<touristModel> listTourist) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        touristAdapter adapter = new touristAdapter(this, listTourist);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
