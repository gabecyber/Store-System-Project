package application;
 
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
 static Stage stg;
 @Override
 public void start(Stage stage) {
  try {
   stg = stage;
   Parent root = FXMLLoader.load(getClass().getResource("FirstScreen.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 } 
 public void changeScene(String fxml) throws IOException {
     Parent pane = FXMLLoader.load(getClass().getResource(fxml));
     stg.getScene().setRoot(pane);
 }
 public static void main(String[] args) {
  launch(args);
 }
}
