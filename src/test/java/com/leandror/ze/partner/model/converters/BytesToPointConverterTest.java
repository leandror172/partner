package com.leandror.ze.partner.model.converters;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leandror.ze.partner.model.EntityPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

class BytesToPointConverterTest {

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @SuppressWarnings("unchecked")
  @Test
  void returnPointInEntityPointWhenConvertSourceBytes() {
    Point expected = new GeometryFactory().createPoint(new Coordinate(1d, 50d)) ;
    EntityPoint result = new BytesToPointConverter().convert(expected.toText().getBytes());
    assertThat(result.getLocation()).isEqualTo(expected);
  }

}
