package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity(name="relatedview")
public class Relatedview {

  @Id
  private Long entryidfrom;
  private Long entryidto;
  private Long refidfrom;
  private Long refidto;
  private String languageto;
  private Long lorder;
  private String formtxt;
  private String sourcename;
  private String sourceprefix;
  private String sourcestring;
  private String sourcetypetxt;

  protected Relatedview() {}

  public Long getEntryidfrom() {
    return entryidfrom;
  }

  public void setEntryidfrom(Long entryidfrom) {
    this.entryidfrom = entryidfrom;
  }


  public Long getEntryidto() {
    return entryidto;
  }

  public void setEntryidto(Long entryidto) {
    this.entryidto = entryidto;
  }


  public Long getRefidfrom() {
    return refidfrom;
  }

  public void setRefidfrom(Long refidfrom) {
    this.refidfrom = refidfrom;
  }


  public Long getRefidto() {
    return refidto;
  }

  public void setRefidto(Long refidto) {
    this.refidto = refidto;
  }


  public String getLanguageto() {
    return languageto;
  }

  public void setLanguageto(String languageto) {
    this.languageto = languageto;
  }


  public Long getLorder() {
    return lorder;
  }

  public void setLorder(Long lorder) {
    this.lorder = lorder;
  }


  public String getFormtxt() {
    return formtxt;
  }

  public void setFormtxt(String formtxt) {
    this.formtxt = formtxt;
  }


  public String getSourcename() {
    return sourcename;
  }

  public void setSourcename(String sourcename) {
    this.sourcename = sourcename;
  }


  public String getSourceprefix() {
    return sourceprefix;
  }

  public void setSourceprefix(String sourceprefix) {
    this.sourceprefix = sourceprefix;
  }


  public String getSourcestring() {
    return sourcestring;
  }

  public void setSourcestring(String sourcestring) {
    this.sourcestring = sourcestring;
  }


  public String getSourcetypetxt() {
    return sourcetypetxt;
  }

  public void setSourcetypetxt(String sourcetypetxt) {
    this.sourcetypetxt = sourcetypetxt;
  }

}
