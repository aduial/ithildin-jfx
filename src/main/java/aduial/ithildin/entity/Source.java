package aduial.ithildin.entity;

public class Source {

  private long id;
  private String name;
  private String prefix;
  private long sourcetypeId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
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


  public long getSourcetypeId() {
    return sourcetypeId;
  }

  public void setSourcetypeId(long sourcetypeId) {
    this.sourcetypeId = sourcetypeId;
  }

}
