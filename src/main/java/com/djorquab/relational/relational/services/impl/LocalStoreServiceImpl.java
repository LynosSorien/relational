package com.djorquab.relational.relational.services.impl;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.services.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
public class LocalStoreServiceImpl implements StoreService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void createStoreForPerson(PersonBO person) {
        //if (!new File("application/people"+person.getId()).isDirectory()) {
            log.info("Sending to peopleJMS");
            jmsTemplate.convertAndSend("peopleJMS", person.initToJms());
        //}
    }

    @Override
    public void deleteStoreForPerson(PersonBO person) {
        //if (new File("application/people"+person.getId()).isDirectory()) {
            log.info("Sending to personRemovedJMS");
            jmsTemplate.convertAndSend("personRemovedJMS", person.initToJms());
        //}
    }
}
