package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity(name="refderivview")
public class RefDerivView{

  @Id
  private Long entryId;
  private String form;
  private String glosses;
  private String sources;

  protected RefDerivView() {}

  public Long getEntryidfrom() {
    return entryId;
  }

  public void setEntryidfrom(Long entryidfrom) {
    this.entryId = entryId;
  }


  public String getForm() {
    return form;
  }

  public void setForm(String form) {
    this.form = form;
  }


  public String getGlosses() {
    return glosses;
  }

  public void setGlosses(String glosses) {
    this.glosses = glosses;
  }


  public String getSources() {
    return sources;
  }

  public void setSources(String sources) {
    this.sources = sources;
  }

}
