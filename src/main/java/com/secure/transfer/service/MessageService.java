package com.secure.transfer.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.transfer.entity.Message;
import com.secure.transfer.repository.MessageRepository;
import com.secure.transfer.util.AESUtil;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(String senderEmail, String receiverEmail, String content) {
        try {
            SecretKey key = AESUtil.generateKey();

            String encrypted = AESUtil.encrypt(content, key);
            String encodedKey = AESUtil.encodeKey(key);

            Message msg = new Message();
            msg.setSenderEmail(senderEmail);
            msg.setReceiverEmail(receiverEmail);
            msg.setEncryptedContent(encrypted);
            msg.setAesKey(encodedKey);
            msg.setTimestamp(LocalDateTime.now());

            messageRepository.save(msg);

        } catch (Exception e) {
            throw new RuntimeException("Encryption failed");
        }
    }

    public String getMessage(Long id, String requesterEmail) {
        try {
            Message msg = messageRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Message not found"));

            if (!msg.getReceiverEmail().equals(requesterEmail)) {
                throw new RuntimeException("Unauthorized access");
            }

            SecretKey key = AESUtil.decodeKey(msg.getAesKey());

            return AESUtil.decrypt(msg.getEncryptedContent(), key);

        } catch (Exception e) {
            throw new RuntimeException("Decryption failed");
        }
    }

    public List<Message> getInbox(String receiverEmail) {
        return messageRepository.findByReceiverEmailOrderByTimestampDesc(receiverEmail);
    }
}