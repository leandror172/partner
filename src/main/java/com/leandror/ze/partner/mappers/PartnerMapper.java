package com.leandror.ze.partner.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.model.Partner;

@Mapper
public interface PartnerMapper {

  PartnerPayload map(Partner partner);

}
