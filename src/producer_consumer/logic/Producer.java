package producer_consumer.logic;

public class Producer {
  private Chapati chapati;
  private Buffer buffer;

  public Producer(Buffer buffer) {
    this.buffer = buffer;
  }

  /**
   * producer thread waits while buffer is full add chapati to buffer
   * 
   * @return
   * @throws InterruptedException
   */
  public void produce() throws InterruptedException {
    int size = 0;
    while (true) {
      synchronized (this) {
        while (buffer.isFull()) {
          notify();
          wait();
        }

        chapati = new Chapati();
        bake(++size);
        cook();
        buffer.addChapati(chapati);

        System.out.println(chapati.toString() + " produced");

        notify();

        Thread.sleep(1000);
      }
    }
  }

  public void bake(int size) {
    chapati.setSize(size);
  }

  public void cook() {
    this.chapati.setIsReady(true);
  }
}
