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
 import java.util.concurrent.*;
 import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

 public class ConsumerProducer {
 public static void main(String[] args) {
  // Create a thread pool with two threads
  ExecutorService executor = Executors.newFixedThreadPool(2);
  executor.execute((Runnable) new ProducerTask());
  executor.execute(new ConsumerTask());
  executor.shutdown();
 }
 }
 
  // A task for adding an int to the buffer

 
 