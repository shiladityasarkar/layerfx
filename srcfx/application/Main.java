//package application;
//
////import javafx.application.Application;
////import javafx.stage.Stage;
////import javafx.scene.Scene;
////import javafx.scene.layout.BorderPane;
//
//
////public class Main extends Application {
////	@Override
////	public void start(Stage primaryStage) {
////		try {
////			BorderPane root = new BorderPane();
////			Scene scene = new Scene(root,400,400);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
////			primaryStage.setScene(scene);
////			primaryStage.show();
////		} catch(Exception e) {
////			e.printStackTrace();
////		}
////	}
////	
////	public static void main(String[] args) {
////		launch(args);
////	}
////}
//import java.io.FileInputStream; 
//import java.io.FileNotFoundException; 
//import javafx.application.Application; 
//import javafx.scene.Group; 
//import javafx.scene.Scene; 
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;  
//import javafx.stage.Stage;  
//
//public class Main extends Application {  
//   @Override 
//   public void start(Stage stage) throws FileNotFoundException {         
//      //Creating an image 
//      Image image = new Image(new FileInputStream("C:\\StrangerCodes\\ecpjava\\ImageStegan\\src\\application\\image1.jpeg"));  
//      
//      //Setting the image view 
//      ImageView imageView = new ImageView(image); 
//      
//      //Setting the position of the image 
//      imageView.setX(50); 
//      imageView.setY(25); 
//      
//      //setting the fit height and width of the image view 
//      imageView.setFitHeight(400); 
//      imageView.setFitWidth(400); 
//      
//      //Setting the preserve ratio of the image view 
//      imageView.setPreserveRatio(true);  
//      
//      //Creating a Group object  
//      Group root = new Group(imageView);  
//      
//      //Creating a scene object 
//      Scene scene = new Scene(root, 500, 450);  
//      
//      //Setting title to the Stage 
//      stage.setTitle("Loading an image");  
//      
//      //Adding scene to the stage 
//      stage.setScene(scene);
//      
//      //Displaying the contents of the stage 
//      stage.show(); 
//   }  
//   public static void main(String args[]) { 
//      launch(args); 
//   } 
//}