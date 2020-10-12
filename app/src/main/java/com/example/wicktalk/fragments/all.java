package com.example.wicktalk.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wicktalk.Adapters.chatDialougeAdapter;
import com.example.wicktalk.Holder.QBUsersHolder;
import com.example.wicktalk.ListUserActivity;
import com.example.wicktalk.R;
import com.example.wicktalk.chatMessageActivity;
import com.example.wicktalk.common.common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.BaseService;
import com.quickblox.auth.session.QBSession;
import com.quickblox.chat.QBChat;
import com.quickblox.chat.QBChatService;
import com.quickblox.chat.QBRestChatService;
import com.quickblox.chat.model.QBChatDialog;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.BaseServiceException;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.core.model.QBEntityLimited;
import com.quickblox.core.request.QBLimitedRequestBuilder;
import com.quickblox.core.request.QBRequestBuilder;
import com.quickblox.core.request.QBRequestGetBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;


public class all extends Fragment {

   FloatingActionButton floatingActionButton;
   ListView chatDialouge;
    public all() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        loadChatDialouge();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        createChateSession();

        chatDialouge = (ListView)view.findViewById(R.id.chatdialouge);
        chatDialouge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 QBChatDialog qbChatDialog = (QBChatDialog)chatDialouge.getAdapter().getItem(i);
                 Intent intent = new Intent(getContext(), chatMessageActivity.class);
                 intent.putExtra(common.DIALOG_EXTRA,qbChatDialog);
                 startActivity(intent);
            }
        });




        loadChatDialouge();

        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.floatbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListUserActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void loadChatDialouge() {
        QBRequestGetBuilder qbRequestBuilder = new QBRequestGetBuilder();
        qbRequestBuilder.setLimit(100);
        QBRestChatService.getChatDialogs(null, (QBRequestGetBuilder) qbRequestBuilder).performAsync(new QBEntityCallback<ArrayList<QBChatDialog>>() {
            @Override
            public void onSuccess(ArrayList<QBChatDialog> qbChatDialogs, Bundle bundle) {
                chatDialougeAdapter adapter=new chatDialougeAdapter(getContext(),qbChatDialogs);
                chatDialouge.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(QBResponseException e) {
Log.e("Error",""+e.getMessage());
            }
        });
    }

    private void createChateSession() {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String user,password;
        user =getActivity().getIntent().getExtras().getString("user");
        password =getActivity().getIntent().getExtras().getString("password");

        //Load all user and save to cache
        QBUsers.getUsers(null).performAsync(new QBEntityCallback<ArrayList<QBUser>>() {
            @Override
            public void onSuccess(ArrayList<QBUser> qbUsers, Bundle bundle) {
                QBUsersHolder.getInstance().putUsers(qbUsers);
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });

       final QBUser qbUser = new QBUser(user,password);
        QBAuth.createSession(qbUser).performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {
                qbUser.setId(qbSession.getUserId());
                try {
                    qbUser.setPassword(BaseService.getBaseService().getToken());
                } catch (BaseServiceException e) {
                    e.printStackTrace();
                }
                QBChatService.getInstance().login(qbUser, new QBEntityCallback() {
                    @Override
                    public void onSuccess(Object o, Bundle bundle) {
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Log.e("Error", ""+ e.getMessage());
                    }
                });
            }

            @Override
            public void onError(QBResponseException e) {

            }
        });
    }
}