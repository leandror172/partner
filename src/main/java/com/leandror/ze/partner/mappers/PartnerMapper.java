package com.leandror.ze.partner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.model.Partner;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
  
  PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);
 
  PartnerPayload toPayload(Partner partner);
  
  Partner toEntity(PartnerPayload payload);

}
