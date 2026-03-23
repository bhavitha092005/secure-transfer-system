package com.secure.transfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.transfer.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByReceiverEmailOrderByTimestampDesc(String receiverEmail);
}