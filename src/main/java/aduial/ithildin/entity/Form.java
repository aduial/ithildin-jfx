package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Form {

  @Id
  private Long id;
  private String txt;
  private String normaltxt;

  protected Form() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getTxt() {
    return txt;
  }

  public void setTxt(String txt) {
    this.txt = txt;
  }


  public String getNormaltxt() {
    return normaltxt;
  }

  public void setNormaltxt(String normaltxt) {
    this.normaltxt = normaltxt;
  }

}
