package model.pracenje_popisa.izvjestaji_o_popisivacu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CjelokupnaAktivnost extends Aktivnost{

    protected Set<DnevnaAktivnost> dnevneAktivnosti;

    public CjelokupnaAktivnost() {
        dnevneAktivnosti = new HashSet<>();
    }


    // SETTERS AND GETTERS

    public Set<DnevnaAktivnost> getDnevneAktivnosti() {
        return dnevneAktivnosti;
    }

    public void setDnevneAktivnosti(Set<DnevnaAktivnost> dnevneAktivnosti) {
        this.dnevneAktivnosti = dnevneAktivnosti;
    }

     public Integer getUkupanBrojPopisanihStanovnika()
    {
        //TODO
        return 0;
    }

    public Integer getUkupanBrojPopisanihDomacinstava()
    {
        //TODO
        return 0;
    }

    public Integer getBrojRadnihDana()
    {
        //TODO
        return 0;
    }

    public Collection<PopisniKrug> getObidjeniPopisniKrugovi()
    {
        //TODO
        return new ArrayList<PopisniKrug>();
    }

    public boolean dodajDnevnuAktivnosti(DnevnaAktivnost dnevnaAktivnost)
    {
        return dnevneAktivnosti.add(dnevnaAktivnost);
    }

}
