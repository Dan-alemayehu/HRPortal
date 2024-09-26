package services;

import com.astontech.hr.Application;
import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.services.ElementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class ElementServiceTest {

    @Autowired
    private ElementService elementService;

    @Test
    public void elementServiceSaveTest(){
        Element element = new Element("ServiceTest");

        //save element, verify it has an ID after the save
        assertNull(element.getId());
        elementService.saveElement(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementService.getElementById(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementService.saveElement(fetchedElement);

        Element updatedElement = elementService.getElementById(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");
    }
}
