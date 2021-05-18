package com.leandror.ze.partner.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.services.PartnerService;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;

@ExtendWith({ SpringExtension.class, RandomBeansExtension.class })
@WebMvcTest(controllers = PartnerController.class)
public class PartnerControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private PartnerService service;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void requestMatchTest(@Random UUID partnerId) throws Exception {
    mockMvc.perform(get("/api/v1/partner/{partnerId}", partnerId).contentType("application/json"))
           .andExpect(status().isOk());
  }

  @Test
  void returnOkWhenGivenNewPartner(@Random(excludes = "id") PartnerPayload payload) throws Exception {
    mockMvc.perform(post("/api/v1/partner").contentType("application/json")
                                           .content(objectMapper.writeValueAsString(payload)))
           .andExpect(status().isOk());
  }

  @Test
  void returnOkWhenGivenExistingPartner(@Random PartnerPayload payload) throws Exception {
    mockMvc.perform(post("/api/v1/partner").contentType("application/json")
                                           .content(objectMapper.writeValueAsString(payload)))
           .andExpect(status().isOk());
  }

  @Test
  void returnOkWhenGetExistingPartner(@Random UUID partnerId, @Random(excludes = "id") PartnerPayload payload)
      throws Exception {
    payload.setId(partnerId);
    when(service.get(partnerId)).thenReturn(payload);

    mockMvc.perform(get("/api/v1/partner/{partnerId}", partnerId).contentType("application/json"))
           .andExpect(status().isOk());

    verify(service, times(1)).get(partnerId); 
    assertThat(partnerId).isEqualTo(partnerId);
  }

  @Test
  void returnNotFoundWhenGetNonExistingPartner(@Random UUID partnerId)
      throws Exception {
    when(service.get(partnerId)).thenReturn(null);

    mockMvc.perform(get("/api/v1/partner/{partnerId}", partnerId).contentType("application/json"))
           .andExpect(status().isNotFound());

    verify(service, times(1)).get(partnerId); 
    assertThat(partnerId).isEqualTo(partnerId);
  }

}
