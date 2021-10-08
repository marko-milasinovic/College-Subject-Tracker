import core.Configs;
import core.ScreenResolution;
import core.Utilities;
import fileOperators.MyFileUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import oshi.SystemInfo;
import repositories.image.IImageRepository;
import repositories.image.InMemoryImageRepository;
import repositories.subject.ISubjectRepository;
import repositories.subject.InMemorySubjectRepository;
import scenes.TrackerScene;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Variables
    //
    private static Stage primaryStage;
    private static ISubjectRepository subjectRepository;
    private static IImageRepository imageRepository;
    
    private enum STARTUP_CONFIGURATION {VALID, INVALID};
    
    private static ThreadPoolExecutor executor = null;
    
    
    
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Main method
    //
    public static void main(String[] args) throws Exception {
        Thread.currentThread().setName("College Subject Tracker");
        
        if(getStartupConfiguration() == STARTUP_CONFIGURATION.INVALID){
            System.out.println("Invalid configuration, exiting program.");
            return;
        };
        
        try {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Configs.EXECUTOR_QUEUE_DEPTH * 2);
        }catch (Exception exception) {
            executor.awaitTermination(10, TimeUnit.SECONDS);
            
            System.out.println("Terminated - College Subject Tracker treads");
        }
        
        subjectRepository = new InMemorySubjectRepository();
        imageRepository = new InMemoryImageRepository();
        
        fileOperators.FileUtils.loadFiles(subjectRepository);
        
        launch(args);
    }
    
    
    
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Main Scene constructor
    //
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            
            this.primaryStage = primaryStage;
            primaryStage.getIcons().add(imageRepository.getImage("DOUBLE_BOOK"));
            
            BorderPane borderPane = new TrackerScene(primaryStage, subjectRepository, imageRepository, executor);
            Scene primaryScene = new Scene(borderPane, ScreenResolution.SCALED_WIDTH, ScreenResolution.SCALED_HEIGHT);
            
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            primaryStage.setScene(primaryScene);
            primaryStage.show();
            
            Utilities.gc();
        }
        catch(Exception e) {
            System.out.println( e.getLocalizedMessage() );
            e.printStackTrace();
        }
        
        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
            executor.shutdown();
            Platform.exit();
            System.exit(0);
        });
    }
    
    
    
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Private methods
    //
    private static final STARTUP_CONFIGURATION getStartupConfiguration()
    {
        if(isUnsupportedSystem() == true){
            System.out.println("Unsupported system detected!");
            return STARTUP_CONFIGURATION.INVALID;
        }
        
        if(Configs.initialiseConfigs() == false){
            System.out.println("Configuration couldn't be loaded!");
            return STARTUP_CONFIGURATION.INVALID;
        }
        
        if(Configs.initialiseConfigs() == false){
            System.out.println("Configuration couldn't be loaded!");
            return STARTUP_CONFIGURATION.INVALID;
        }
        
        if(MyFileUtils.createFileIfNotExists(Configs.FULL_SAVE_FILE_JAR_PATH) == false){
            System.out.println("File location couldn't be loaded!");
            return STARTUP_CONFIGURATION.INVALID;
        }
        
        if(ScreenResolution.initialiseScreenResolution() == false){
            System.out.println("Screen resolution couldnt be loaded, starting with defaults!");
        }
        
        return STARTUP_CONFIGURATION.VALID;
    }
    
    private static final boolean isUnsupportedSystem(){
        SystemInfo si = new SystemInfo();
        if(si.getOperatingSystem().getFamily() == "Windows")
        {
            Configs.USER_DIRECTORY_PATH = FileUtils.getUserDirectoryPath();
            if(!Configs.USER_DIRECTORY_PATH.isBlank()){
                return false;
            }
        }
        else{
            //linux configuration code
        }
        return true;
    }
}
