package com.leandror.ze.partner.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PartnerController.class)
public class PartnerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private UUID partnerId;


  @BeforeEach
  void setUp() throws Exception {
    partnerId = UUID.randomUUID();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void requestMatchTest() throws Exception {
    mockMvc.perform(get("/api/v1/partner/".concat(partnerId.toString())).contentType("application/json"))
           .andExpect(status().isOk());
  }

}
