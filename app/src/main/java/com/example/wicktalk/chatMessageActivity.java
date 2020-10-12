package com.example.wicktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.wicktalk.Adapters.chatMessageAdapter;
import com.example.wicktalk.Holder.QBChatMessageHolder;
import com.example.wicktalk.common.common;
import com.google.android.gms.common.internal.service.Common;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBIncomingMessagesManager;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.exception.QBChatException;
import com.quickblox.chat.listeners.QBChatDialogMessageListener;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBChatMessage;
import com.quickblox.chat.request.QBMessageGetBuilder;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;

import java.util.ArrayList;

public class chatMessageActivity extends AppCompatActivity {

    QBChatDialog qbChatDialog;
    ListView lstChatMessages;
    ImageButton submitButton;
    EditText edtContent;
chatMessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        initViews();
        initChatDialoge();
        retrieveMessage();
    }

    private void retrieveMessage() {
        QBMessageGetBuilder messageGetBuilder = new QBMessageGetBuilder();
        messageGetBuilder.setLimit(500);
        if(qbChatDialog !=null)
        {
            QBRestChatService.getDialogMessages(qbChatDialog,messageGetBuilder).performAsync(new QBEntityCallback<ArrayList<QBChatMessage>>() {
                @Override
                public void onSuccess(ArrayList<QBChatMessage> qbChatMessages, Bundle bundle) {
                    //put message to cache
                    QBChatMessageHolder.getInstance().putMessages(qbChatDialog.getDialogId(),qbChatMessages );
                    adapter = new chatMessageAdapter(getBaseContext(),qbChatMessages);
                    lstChatMessages.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(QBResponseException e) {

                }
            });
        }

    }

    private void initChatDialoge() {
        qbChatDialog = (QBChatDialog)getIntent().getSerializableExtra(common.DIALOG_EXTRA);
        qbChatDialog.initForChat(QBChatService.getInstance());

        //Register listner incoming message

        QBIncomingMessagesManager incomingMessage = QBChatService.getInstance().getIncomingMessagesManager();
        incomingMessage.addDialogMessageListener(new QBChatDialogMessageListener() {
            @Override
            public void processMessage(String s, QBChatMessage qbChatMessage, Integer integer) {

            }

            @Override
            public void processError(String s, QBChatException e, QBChatMessage qbChatMessage, Integer integer) {

            }
        });

        qbChatDialog.addMessageListener(new QBChatDialogMessageListener() {
            @Override
            public void processMessage(String s, QBChatMessage qbChatMessage, Integer integer) {
                QBChatMessageHolder.getInstance().putMessage(qbChatMessage.getDialogId(),qbChatMessage);
                ArrayList<QBChatMessage> messages = QBChatMessageHolder.getInstance().getChatMessageByDialogId(qbChatMessage.getDialogId());
                adapter = new chatMessageAdapter(getBaseContext(),messages);
                lstChatMessages.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void processError(String s, QBChatException e, QBChatMessage qbChatMessage, Integer integer) {
                Log.e("Error",""+e.getMessage());
            }
        });

    }

    private void initViews(){
        lstChatMessages = (ListView)findViewById(R.id.list_of_message);
        submitButton = (ImageButton)findViewById(R.id.send_button);
        edtContent = (EditText)findViewById(R.id.edt_content);

    }
}