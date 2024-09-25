package mo.bitcode.kyrie.repo.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import mo.bitcode.kyrie.common.model.KyrieScaffoldTableBase;
import mo.bitcode.kyrie.common.model.LocationAttribute;
import mo.bitcode.kyrie.service.court.model.CourtStatus;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "court")
@JsonIgnoreProperties({"createBy", "updateBy"})
public class CourtEntity extends KyrieScaffoldTableBase<CourtStatus> implements LocationAttribute {

  private String address;
  private int approvalNeeded;
  private String geoHash;
  private double latitude;
  private double longitude;
  private String name;
  private int numberOfCourt;

}
