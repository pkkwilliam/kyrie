package mo.bitcode.kyrie.service.court.model;

import mo.bitcode.core.service.rest_template.scaffold.table_base.TableBaseStatus;

public enum CourtStatus implements TableBaseStatus {

  ACTIVE,
  PENDING;

  @Override
  public String getStatus() {
    return this.toString();
  }

}
