package com.secure.transfer.dto;

public class MessageRequest {

    private String receiverEmail;
    private String content;

    public MessageRequest() {}

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getContent() {
        return content;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public void setContent(String content) {
        this.content = content;
    }
}