package producer_consumer.logic;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
  private Queue<Chapati> chapatis;
  private int capacity;

  /**
   * Create Chapati Buffer
   * 
   * @param capacity amaount of chapatis that buffer can store
   */
  public Buffer(int capacity) {
    this.chapatis = new LinkedList<>();
    this.capacity = capacity;
  }

  /**
   * Get chapatis
   * 
   * @return - Queue<Chapati>
   */
  public Queue<Chapati> getChapatis() {
    return this.chapatis;
  }

  /**
   * Reset chapatis
   * 
   * @param chapatis - Queue<Chappati>
   */
  public void setChapatis(Queue<Chapati> chapatis) {
    this.chapatis = chapatis;
  }

  /**
   * Add chapati to this buffer
   * 
   * @param chapati - new created chapati
   */
  public void addChapati(Chapati chapati) {
    this.chapatis.add(chapati);
  }

  /**
   * Remove chapati to the buffer
   * 
   * @return - Chappati
   */
  public Chapati removeChapati() {
    return this.chapatis.poll();
  }

  /**
   * Get buffer capacity
   * 
   * @return - int
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Reset buffer capacity
   * 
   * @param capacity - new capacity
   */
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  /**
   * Check if buffer is full
   * 
   * @return - boolean
   */
  public boolean isFull() {
    return this.capacity == this.chapatis.size();
  }

  /**
   * Check if buffer is empty
   * 
   * @return boolean
   */
  public boolean isEmpty() {
    return this.chapatis.isEmpty();
  }

}
