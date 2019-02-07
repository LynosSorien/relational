package com.djorquab.relational.relational.rest.backoffice;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.bo.PersonBO;
import com.djorquab.relational.relational.managers.PeopleManager;
import com.djorquab.relational.relational.managers.PropertyManager;
import com.djorquab.relational.relational.repositories.ErrorJMSRepository;
import com.djorquab.relational.relational.rest.AbstractRestTest;
import com.djorquab.relational.relational.rest.TestParam;
import com.djorquab.relational.relational.services.PeopleService;
import com.djorquab.relational.relational.services.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Slf4j
public class AdminTest extends AbstractRestTest {
    private static final long WAIT_TIME = 3000;
    @Autowired
    private ErrorJMSRepository errorJMSRepository;

    @Autowired
    private PeopleManager peopleManager;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PropertyManager properties;

    @Autowired
    private StoreService storeService;

    @Before
    public void cleanEvents() {
        peopleManager.deleteAll();
        sleep();
        errorJMSRepository.deleteAll();

        File appFolder = new File(properties.getFolderStorage());
        for (String entry : appFolder.list()) {
            new File(appFolder.getPath(), entry).delete();
        }
        sleep();
        appFolder.delete();
    }

    private void sleep() {
        try {
            Thread.sleep(WAIT_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser(username = "testuser")
    public void simpleRestTest() {
        get("/backoffice/admin/jms");
        get("/backoffice/admin/jms/paging");
    }

    @Test
    public void createUserAndRemove() {
        Assert.assertEquals(0L, errorJMSRepository.count());

        PersonBO captainAmerica = peopleManager.create(PersonBO.builder().name("Steve").surname("Rogers").build());
        sleep();
        Assert.assertEquals(0L, errorJMSRepository.count());
        peopleManager.delete(captainAmerica);
        sleep();
        Assert.assertEquals(0L, errorJMSRepository.count());
    }

    @Test
    public void createTwoTimes() {
        Assert.assertEquals(0L, errorJMSRepository.count());

        PersonBO captainAmerica = peopleManager.create(PersonBO.builder().name("Steve").surname("Rogers").build());
        sleep();
        Assert.assertEquals(0L, errorJMSRepository.count());

        storeService.createStoreForPerson(captainAmerica);
        sleep();
        Assert.assertEquals(1L, errorJMSRepository.count());

        peopleManager.delete(captainAmerica.getId());
        sleep();
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testingTryAgain() {
        Assert.assertEquals(0L, errorJMSRepository.count());

        PersonBO captainAmerica = peopleManager.create(PersonBO.builder().name("Steve").surname("Rogers").build());
        sleep();
        Assert.assertEquals(0L, errorJMSRepository.count());

        storeService.createStoreForPerson(captainAmerica);
        sleep();
        Assert.assertEquals(1L, errorJMSRepository.count());

        storeService.deleteStoreForPerson(captainAmerica);
        sleep();
        Assert.assertEquals(1L, errorJMSRepository.count());
        get("/backoffice/admin/jms/error/doAgain/"+errorJMSRepository.findAll().iterator().next().getId());
        sleep();
        Assert.assertEquals(0L, errorJMSRepository.count());
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testingDeleteErrorJMS() {
        Assert.assertEquals(0L, errorJMSRepository.count());

        PersonBO captainAmerica = peopleManager.create(PersonBO.builder().name("Steve").surname("Rogers").build());
        sleep();
        Assert.assertEquals(0L, errorJMSRepository.count());

        storeService.createStoreForPerson(captainAmerica);
        sleep();
        Assert.assertEquals(1L, errorJMSRepository.count());

        delete("/backoffice/admin/jms/error", new TestParam<>("errorId", errorJMSRepository.findAll().iterator().next().getId()));
    }
}
