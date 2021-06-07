package com.leandror.ze.partner.dtos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerPayload {

  private UUID id;
  private String tradingName;
  private String ownerName;
  private @NonNull String document;
  private PointPayload address;

  public PartnerPayload(UUID id, String tradingName, String ownerName, @NonNull String document,
      List<Double> coordinates) {
    this.id = id;
    this.tradingName = tradingName;
    this.ownerName = ownerName;
    this.document = document;
    this.address = new PointPayload(coordinates);
  }

  public PartnerPayload(UUID id, String tradingName, String ownerName, String document, Point address) {
    this.id = id;
    this.tradingName = tradingName;
    this.ownerName = ownerName;
    this.document = document;
    this.address = new PointPayload(address);
  }

  @JsonIgnore
  public Point getAddressPoint() {
    return Optional.ofNullable(address).orElse(new PointPayload()).getPointGeometry();
  }

  @JsonIgnore
  public void setAddressPoint(Point point) {
    this.address = new PointPayload(point);
  }

}
