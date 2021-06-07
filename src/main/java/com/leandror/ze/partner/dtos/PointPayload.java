package com.leandror.ze.partner.dtos;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import lombok.EqualsAndHashCode;

@JsonIgnoreProperties({ "bbox" })
@EqualsAndHashCode
public class PointPayload {

  @JsonIgnore
  private Point point;

  public PointPayload(Point point) {
    this.point = Optional.ofNullable(point).orElse(new GeometryFactory().createPoint(new Coordinate()));
  }

  public PointPayload() {
    this.point = new GeometryFactory().createPoint(new Coordinate());

  }

  public PointPayload(List<Double> coordinates) {
    assert coordinates != null;
    assert coordinates.size() >= 1;
    this.point = new GeometryFactory().createPoint(new Coordinate(coordinates.get(0), coordinates.get(1)));
  }

  public void setCoordinates(List<Double> coordinates) {
    assert coordinates != null;
    assert coordinates.size() >= 1;
    this.point = new GeometryFactory().createPoint(new Coordinate(coordinates.get(0), coordinates.get(1)));
  }

  public List<Double> getCoordinates() {
    return List.of(point.getCoordinate().x, point.getCoordinate().y);
  }

  public String getType() {
    return point.getGeometryType();
  }

  @JsonIgnore
  public Point getPointGeometry() {
    return point;
  }

}
