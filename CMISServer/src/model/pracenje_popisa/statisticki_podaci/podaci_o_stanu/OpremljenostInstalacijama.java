package model.pracenje_popisa.statisticki_podaci.podaci_o_stanu;

public class OpremljenostInstalacijama {

    private VODOVOD vodovod;
    private KANALIZACIJA kanalizacija;
    private PRIKLJUCENA_INSTALACIJA elektricnaEnergija;
    private PRIKLJUCENA_INSTALACIJA plin;
    private PRIKLJUCENA_INSTALACIJA centralnoGrijanje;

    public VODOVOD getVodovod() {
        return vodovod;
    }

    public void setVodovod(VODOVOD vodovod) {
        this.vodovod = vodovod;
    }

    public KANALIZACIJA getKanalizacija() {
        return kanalizacija;
    }

    public void setKanalizacija(KANALIZACIJA kanalizacija) {
        this.kanalizacija = kanalizacija;
    }

    public PRIKLJUCENA_INSTALACIJA getElektricnaEnergija() {
        return elektricnaEnergija;
    }

    public void setElektricnaEnergija(PRIKLJUCENA_INSTALACIJA elektricnaEnergija) {
        this.elektricnaEnergija = elektricnaEnergija;
    }

    public PRIKLJUCENA_INSTALACIJA getPlin() {
        return plin;
    }

    public void setPlin(PRIKLJUCENA_INSTALACIJA plin) {
        this.plin = plin;
    }

    public PRIKLJUCENA_INSTALACIJA getCentralnoGrijanje() {
        return centralnoGrijanje;
    }

    public void setCentralnoGrijanje(PRIKLJUCENA_INSTALACIJA centralnoGrijanje) {
        this.centralnoGrijanje = centralnoGrijanje;
    }
}
