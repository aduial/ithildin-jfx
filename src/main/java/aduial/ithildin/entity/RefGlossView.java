package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class RefGlossView{

  @Id
  private long entryId;
  private String refgloss;

  protected RefGlossView() {}

  public long getEntryId() {
    return entryId;
  }

  public void setEntryId(long entryId) {
    this.entryId = entryId;
  }


  public String getRefgloss() {
    return refgloss;
  }

  public void setRefgloss(String refgloss) {
    this.refgloss = refgloss;
  }

}
