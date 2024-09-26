package com.astontech.hr.repositories;

import com.astontech.hr.domain.ElementType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElementTypeRepository extends CrudRepository<ElementType, Integer> {

    List<ElementType> findAllByElementTypeName(String elementTypeName);
    List<ElementType> findByElementTypeName(String elementTypeName);
    List<ElementType> findByElementTypeNameContaining(String elementTypeNamePart);
}
