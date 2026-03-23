package com.secure.transfer.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderEmail;
    private String receiverEmail;

    private String fileName;

    @Lob
    private byte[] encryptedData;

    @Lob
    private String aesKey;

    private LocalDateTime timestamp;

    public FileEntity() {}

    public Long getId() { return id; }
    public String getSenderEmail() { return senderEmail; }
    public String getReceiverEmail() { return receiverEmail; }
    public String getFileName() { return fileName; }
    public byte[] getEncryptedData() { return encryptedData; }
    public String getAesKey() { return aesKey; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(Long id) { this.id = id; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
    public void setReceiverEmail(String receiverEmail) { this.receiverEmail = receiverEmail; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setEncryptedData(byte[] encryptedData) { this.encryptedData = encryptedData; }
    public void setAesKey(String aesKey) { this.aesKey = aesKey; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}