package com.leandror.ze.partner.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;
import com.leandror.ze.partner.model.Partner;
import com.leandror.ze.partner.repositories.PartnerRepository;

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

  public Optional<PartnerPayload> get(UUID partnerId) {
    return repository.findById(partnerId).map(mapper::toPayload);
  }

  public PartnerPayload save(PartnerPayload payload) {
    return mapper.toPayload(repository.save(mapper.toEntity(payload)));
  }

  public Optional<PartnerPayload> searchNearest(Double longitude, Double latitude) {
    // TODO Auto-generated method stub
    return null;
  }

}
