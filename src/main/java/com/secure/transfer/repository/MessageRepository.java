package com.secure.transfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.transfer.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByReceiverEmailOrderByTimestampDesc(String receiverEmail);
}