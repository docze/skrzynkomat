package skrzynkomat;

import java.util.ArrayList;
import java.util.List;

class Odbiorca {
    private String numerTelefonu;
    private List<Paczka> paczki = new ArrayList<Paczka>();
    

    public Odbiorca(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public List<Paczka> getPaczki() {
        return paczki;
    }

    public void dodajPaczke(Paczka paczka) {
        paczki.add(paczka);
        paczka.setOdbiorca(this);
    }

    public void usunPaczke(Paczka paczka) {
        paczki.remove(paczka);
    }

    public Paczka znajdzPaczkePoKodzie(String kodOdbioru) {
        for (Paczka paczka : paczki) {
            if (paczka.getKodOdbioru().equals(kodOdbioru)) {
                return paczka;
            }
        }
        return null;
    }
}