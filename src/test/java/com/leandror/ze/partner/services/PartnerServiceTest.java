package com.leandror.ze.partner.services;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.leandror.ze.partner.PartnerRepository;
import com.leandror.ze.partner.mappers.PartnerMapper;
import com.leandror.ze.partner.mappers.PartnerMapperImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {PartnerMapperImpl.class})
public class PartnerServiceTest {

  @InjectMocks
  private PartnerService service;
  @Mock
  private PartnerRepository repository;
  @Spy
  private PartnerMapper mapper;

  private UUID partnerId;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    partnerId = UUID.randomUUID();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void partnerNotFoundTest() {
    assertNull(service.get(partnerId));
  }

}
