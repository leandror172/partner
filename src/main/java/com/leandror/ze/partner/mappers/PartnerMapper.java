package com.leandror.ze.partner.mappers;

import org.mapstruct.Mapper;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.model.Partner;

@Mapper
public interface PartnerMapper {

  PartnerPayload toPayload(Partner partner);
  
  Partner toEntity(PartnerPayload payload);

}
