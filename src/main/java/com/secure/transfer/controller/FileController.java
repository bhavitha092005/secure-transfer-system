package com.secure.transfer.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.secure.transfer.entity.FileEntity;
import com.secure.transfer.service.FileService;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("receiverEmail") String receiver,
                             Principal principal) {

        fileService.uploadFile(principal.getName(), receiver, file);
        return "File uploaded securely";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id,
                                               Principal principal) {

        byte[] data = fileService.downloadFile(id, principal.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=file")
                .body(data);
    }

    @GetMapping("/inbox")
    public List<FileEntity> inbox(Principal principal) {

        List<FileEntity> files = fileService.getFiles(principal.getName());

      //Hide
        files.forEach(f -> {
            f.setEncryptedData(null);
            f.setAesKey(null);
        });

        return files;
    }
}