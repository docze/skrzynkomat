package skrzynkomat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Skrzynkomat {
    private Map<Integer, Skrzynka> skrzynki = new HashMap<Integer, Skrzynka>();
    private Map<String, Odbiorca> odbiorcy = new HashMap<String, Odbiorca>();
    private Timer timer = new Timer();

    public void umiescPaczke(int numerSkrzynki, Paczka paczka) {
        Skrzynka skrzynka = skrzynki.get(numerSkrzynki);
        if (skrzynka == null) {
            skrzynka = new Skrzynka(numerSkrzynki);
            skrzynki.put(numerSkrzynki, skrzynka);
        }
        skrzynka.dodajPaczke(paczka);
        String kodOdbioru = generujKodOdbioru();
        String numerTelefonu = paczka.getOdbiorca().getNumerTelefonu();
        String wiadomosc = "Twoj kod odbioru to: " + kodOdbioru + ". Paczka czeka na odbior przez 48h.";
        wyslijWiadomosc(numerTelefonu, wiadomosc);
        paczka.setKodOdbioru(kodOdbioru);
    }

    private String generujKodOdbioru() {
        Random rand = new Random();
        String kodOdbioru = "";
        for (int i = 0; i < 6; i++) {
            kodOdbioru += rand.nextInt(10);
        }
        return kodOdbioru;
    }

    public void odbierzPaczke(String kodOdbioru, String numerTelefonu) {
        Odbiorca odbiorca = odbiorcy.get(numerTelefonu);
        if (odbiorca != null) {
            Paczka paczka = odbiorca.znajdzPaczkePoKodzie(kodOdbioru);
            if (paczka != null) {
                paczka.setOdebrana(true);
                zamknijSkrzynke(paczka.getSkrzynka().getNumer());
            }
        }
    }

    private void zamknijSkrzynke(int numerSkrzynki) {
        Skrzynka skrzynka = skrzynki.get(numerSkrzynki);
        if (skrzynka != null && !skrzynka.isZamknieta()) {
            skrzynka.setZamknieta(true);
            uruchomTimer(skrzynka);
        }
    }

    private void uruchomTimer(Skrzynka skrzynka) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!skrzynka.isOtwarta()) {
                    String numerTelefonu = skrzynka.getPaczki().get(0).getOdbiorca().getNumerTelefonu();
                    String wiadomosc = "Paczka zostala automatycznie potwierdzona po 60 sekundach od zamknięcia skrzynki.";
                    wyslijWiadomosc(numerTelefonu, wiadomosc);
                    potwierdzOdbior(skrzynka.getPaczki(), numerTelefonu);
                }
            }
        };
        timer.schedule(timerTask, 60000);
    }
    public void potwierdzOdbior(List<Paczka> paczki, String numerTelefonu) {
        Odbiorca odbiorca = odbiorcy.get(numerTelefonu);
        if (odbiorca != null) {
            for (Paczka paczka : paczki) {
                if (paczka.isOdebrana() && !paczka.isPotwierdzona()) {
                    paczka.setPotwierdzona(true);
                    odbiorca.usunPaczke(paczka);
                }
            }
        }
    }

    public void przedluzPaczke(String kodOdbioru, String numerTelefonu) {
        Odbiorca odbiorca = odbiorcy.get(numerTelefonu);
        if (odbiorca != null) {
            Paczka paczka = odbiorca.znajdzPaczkePoKodzie(kodOdbioru);
            if (paczka != null && !paczka.isPrzedluzona() && paczka.getCzasDoOdbioru() > 12 * 60 * 60 * 1000) {
                paczka.setCzasDoOdbioru(paczka.getCzasDoOdbioru() + 48 * 60 * 60 * 1000);
                paczka.setPrzedluzona(true);
            }
        }
    }

    public void zarejestrujOdbiorce(Odbiorca odbiorca) {
        odbiorcy.put(odbiorca.getNumerTelefonu(), odbiorca);
    }

    private void wyslijWiadomosc(String numerTelefonu, String wiadomosc) {
        // kod do wysyłania wiadomości na telefon
    }

}

