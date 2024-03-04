package com.ms.email.Repository;

import com.ms.email.emails.EmailsModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface EmailsRepository extends JpaRepository<EmailsModels, UUID> {
}
