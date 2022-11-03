package com.example.writetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button btn;
    private DatabaseReference rootDatabaseref;
    private TextView textView;
    private Button btnRead;

    
     // code from https://www.youtube.com/watch?v=T4DQvImX5P8&list=PLYx38U7gxBf3pmsHVTUwRT_lGON6ZIBHi&index=2&ab_channel=TechnicalSkillz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        btn=findViewById(R.id.btn);
        btnRead=findViewById(R.id.btnRead);
        textView=findViewById(R.id.textView);
        
        rootDatabaseref= FirebaseDatabase.getInstance().getReference().child("MyData");

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootDatabaseref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                        {
                            String data=dataSnapshot.getValue().toString();
                            textView.setText(data);
                        }
                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
            });
        }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=input.getText().toString();
                rootDatabaseref.setValue(data);

            }


        });
    }
}