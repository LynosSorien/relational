package com.djorquab.relational.relational.bo;

import com.djorquab.relational.relational.commons.ActionType;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.JMSSystem;
import com.djorquab.relational.relational.processor.ActionDefinition;
import com.djorquab.relational.relational.processor.ViewColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorJMSBO implements Serializable {
    @ViewColumn(header = "ID", actions = {
            @ActionDefinition(type = ActionType.DO, pathVariable = true, path = "/backoffice/admin/jms/error/doAgain", method = "GET", newLocation = true),
            @ActionDefinition(type = ActionType.DELETE, path = "/backoffice/admin/jms/error", requestParam = "errorId", method = "DELETE")
    })
    private Long id;
    @ViewColumn(header = "Operation", position = 0)
    private JMSOperation operation;
    @ViewColumn(header = "System", position = 1)
    private JMSSystem system;
    @ViewColumn(header = "Payload", position = 2)
    private String payload;
    @ViewColumn(header = "Created on", position = 3)
    private Date createdOn;
    private Date lastModified;
}
