package skrzynkomat;

class Paczka {
    private Skrzynka skrzynka;
    private Odbiorca odbiorca;
    private String kodOdbioru;
    private boolean odebrana = false;
    private boolean potwierdzona = false;
    private boolean przedluzona = false;
    private long czasDoOdbioru;

    public Paczka(String kodOdbioru, Odbiorca odbiorca, long czasDoOdbioru) {
        this.kodOdbioru = kodOdbioru;
        this.odbiorca = odbiorca;
        this.czasDoOdbioru = czasDoOdbioru;
    }

    public void setOdbiorca(Odbiorca odbiorca) {
        this.odbiorca = odbiorca;
    }

    public void setKodOdbioru(String kodOdbioru) {
        this.kodOdbioru = kodOdbioru;
    }

    public Skrzynka getSkrzynka() {
        return skrzynka;
    }

    public void setSkrzynka(Skrzynka skrzynka) {
        this.skrzynka = skrzynka;
    }

    public Odbiorca getOdbiorca() {
        return odbiorca;
    }

    public String getKodOdbioru() {
        return kodOdbioru;
    }

    public boolean isOdebrana() {
        return odebrana;
    }

    public void setOdebrana(boolean odebrana) {
        this.odebrana = odebrana;
    }

    public boolean isPotwierdzona() {
        return potwierdzona;
    }

    public void setPotwierdzona(boolean potwierdzona) {
        this.potwierdzona = potwierdzona;
    }

    public boolean isPrzedluzona() {
        return przedluzona;
    }

    public void setPrzedluzona(boolean przedluzona) {
        this.przedluzona = przedluzona;
    }

    public long getCzasDoOdbioru() {
        return czasDoOdbioru;
    }

    public void setCzasDoOdbioru(long czasDoOdbioru) {
        this.czasDoOdbioru = czasDoOdbioru;
    }
}