package com.example.wicktalk.Holder;

import android.util.SparseArray;

import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

public class QBUsersHolder {

    private static QBUsersHolder instance;

    private SparseArray<QBUser> qbUsersparseArray;
    public static synchronized QBUsersHolder getInstance(){
        if(instance == null)
            instance = new QBUsersHolder();
        return instance;
    }

    private QBUsersHolder(){
        qbUsersparseArray  = new SparseArray<>();
    }

    public void putUsers(List<QBUser> users)
    {
        for(QBUser user:users)
            putUser(user);
    }

    private void putUser(QBUser user) {
             qbUsersparseArray.put(user.getId(),user);
    }
    public QBUser getUserById(int id){
        return qbUsersparseArray.get(id);
    }
    public List<QBUser> getUsersByIds(List<Integer> ids)
    {
        List<QBUser> qbUser = new ArrayList<>();
        for (Integer id:ids){
            QBUser user = getUserById(id);
            if (user != null)
                qbUser.add(user);
        }
        return qbUser;
    }
}
