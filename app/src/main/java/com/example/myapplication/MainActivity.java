package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivSmile, ivCall, ivWeb, ivMap;
    Button btnCreate;
    LinearLayout layVertical;
    final int ADD_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivSmile = findViewById(R.id.ivSmile);
        ivCall = findViewById(R.id.ivCall);
        ivWeb = findViewById(R.id.ivWeb);
        ivMap = findViewById(R.id.ivMap);
        layVertical = findViewById(R.id.layVertical);
        btnCreate = findViewById(R.id.btnCreate);

        layVertical.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.myapplication.AddContact.class);
                startActivityForResult(intent, ADD_CONTACT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_CONTACT){
            if(resultCode == RESULT_OK){
                layVertical.setVisibility(View.VISIBLE);
                //setting the correct image with the corresponding mood passed from an intent from AddContact.java
                if(data.getStringExtra("mood").equals("green") ){
                    ivSmile.setImageResource(R.drawable.happy);
                } else if (data.getStringExtra("mood").equals("yellow")) {
                    ivSmile.setImageResource(R.drawable.caution);
                }else{
                    ivSmile.setImageResource(R.drawable.flag);
                }
                /**
                 * This method sets implicit intents to the dialler , data from the passed intent on AddContact.java
                 */
                ivCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String number = data.getStringExtra("number");
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                        startActivity(intent);
                    }
                });
                /**
                 * This method sets implicit intents for the website, uses data passed from intent created on AddContact.java
                 */
                ivWeb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String web = data.getStringExtra("web");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+web));
                        startActivity(intent);
                    }
                });
                /**
                 * This method sets implicit intents for the map, Uses data passed from intent created on AddContact.java
                 */
                ivMap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String map = data.getStringExtra("location");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+map));
                        startActivity(intent);
                    }
                });
            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(MainActivity.this, "No Contact was created", Toast.LENGTH_SHORT).show();
            }
        }
    }
}