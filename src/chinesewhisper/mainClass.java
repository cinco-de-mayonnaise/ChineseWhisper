/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package chinesewhisper;

import java.util.concurrent.ConcurrentHashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abdullah
 */
public class mainClass extends Application 
{
    @Override
    public void start(Stage stage) throws Exception 
    {
        // THIS part is necessary for SceneSwitcher to work, it needs a reference to getClass() so it can open fxml files
        SceneSwitcher.setMainstage(stage);
        SceneSwitcher.setGlobal_class_handle(getClass());
        /* THIS part  */
        
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
