package com.example.wicktalk.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wicktalk.R;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

public class ListUsersAdapter extends BaseAdapter {
    protected ArrayList<QBUser> qbUserArrayList;
    private Context context;

    public ListUsersAdapter(ArrayList<QBUser> qbUserArrayList, Context context) {
        this.qbUserArrayList = qbUserArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return qbUserArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return qbUserArrayList.get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1 = inflater.inflate(android.R.layout.simple_list_item_multiple_choice,null);

            TextView textView =(TextView)view1.findViewById(android.R.id.text1);
            textView.setText(qbUserArrayList.get(i).getLogin());

        }
        return view1;
    }
}
