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
import static Updated_logic.Buffer.buffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ProducerTask implements Runnable {
  public void run() {
  try {
  int i = 1;
 while (true) {
  System.out.println("Producer writes " + i);
 buffer.write(i++); // Add a value to the buffer
 // Put the thread into sleep
  Thread.sleep((int)(Math.random() * 10000));
  }
  }
  catch (InterruptedException ex) {
      ex.printStackTrace();
  }
 }
  }