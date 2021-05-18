package com.leandror.ze.partner.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandror.ze.partner.PartnerRepository;
import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;

@Service
public class PartnerService {
  
  private PartnerRepository repository;
  private PartnerMapper mapper;

  @Autowired
  public PartnerService(PartnerRepository repository, PartnerMapper mapper) {
    super();
    this.repository = repository;
    this.mapper = mapper;
  }

  public PartnerPayload get(UUID partnerId) {
    return repository.findById(partnerId).map(mapper::toPayload).orElse(null);
  }

  public void save(PartnerPayload payload) {
    repository.save(mapper.toEntity(payload));
  }

}
