package com.example.wicktalk.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.wicktalk.R;
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
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;


public class all extends Fragment {

   FloatingActionButton floatingActionButton;
   ListView chatDialouge;
    public all() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        createChateSession();
        loadChatDialouge();
        return view;
    }

    private void loadChatDialouge() {
        QBRequestBuilder qbRequestBuilder = new QBRequestBuilder();

        QBRestChatService.getChatDialogs(null, (QBRequestGetBuilder) qbRequestBuilder).performAsync(new QBEntityCallback<ArrayList<QBChatDialog>>() {
            @Override
            public void onSuccess(ArrayList<QBChatDialog> qbChatDialogs, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {

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

        QBUser qbUser = new QBUser(user,password);
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