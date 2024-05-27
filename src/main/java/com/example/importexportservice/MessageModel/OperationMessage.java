package com.example.importexportservice.MessageModel;

import java.util.Date;

public class OperationMessage {
    private String MessageId;
    private String Message;
    private Date MassageDate ;

    private long OperationId;


    private String action;
    private String message;



    public OperationMessage(String messageId, String message, Date massageDate, long operationId, String action, String message1) {
        MessageId = messageId;
        Message = message;
        MassageDate = massageDate;
        OperationId = operationId;
        this.action = action;
        this.message = message1;
    }

    public OperationMessage() {
    }

    @Override
    public String toString() {
        return "OperationMessage{" +
                "MessageId='" + MessageId + '\'' +
                ", Message='" + Message + '\'' +
                ", MassageDate=" + MassageDate +
                ", OperationId='" + OperationId + '\'' +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessageId() {
        return MessageId;
    }

    public void setMessageId(String messageId) {
        MessageId = messageId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Date getMassageDate() {
        return MassageDate;
    }

    public void setMassageDate(Date massageDate) {
        MassageDate = massageDate;
    }

    public long getOperationId() {
        return OperationId;
    }

    public void setOperationId(long operationId) {
        OperationId = operationId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
