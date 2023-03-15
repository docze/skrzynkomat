package skrzynkomat;

import java.util.ArrayList;
import java.util.List;

class Skrzynka {
    private int numer;
    private List<Paczka> paczki = new ArrayList<Paczka>();
    private boolean zamknieta = false;

    public Skrzynka(int numer) {
        this.numer = numer;
    }

    public int getNumer() {
        return numer;
    }

    public List<Paczka> getPaczki() {
        return paczki;
    }

    public boolean isZamknieta() {
        return zamknieta;
    }

    public void setZamknieta(boolean zamknieta) {
        this.zamknieta = zamknieta;
    }

    public boolean isOtwarta() {
        return paczki.size() > 0 && !zamknieta;
    }

    public void dodajPaczke(Paczka paczka) {
        paczki.add(paczka);
        paczka.setSkrzynka(this);
    }
}