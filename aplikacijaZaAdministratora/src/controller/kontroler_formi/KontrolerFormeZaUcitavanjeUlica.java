package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import test.Aplikacija;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class KontrolerFormeZaUcitavanjeUlica implements Initializable {
	public TextField imeTextField;
	public GridPane gridPane;
	private Map<Integer, TextField> poljaZaPopunjavanje;
    public static List<String> listaUlica;
    private int row = 3;

    @FXML
    private void dodajUlicu() {
        TextField imeTextField2 = new TextField();
		imeTextField2.setStyle("-fx-border-color: TRANSPARENT");
        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(30);
        rc.setMaxHeight(30);
        rc.setPrefHeight(30);
        imeTextField2.setPrefWidth(535);
        imeTextField2.setPrefHeight(25.0);
        GridPane.setColumnSpan(imeTextField2, 4);
        GridPane.setRowIndex(imeTextField2, 2);
        GridPane.setMargin(imeTextField2, new Insets(10, 50.0, 0, 20.0));
        gridPane.getRowConstraints().add(rc);
        gridPane.add(imeTextField2,0,row);
        poljaZaPopunjavanje.put(poljaZaPopunjavanje.keySet().size(), imeTextField2);
        ++row;
    }

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		poljaZaPopunjavanje = new HashMap<>();
		poljaZaPopunjavanje.put(0, imeTextField);
	}

	public void sacuvajListuUlica(ActionEvent actionEvent) {
		listaUlica = poljaZaPopunjavanje.values().stream().filter(e -> !e.getText().isBlank()).map(TextField::getText).collect(Collectors.toList());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da izađete?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        KontrolerFormeZaDodavanjePopisnihKrugova.newStage.close();
    }
}
