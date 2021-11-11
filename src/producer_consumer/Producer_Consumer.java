/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer_consumer;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 *
 * @author Kwizera
 */
public class Producer_Consumer extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Prepare the production prerequisites");
   
        
        HBox root = new HBox();
//        String Imagestring="consumer_producer_images/butler.jpg";
//        Image image=new Image(Producer_Consumer.class.getResource(Imagestring).toExternalForm());
//        ImageView imageview=new ImageView(image);
        root.getChildren().add(btn);
          btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    root.getChildren().add(GetEssential_Tools());
                    root.getChildren().remove(btn);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer_Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Scene scene = new Scene(root, 1300, 700);
        
        primaryStage.setTitle("Produce vs consumer!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
  
   public FlowPane GetEssential_Tools() throws InterruptedException{
          int height = 300;
           int width = 300;
            String Imagestring="consumer_producer_images/bakingg";
            String butler="consumer_producer_images/butler.jpg";
            String baking_powder="consumer_producer_images/bakingg powder.jpg";
            String capati="consumer_producer_images/capati.jpg";
            String cooking_oil="consumer_producer_images/oil.jpg";
            String onions="consumer_producer_images/onions.jpg";
               String fryingPan="consumer_producer_images/fryin pan.jpg";
            String stove="consumer_producer_images/stove.jpg";
            String table="consumer_producer_images/tabel.jpg";
            String producer1="consumer_producer_images/producer_gif.gif";

            
    ArrayList<String> myImages=new ArrayList<String>();
    myImages.add(stove);
    myImages.add(fryingPan);
   // myImages.add(table);
     
    
    myImages.add(baking_powder);
    myImages.add(cooking_oil);
  myImages.add(onions);
  myImages.add(producer1);
   Timeline mytimeline = null;
    FlowPane Essential_Tools=new FlowPane();
Thread thread = null;
         for(String image_source:myImages){
            Image image=new Image(Producer_Consumer.class.getResource(image_source).toExternalForm(),100,100,false,false);
            ImageView imageview=new ImageView(image);
        thread=new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer_Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.print("Waiting a sec..");
                    Platform.runLater(()->{
                          Essential_Tools.getChildren().add(imageview);
                          
                    });
            }
        });
           thread.start();
         }
         final Thread newThread=thread;
      
         
     
        
             Essential_Tools.setAlignment(Pos.TOP_LEFT);
          Essential_Tools.setHgap(10);
           Essential_Tools.setVgap(2);
           
                   return Essential_Tools;
                   
   }
        
   
}

    

