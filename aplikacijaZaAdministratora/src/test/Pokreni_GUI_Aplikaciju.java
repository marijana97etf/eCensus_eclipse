package test;

import controller.kontroler_za_cuvanje_naloga.CuvanjeNalogaNaFS;
import controller.kontroler_za_cuvanje_naloga.KontrolerZaCuvanjeNaloga;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.SkladisteNaloga;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

import java.awt.*;

public class Pokreni_GUI_Aplikaciju extends Application {

    private static Stage stage;

    private static KontrolerZaCuvanjeNaloga kontrolerZaCuvanjeNaloga;

    static {
        // Deserijalizacija skladista naloga
        kontrolerZaCuvanjeNaloga = new KontrolerZaCuvanjeNaloga(new CuvanjeNalogaNaFS());
        kontrolerZaCuvanjeNaloga.ucitajNaloge();

//        kontrolerZaCuvanjeNaloga.setSkladisteNaloga(new SkladisteNaloga(new AdministratorAgencije(
//                "admin",
//                "admin",
//                "admin",
//                "admin",
//                "admin",
//                JEZIK.SRPSKI,
//                PISMO.LATINICA
//        )));
    }

    public static KontrolerZaCuvanjeNaloga getKontrolerZaCuvanjeNaloga() {
        return kontrolerZaCuvanjeNaloga;
    }


    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"));
        primaryStage.setTitle("eCensus - AdminMode");
        Pair<Integer,Integer> dimension = calculateDimensions();
        primaryStage.setScene(new Scene(root, dimension.getKey(), dimension.getValue()));
        primaryStage.show();

        primaryStage.setOnCloseRequest(we->
        {
            kontrolerZaCuvanjeNaloga.sacuvajNaloge(kontrolerZaCuvanjeNaloga.getSkladisteNaloga());
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
}
