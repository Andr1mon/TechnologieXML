package fr.ensim.tp.xmjson.deezer.service;

public enum Output {
  XML("xml"),
  JSON("json");

  private final String name;

  Output(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
