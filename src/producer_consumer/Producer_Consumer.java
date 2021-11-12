/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer;

import Updated_logic.ConsumerTask;
import Updated_logic.ProducerTask;
import static Updated_logic.ProducerTask.i;
import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import static java.util.concurrent.TimeUnit.SECONDS;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
/**
 *
 * @author Kwizera
 */
public class Producer_Consumer extends Application {
    String  client_StateUri=null;
    ImageView ClientImageView=null;
       static VBox platePane=null;
       ProducerTask produce;
       ConsumerTask consumerTask;
       static GridPane tablegridpane=null;
       static boolean isReady=false;
       public static Label producerStatus=new Label("Producer ");
         public static Label consumerStatus=new Label("Consumer");
         public static TextArea statusTextArea=new TextArea();
       public static Producer_Consumer producer_consumer=new Producer_Consumer();
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        produce=new ProducerTask();
        consumerTask=new ConsumerTask();
        
        
          ExecutorService executor = Executors.newFixedThreadPool(2);
  
        btn.setText("Prepare the production prerequisites");
         client_StateUri="consumer_producer_images/waiting1.jpg";
         String newClientState="consumer_producer_images/waiting2.jpg";
          String newClientState_eating="consumer_producer_images/eating_chapati.gif";
   
//        GridPane homePane=new GridPane();
        HBox root = new HBox();
//        String Imagestring="consumer_producer_images/butler.jpg";
//        Image image=new Image(Producer_Consumer.class.getResource(Imagestring).toExternalForm());
//        ImageView imageview=new ImageView(image);
        root.getChildren().add(btn);
         VBox vbox=new VBox();
         vbox.setSpacing(10);
         Button startProduction=new Button("Start production");
         vbox.getChildren().add(startProduction);
            GridPane homePane = new GridPane();
         homePane.add(root  ,0, 0, 1, 1);
         
       
          //HandleTable_plates_Add();
        tablegridpane=new GridPane();
        tablegridpane.setVgap(10);
        tablegridpane.setHgap(15);
// tablegridpane.add(producerStatus, 0,0,1,1);
//  tablegridpane.add(consumerStatus, 0,1,1,1);
 
       
         tablegridpane.setVgap(1);
         
         SetClientPane(newClientState_eating);
          FlowPane ClientPane=getClientStatePane();
              
          btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    root.getChildren().add(Display_Images(Get_Essentials()));
                    root.getChildren().remove(btn);
                     root.getChildren().add(vbox);
                      
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer_Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
          
        
         startProduction.setOnAction(event->{
            try {
                vbox.getChildren().add(Display_Images(StartProduction_Data()));
                Platform.runLater(()->homePane.add(tablegridpane, 4,1,1,1));
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer_Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
           startProduction.setDisable(true);
            String producing_chapati="consumer_producer_images/producer_gif.gif";
           
           homePane.add(statusTextArea, 4,0,1,1);
           statusTextArea.setEditable(false);
//          Platform.runLater(()->statusTextArea.setMinHeight(i));
            Image image=new Image(Producer_Consumer.class.getResource(producing_chapati).toExternalForm(),100,100,false,false);
                      ImageView imageview=new ImageView(image); 
                   HBox hb=new HBox();
                    hb.getChildren().add(imageview);
                    vbox.getChildren().add(producerStatus);
                       vbox.getChildren().add(imageview);
           Thread annimate=new Thread(new Runnable(){
               public void run(){
                       
                        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    
        final Runnable runnable = new Runnable() {
             int timerr=100;
            public void run() {
           
                   Platform.runLater(()->{
                     
                       imageview.setX(imageview.getLayoutX()+timerr*20);
                       imageview.setY(imageview.getLayoutY()+timerr*10);
                   });
                   timerr--;
                   
                if (timerr<0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
                     
           });
           annimate.start();
           
        executor.execute((Runnable)produce );

  executor.execute(consumerTask);
  executor.shutdown();
  
  StackPane tableroomPane=getTablePane();
                           tablegridpane.add(tableroomPane,0,3,1,1);
                           tablegridpane.add(ClientPane,4,3,1,1);
         });
         
       //homePane.setAlignment(Pos.CENTER);
        //homePane.setGridLinesVisible(true);
         homePane.setHgap(10);
          homePane.autosize();
         Scene scene = new Scene(homePane, 1200, 6500);
        
        primaryStage.setTitle("Produce vs consumer!");
        primaryStage.setScene(scene);
        primaryStage.show();
         
    }

 
    public static void SetReady(boolean ready){
        isReady=ready;
    }
 
   
    public ArrayList<String> Get_Essentials(){
                  String Imagestring="consumer_producer_images/bakingg";
            String butler="consumer_producer_images/butler.jpg";
            String baking_powder="consumer_producer_images/bakingg powder.jpg";
            String capati="consumer_producer_images/capati.jpg";
            String cooking_oil="consumer_producer_images/oil.jpg";
            String onions="consumer_producer_images/onions.jpg";
               String fryingPan="consumer_producer_images/fryin pan.jpg";
            String stove="consumer_producer_images/stove.jpg";
            String table="consumer_producer_images/tabel.jpg";
                     
    ArrayList<String> myImages=new ArrayList<String>();
    myImages.add(stove);
    myImages.add(fryingPan);
   // myImages.add(table);
     
    
    myImages.add(baking_powder);
    myImages.add(cooking_oil);
  myImages.add(onions);
  return myImages;
    }
    public  ArrayList<String> StartProduction_Data(){
         int height = 300;
           int width = 300;
        FlowPane flowpane=new FlowPane();
          String Imagestring="consumer_producer_images/arura.jpg";
           String cooking_oil="consumer_producer_images/cookingg oil.jpg";
           String Karaga="consumer_producer_images/karagga1.gif";
            String powder="consumer_producer_images/powder.jpg";
          
             ArrayList<String> myImages=new ArrayList<String>();
              //myImages.add(producing_chapati);
                  myImages.add(Imagestring);
                     myImages.add( cooking_oil);
                         myImages.add(Karaga);
                         myImages.add(powder);
              
        return  myImages;
    }
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
  
   public FlowPane Display_Images(ArrayList<String> myImages) throws InterruptedException{
          int height = 300;
           int width = 300;
         
//            String producer1="consumer_producer_images/producer_gif.gif";

  //myImages.add(producer1);
    FlowPane Essential_Tools=new FlowPane();    
Thread myThread=new Thread(new Runnable(){
       int Imagesize = myImages.size();
    @Override
    public void run(){
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {

            public void run() {

                System.out.println(Imagesize);
                 Image image=new Image(Producer_Consumer.class.getResource(myImages.get(Imagesize-1)).toExternalForm(),100,100,false,false);
            ImageView imageview=new ImageView(image);   
                System.out.print("Waiting a sec..");
                Platform.runLater(()->{
                          Essential_Tools.getChildren().add(imageview);
                     
                    });
                Imagesize--;

                if (Imagesize < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
          });
myThread.start();
             Essential_Tools.setAlignment(Pos.TOP_LEFT);
          Essential_Tools.setHgap(10);
           Essential_Tools.setVgap(2);
           Essential_Tools.setMaxHeight(height);
           Essential_Tools.setMaxWidth(width);
           
                   return Essential_Tools;
   
   }
   public  void SetClientPane(String Imgeuri){
  
       client_StateUri=Imgeuri;
    
   }
//   public void ChangeCustomerState(String oldIm,String newIm){
//       System.out.println("Now old is "+oldIm+" and new is "+newIm);
//       oldIm=newIm;
//       
//   }
   public FlowPane getClientStatePane(){
        FlowPane customerPane=new FlowPane(); 
       Image client_image= new Image(Producer_Consumer.class.getResource(client_StateUri).toExternalForm(),100,100,false,false);
       ClientImageView=new ImageView(client_image);
       customerPane.getChildren().add(ClientImageView);
       return customerPane;
   }
        public StackPane getTablePane(){
         String tableSrc="consumer_producer_images/servingtable.jpg";
         StackPane tableroomPane=new StackPane();
         Image tableImage=new Image(Producer_Consumer.class.getResource(tableSrc).toExternalForm(),100,100,false,false);
         ImageView tableImageview=new ImageView(tableImage);
         tableroomPane.getChildren().add(tableImageview);
        tableroomPane.getChildren().add(new Label("Serving table"));
//         tableroomPane.getChildren().add(platePane);
         return tableroomPane;
    }
   
   public static class Buffer {
    private static final int CAPACITY = 1; // buffer size
  private java.util.LinkedList<Integer> queue =
  new java.util.LinkedList<>();
  
  public static Buffer buffer = new Buffer();
    Image image=null;
     ImageView imageview=null;
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
          Platform.runLater(()->statusTextArea.appendText("Wait for notFull condition \n"));
         //Platform.runLater(()->  producerStatus.setText("Wait for notFull condition"));
      notFull.await();
  }    

 queue.offer(value);
   HandleTable_plates_Add();

notEmpty.signal(); // Signal notEmpty condition
  }
catch (InterruptedException ex) {
 ex.printStackTrace();
}
  finally {
  lock.unlock(); // Release the lock
 }
  }
  public  void HandleTable_plates_Add(){
        platePane=new VBox();
        ArrayList<String> myImages=new ArrayList<String>();
         String chapatiResource="consumer_producer_images/chapatiPlate.jpg";
            //myImages.add(chapatiResource);
 
              image=new Image(Producer_Consumer.class.getResource(chapatiResource).toExternalForm(),100,100,false,false);
            imageview=new ImageView(image);   
              
                Platform.runLater(()->{
                     
                          platePane.getChildren().add(imageview);
                         tablegridpane.add(platePane,0,2,1,1);
                        //producerStatus.setText("Producer writes " +i);
                    });
    
       
    }
  public void Remove_plate(){
      
                Platform.runLater(()->{
                 

                          platePane.getChildren().remove(imageview);
                         tablegridpane.getChildren().remove(platePane);
                         //consumerStatus.setText("Consumer reads" + buffer.read());
                         
                    });
  }

 
 public int read() {
 int value = 0;
 lock.lock(); // Acquire the lock
  try {
 while (queue.isEmpty()) {
 System.out.println("\t\t\tWait for notEmpty condition");
 Platform.runLater(()->statusTextArea.appendText("\t\t\t\t\t\t Wait for notEmpty condition\n"));
 //Platform.runLater(()-> consumerStatus.setText("Wait for notEmpty condition"));
 notEmpty.await();
 }
  
  value = queue.remove();
   Remove_plate();
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



   
        
   
}

    

