/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chinesewhisper;

/**
 *
 * @author AbdullahTrees
 */
import java.util.concurrent.ConcurrentHashMap;


/* COMMON DATA CLASS: A singleton class which can be accessed by every FXML scene that is loaded in this project. 
 This class is meant to be used to communicate information/data across scenes.
 Some important information that could be held in this class include
    - Database (loaded from file into class at startup)
    - Runtime Object holders (that can hold any object)
    - Strings/Data to be passed from the current scene to a new scene

    How to access?
    ----------------
    Just import CommonInstancesClass and then do CommonInstancesClass.getInstance() to get the handle to the instance.
    Then use get/setObjects to grab Object references from inside.

    CommonInstancesClass CIC = CommonInstancesClass.getInstance();
    Arraylist<Users> userlist = ...;
    User SomePerson;

    // Put Array of Users in CIC
    CIC.putObjectIfAbsent("any string, has to be unique", userlist);
    
    // The string acts as a key that allows you to retrieve only that data which is associated with that key.

    // Get some information from CIC (don't forget to cast the type properly, because it's an Object)
    SomePerson = (User) CIC.getObject("Show in Scene");
    
    // do work with SomePerson...
    SomePerson.blablabla();
*/
public class CommonInstancesClass
{
    private final static CommonInstancesClass INSTANCE = new CommonInstancesClass();
    private final ConcurrentHashMap<String, Object> RuntimeObjects;
    
    
    /**
     * SINGLETON CLASS
     * --------------------
     * 
     * It is a class whose constructor is private. This means no one can create 
     * instances of this class haphazardly (so kind of like abstract classes).
     * But unlike abstract classes, which cannot be instantiated AT ALL, singleton
     * classes can be instantiated ONLY ONCE! In our example here, the CIC class's
     * constructor is called inside the class field, so the JVM will probably 
     * instantiate this class sometime during the startup of this application. 
     * Note that you do not need to instantiate this class (you cant, actually), 
     * because it was already done for you by the JVM.
     * 
     * 
     * >> Why didn't we just declare the hashmap as `private final static` in this 
     * class? 
     * If this class is not singleton, then there's nothing preventing you from 
     * making multiple instances of this class and then you're gonna complain 
     * about why you can't pass data from one scene to another. (because you 
     * accidentally made another instance of a hashmap somewhere...)
     * 
     * 
     * >> This is too hard! :((
     * ok fine :/ You can declare a public final static HashMap inside the mainClass
     * as a field as well. Essentially equivalent. 
     * 
     * But putting it like this allows you to GENERALIZE the concept of shared 
     * storage for scenes and allows you to put it in any project in the future 
     * just by including this file. 
     * 
     * Also remember what I said in class, you don't need to understand everything 
     * completely to use it. Such is the mindset of programmers.
    */
    private CommonInstancesClass()
    { 
        RuntimeObjects = new ConcurrentHashMap<>();
    }
    
    public static CommonInstancesClass getInstance()
    {
        return INSTANCE;
    }
    
    public Object getObject(String identifier)
    {
        return RuntimeObjects.get(identifier);
    }
    
    public Object putObjectIfAbsent(String identifier, Object obj)
    {
        return RuntimeObjects.putIfAbsent(identifier, obj);
    }
    
    public Object deleteObject(String identifier)
    {
        return RuntimeObjects.remove(identifier);
    }
    
    public void overwriteObject(String identifier, Object obj)
    {
        RuntimeObjects.put(identifier, obj);
    }
}
