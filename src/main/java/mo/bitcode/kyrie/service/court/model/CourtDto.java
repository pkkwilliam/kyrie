package mo.bitcode.kyrie.service.court.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourtDto {

  private String id;
  private String address;
  private CourtStatus courtStatus;
  private double distance;
  private String name;
  private int numberOfCourt;

}
