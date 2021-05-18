package com.leandror.ze.partner.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandror.ze.partner.PartnerRepository;
import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;

@Service
public class PartnerService {
  
  @Autowired
  private PartnerRepository repository;
  @Autowired
  private PartnerMapper mapper;

  public PartnerPayload get(UUID partnerId) {
    return repository.findById(partnerId).map(mapper::map).orElse(null);
  }

}
