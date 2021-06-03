package com.leandror.ze.partner.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;
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
    var result = repository.findById(partnerId);
    return result.map(mapper::toPayload);
  }

  public PartnerPayload save(PartnerPayload payload) {
    var result = repository.save(mapper.toEntity(payload));
    return mapper.toPayload(result);
  }

}
