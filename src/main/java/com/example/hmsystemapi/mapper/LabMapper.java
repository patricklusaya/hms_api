package com.example.hmsystemapi.mapper;

import com.example.hmsystemapi.dto.requests.LabPost;
import com.example.hmsystemapi.dto.requests.LabRequest;
import com.example.hmsystemapi.dto.requests.PhamDto;
import com.example.hmsystemapi.dto.requests.PhamPost;
import com.example.hmsystemapi.model.Lab;
import com.example.hmsystemapi.model.Pham;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LabMapper {
    LabRequest mapLabToDto(Lab lab);
    Lab mapDtoToLab(LabRequest labRequest);

    LabPost mapLabToLabPostDto(Lab lab);
    Lab mapLabPostDtoToLab( LabPost labPost);

    Pham mapPhamDtoToPham(PhamDto phamDto);
    Pham mapPhamPostToPham(PhamPost phamPost);
}
