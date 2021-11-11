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

public class Buffer {
    private static final int CAPACITY = 1; // buffer size
  private java.util.LinkedList<Integer> queue =
  new java.util.LinkedList<>();
  public static Buffer buffer = new Buffer();
 // Create a new lock
 private static Lock lock = new ReentrantLock();
 
  // Create two conditions
  private static Condition notEmpty = lock.newCondition();
  private static Condition notFull = lock.newCondition();
 
 public void write(int value) {
 lock.lock(); // Acquire the lock
 try {

     while (queue.size() == CAPACITY) {
 
         System.out.println("Wait for notFull condition");

      notFull.await();
  }

 queue.offer(value);
notEmpty.signal(); // Signal notEmpty condition
  }
catch (InterruptedException ex) {
 ex.printStackTrace();
}
  finally {
  lock.unlock(); // Release the lock
 }
  }
 
 public int read() {
 int value = 0;
 lock.lock(); // Acquire the lock
  try {
 while (queue.isEmpty()) {
 System.out.println("\t\t\tWait for notEmpty condition");
 notEmpty.await();
 }
 
  value = queue.remove();
  notFull.signal(); // Signal notFull condition
  }
 catch (InterruptedException ex) {
  ex.printStackTrace();
  } 
finally {
  lock.unlock(); // Release the lock
  return value;
  }
 }
 }


