package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity(name="classview")
public class ClassView{

  @Id
  private Long entryid;
  private String mark;
  private Long lgorder;
  private String grammartxt;
  private String grammartypetxt;
  private Long grammarid;

  protected ClassView() {}

  public Long getEntryid() {
    return entryid;
  }

  public void setEntryid(Long entryid) {
    this.entryid = entryid;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public Long getLgorder() {
    return lgorder;
  }

  public void setLgorder(Long lgorder) {
    this.lgorder = lgorder;
  }


  public String getGrammartxt() {
    return grammartxt;
  }

  public void setGrammartxt(String grammartxt) {
    this.grammartxt = grammartxt;
  }


  public String getGrammartypetxt() {
    return grammartypetxt;
  }

  public void setGrammartypetxt(String grammartypetxt) {
    this.grammartypetxt = grammartypetxt;
  }


  public Long getGrammarid() {
    return grammarid;
  }

  public void setGrammarid(Long grammarid) {
    this.grammarid = grammarid;
  }

}
