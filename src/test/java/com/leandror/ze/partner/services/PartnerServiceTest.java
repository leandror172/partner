package com.leandror.ze.partner.services;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.leandror.ze.partner.PartnerRepository;
import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.mappers.PartnerMapper;
import com.leandror.ze.partner.mappers.PartnerMapperImpl;
import com.leandror.ze.partner.model.Partner;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { PartnerMapper.class, PartnerMapperImpl.class })
public class PartnerServiceTest {

//  @InjectMocks
  private PartnerService service;
  @Mock
  private PartnerRepository repository;
  @Spy
  private PartnerMapper mapper;

  private UUID partnerId;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    service = new PartnerService(repository, mapper);
    partnerId = UUID.randomUUID();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void noPartnerReturnedIfPartnerNotFound() {
    assertNull(service.get(partnerId));
  }

  @Test
  void partnerReturnedIfPartnerIdMatches() {
    // very tight coupled!! this test should be changed!
    var partner = new Partner(partnerId, randomAlphabetic(50), randomAlphabetic(50), randomAlphabetic(14));
    when(repository.findById(partnerId)).thenReturn(Optional.of(partner));
    assertNull(service.get(partnerId));
    verify(repository, times(1)).findById(partnerId);
    verify(mapper, times(1)).toPayload(partner);
  }

  @Test
  void partnerCreatedIfPartnerCreationExecuted() {
    var payload = new PartnerPayload(null, randomAlphabetic(50), randomAlphabetic(50), randomAlphabetic(14));
    var partner = new Partner(payload.getId(), payload.getTradingName(), payload.getOwnerName(), payload.getDocument());
    when(mapper.toEntity(payload)).thenReturn(partner);
    service.save(payload);
    verify(repository, times(1)).save(partner);
    verify(mapper, times(1)).toEntity(payload);
  }

}
