package aduial.ithildin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by luthien on 18/02/2021.
 */
@Entity
public class Source {

  @Id
  private Long id;
  private String name;
  private String prefix;
  private Long sourcetypeId;

  protected Source() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }


  public Long getSourcetypeId() {
    return sourcetypeId;
  }

  public void setSourcetypeId(Long sourcetypeId) {
    this.sourcetypeId = sourcetypeId;
  }

}
