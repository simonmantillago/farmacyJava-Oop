package com.farmacysystem.identificationtypes.domain.service;

import java.util.List;
import java.util.Optional;

import com.farmacysystem.identificationtypes.domain.entity.IdentificationType;

public interface IdentificationTypeService {
    void createIdentificationType(IdentificationType customer);
    IdentificationType deleteIdentificationType(String description);
    List<IdentificationType> findAllIdentificationTypes();
}
