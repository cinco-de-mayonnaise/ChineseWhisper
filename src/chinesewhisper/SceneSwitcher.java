/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chinesewhisper;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author AbdullahTrees
 */
public class SceneSwitcher
{
    static Class <?> global_class_handle = null;
    static Stage mainstage = null;

    public static void setGlobal_class_handle(Class<?> global_class_handle) {
        SceneSwitcher.global_class_handle = global_class_handle;
    }

    public static void setMainstage(Stage mainstage) {
        SceneSwitcher.mainstage = mainstage;
    }

    public static Class<?> getGlobal_class_handle() {
        return global_class_handle;
    }

    public static Stage getMainstage() {
        return mainstage;
    }
    

    // Changes the window(in cur_stage) to the Scene(in fxml_url). No other properties are changed. 
    public static void switchToScene(Stage cur_stage, String fxml_url)
    {
        if (global_class_handle == null)
            throw new IllegalStateException("SceneSwitcher class cannot be used without a runtime reference to the main class. ");
        
        try
        {
            FXMLLoader loader = new FXMLLoader(global_class_handle.getResource(fxml_url));
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            cur_stage.setScene(scene);
            cur_stage.show();
        }
        catch (Throwable t)
        { 
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
        }
    }
    
    // Creates a new child window with the Scene, and returns the stage associated with the window.
    public static Stage createStagewithScene(String fxml_url, boolean resizable)
    {
        if (global_class_handle == null)
            throw new IllegalStateException("SceneSwitcher class cannot be used without a runtime reference to the main class. ");
        if (mainstage == null)
            throw new IllegalStateException("SceneSwitcher class cannot be used without a runtime reference to the main class. ");
        
        /*   // Technically we shouldn't use static as we can just use the CIC to get references to these,
        // an example of how you can get rid of static,
        
        CommonInstancesClass CIC = CommonInstancesClass.getInstance();
        Class <?> global_class_handle = (Class<?>) CIC.getObject(GLOBAL_CLASS_HANDLE);
        Stage mainstage = (Stage) CIC.getObject(MAIN_STAGE);
        */
        
        Stage newstage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(global_class_handle.getResource(fxml_url));
            
            Scene scene = new Scene(root);
            newstage.setScene(scene);
            newstage.setResizable(resizable);
            newstage.initOwner(mainstage);
            newstage.show();
        }
        catch (Throwable t)
        { 
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
        }
        
        return newstage;
    }
    
    public static Stage createStagewithScene(String fxml_url, boolean resizable, int width, int height)
    {
        if (global_class_handle == null)
            throw new IllegalStateException("SceneSwitcher class cannot be used without a runtime reference to the main class. ");
        if (mainstage == null)
            throw new IllegalStateException("SceneSwitcher class cannot be used without a runtime reference to the main class. ");
        
        Stage newstage = new Stage();
        try
        {
            Parent root = FXMLLoader.load(global_class_handle.getResource(fxml_url));

            Scene scene = new Scene(root, width, height);
            newstage.setScene(scene);
            newstage.setResizable(resizable);
            newstage.initOwner(mainstage);
            newstage.show();
        }
        catch (Throwable t)
        { 
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
        }
        
        return newstage;
    }
    
    public static Parent getRootNodeFromURL(String fxml_url)
    {   
        if (global_class_handle == null)
            throw new IllegalStateException("SceneSwitcher class cannot be used without a runtime reference to the main class. ");

        try
        {
            Parent root = FXMLLoader.load(global_class_handle.getResource(fxml_url));
            
            return root;
        }
        catch (Throwable t)
        { 
            t.printStackTrace();
            if (global_class_handle.getResource(fxml_url) == null)
            {
                System.out.println("\n-----getResource failed! Ensure fxml_url is correct...\nfxml_url: " + fxml_url + "\n");
            }
            return null;
        }
    }
    
    // Convenience method that allows you to get the Stage that an event belongs to. This has now become unnecessary thanks to storing the mainStage
    // in a static field, but may still be important if you plan on doing advanced things in your project.
    public static Stage getStageFromEvent(Event event)
    {
        return (Stage)((Node)event.getSource()).getScene().getWindow();
    }
    
    // Convenience method that allows you to get the Stage that an node belonds to. This has now become unnecessary thanks to storing the mainStage
    // in a static field, but may still be important if you plan on doing advanced things in your project.
    // Keep in mind this method can return null if the fxml this node belongs to has not been loaded.
    public static Stage getStageFromNode(Node n)
    {
        return (Stage)(n.getScene().getWindow());
    }
    
    
    // Convenience method that allow you to create Alerts easily. 
    public static void raiseAlert_NotImplemented()
    {
        raiseAlert_GenericWarning("", "", "This feature has not been implemented yet.");
    }
    
    // Convenience method that allow you to create Alerts easily. 
    public static void raiseAlert_BugCheck(String errorstr)
    {
        raiseAlert_GenericError("", "Oh no! A bug!", "You shouldn't be seeing this dialog box! If you see this, let the developers know.\nHere's some additional information that could help them...\n\n\n" + errorstr);
    }
    
    // Helper methods that allow you to create Alerts easily. 
    public static void raiseAlert_GenericError(String title, String header, String content)
    {
        raiseAlert_Generic(title, header, content, AlertType.ERROR);
    }
    
    public static void raiseAlert_GenericWarning(String title, String header, String content)
    {
        raiseAlert_Generic(title, header, content, AlertType.WARNING);
    }
    
    // Notice how we have avoided code duplication (having the exact same code in many places)
    // This allows us to fix bugs more easily because if there's a bug, then you only need to fix
    // it in once place instead of having to fix all the copies
    public static void raiseAlert_Generic(String title, String header, String content, AlertType type)
    {
        Alert win = new Alert(type);
        if (!title.equals(""))
            win.setTitle(title);
        
        if (!header.equals(""))
            win.setHeaderText(header);
        
        if (!content.equals(""))
            win.setContentText(content);
        
        win.showAndWait();
    }
}

