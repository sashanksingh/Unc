package com.example.sashank.uncensored;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entourage extends AppCompatActivity {

    private Button clcikMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entourage);
        clcikMe=(Button)findViewById(R.id.click_me);
        clcikMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Entourage.this,HomeActivity.class);
                startActivity(intent);

            }
        });
    }
}
