package producer_consumer.logic;

public class Producer {
  private Chapati chapati;
  private Buffer buffer;

  public Producer(Buffer buffer) {
    this.chapati = new Chapati();
    this.buffer = buffer;
  }

  /**
   * producer thread waits while buffer is full add chapati to buffer
   * 
   * @throws InterruptedException
   */
  public void produce() throws InterruptedException {
    int size = 0;
    while (true) {
      synchronized (this) {
        while (buffer.isFull())
          wait();

        bake(++size);
        cook();
        buffer.addChapati(this.chapati);

        System.out.println("Chapati " + size + "in produced");

        notify();

        Thread.sleep(1000);
      }
    }
  }

  public void bake(int size) {
    this.chapati.setSize(size);
  }

  public void cook() {
    this.chapati.setIsReady(true);
  }
}
