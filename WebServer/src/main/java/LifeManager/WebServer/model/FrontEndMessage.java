package LifeManager.WebServer.model;

public class FrontEndMessage {
    private String id;
    private String type;
    private String value;

  public FrontEndMessage() {
  }

  public FrontEndMessage(String id, String type, String value) {
    this.id = id;
    this.type = type;
    this.value = value;
  }

  public FrontEndMessage(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String toString(){
    return "id " + getId() + " type " + getType() + " value " + getValue();
  }
}
