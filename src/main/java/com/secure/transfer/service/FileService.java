package com.secure.transfer.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.secure.transfer.entity.FileEntity;
import com.secure.transfer.repository.FileRepository;
import com.secure.transfer.util.AESUtil;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    
    public void uploadFile(String sender, String receiver, MultipartFile file) {
        try {

            //  Null / Empty Check
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File cannot be empty");
            }

            // File Size Limit (5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                throw new RuntimeException("File size exceeds 5MB limit");
            }

            //  File Type Validation
            String fileName = file.getOriginalFilename();

            if (fileName == null || 
                !(fileName.endsWith(".pdf") || 
                  fileName.endsWith(".jpg") || 
                  fileName.endsWith(".png"))) {

                throw new RuntimeException("Only PDF, JPG, PNG files are allowed");
            }

            // Encryption
            SecretKey key = AESUtil.generateKey();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(file.getBytes());

            //  Save to DB
            FileEntity entity = new FileEntity();
            entity.setSenderEmail(sender);
            entity.setReceiverEmail(receiver);
            entity.setFileName(fileName);
            entity.setEncryptedData(encryptedBytes);
            entity.setAesKey(AESUtil.encodeKey(key));
            entity.setTimestamp(LocalDateTime.now());

            fileRepository.save(entity);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Download 
    public byte[] downloadFile(Long id, String requester) {
        try {
            FileEntity file = fileRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("File not found"));

            if (!file.getReceiverEmail().equals(requester)) {
                throw new RuntimeException("Unauthorized");
            }

            SecretKey key = AESUtil.decodeKey(file.getAesKey());

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            return cipher.doFinal(file.getEncryptedData());

        } catch (Exception e) {
            throw new RuntimeException("File decryption failed");
        }
    }

    public List<FileEntity> getFiles(String receiver) {
        return fileRepository.findByReceiverEmailOrderByTimestampDesc(receiver);
    }
}