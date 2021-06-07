package com.leandror.ze.partner.model.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import com.leandror.ze.partner.model.EntityPoint;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Component
@ReadingConverter
public class BytesToPointConverter implements Converter<byte[], EntityPoint> {
  @Override
  public EntityPoint convert(final byte[] source) {
    try {
      return new EntityPoint(new GeometryFactory().createPoint(new WKTReader().read(new String(source))
                                                                              .getCoordinate()));
    } catch (ParseException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
