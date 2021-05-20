package com.leandror.ze.partner.services;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.leandror.ze.partner.PartnerRepository;
import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;
import com.leandror.ze.partner.mappers.PartnerMapperImpl;
import com.leandror.ze.partner.model.Partner;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

@ExtendWith({ MockitoExtension.class, RandomBeansExtension.class })
@SpringBootTest(classes = { PartnerMapper.class, PartnerMapperImpl.class })
public class PartnerServiceTest {

  private PartnerService service;
  @Mock
  private PartnerRepository repository;

  private UUID partnerId;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    service = new PartnerService(repository, PartnerMapper.INSTANCE);
    partnerId = UUID.randomUUID();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void partnerReturnedIfPartnerIdMatches() {
    // very tight coupled!! this test should be changed!
    var partner = new Partner(partnerId, randomAlphabetic(50), randomAlphabetic(50), randomAlphabetic(14));
    when(repository.findById(partnerId)).thenReturn(Optional.of(partner));
    var result = service.get(partnerId);
    assertThat(result).isNotEqualTo(Optional.empty());
    verify(repository, times(1)).findById(partnerId);
  }

  @Test
  void partnerNotReturnedIfPartnerNotFound() {
    when(repository.findById(partnerId)).thenReturn(Optional.empty());
    assertThat(service.get(partnerId)).isEqualTo(Optional.empty());
    verify(repository, times(1)).findById(partnerId);
  }

  @Test
  void partnerCreatedIfPartnerCreationExecuted(@Random UUID partnerId) {
    var payload = new PartnerPayload(null, randomAlphabetic(50), randomAlphabetic(50), randomAlphabetic(14));
    var partnerSaved = new Partner(partnerId, payload.getTradingName(), payload.getOwnerName(), payload.getDocument());
    when(repository.save(any(Partner.class))).thenReturn(partnerSaved);
    service.save(payload);
    ArgumentCaptor<Partner> captor = ArgumentCaptor.forClass(Partner.class);
    verify(repository, times(1)).save(captor.capture());
  }

}
