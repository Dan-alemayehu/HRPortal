package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class ElementRepositoryTest {

    @Autowired
    private ElementRepository elementRepository;

    @Test
    public void testSaveElement(){
        Element element = new Element();
        element.setElementName("Phone");

        //save element, verify it has an ID after the save
        assertNull(element.getId());
        elementRepository.save(element);
        assertNotNull(element.getId());

        //fetch
        Element fetchedElement = elementRepository.findOne(element.getId());
        assertNotNull(fetchedElement);
        assertEquals(element.getId(), fetchedElement.getId());

        //update
        fetchedElement.setElementName("Email");
        elementRepository.save(fetchedElement);

        Element updatedElement = elementRepository.findOne(fetchedElement.getId());
        assertEquals(updatedElement.getElementName(), "Email");



    }

    @Test
    public void testSaveElementList(){
        List<Element> elementList = new ArrayList<>();

        elementList.add(new Element("Phone"));
        elementList.add(new Element("Email"));
        elementList.add(new Element("Laptop"));
        elementList.add(new Element("PayRate"));

        elementRepository.save(elementList);

        Iterable<Element> fetchedElementList = elementRepository.findAll();

        int count = 0;
        for(Element element : fetchedElementList){
            count = count + 1;
            System.out.println("count " + count + " : " + element.getElementName());
        }
//        assertEquals(5, count);
    }

    @Test
    public void testFindByName(){
        Element element = new Element("FindTestSingle");
        elementRepository.save(element);

        Element foundByNameElement = elementRepository.findByElementName("FindTestSingle");

        assertEquals(element.getId(), foundByNameElement.getId());
    }

    @Test
    public void testFindAllByName(){
        Element element1 = new Element("FindTest");
        elementRepository.save(element1);
        Element element2 = new Element("FindTest");
        elementRepository.save(element2);
        Element element3 = new Element("FindTest");
        elementRepository.save(element3);

        List<Element> foundAllByNameElement = elementRepository.findAllByElementName("FindTest");

        assertEquals(3, foundAllByNameElement.size());
    }

    @Test
    public void findAllByElementNameIgnoreCase(){
        Element element1 = new Element("FindTest");
        elementRepository.save(element1);
        Element element2 = new Element("findTest");
        elementRepository.save(element2);
        Element element3 = new Element("Findtest");
        elementRepository.save(element3);

        List<Element> foundAllByNameElement = elementRepository.findAllByElementNameIgnoreCase("FindTest");
        assertEquals(3, foundAllByNameElement.size());
    }

    @Test
    public void findByElementNameContainingIgnoreCase(){
        Element element1 = new Element("FindTest");
        elementRepository.save(element1);
        Element element2 = new Element("findTesting");
        elementRepository.save(element2);
        Element element3 = new Element("findTesters");
        elementRepository.save(element3);

        List<Element> findElementContainingIgnoreCase = elementRepository.findByElementNameContainingIgnoreCase("Test");
        assertEquals(3, findElementContainingIgnoreCase.size());
    }

    @Test
    public void findIdBetween(){
        Element element1 = new Element("FindTest");
        elementRepository.save(element1);
        Element element2 = new Element("findTest");
        elementRepository.save(element2);
        Element element3 = new Element("findTest");
        elementRepository.save(element3);
        Element element4 = new Element("findTest");
        elementRepository.save(element4);
        Element element5 = new Element("findTest");
        elementRepository.save(element5);

        List<Element> findIdBetweens = elementRepository.findByIdBetween(2,4);
        assertEquals(3, findIdBetweens.size());

        List<Integer> ids = new ArrayList<>();
        for (Element element : findIdBetweens) {
            ids.add(element.getId());
        }

        assertTrue(ids.containsAll(Arrays.asList(2, 3, 4)));
    }

    @Test
    public void countElementName(){
        Element element1 = new Element("Phil");
        elementRepository.save(element1);
        Element element2 = new Element("Bobby");
        elementRepository.save(element2);
        Element element3 = new Element("Greg");
        elementRepository.save(element3);
        Element element4 = new Element("Larry");
        elementRepository.save(element4);
        Element element5 = new Element("Greg");
        elementRepository.save(element5);

        Long countElementNames = elementRepository.countByElementName("Greg");
        assertEquals(Long.valueOf(2), countElementNames);
    }

    @Test
    public void findFirstByElementNameOrderByIdAsc(){
        Element element1 = new Element("Phil");
        elementRepository.save(element1);
        Element element2 = new Element("Bobby");
        elementRepository.save(element2);
        Element element3 = new Element("Greg");
        elementRepository.save(element3);
        Element element4 = new Element("Larry");
        elementRepository.save(element4);
        Element element5 = new Element("Greg");
        elementRepository.save(element5);

        Element findFirstBy = elementRepository.findFirstByElementNameOrderByIdAsc("Greg");
        assertEquals(element3.getId(), findFirstBy.getId());
    }
}
