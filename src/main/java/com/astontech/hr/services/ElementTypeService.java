package com.astontech.hr.services;

import com.astontech.hr.domain.ElementType;

import java.util.List;

public interface ElementTypeService {

    Iterable<ElementType> listAllElementTypes();

    ElementType getElementTypeById(Integer id);

    ElementType saveElementType(ElementType elementType);

    Iterable<ElementType> saveElementTypeList(Iterable<ElementType> elementTypeIterable);

    void deleteElementTypeById(Integer id);

    //  custom methods
    List<ElementType> findByElementTypeName(String elementTypeName);

    List<ElementType> findByElementTypeNameContaining(String elementTypeNamePart);


}