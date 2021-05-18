package com.leandror.ze.partner.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class PartnerPayload {

  private UUID id;
  private String tradingName;
  private String ownerName;
  private String document;

}
