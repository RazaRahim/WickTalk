package com.example.wicktalk.Holder;

import android.widget.ListView;

import com.quickblox.chat.model.QBChatDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QBChatDialogeHolder {
private  static QBChatDialogeHolder instance;
private HashMap<String, QBChatDialog> qbChatDialogHashMap;

public static synchronized QBChatDialogeHolder getInstance(){
    QBChatDialogeHolder qbChatDialogeHolder;
    synchronized (QBChatDialogeHolder.class)
    {
        if(instance ==null)
            instance = new QBChatDialogeHolder();
    }
    qbChatDialogeHolder = instance;
    return qbChatDialogeHolder;
}

    public QBChatDialogeHolder() { this.qbChatDialogHashMap = new HashMap<>(); }

    public void putDialogs(List<QBChatDialog> dialogs)
    {
        for (QBChatDialog qbChatDialog:dialogs)
            putDialoge(qbChatDialog);
    }

    public void putDialoge(QBChatDialog qbChatDialog) {
    this.qbChatDialogHashMap.put(qbChatDialog.getDialogId(),qbChatDialog);
    }

    public QBChatDialog getChatDialogById(String dialogId)
    {
        return (QBChatDialog)qbChatDialogHashMap.get(dialogId);
    }

    public List<QBChatDialog> getChatDialogByIds(List<String> dialogIds)
    {
        List<QBChatDialog> chatDialogs = new ArrayList<>();
        for (String id:dialogIds)
        {
            QBChatDialog chatDialog = getChatDialogById(id);
            if(chatDialog !=null)
                chatDialogs.add(chatDialog);
        }
        return chatDialogs;
    }

    public ArrayList<QBChatDialog> getAllChatDialogs()
    {
        ArrayList<QBChatDialog> qbChat = new ArrayList<>();
        for (String key:qbChatDialogHashMap.keySet())
            qbChat.add(qbChatDialogHashMap.get(key));
        return qbChat;
    }
}
