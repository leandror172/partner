package com.leandror.ze.partner.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leandror.ze.partner.dtos.PartnerPayload;

@RestController()
@RequestMapping("/api/v1/partner")
public class PartnerController {

  @GetMapping(path = "/{id}", produces = "application/json")
  public PartnerPayload getPartner(@PathVariable  UUID id) {
    
    return new PartnerPayload();
  }
}
