package com.example.sashank.uncensored;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostIt extends AppCompatActivity {

    private EditText name,age,gender,countrty,title,message;
    private Button mButton;
    private ProgressBar newPostProgress;
    private FirebaseFirestore firebaseFirestore;
    private android.support.v7.widget.Toolbar newToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_it);
        firebaseFirestore = FirebaseFirestore.getInstance();


        newToolbar=findViewById(R.id.newpost_toolbar);
        setSupportActionBar(newToolbar);
        getSupportActionBar().setTitle("Add New Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        gender=(EditText)findViewById(R.id.gender);
        countrty=(EditText)findViewById(R.id.country);
        title=(EditText)findViewById(R.id.title);
        message=(EditText)findViewById(R.id.post);
        mButton=(Button) findViewById(R.id.submit);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name1=name.getText().toString();
                final String age1=age.getText().toString();
                final String gender1=gender.getText().toString();
                final String country1=countrty.getText().toString();
                final String title1=title.getText().toString();
                final String message1=message.getText().toString();



                if(!TextUtils.isEmpty(title1) && !TextUtils.isEmpty(message1))
                {
                    final String randomName = UUID.randomUUID().toString();

                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("name", name1);
                    postMap.put("age", age1);
                    postMap.put("gender", gender1);
                    postMap.put("country", country1);
                    postMap.put("title", title1);
                    postMap.put("message", message1);
                    postMap.put("timestamp", FieldValue.serverTimestamp());





                    firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>(){
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(PostIt.this, "Successfully Added", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(PostIt.this, HomeActivity.class);
                                startActivity(mainIntent);


                            } else {

                                Toast.makeText(PostIt.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                            }



                        }
                    });
                }
                else
                {
                    Toast.makeText(PostIt.this, "some field are missing", Toast.LENGTH_LONG).show();
                }
            }
        });
        }


}
