package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Entry {

  @Id
  private long id;
  private long formId;
  private long languageId;
  private long glossId;
  private long catId;
  private long ruleFormId;
  private long fromFormId;
  private long stemFormId;
  private String tengwar;
  private String mark;
  private String eldamoPageid;
  private String orderfield;
  private long orthoFormId;
  private long parentId;
  private long ordering;
  private long entrytypeId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFormId() {
    return formId;
  }

  public void setFormId(long formId) {
    this.formId = formId;
  }


  public long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(long languageId) {
    this.languageId = languageId;
  }


  public long getGlossId() {
    return glossId;
  }

  public void setGlossId(long glossId) {
    this.glossId = glossId;
  }


  public long getCatId() {
    return catId;
  }

  public void setCatId(long catId) {
    this.catId = catId;
  }


  public long getRuleFormId() {
    return ruleFormId;
  }

  public void setRuleFormId(long ruleFormId) {
    this.ruleFormId = ruleFormId;
  }


  public long getFromFormId() {
    return fromFormId;
  }

  public void setFromFormId(long fromFormId) {
    this.fromFormId = fromFormId;
  }


  public long getStemFormId() {
    return stemFormId;
  }

  public void setStemFormId(long stemFormId) {
    this.stemFormId = stemFormId;
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


  public long getOrthoFormId() {
    return orthoFormId;
  }

  public void setOrthoFormId(long orthoFormId) {
    this.orthoFormId = orthoFormId;
  }


  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }


  public long getOrdering() {
    return ordering;
  }

  public void setOrdering(long ordering) {
    this.ordering = ordering;
  }


  public long getEntrytypeId() {
    return entrytypeId;
  }

  public void setEntrytypeId(long entrytypeId) {
    this.entrytypeId = entrytypeId;
  }

}
