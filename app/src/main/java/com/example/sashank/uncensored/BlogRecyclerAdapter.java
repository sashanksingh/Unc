package com.example.sashank.uncensored;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder> {

    public List<BlogPost> blog_list;
    public Context context;

    public BlogRecyclerAdapter(Context context, List<BlogPost> blog_list)
    {
        this.blog_list=blog_list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        String name = blog_list.get(position).getName();
        String title_name = blog_list.get(position).getTitle();
        String cntry = blog_list.get(position).getCountry();
        String agge = blog_list.get(position).getAge();
        String gndr = blog_list.get(position).getGender();
        String msg = blog_list.get(position).getMessage();
        String opn = blog_list.get(position).getOpinion();

        holder.setDesctext(name, title_name, cntry, agge, gndr, msg, opn);

        long millisecond = blog_list.get(position).getTimestamp().getTime();
        String dateString = DateFormat.format("MM/dd/yyyy",new Date(millisecond)).toString();
        holder.setTime(dateString);

    }

    @Override
    public int getItemCount() {
        return blog_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public View mView;

        public TextView username;
        public TextView title;
        public TextView blogdate;
        public TextView country;
        public TextView age;
        public TextView gender;
        public TextView message;
        public TextView opinion;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mView=itemView;
        }

        public void setDesctext(String userName, String descText, String Country, String Age, String Gender, String Message, String Opinion)
        {

            username = (TextView) mView.findViewById(R.id.blog_user_name);
            title = (TextView) mView.findViewById(R.id.title);
            country = (TextView) mView.findViewById(R.id.country);
            age = (TextView) mView.findViewById(R.id.age);
            gender = (TextView) mView.findViewById(R.id.gender);
            message = (TextView) mView.findViewById(R.id.message);
            opinion = (TextView) mView.findViewById(R.id.opinion);
            username.setText(userName);
            title.setText(descText);
//            date.setText(Date);
            country.setText(Country);
            age.setText(Age);
            gender.setText(Gender);
            message.setText(Message);
            opinion.setText(Opinion);
        }

        public void setTime(String date)
        {
            blogdate = (TextView) mView.findViewById(R.id.blog_date);
            blogdate.setText(date);

        }
    }
}
