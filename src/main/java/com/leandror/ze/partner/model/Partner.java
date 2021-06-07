package com.leandror.ze.partner.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
//@Entity
@RedisHash("Partner")
public class Partner {

  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String tradingName;
  private String ownerName;
  private String document;
  private EntityPoint address;


}
