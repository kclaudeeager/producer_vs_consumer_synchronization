package producer_consumer.logic;

public class Chapati {
  private int size;
  private boolean isReady;

  public Chapati() {
  }

  public Chapati(int size, boolean isReady) {
    this.size = size;
    this.isReady = isReady;
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  /**
   * Check if chapati is ready, its size should be greater or equal to zero
   * 
   * @return boolean
   */
  public boolean isIsReady() {
    if (this.size <= 0) {
      return false;
    }
    return this.isReady;
  }

  public void setIsReady(boolean isReady) {
    this.isReady = isReady;
  }

  @Override
  public String toString() {
    return "Chapati " + size + "in";
  }
}