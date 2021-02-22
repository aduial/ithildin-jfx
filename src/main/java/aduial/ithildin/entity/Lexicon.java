package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Lexicon {

  @Id
  private Long entryId;
  private String form;
  private String langMnemonic;
  private String langName;
  private String gloss;
  private String cat;
  private String tengwar;
  private String mark;
  private String eldamoPageid;
  private String orderfield;

  private Long parentId;
  private Long ordering;
  private Long entrytypeId;
  private String entryType;

  protected Lexicon() {}

  public Long getEntryId() {
    return entryId;
  }

  public void setEntryId(Long id) {
    this.entryId = id;
  }


  public String getForm() {
    return form;
  }

  public void setForm(String form) {
    this.form = form;
  }


  public String getLangMnemonic() {
    return langMnemonic;
  }

  public void setLangMnemonic(String langMnemonic) {
    this.langMnemonic = langMnemonic;
  }


  public String getLangName() {
    return langName;
  }

  public void setLangName(String langName) {
    this.langName = langName;
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


  public String getTengwar() {
    return tengwar;
  }

  public void setTengwar(String tengwar) {
    this.tengwar = tengwar;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getEldamoPageid() {
    return eldamoPageid;
  }

  public void setEldamoPageid(String eldamoPageid) {
    this.eldamoPageid = eldamoPageid;
  }


  public String getOrderfield() {
    return orderfield;
  }

  public void setOrderfield(String orderfield) {
    this.orderfield = orderfield;
  }


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


  public Long getOrdering() {
    return ordering;
  }

  public void setOrdering(Long ordering) {
    this.ordering = ordering;
  }


  public Long getEntrytypeId() {
    return entrytypeId;
  }

  public void setEntrytypeId(Long entrytypeId) {
    this.entrytypeId = entrytypeId;
  }


  public String getEntryType() {
    return entryType;
  }

  public void setEntryType(String entryType) {
    this.entryType = entryType;
  }

}
