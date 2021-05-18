package com.leandror.ze.partner.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Partner {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String tradingName;
  private String ownerName;
  private String document;
}
