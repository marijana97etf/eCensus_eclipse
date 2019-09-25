package model.table_input_models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class PopisniKrugInputModel {
    protected SimpleIntegerProperty id;
    protected SimpleStringProperty opstina;
    protected SimpleStringProperty grad;
    protected PopisniKrug popisniKrug;

    public PopisniKrugInputModel(PopisniKrug popisniKrug) {
        id = new SimpleIntegerProperty(0);
        opstina = new SimpleStringProperty(popisniKrug.getOpstina());
        grad = new SimpleStringProperty(popisniKrug.getGrad());
        this.popisniKrug = popisniKrug;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getOpstina() {
        return opstina.get();
    }

    public SimpleStringProperty opstinaProperty() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina.set(opstina);
    }

    public String getGrad() {
        return grad.get();
    }

    public SimpleStringProperty gradProperty() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad.set(grad);
    }

    public PopisniKrug getPopisniKrug() {
        return popisniKrug;
    }

    public void setPopisniKrug(PopisniKrug popisniKrug) {
        this.popisniKrug = popisniKrug;
    }
}
