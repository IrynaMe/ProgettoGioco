
public interface Personaggio {
    void stampaDati();

    int getForza();

    int getVita();

    void attacca(Personaggio p);

    void riceviDanno(int danno);

    void setGiocatore(boolean giocator);

    boolean scappare(); //torna random risultato: scappato/non scappato


}

