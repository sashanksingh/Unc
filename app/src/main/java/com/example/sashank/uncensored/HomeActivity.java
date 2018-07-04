package com.example.sashank.uncensored;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    private TextView mTextView;
    private ImageView mImageView;
    private FloatingActionButton mFloatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        mTextView=(TextView)findViewById(R.id.textView);
        mImageView=(ImageView)findViewById(R.id.imageView);
        mFloatingActionButton=(FloatingActionButton)findViewById(R.id.floating_button);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Regarding Uncensored" + "&body=" + "Hi there," + "&to=" + "cybeorg_me@protonmail.com");
                testIntent.setData(data);
                startActivity(testIntent);
            }
        });

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,PostIt.class);
                startActivity(intent);
            }
        });




FragmentManager fragmentManager=getSupportFragmentManager();
FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
HomeFragment homeFragment=new HomeFragment();
fragmentTransaction.add(R.id.main_container,homeFragment);
fragmentTransaction.commit();

    }



}
