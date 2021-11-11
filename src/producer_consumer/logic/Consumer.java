package producer_consumer.logic;

public class Consumer {
  private Buffer buffer;

  public Consumer(Buffer buffer) {
    this.buffer = buffer;
  }

  /**
   * consumer thread waits while buffer is empty
   * 
   * @return
   * @throws InterruptedException
   */
  public void eat() throws InterruptedException {
    while (true) {
      synchronized (this) {
        while (buffer.isEmpty())
          wait();

        Chapati chap = buffer.removeChapati();

        System.out.println(chap.toString() + " consumed");

        notify();

        Thread.sleep(1000);
      }
    }
  }
}
