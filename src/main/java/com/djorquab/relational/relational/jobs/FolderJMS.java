package com.djorquab.relational.relational.jobs;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.commons.JMSSystem;
import com.djorquab.relational.relational.managers.PropertyManager;
import com.djorquab.relational.relational.services.ErrorJMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class FolderJMS {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ErrorJMSService errorService;

    @Autowired
    private PropertyManager properties;

    @JmsListener(destination = "peopleJMS", containerFactory = "factoryJMS")
    public void createPersonFolder(PersonBO person) {
        log.info("New Person received into JMS, creating directory {}", person);
        if (person.getId() != null && person.getTryTimes() < 10) {
            if (!new File(properties.getFolderStorage()+"/person" + person.getId()).mkdirs()) {
                log.error("Direciton not created!");
                jmsTemplate.convertAndSend("peopleJMS", person.increaseTryTimes());
            }
        } else if (person.getId() != null) {
            errorService.createError(JMSOperation.CREATE_FOLDER, JMSSystem.PEOPLE, person.getId().toString());
        }
    }

    @JmsListener(destination = "personRemovedJMS", containerFactory = "factoryJMS")
    public void removePersonFolder(PersonBO person) {
        log.info("Remove Person received into JMS, creating directory {}", person);
        if (person.getId() != null && person.getTryTimes() < 10) {
            if (!new File(properties.getFolderStorage()+"/person" + person.getId()).delete()) {
                log.error("Direciton not removed!");
                jmsTemplate.convertAndSend("personRemovedJMS", person.increaseTryTimes());
            }
        } else if (person.getId() != null) {
            errorService.createError(JMSOperation.DELETE_FOLDER, JMSSystem.PEOPLE, person.getId().toString());
        }
    }
}
