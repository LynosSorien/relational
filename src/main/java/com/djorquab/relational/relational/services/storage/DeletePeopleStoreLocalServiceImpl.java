package com.djorquab.relational.relational.services.storage;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.services.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("deletePeopleStoreService")
@Slf4j
public class DeletePeopleStoreLocalServiceImpl extends AbstractPeopleStorageService {

    public DeletePeopleStoreLocalServiceImpl(@Autowired JmsTemplate jmsTemplate, @Autowired PeopleService service) {
        super(jmsTemplate, service);
    }

    @Override
    public JMSOperation getAcceptedOperation() {
        return JMSOperation.DELETE_FOLDER;
    }

    @Override
    protected void execute(PersonBO person) {
        log.info("Sending to personRemovedJMS {}", person);
        getJmsTempalte().convertAndSend("personRemovedJMS", person.initToJms());
    }
}
