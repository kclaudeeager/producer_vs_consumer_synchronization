/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Updated_logic;

import static Updated_logic.Buffer.buffer;

/**
 *
 * @author Kwizera
 */
public class ConsumerTask implements Runnable {
  public void run() {
try {
 while (true) {
  System.out.println("\t\t\tConsumer reads " + buffer.read());
  // Put the thread into sleep
  Thread.sleep((int)(Math.random() * 10000));
  }
  }
  catch (InterruptedException ex) {
  ex.printStackTrace();
  }
  }
  }