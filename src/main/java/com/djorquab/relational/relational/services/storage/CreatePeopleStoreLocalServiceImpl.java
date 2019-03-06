package com.djorquab.relational.relational.services.storage;

import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.commons.JMSOperation;
import com.djorquab.relational.relational.services.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("createPeopleStoreService")
@Slf4j
public class CreatePeopleStoreLocalServiceImpl extends AbstractPeopleStorageService {

    public CreatePeopleStoreLocalServiceImpl(@Autowired JmsTemplate jmsTemplate, @Autowired PeopleService service) {
        super(jmsTemplate, service);
    }

    @Override
    public JMSOperation getAcceptedOperation() {
        return JMSOperation.CREATE_FOLDER;
    }

    @Override
    protected void execute(PersonBO person) {
        log.info("Sending to peopleJMS {}", person);
        getJmsTempalte().convertAndSend("peopleJMS", person.initToJms());
    }
}
