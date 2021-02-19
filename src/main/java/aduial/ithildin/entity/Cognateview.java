package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Cognateview {

  @Id
  private long entryidfrom;
  private long entryidto;
  private long refidfrom;
  private long refidto;
  private String languageto;
  private String formtxt;
  private String sourcename;
  private String sourceprefix;
  private String sourcestring;
  private String sourcetypetxt;


  public long getEntryidfrom() {
    return entryidfrom;
  }

  public void setEntryidfrom(long entryidfrom) {
    this.entryidfrom = entryidfrom;
  }


  public long getEntryidto() {
    return entryidto;
  }

  public void setEntryidto(long entryidto) {
    this.entryidto = entryidto;
  }


  public long getRefidfrom() {
    return refidfrom;
  }

  public void setRefidfrom(long refidfrom) {
    this.refidfrom = refidfrom;
  }


  public long getRefidto() {
    return refidto;
  }

  public void setRefidto(long refidto) {
    this.refidto = refidto;
  }


  public String getLanguageto() {
    return languageto;
  }

  public void setLanguageto(String languageto) {
    this.languageto = languageto;
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