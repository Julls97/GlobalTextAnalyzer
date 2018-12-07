package example;

import java.io.Serializable;

public class Pair implements Serializable {
  public String x;
  public int y;
  
  public Pair() {
    this.x = "";
    this.y = 0;
  }
  
  public Pair(String x, int y) {
    this.x = x;
    this.y = y;
  }
}
