package com.secure.transfer.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.secure.transfer.dto.MessageRequest;
import com.secure.transfer.entity.Message;
import com.secure.transfer.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody MessageRequest request, Principal principal) {
        String sender = principal.getName();
        messageService.sendMessage(sender, request.getReceiverEmail(), request.getContent());
        return "Message sent securely";
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable Long id, Principal principal) {
        return messageService.getMessage(id, principal.getName());
    }

    @GetMapping("/inbox")
    public List<Message> getInbox(Principal principal) {

        List<Message> messages = messageService.getInbox(principal.getName());

        //Hide
        messages.forEach(msg -> {
            msg.setAesKey(null);
            msg.setEncryptedContent(null);
        });

        return messages;
    }
}