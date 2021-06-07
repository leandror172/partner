package com.leandror.ze.partner.model.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import com.leandror.ze.partner.model.EntityPoint;

@Component
@WritingConverter
public class PointToBytesConverter implements Converter<EntityPoint, byte[]> {
  @Override
  public byte[] convert(final EntityPoint source) {
    return source.getLocation().toText().getBytes();
  }
}
