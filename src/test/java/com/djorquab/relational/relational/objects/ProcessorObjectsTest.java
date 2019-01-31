package com.djorquab.relational.relational.objects;

import com.djorquab.relational.relational.RelationalApplication;
import com.djorquab.relational.relational.commons.ColumnDefinition;
import com.djorquab.relational.relational.commons.FormFieldDefinition;
import edu.emory.mathcs.backport.java.util.Collections;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RelationalApplication.class)
public class ProcessorObjectsTest {

    @Test
    public void formFieldDefinitionTest() {
        List<FormFieldDefinition> list = new LinkedList<>();
        list.add(null);
        list.add(FormFieldDefinition.builder().position(0).build());

        Collections.sort(list);
        Assert.assertNull(list.get(0));
    }

    @Test
    public void columnDefinitionTest() {
        List<ColumnDefinition> list = new LinkedList<>();
        list.add(null);
        list.add(ColumnDefinition.builder().position(0).build());

        Collections.sort(list);
        Assert.assertNull(list.get(0));
    }
}
