package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Ref {

  @Id
  private long id;
  private long entryId;
  private long formId;
  private long glossId;
  private long languageId;
  private long sourceId;
  private String mark;
  private long ruleFromformId;
  private long ruleRlformId;
  private long ruleRuleformId;
  private long ordering;
  private long entrytypeId;
  private String source;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getEntryId() {
    return entryId;
  }

  public void setEntryId(long entryId) {
    this.entryId = entryId;
  }


  public long getFormId() {
    return formId;
  }

  public void setFormId(long formId) {
    this.formId = formId;
  }


  public long getGlossId() {
    return glossId;
  }

  public void setGlossId(long glossId) {
    this.glossId = glossId;
  }


  public long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(long languageId) {
    this.languageId = languageId;
  }


  public long getSourceId() {
    return sourceId;
  }

  public void setSourceId(long sourceId) {
    this.sourceId = sourceId;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public long getRuleFromformId() {
    return ruleFromformId;
  }

  public void setRuleFromformId(long ruleFromformId) {
    this.ruleFromformId = ruleFromformId;
  }


  public long getRuleRlformId() {
    return ruleRlformId;
  }

  public void setRuleRlformId(long ruleRlformId) {
    this.ruleRlformId = ruleRlformId;
  }


  public long getRuleRuleformId() {
    return ruleRuleformId;
  }

  public void setRuleRuleformId(long ruleRuleformId) {
    this.ruleRuleformId = ruleRuleformId;
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


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}
