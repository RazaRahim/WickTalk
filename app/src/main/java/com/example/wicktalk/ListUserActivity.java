package com.example.wicktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wicktalk.Adapters.ListUsersAdapter;
import com.example.wicktalk.Holder.QBUsersHolder;
import com.example.wicktalk.common.common;
import com.google.android.gms.common.internal.service.Common;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.chat.model.QBDialogType;
import com.quickblox.chat.utils.DialogUtils;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {
ListView lstUsers;
Button btnCreateChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        RetriveAllUsers();

        lstUsers = (ListView)findViewById(R.id.users);
        lstUsers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnCreateChat = (Button)findViewById(R.id.btn_chatUser);
        btnCreateChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countChoice = lstUsers.getCount();
                if(lstUsers.getCheckedItemPositions().size() == 1)
                    createPrivateChat(lstUsers.getCheckedItemPositions());

                else if(lstUsers.getCheckedItemPositions().size() > 1)
                    createGroupChat(lstUsers.getCheckedItemPositions());
                else
                    Toast.makeText(ListUserActivity.this, "Please Select Friend to Chat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createGroupChat(SparseBooleanArray checkedItemPositions) {
        ProgressDialog nDialouge = new ProgressDialog(ListUserActivity.this);
        nDialouge.setMessage("Please Waiting...");
        nDialouge.setCanceledOnTouchOutside(false);
        nDialouge.show();

        int countChoice = lstUsers.getCount();
        ArrayList<Integer> occupantIdsList = new ArrayList<>();
        for(int i=0;i<countChoice;i++){
            if(checkedItemPositions.get(i));
            {
                QBUser user = (QBUser)lstUsers.getItemAtPosition(i);
                occupantIdsList.add(user.getId());
            }
        }
        //Create Chat Dialouge

        QBChatDialog dialog = new QBChatDialog();
        dialog.setName(common.createChatDialougeName(occupantIdsList));
        dialog.setType(QBDialogType.GROUP);
        dialog.setOccupantsIds(occupantIdsList);

        QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
            @Override
            public void onSuccess(QBChatDialog qbChatDialog, Bundle bundle) {
                nDialouge.dismiss();
                Toast.makeText(ListUserActivity.this, "Create Chat Dialouge Sessfully..", Toast.LENGTH_SHORT).show();
            finish();
            }

            @Override
            public void onError(QBResponseException e) {
Log.e("Error",""+e.getMessage());
            }
        });

    }

    private void createPrivateChat(SparseBooleanArray checkedItemPositions) {
        ProgressDialog nDialouge = new ProgressDialog(ListUserActivity.this);
        nDialouge.setMessage("Please Waiting...");
        nDialouge.setCanceledOnTouchOutside(false);
        nDialouge.show();

        int countChoice = lstUsers.getCount();

        for(int i=0;i<countChoice;i++){
            if(checkedItemPositions.get(i));
            {
                QBUser user = (QBUser)lstUsers.getItemAtPosition(i);
                QBChatDialog dialog = DialogUtils.buildPrivateDialog(user.getId());
                QBRestChatService.createChatDialog(dialog).performAsync(new QBEntityCallback<QBChatDialog>() {
                    @Override
                    public void onSuccess(QBChatDialog qbChatDialog, Bundle bundle) {
                        nDialouge.dismiss();
                        Toast.makeText(ListUserActivity.this, "Create Private Chat Dialouge Sessfully..", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Log.e("Error",""+e.getMessage());
                    }
                });
            }
        }
    }

    private void RetriveAllUsers() {
        QBUsers.getUsers(null).performAsync(new QBEntityCallback<ArrayList<QBUser>>() {
            @Override
            public void onSuccess(ArrayList<QBUser> qbUsers, Bundle bundle) {
                //create cache folder
                QBUsersHolder.getInstance().putUsers(qbUsers);

                ArrayList<QBUser> qbUserWithoutCrrent = new ArrayList<QBUser>();
                for(QBUser user : qbUsers){
                    if(!user.getLogin().equals(QBChatService.getInstance().getUser().getLogin()))
                        qbUserWithoutCrrent.add(user);
                }
                ListUsersAdapter adapter = new ListUsersAdapter(qbUserWithoutCrrent,getBaseContext());
                lstUsers.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("Error",""+e.getMessage());
            }
        });
    }
}