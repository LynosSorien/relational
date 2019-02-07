package com.djorquab.relational.relational.jobs;

import com.djorquab.relational.relational.bo.PersonBO;
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

    @JmsListener(destination = "peopleJMS", containerFactory = "factoryJMS")
    public void createPersonFolder(PersonBO person) {
        log.info("New Person received into JMS, creating directory {}", person);
        if (person.getId() != null && person.getTryTimes() < 10) {
            if (!new File("application/person" + person.getId()).mkdirs()) {
                log.error("Direciton is not created!");
                jmsTemplate.convertAndSend("peopleJMS", person.increaseTryTimes());
            }
        }
    }

    @JmsListener(destination = "personRemovedJMS", containerFactory = "factoryJMS")
    public void removePersonFolder(PersonBO person) {
        log.info("Remove Person received into JMS, creating directory {}", person);
        if (person.getId() != null && person.getTryTimes() < 10) {
            if (!new File("application/person" + person.getId()).delete()) {
                log.error("Direciton is not removed!");
                jmsTemplate.convertAndSend("peopleJMS", person.increaseTryTimes());
            }
        }
    }
}
