package aduial.ithildin.entity;

public class Linked {

  private long id;
  private long linkedtypeId;
  private long entryId;
  private long refId;
  private long toLanguageId;
  private long toEntryId;
  private long ordering;
  private long sourceId;
  private String mark;
  private String source;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getLinkedtypeId() {
    return linkedtypeId;
  }

  public void setLinkedtypeId(long linkedtypeId) {
    this.linkedtypeId = linkedtypeId;
  }


  public long getEntryId() {
    return entryId;
  }

  public void setEntryId(long entryId) {
    this.entryId = entryId;
  }


  public long getRefId() {
    return refId;
  }

  public void setRefId(long refId) {
    this.refId = refId;
  }


  public long getToLanguageId() {
    return toLanguageId;
  }

  public void setToLanguageId(long toLanguageId) {
    this.toLanguageId = toLanguageId;
  }


  public long getToEntryId() {
    return toEntryId;
  }

  public void setToEntryId(long toEntryId) {
    this.toEntryId = toEntryId;
  }


  public long getOrdering() {
    return ordering;
  }

  public void setOrdering(long ordering) {
    this.ordering = ordering;
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


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

}
