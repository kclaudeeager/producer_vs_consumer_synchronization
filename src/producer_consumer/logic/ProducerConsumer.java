package producer_consumer.logic;

public class ProducerConsumer {
  public final static Buffer buffer = new Buffer(30);
  public final static Producer producer = new Producer(buffer);
  public final static Consumer consumer = new Consumer(buffer);

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread() {
      public void run() {
        try {
          producer.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Thread t2 = new Thread() {
      public void run() {
        try {
          consumer.eat();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };

    t1.start();
    t2.start();
    t1.join();
    t2.join();
  }
}
