package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity(name="inflectview")
public class InflectView{

  @Id
  private Long entryidfrom;
  private Long refidfrom;
  private String formtxt;
  private Long lgorder;
  private String inflection;
  private String inflecttypetxt;

  protected InflectView() {}

  public Long getEntryidfrom() {
    return entryidfrom;
  }

  public void setEntryidfrom(Long entryidfrom) {
    this.entryidfrom = entryidfrom;
  }


  public Long getRefidfrom() {
    return refidfrom;
  }

  public void setRefidfrom(Long refidfrom) {
    this.refidfrom = refidfrom;
  }


  public String getFormtxt() {
    return formtxt;
  }

  public void setFormtxt(String formtxt) {
    this.formtxt = formtxt;
  }


  public Long getLgorder() {
    return lgorder;
  }

  public void setLgorder(Long lgorder) {
    this.lgorder = lgorder;
  }


  public String getInflection() {
    return inflection;
  }

  public void setInflection(String inflection) {
    this.inflection = inflection;
  }


  public String getInflecttypetxt() {
    return inflecttypetxt;
  }

  public void setInflecttypetxt(String inflecttypetxt) {
    this.inflecttypetxt = inflecttypetxt;
  }

}
