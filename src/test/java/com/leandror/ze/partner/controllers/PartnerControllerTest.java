package com.leandror.ze.partner.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leandror.ze.partner.dtos.PartnerPayload;
import com.leandror.ze.partner.services.PartnerService;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import mil.nga.sf.geojson.MultiPoint;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;

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
  void returnOkWhenGetExistingPartner(@Random PartnerPayload payload) throws Exception {

    when(service.get(payload.getId())).thenReturn(Optional.of(payload));

    String result = mockMvc.perform(apiCallGetPartner(payload.getId()))
                           .andExpect(status().isOk())
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

    verify(service, times(1)).get(payload.getId());
    assertThat(payload).isEqualTo(objectMapper.readValue(result, PartnerPayload.class));
  }

  @Test
  void returnNotFoundWhenGetNonExistingPartner(@Random UUID partnerId) throws Exception {

    when(service.get(partnerId)).thenReturn(Optional.empty());

    mockMvc.perform(apiCallGetPartner(partnerId)).andExpect(status().isNotFound());

    verify(service, times(1)).get(partnerId);
  }

  @Test
  void returnOkWhenPostExistingPartner(@Random PartnerPayload payload) throws Exception {
    mockMvc.perform(apiCallPostPartner(payload)).andExpect(status().isOk());
  }

  @Test
  void returnOkAndNewIdWhenPostNewPartner(@Random UUID partnerId, @Random(excludes = "id") PartnerPayload payload)
      throws Exception {

    when(service.save(payload)).thenReturn(new PartnerPayload(partnerId,
                                                              payload.getTradingName(),
                                                              payload.getOwnerName(),
                                                              payload.getDocument()));

    String result = mockMvc.perform(apiCallPostPartner(payload))
                           .andExpect(status().isOk())
                           .andReturn()
                           .getResponse()
                           .getContentAsString();

    verify(service, times(1)).save(payload);
    assertThat(partnerId).isEqualTo(UUID.fromString(objectMapper.readTree(result).get("id").asText()));
  }

  @Test
  void returnNotFoundWhenSearchNearestPartnerAndThereIsNoPartner(@Random Double longitude, @Random Double latitude)
      throws Exception {
    mockMvc.perform(get("/api/v1/partner/near").contentType("application/json")
                                               .queryParam("longitude", String.valueOf(longitude))
                                               .queryParam("latitude", String.valueOf(latitude)))
           .andExpect(status().isNotFound());
  }

  private MockHttpServletRequestBuilder apiCallGetPartner(UUID partnerId) {
    return get("/api/v1/partner/{partnerId}", partnerId).contentType("application/json");
  }

  private MockHttpServletRequestBuilder apiCallPostPartner(PartnerPayload payload) throws JsonProcessingException {
    return post("/api/v1/partner").contentType("application/json").content(objectMapper.writeValueAsString(payload));
  }
}
