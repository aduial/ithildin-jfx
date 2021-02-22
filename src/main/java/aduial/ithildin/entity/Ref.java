package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Ref {

  @Id
  private Long id;
  private Long entryId;
  private Long formId;
  private Long glossId;
  private Long languageId;
  private Long sourceId;
  private String mark;
  private Long ruleFromformId;
  private Long ruleRlformId;
  private Long ruleRuleformId;
  private Long ordering;
  private Long entrytypeId;
  private String source;

  protected Ref() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getEntryId() {
    return entryId;
  }

  public void setEntryId(Long entryId) {
    this.entryId = entryId;
  }


  public Long getFormId() {
    return formId;
  }

  public void setFormId(Long formId) {
    this.formId = formId;
  }


  public Long getGlossId() {
    return glossId;
  }

  public void setGlossId(Long glossId) {
    this.glossId = glossId;
  }


  public Long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(Long languageId) {
    this.languageId = languageId;
  }


  public Long getSourceId() {
    return sourceId;
  }

  public void setSourceId(Long sourceId) {
    this.sourceId = sourceId;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public Long getRuleFromformId() {
    return ruleFromformId;
  }

  public void setRuleFromformId(Long ruleFromformId) {
    this.ruleFromformId = ruleFromformId;
  }


  public Long getRuleRlformId() {
    return ruleRlformId;
  }

  public void setRuleRlformId(Long ruleRlformId) {
    this.ruleRlformId = ruleRlformId;
  }


  public Long getRuleRuleformId() {
    return ruleRuleformId;
  }

  public void setRuleRuleformId(Long ruleRuleformId) {
    this.ruleRuleformId = ruleRuleformId;
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


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}
