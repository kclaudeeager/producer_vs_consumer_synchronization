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

/**
 *
 * @author Kwizera
 */
public class Producer_Consumer extends Application {
    String  client_StateUri=null;
    ImageView ClientImageView=null;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        
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
         StackPane tableroomPane=getTablePane();
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
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer_Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
           startProduction.setDisable(true);
           homePane.add(tableroomPane, 1,1,1,1);
           homePane.add(ClientPane,2,1,1,1);

           
         });
      
         Scene scene = new Scene(homePane, 1200, 600);
        
        primaryStage.setTitle("Produce vs consumer!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public StackPane getTablePane(){
         String tableSrc="consumer_producer_images/table.jpg";
         StackPane tableroomPane=new StackPane();
         Image tableImage=new Image(Producer_Consumer.class.getResource(tableSrc).toExternalForm(),100,100,false,false);
         ImageView tableImageview=new ImageView(tableImage);
         tableroomPane.getChildren().add(tableImageview);
         return tableroomPane;
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
    public ArrayList<String> StartProduction_Data(){
         int height = 300;
           int width = 300;
        FlowPane flowpane=new FlowPane();
          String Imagestring="consumer_producer_images/arura.jpg";
           String cooking_oil="consumer_producer_images/cookingg oil.jpg";
           String Karaga="consumer_producer_images/karagga1.gif";
            String powder="consumer_producer_images/powder.jpg";
            String producing_chapati="consumer_producer_images/producer_gif.gif";
             ArrayList<String> myImages=new ArrayList<String>();
              myImages.add(producing_chapati);
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
   
   
   
        
   
}

    

