package com.leandror.ze.partner.services;

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

import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;
import com.leandror.ze.partner.mappers.PartnerMapperImpl;
import com.leandror.ze.partner.model.Partner;
import com.leandror.ze.partner.repositories.PartnerRepository;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

@ExtendWith({ MockitoExtension.class, RandomBeansExtension.class })
@SpringBootTest(classes = { PartnerMapper.class, PartnerMapperImpl.class })
public class PartnerServiceTest {

  private PartnerService service;
  @Mock
  private PartnerRepository repository;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    service = new PartnerService(repository, PartnerMapper.INSTANCE);
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void partnerReturnedIfPartnerIdMatches(@Random Partner partner) {

    when(repository.findById(partner.getId())).thenReturn(Optional.of(partner));
    var result = service.get(partner.getId());
    assertThat(result).isNotEqualTo(Optional.empty());
    verify(repository, times(1)).findById(partner.getId());
  }

  @Test
  void partnerNotReturnedIfPartnerNotFound(@Random UUID partnerId) {

    when(repository.findById(partnerId)).thenReturn(Optional.empty());
    assertThat(service.get(partnerId)).isEqualTo(Optional.empty());
    verify(repository, times(1)).findById(partnerId);
  }

  @Test
  void partnerCreatedIfPartnerCreationExecuted(@Random Partner partner) {

    // very tight coupled!! maybe this test should be changed?
    var payload = new PartnerPayload(null, partner.getTradingName(), partner.getOwnerName(), partner.getDocument());
    when(repository.save(any(Partner.class))).thenReturn(partner);
    service.save(payload);
    ArgumentCaptor<Partner> captor = ArgumentCaptor.forClass(Partner.class);
    verify(repository, times(1)).save(captor.capture());
  }

}
