package kontroleri;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class KontrolerFormeZaPopunjavanjeSpiskaLica {
    @FXML
    private GridPane gridPane;
    private int row = 3;

    public void dodajVrstu() {
        TextField imeTextField = new TextField();
        TextField prezimeTextField = new TextField();
        TextField JMBGTextField = new TextField();
        ComboBox<String> odnosPremaNosiocuDomacinstvaComboBox = new ComboBox<>();
        odnosPremaNosiocuDomacinstvaComboBox.setMinWidth(200);
        odnosPremaNosiocuDomacinstvaComboBox.setPrefWidth(200);
        odnosPremaNosiocuDomacinstvaComboBox.setMaxWidth(200);
        odnosPremaNosiocuDomacinstvaComboBox.setPromptText("Odaberi tip odnosa...");
        TextField redniBrojPorodiceTextField = new TextField();
        ComboBox<String> polozajUPorodiciComboBox = new ComboBox<>();
        polozajUPorodiciComboBox.setMinWidth(200);
        polozajUPorodiciComboBox.setPrefWidth(200);
        polozajUPorodiciComboBox.setMaxWidth(200);
        polozajUPorodiciComboBox.setPromptText("Odaberi položaj...");

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(30);
        rc.setMaxHeight(30);
        rc.setPrefHeight(30);
        gridPane.getRowConstraints().add(rc);

        gridPane.add(imeTextField,0,row);
        gridPane.add(prezimeTextField, 1, row);
        gridPane.add(JMBGTextField, 2, row);
        gridPane.add(odnosPremaNosiocuDomacinstvaComboBox, 3, row);
        gridPane.add(redniBrojPorodiceTextField, 4, row);
        gridPane.add(polozajUPorodiciComboBox, 5, row);

        ++row;
    }
}
