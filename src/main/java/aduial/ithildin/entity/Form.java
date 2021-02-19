package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Form {

  @Id
  private long id;
  private String txt;
  private String normaltxt;


  public long getId() {
    return id;
  }

  public void setId(long id) {
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