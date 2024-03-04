package com.ms.email.emails;

import com.ms.email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_EMAILS")
public class EmailsModels implements Serializable {

    public static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;

    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "text")
    private String text;

    private LocalDateTime sendDateTime;
    private StatusEmail statusEmail;
}
