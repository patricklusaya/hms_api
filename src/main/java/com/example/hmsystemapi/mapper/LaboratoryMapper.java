package com.example.hmsystemapi.mapper;

import com.example.hmsystemapi.dto.requests.LabRequest;
import com.example.hmsystemapi.model.Lab;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LaboratoryMapper {
    LabRequest mapLabToDto(Lab lab);
    Lab mapDtoToLab(LabRequest labRequest);
}
