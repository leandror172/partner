package com.leandror.ze.partner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.model.Partner;

@Mapper(componentModel = "spring")
public interface PartnerMapper {

  PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

  @Mapping(target = "address", ignore = true)
  @Mapping(target = "addressPoint", source = "address.location")
  PartnerPayload toPayload(Partner partner);

  @Mapping(target = "address.location", source = "addressPoint")
  Partner toEntity(PartnerPayload payload);

}
