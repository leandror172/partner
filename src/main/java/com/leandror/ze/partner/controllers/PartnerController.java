package com.leandror.ze.partner.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.exceptions.ResourceNotFoundException;
import com.leandror.ze.partner.services.PartnerService;

@RestController()
@RequestMapping("/api/v1/partner")
public class PartnerController {
  
  private PartnerService service;

  @Autowired
  public PartnerController(PartnerService service) {
    this.service = service;
  }

  @GetMapping(path = "/{id}", produces = "application/json")
  public PartnerPayload getPartner(@PathVariable  UUID id) {
    return service.get(id).orElseThrow(() -> new ResourceNotFoundException());
  }

  @PostMapping(produces = "application/json")
  public PartnerPayload savePartner(@RequestBody PartnerPayload partnerPayload) {
    return service.save(partnerPayload);
  }
}
