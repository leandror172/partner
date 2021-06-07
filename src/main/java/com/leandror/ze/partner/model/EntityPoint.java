package com.leandror.ze.partner.model;

import java.util.List;

import org.hibernate.annotations.Type;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.util.Assert;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntityPoint {

  @GeoIndexed
  @Type(type = "org.hibernate.spatial.GeometryType")
  private Point location;

  public EntityPoint() {
  }

  public EntityPoint(Point location) {
    this.location = location;
  }

  public EntityPoint(List<Double> coordinates) {
    Assert.notNull(coordinates, "coordinates must not be null");
    Assert.isTrue(coordinates.size() >= 2, "coordinates must have at least 2 values");
    this.location = new GeometryFactory().createPoint(new Coordinate(coordinates.get(0), coordinates.get(1)));
  }
}
