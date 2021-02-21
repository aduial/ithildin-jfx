package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity(name = "simplexicon")
public class SimpLexicon{

  @Id
  private long   entryId;
  private String mark;
  private String form;
  private long languageId;
  private String languagename;
  private String gloss;
  private String cat;
  private String stem;
  private long entrytypeId;

  protected SimpLexicon() {}

  public long getEntryId() {
    return entryId;
  }

  public void setEntryId(long id) {
    this.entryId = id;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getForm() {
    return form;
  }

  public void setForm(String form) {
    this.form = form;
  }


  public long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(long languageId) {
    this.languageId = languageId;
  }


  public String getLanguagename() {
    return languagename;
  }

  public void setLanguagename(String languagename) {
    this.languagename = languagename;
  }


  public String getGloss() {
    return gloss;
  }

  public void setGloss(String gloss) {
    this.gloss = gloss;
  }


  public String getCat() {
    return cat;
  }

  public void setCat(String cat) {
    this.cat = cat;
  }


  public String getStem() {
    return stem;
  }

  public void setStem(String stem) {
    this.stem = stem;
  }


  public long getEntrytypeId() {
    return entrytypeId;
  }

  public void setEntrytypeId(long entrytypeId) {
    this.entrytypeId = entrytypeId;
  }

}
