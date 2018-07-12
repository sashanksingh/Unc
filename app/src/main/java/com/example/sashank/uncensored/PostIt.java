package com.example.sashank.uncensored;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class PostIt extends AppCompatActivity {

    private EditText name,age,title,message;
    private Button mButton;
    private ProgressBar newPostProgress;
    private FirebaseFirestore firebaseFirestore;

    Spinner citizenship;
    String gender = "";
    String opinion = "";

    android.support.v7.widget.Toolbar toolbar;

    MaterialSearchView materialSearchView;
    String[] list;

    AutoCompleteTextView suggestion_box;
    Spinner countries;

    ArrayList<String> countriesName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_it);

        firebaseFirestore = FirebaseFirestore.getInstance();

        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        title=(EditText)findViewById(R.id.title);
        message=(EditText)findViewById(R.id.post);
        mButton=(Button) findViewById(R.id.submit);

        suggestion_box = (AutoCompleteTextView) findViewById(R.id.suggestion_box);
        countries = (Spinner) findViewById(R.id.countries);

        countriesName.add("Afghanistan");
        countriesName.add("Albania");
        countriesName.add("Algeria");
        countriesName.add("Andorra");
        countriesName.add("Bahrain");
        countriesName.add("Bangladesh");
        countriesName.add("Canada");
        countriesName.add("Egypt");
        countriesName.add("Estonia");
        countriesName.add("Iceland");
        countriesName.add("India");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostIt.this, android.R.layout.simple_spinner_dropdown_item, countriesName);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(PostIt.this, android.R.layout.simple_spinner_dropdown_item, countriesName);

        suggestion_box.setAdapter(adapter);
        countries.setAdapter(adapter);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name1 = name.getText().toString();
                final String age1 = age.getText().toString();
                final String gender1 = gender.toString();
                final String country = countries.getSelectedItem().toString();
                final String title1 = title.getText().toString();
                final String message1 = message.getText().toString();
                final String opin = opinion.toString();

                if(!TextUtils.isEmpty(title1) && !TextUtils.isEmpty(message1))
                {
                    final String randomName = UUID.randomUUID().toString();

                    Map<String, Object> postMap = new HashMap<>();
                    postMap.put("name", name1);
                    postMap.put("age", age1);
                    postMap.put("gender",gender1);
                    postMap.put("country",country);
                    postMap.put("title", title1);
                    postMap.put("message", message1);
                    postMap.put("opinion", opin);
                    postMap.put("timestamp", FieldValue.serverTimestamp());

                    firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>(){
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(PostIt.this, "Post Added Successfully", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(PostIt.this, "Title Or Message Field Are Required.", Toast.LENGTH_LONG).show();
                }
            }
        });

        }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    gender = "Male";
                    break;
            case R.id.radio_female:
                if (checked)
                    gender = "Female";
                    break;
            case R.id.radio_others:
                if (checked)
                    gender = "Others";
                    break;
            case R.id.radio_view:
                if (checked)
                    opinion = "View";
                break;
            case R.id.radio_confession:
                if (checked)
                    opinion = "Confession";
                break;
        }
    }
}
