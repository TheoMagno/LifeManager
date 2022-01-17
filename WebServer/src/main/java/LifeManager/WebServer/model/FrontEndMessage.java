package LifeManager.WebServer.model;

public class FrontEndMessage {
    private int id;
    private int type;
    private String value;

  public FrontEndMessage() {
  }

  public FrontEndMessage(int id, int type, String value) {
    this.id = id;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int id) {
    this.id = type;
  }
}
