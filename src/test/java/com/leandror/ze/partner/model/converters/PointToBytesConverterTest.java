package com.leandror.ze.partner.model.converters;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.leandror.ze.partner.model.EntityPoint;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

class PointToBytesConverterTest {

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void test() {
  }

  @SuppressWarnings("unchecked")
  @Test
  void returnPointInBytesWhenConvertSourceEntityPoint() throws ParseException {
    EntityPoint expected = new EntityPoint(new GeometryFactory().createPoint(new Coordinate(1d, 50d)));
    Point result = new GeometryFactory().createPoint(new WKTReader().read(new String(new PointToBytesConverter().convert(expected)))
                                                                   .getCoordinate());
    assertThat(result).isEqualTo(expected.getLocation());
  }
}
