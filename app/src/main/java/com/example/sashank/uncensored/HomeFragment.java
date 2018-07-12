package com.example.sashank.uncensored;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView blog_list_view;
    private List<BlogPost> blog_list;
    private FirebaseFirestore firebaseFirestore;
    private BlogRecyclerAdapter blogRecyclerAdapter;

    private ImageView mImageView;

    Toolbar toolbar;

    MenuItem fav;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);

        blog_list=new ArrayList<>();

        mImageView=(ImageView) view.findViewById(R.id.imageView);

        blog_list_view=(RecyclerView) view.findViewById(R.id.blog_list_view);
        blog_list_view.setHasFixedSize(true);
        blog_list_view.setLayoutManager(new LinearLayoutManager(container.getContext()));
        blogRecyclerAdapter=new BlogRecyclerAdapter(getContext(),blog_list);
        blog_list_view.setAdapter(blogRecyclerAdapter);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Regarding Uncensored" + "&body=" + "Hi there," + "&to=" + "cybeorg_me@protonmail.com");
                testIntent.setData(data);
                startActivity(testIntent);
            }
        });


        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Posts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                for(DocumentChange doc:documentSnapshots.getDocumentChanges())
                {
                  if(doc.getType()==DocumentChange.Type.ADDED)
                  {
                      BlogPost blogPost=doc.getDocument().toObject(BlogPost.class);
                      blog_list.add(blogPost);
                      blogRecyclerAdapter.notifyDataSetChanged();
                  }
                }
            }
        });

        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        fav = menu.add("");
        fav.setIcon(R.drawable.ic_mail_black_24dp);
//        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.mail_btn:
                Toast.makeText(getContext(), "Mail", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }
    }
}
