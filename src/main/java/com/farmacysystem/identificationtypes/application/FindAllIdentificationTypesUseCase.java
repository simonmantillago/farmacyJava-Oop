package com.farmacysystem.identificationtypes.application;

import java.util.List;

import com.farmacysystem.identificationtypes.domain.entity.IdentificationType;
import com.farmacysystem.identificationtypes.domain.service.IdentificationTypeService;

public class FindAllIdentificationTypesUseCase {
    private final IdentificationTypeService identificationTypeService;

    public FindAllIdentificationTypesUseCase(IdentificationTypeService identificationTypeService) {
        this.identificationTypeService = identificationTypeService;
    }

    public List<IdentificationType> execute() {
        return identificationTypeService.findAllIdentificationTypes();
    }
}
