package com.sheygam.java_17_31_01_18;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gregorysheygam on 31/01/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Person> persons;

    public MyAdapter() {
        persons = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            persons.add(new Person("Person " + i, "person" + i + "@mail.com"));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person p = persons.get(position);
        holder.nameTxt.setText(p.getName());
        holder.emailTxt.setText(p.getEmail());
    }

    public void remove(int position){
        persons.remove(position);
        notifyItemRemoved(position);
    }

    public void move(int from, int to){
        Person p = persons.remove(from);
        persons.add(to, p);
        notifyItemMoved(from, to);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt, emailTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            emailTxt = itemView.findViewById(R.id.emailTxt);
        }
    }
}
