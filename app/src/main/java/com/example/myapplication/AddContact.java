package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddContact extends AppCompatActivity implements View.OnClickListener {
    EditText etName, etNumber, etWeb, etLocation;
    ImageView ivGreen, ivYellow, ivRed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etLocation = findViewById(R.id.etLocation);
        etWeb = findViewById(R.id.etWeb);
        ivGreen = findViewById(R.id.ivGreen);
        ivYellow = findViewById(R.id.ivYellow);
        ivRed = findViewById(R.id.ivRed);

        ivGreen.setOnClickListener(this);
        ivYellow.setOnClickListener(this);
        ivRed.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty() ||
                etLocation.getText().toString().isEmpty() || etWeb.getText().toString().isEmpty()){
            Toast.makeText(AddContact.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
        }else{
            String name = etName.getText().toString().trim();
            String number = etNumber.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String web = etWeb.getText().toString().trim();

            Intent intent = new Intent();
            intent.putExtra("name",name);
            intent.putExtra("number",number);
            intent.putExtra("location",location);
            intent.putExtra("web",web);
            //pair the view click with the correct button
            if(v.getId() == R.id.ivGreen){
                intent.putExtra("mood","green");
            } else if (v.getId() == R.id.ivYellow) {
                intent.putExtra("mood","yellow");
            }else {
                intent.putExtra("mood","red");
            }
            setResult(RESULT_OK,intent);
            AddContact.this.finish();

        }

    }
}