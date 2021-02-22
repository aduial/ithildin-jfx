package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Entry {

  @Id
  private Long id;
  private Long formId;
  private Long languageId;
  private Long glossId;
  private Long catId;
  private Long ruleFormId;
  private Long fromFormId;
  private Long stemFormId;
  private String tengwar;
  private String mark;
  private String eldamoPageid;
  private String orderfield;
  private Long orthoFormId;
  private Long parentId;
  private Long ordering;
  private Long entrytypeId;

  protected Entry() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getFormId() {
    return formId;
  }

  public void setFormId(Long formId) {
    this.formId = formId;
  }


  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }


  public Long getGlossId() {
    return glossId;
  }

  public void setGlossId(Long glossId) {
    this.glossId = glossId;
  }


  public Long getCatId() {
    return catId;
  }

  public void setCatId(Long catId) {
    this.catId = catId;
  }


  public Long getRuleFormId() {
    return ruleFormId;
  }

  public void setRuleFormId(Long ruleFormId) {
    this.ruleFormId = ruleFormId;
  }


  public Long getFromFormId() {
    return fromFormId;
  }

  public void setFromFormId(Long fromFormId) {
    this.fromFormId = fromFormId;
  }


  public Long getStemFormId() {
    return stemFormId;
  }

  public void setStemFormId(Long stemFormId) {
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


  public Long getOrthoFormId() {
    return orthoFormId;
  }

  public void setOrthoFormId(Long orthoFormId) {
    this.orthoFormId = orthoFormId;
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

}
