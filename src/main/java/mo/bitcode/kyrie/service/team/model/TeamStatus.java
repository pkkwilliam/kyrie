package mo.bitcode.kyrie.service.team.model;

import mo.bitcode.core.service.rest_template.scaffold.table_base.TableBaseStatus;

public enum TeamStatus implements TableBaseStatus {

  ACTIVE;

  @Override
  public String getStatus() {
    return this.toString();
  }

}
