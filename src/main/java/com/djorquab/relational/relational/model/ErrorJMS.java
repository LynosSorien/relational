package com.djorquab.relational.relational.model;

import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.JMSSystem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "JMS_ERRORS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorJMS implements AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private JMSOperation operation;

    @Column(name = "system")
    @Enumerated(EnumType.STRING)
    private JMSSystem system;

    @Column(name = "payload")
    private String payload;

    @Column(name = "last_modified")
    private Date lastModified;

    @Column(name = "created_on")
    private Date createdOn;
}
