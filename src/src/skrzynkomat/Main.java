package skrzynkomat;

class Main {
    public static void main(String[] args) {
        Skrzynkomat skrzynkomat = new Skrzynkomat();
        // rejestracja odbiorców
        Odbiorca odbiorca1 = new Odbiorca("123456789");
        Odbiorca odbiorca2 = new Odbiorca("987654321");
        skrzynkomat.zarejestrujOdbiorce(odbiorca1);
        skrzynkomat.zarejestrujOdbiorce(odbiorca2);

        // umieszczenie paczek w skrzynce
        Paczka paczka1 = new Paczka("abc123", odbiorca1, 48 * 60 * 60 * 1000); // 48 godzin
        Paczka paczka2 = new Paczka("def456", odbiorca1, 24 * 60 * 60 * 1000); // 24 godziny
        Paczka paczka3 = new Paczka("ghi789", odbiorca2, 48 * 60 * 60 * 1000); // 48 godzin
        skrzynkomat.umiescPaczkeWPierwszejWolnejSkrzynce(paczka1);
        skrzynkomat.umiescPaczkeWPierwszejWolnejSkrzynce(paczka2);
        skrzynkomat.umiescPaczkeWPierwszejWolnejSkrzynce(paczka3);

        // odbiór paczek
        Paczka paczka = skrzynkomat.znajdzPaczkePoKodzie("abc123", "123456789");
        if (paczka != null) {
            skrzynkomat.odbierzPaczke(paczka, "123456789");
        }

        // przedłużenie czasu odbioru
        paczka = skrzynkomat.znajdzPaczkePoKodzie("def456", "123456789");
        if (paczka != null) {
            skrzynkomat.przedluzOdbior(paczka, "123456789");
        }
    }
}