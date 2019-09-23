package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    public static String trenutniJezik;
    
    private static final String CONFIG_FILE_PATH = "resources" + File.separator + "config.properties";

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Properties properties = new Properties();
    	properties.load(new FileInputStream(new File(CONFIG_FILE_PATH)));
    	trenutniJezik = properties.getProperty("TRENUTNI_JEZIK_I_PISMO").split(",")[0];
    	
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPrijavu.fxml"));
        primaryStage.setTitle("eCensus");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}