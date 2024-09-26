package com.astontech.hr.repositories;

import com.astontech.hr.domain.Element;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementRepository extends CrudRepository<Element, Integer> {

    //will auto generate the required code from the structure of the method name
    Element findByElementName(String elementName);

    List<Element> findAllByElementName(String elementName);

    List<Element> findAllByElementNameIgnoreCase(String elementName);

    List<Element> findByElementNameContainingIgnoreCase(String substring);

    List<Element> findByIdBetween(Integer startId, Integer endId);

    Long countByElementName(String elementName);

    Element findFirstByElementNameOrderByIdAsc(String elementName);

}
