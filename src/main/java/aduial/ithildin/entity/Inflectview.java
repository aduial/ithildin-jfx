package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Inflectview {

  @Id
  private long entryidfrom;
  private long refidfrom;
  private String formtxt;
  private long lgorder;
  private String inflection;
  private String inflecttypetxt;


  public long getEntryidfrom() {
    return entryidfrom;
  }

  public void setEntryidfrom(long entryidfrom) {
    this.entryidfrom = entryidfrom;
  }


  public long getRefidfrom() {
    return refidfrom;
  }

  public void setRefidfrom(long refidfrom) {
    this.refidfrom = refidfrom;
  }


  public String getFormtxt() {
    return formtxt;
  }

  public void setFormtxt(String formtxt) {
    this.formtxt = formtxt;
  }


  public long getLgorder() {
    return lgorder;
  }

  public void setLgorder(long lgorder) {
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
