package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Linked {

  @Id
  private Long id;
  private Long linkedtypeId;
  private Long entryId;
  private Long refId;
  private Long toLanguageId;
  private Long toEntryId;
  private Long ordering;
  private Long sourceId;
  private String mark;
  private String source;

  protected Linked() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getLinkedtypeId() {
    return linkedtypeId;
  }

  public void setLinkedtypeId(Long linkedtypeId) {
    this.linkedtypeId = linkedtypeId;
  }


  public Long getEntryId() {
    return entryId;
  }

  public void setEntryId(Long entryId) {
    this.entryId = entryId;
  }


  public Long getRefId() {
    return refId;
  }

  public void setRefId(Long refId) {
    this.refId = refId;
  }


  public Long getToLanguageId() {
    return toLanguageId;
  }

  public void setToLanguageId(Long toLanguageId) {
    this.toLanguageId = toLanguageId;
  }


  public Long getToEntryId() {
    return toEntryId;
  }

  public void setToEntryId(Long toEntryId) {
    this.toEntryId = toEntryId;
  }


  public Long getOrdering() {
    return ordering;
  }

  public void setOrdering(Long ordering) {
    this.ordering = ordering;
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


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}
