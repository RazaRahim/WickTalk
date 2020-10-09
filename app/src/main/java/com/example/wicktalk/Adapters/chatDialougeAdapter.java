package com.example.wicktalk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wicktalk.R;
import com.quickblox.chat.model.QBChatDialog;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class chatDialougeAdapter extends BaseAdapter {
   private Context context;
    private ArrayList<QBChatDialog> qbChatDialogs;

    public chatDialougeAdapter(Context context, ArrayList<QBChatDialog> qbChatDialogs) {
        this.context = context;
        this.qbChatDialogs = qbChatDialogs;
    }

    @Override
    public int getCount() {
        return qbChatDialogs.size();
    }

    @Override
    public Object getItem(int i) {
        return qbChatDialogs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
     View view1 = view;
     if(view1 == null){
         LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         view1 = inflater.inflate(R.layout.list_chat_dialouge,null);

         TextView message,title;
         ImageView imageView;

         message = (TextView)view1.findViewById(R.id.list_chat_dialouge_title);
         title = (TextView)view1.findViewById(R.id.list_chat_dialouge_message);
         imageView = (ImageView) view1.findViewById(R.id.image);


     }
     return view1;
    }
}
