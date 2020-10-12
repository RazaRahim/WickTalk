package com.example.wicktalk.Holder;

import com.quickblox.chat.model.QBChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QBChatMessageHolder {
    private static QBChatMessageHolder intance;
    private HashMap<String, ArrayList<QBChatMessage>> qbChatMessageArray;

    public static synchronized QBChatMessageHolder getInstance(){
        QBChatMessageHolder qbChatMessageHolder;
        synchronized (QBChatMessageHolder.class)
        {
            if(intance == null)
                intance = new QBChatMessageHolder();
            qbChatMessageHolder = intance;
        }
        return qbChatMessageHolder;

    }

   private QBChatMessageHolder()
   {
       this.qbChatMessageArray = new HashMap<>();
   }
   public void putMessages(String dialogid,ArrayList<QBChatMessage> qbChatMessages)
   {
       this.qbChatMessageArray.put(dialogid,qbChatMessages);
   }

   public void putMessage(String dialogid,QBChatMessage qbChatMessage)
   {
       List<QBChatMessage> lstResult = (List)this.qbChatMessageArray.get(dialogid);
       lstResult.add(qbChatMessage);
       ArrayList<QBChatMessage> lastAdded = new ArrayList(lstResult.size());
       lastAdded.addAll(lstResult);
       putMessages(dialogid,lastAdded);
   }

   public ArrayList<QBChatMessage> getChatMessageByDialogId(String dialogId)
   {
       return (ArrayList<QBChatMessage>)this.qbChatMessageArray.get(dialogId);
   }
}
