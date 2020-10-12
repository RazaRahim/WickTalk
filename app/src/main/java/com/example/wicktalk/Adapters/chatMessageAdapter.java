package com.example.wicktalk.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.wicktalk.Holder.QBUsersHolder;
import com.example.wicktalk.R;
import com.github.library.bubbleview.BubbleTextView;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.model.QBChatMessage;

import java.util.ArrayList;

public class chatMessageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<QBChatMessage> qbChatMessages;

    public chatMessageAdapter(Context context, ArrayList<QBChatMessage> qbChatMessages) {
        this.context = context;
        this.qbChatMessages = qbChatMessages;
    }

    @Override
    public int getCount() {
        return qbChatMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return qbChatMessages.get(i);
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
            if(qbChatMessages.get(i).getSenderId().equals(QBChatService.getInstance().getUser().getId()))
            {
                view1 = inflater.inflate(R.layout.list_send_message,null);
                BubbleTextView bubbleTextView = (BubbleTextView)view1.findViewById(R.id.message_content);
                bubbleTextView.setText(qbChatMessages.get(i).getBody());
            }
            else
            {
                view1 = inflater.inflate(R.layout.list_rec_message,null);
                BubbleTextView bubbleTextView = (BubbleTextView)view1.findViewById(R.id.message_content);
                bubbleTextView.setText(qbChatMessages.get(i).getBody());
                TextView textView = (TextView)view1.findViewById(R.id.message_user);
                textView.setText(QBUsersHolder.getInstance().getUserById(qbChatMessages.get(i).getSenderId()).getFullName());

            }
        }
        return view1;
    }

}
