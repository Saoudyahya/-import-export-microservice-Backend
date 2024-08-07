package com.example.importexportservice.MessageModel;


import java.util.Date;

public class SupplierMessage {


    private String MessageId;
    private String Message;
    private Date MassageDate ;

    private Long supplierId;
    private String supplierName;
    private String action;
    private String message;


    public SupplierMessage(String messageId, String message, Date massageDate, Long supplierId, String supplierName, String action, String message1) {
        MessageId = messageId;
        Message = message;
        MassageDate = massageDate;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.action = action;
        this.message = message1;
    }

    public SupplierMessage() {
    }


    @Override
    public String toString() {
        return "SupplierMessage{" +
                "MessageId='" + MessageId + '\'' +
                ", Message='" + Message + '\'' +
                ", MassageDate=" + MassageDate +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
}
