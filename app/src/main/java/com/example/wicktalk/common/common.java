package com.example.wicktalk.common;

import com.example.wicktalk.Holder.QBUsersHolder;
import com.quickblox.users.model.QBUser;

import java.util.List;

public class common {

    public static String createChatDialougeName(List<Integer> qbUsers)
    {
        List<QBUser> qbUsers1 = QBUsersHolder.getInstance().getUsersByIds(qbUsers);
        StringBuilder name = new StringBuilder();
        for (QBUser user:qbUsers1)
            name.append(user.getFullName()).append("");
        if(name.length()>30)
            name = name.replace(30,name.length()-1,"....");
            return name.toString();
    }
}
