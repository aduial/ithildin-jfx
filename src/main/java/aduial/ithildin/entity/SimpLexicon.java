package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity(name = "simplexicon")
public class SimpLexicon{

  @Id
  private Long   entryId;
  private String mark;
  private String form;
  private Long languageId;
  private String languagename;
  private String gloss;
  private String cat;
  private String stem;
  private Long entrytypeId;

  protected SimpLexicon() {}

  public Long getEntryId() {
    return entryId;
  }

  public void setEntryId(Long id) {
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


  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
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


  public Long getEntrytypeId() {
    return entrytypeId;
  }

  public void setEntrytypeId(Long entrytypeId) {
    this.entrytypeId = entrytypeId;
  }

}
