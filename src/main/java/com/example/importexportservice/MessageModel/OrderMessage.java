package com.example.importexportservice.MessageModel;


import java.util.Date;
public class OrderMessage {
    private String MessageId;
    private String Message;
    private Date MassageDate ;

    private Long OrderId;

    private String action;
    private String message;

    public OrderMessage(String messageId, String message, Date massageDate, Long orderId, String action, String message1) {
        MessageId = messageId;
        Message = message;
        MassageDate = massageDate;
        OrderId = orderId;
        this.action = action;
        this.message = message1;
    }

    public OrderMessage() {
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "MessageId='" + MessageId + '\'' +
                ", Message='" + Message + '\'' +
                ", MassageDate=" + MassageDate +
                ", OrderId=" + OrderId +
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

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
