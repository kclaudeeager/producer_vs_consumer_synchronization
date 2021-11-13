/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Updated_logic;

/**
 *
 * @author Kwizera
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import static producer_consumer.Producer_Consumer.Buffer.buffer;
import static producer_consumer.Producer_Consumer.producer_consumer;
import static producer_consumer.Producer_Consumer.producerStatus;
import static producer_consumer.Producer_Consumer.statusTextArea;
public class ProducerTask implements Runnable {
   public static int i = 1;
  public void run() {
  try {
  
 while (true) {
  System.out.println("Producer writes " + i);
  Platform.runLater(()->statusTextArea.appendText("Producer produced  " + i+" Chapaties\n"));
        //        
 buffer.Produce(i++); // Add a value to the buffer
 
 // Put the thread into sleep
  Thread.sleep((int)(Math.random() * 10000));
  }
  }
  catch (InterruptedException ex) {
      ex.printStackTrace();
  }

 }
  }