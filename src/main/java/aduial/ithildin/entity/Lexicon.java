package aduial.ithildin.entity;

public class Lexicon {

  private long id;
  private String form;
  private String langMnemonic;
  private String langName;
  private String gloss;
  private String cat;
  private String tengwar;
  private String mark;
  private String eldamoPageid;
  private String orderfield;
  private long parentId;
  private long ordering;
  private long entrytypeId;
  private String entryType;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public String getEntryType() {
    return entryType;
  }

  public void setEntryType(String entryType) {
    this.entryType = entryType;
  }

}
