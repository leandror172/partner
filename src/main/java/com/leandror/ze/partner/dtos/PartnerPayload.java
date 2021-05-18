package com.leandror.ze.partner.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@Data
@AllArgsConstructor
public class PartnerPayload {

  private UUID id;
  private String tradingName;
  private String ownerName;
  private @NonNull String document;

}
