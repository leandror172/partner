package com.leandror.ze.partner.dtos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import lombok.NonNull;

@JsonTest
@ExtendWith({ RandomBeansExtension.class })
public class PartnerPayloadTest {

  @Autowired
  private JacksonTester<PartnerPayload> json;

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testSerialize(@Random UUID id, @Random String tradingName, @Random String ownerName,
                     @Random @NonNull String document, @Random(type = Double.class, size = 2) List<Double> coordinates)
      throws Exception {
    PartnerPayload payload = new PartnerPayload(id, tradingName, ownerName, document, coordinates);

    JsonContent<PartnerPayload> result = this.json.write(payload);
    assertThat(result).hasJsonPathStringValue("$.id");
    assertThat(result).extractingJsonPathValue("$.id").isEqualTo(id.toString());
    assertThat(result).extractingJsonPathStringValue("$.tradingName").isEqualTo(tradingName);
    assertThat(result).extractingJsonPathStringValue("$.ownerName").isEqualTo(ownerName);
    assertThat(result).extractingJsonPathStringValue("$.document").isEqualTo(document);
    assertThat(result).extractingJsonPathArrayValue("$.address.coordinates").isEqualTo(coordinates);
  }
  
  @Test
  public void testDeserialize() throws Exception {
   
    String jsonContent = """
          {
            "id": "10a281d1-64f9-4f61-89ef-c82c0c3882df", 
            "tradingName": "Adega da Cerveja - Pinheiros",
            "ownerName": "Zé da Silva",
            "document": "1432132123891/0001",
            "address": { 
              "type": "Point",
              "coordinates": [-46.57421, -21.785741]
            }
          }
        """;
   
    PartnerPayload result = this.json.parse(jsonContent).getObject();
   
    assertThat(result.getId()).isEqualTo(UUID.fromString("10a281d1-64f9-4f61-89ef-c82c0c3882df"));
    assertThat(result.getTradingName()).isEqualTo("Adega da Cerveja - Pinheiros");
    assertThat(result.getOwnerName()).isEqualTo("Zé da Silva");
    assertThat(result.getDocument()).isEqualTo("1432132123891/0001");
    assertThat(result.getAddress().getType()).isEqualTo("Point");
    assertThat(result.getAddress().getCoordinates()).isEqualTo(List.of(-46.57421, -21.785741));
  }

}
