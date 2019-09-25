package test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import util.ConnectionLogger;
import util.PromjenaPisma;

public class Aplikacija extends Application {

    private static Stage stage;
    
    public static final String CONFIG_FILE = "resources" + File.separator + "config.properties";
    public static final String LOG_FILE = "resources" + File.separator + "error.log";
    
    public static ConnectionLogger connLogger;

    static {
    	Logger logger = Logger.getLogger("Main");
    	try {
			logger.addHandler(new FileHandler(LOG_FILE, true));
			logger.setLevel(Level.ALL);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	connLogger = new ConnectionLogger(logger);

    	getProperty();
    }

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"));
        primaryStage.setTitle("eCensus - AdminMode");
        Pair<Integer,Integer> dimension = calculateDimensions();
        primaryStage.setScene(new Scene(root, dimension.getKey(), dimension.getValue()));
        primaryStage.show();

        primaryStage.setOnCloseRequest(we->
        {
            Platform.exit();
            System.exit(0);
        });
    }

    public static Pair<Integer,Integer> calculateDimensions()
    {
        int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
        System.out.println(dpi);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        double sceneHeight = 100*height/(1.4*dpi);
        double sceneWidth = 100*width/(1.7*dpi);

        if(dpi>=110 && dpi<140)
        {
            sceneHeight= 100*height/(1.2*dpi);
            sceneWidth=100*width/(1.4*dpi);
        }
        else if(dpi>=140 && dpi<160)
        {
            sceneHeight= 110*height/dpi;
            sceneWidth=100*width/(1.1*dpi);
        }
        else if(dpi>=160)
        {
            sceneHeight= 140*height/dpi;
            sceneWidth=120*width/dpi;
        }

        if(Math.abs(width /height-4.0/3)<0.01)
        {
            sceneHeight = 100*height/(1.4*dpi);
            sceneWidth = 100*width/(1.3*dpi);
        }

        return new Pair<>((int)sceneWidth,(int)sceneHeight);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static String getProperty() {
        String jezik = "";
        try {
            Properties properties = new Properties();
            FileInputStream in = new FileInputStream(new File(CONFIG_FILE));
            properties.load(in);
            jezik = properties.getProperty("TRENUTNI_JEZIK_I_PISMO");
            in.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return jezik;
    }

    public static void setProperty(String property) throws IOException{

        Properties properties = new Properties();
        FileInputStream in = new FileInputStream(new File(CONFIG_FILE));
        properties.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream(CONFIG_FILE);
        properties.setProperty("TRENUTNI_JEZIK_I_PISMO", property);
        properties.store(out, null);
    }
}
