package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Refcognateview {

  @Id
  private long entryidfrom;
  private String lang;
  private String form;
  private String gloss;
  private String sources;


  public long getEntryidfrom() {
    return entryidfrom;
  }

  public void setEntryidfrom(long entryidfrom) {
    this.entryidfrom = entryidfrom;
  }


  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }


  public String getForm() {
    return form;
  }

  public void setForm(String form) {
    this.form = form;
  }


  public String getGloss() {
    return gloss;
  }

  public void setGloss(String gloss) {
    this.gloss = gloss;
  }


  public String getSources() {
    return sources;
  }

  public void setSources(String sources) {
    this.sources = sources;
  }

}
