import core.Configs;
import core.ScreenResolution;
import core.Utilities;
import fileOperators.MyFileUtils;
import javafx.application.Application;
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

public class Main extends Application {
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Variables
    //
    private static Stage window;
    public static ISubjectRepository subjectRepository;
    public static IImageRepository imageRepository;
    
    private enum STARTUP_CONFIGURATION {VALID, INVALID};
    
    
    
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Main method
    //
    public static void main(String[] args) throws Exception {
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
            
            Main.window = primaryStage;
            //window.getIcons().add(new Image(UtilsFX.getFileStream("icons/book-double.png")));
        
            BorderPane bPane = new TrackerScene(window, subjectRepository, imageRepository);
            Scene scene = new Scene(bPane, ScreenResolution.SCALED_WIDTH, ScreenResolution.SCALED_HEIGHT);
        
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
            window.setScene(scene);
            window.show();
        
            Utilities.gc();
        }
        catch(Exception e) {
            System.out.println( e.getLocalizedMessage() );
            e.printStackTrace();
        }
    }
    
    
    
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    // Private methods
    //
}
