import java.util.Random;
import java.util.Scanner;

public class Eroe implements Umano {
    Random r = new Random();
    protected int forza;
    protected int vita;
    protected boolean isGiocatore;

    public Eroe() {
        this.forza = 10;
        this.vita = 50;
    }

    public int getForza() {
        return forza;
    }

    @Override
    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public void setGiocatore(boolean giocatore) {
        isGiocatore = giocatore;
    }

    @Override
    public void attacca(Personaggio p) {
        if (vita > 0) {
            combatti(p);
        } else {
            System.out.println("L'eroe non puo attacare");
            forza = 0;
        }

    }

    @Override
    public void combatti(Personaggio p) {
        if (forza >= 2) {
            System.out.println("L'eroe combatte!");
            p.riceviDanno(getForza());
            forza -= 2;
            vita += 5;
        } else {
            forza = 0;
        }
    }

    @Override
    public void riceviDanno(int danno) {
        if (vita > 0) {
            vita -= danno;
        } else {
            vita = 0;
        }
    }

    @Override
    public boolean scappare() {
        boolean isScappato = false;
        System.out.println("Hai opportunità 50%/50% di scappare. \nIn entrambi i casi perdi 10 punti di vita\n Se scappi con successo ottieni 10 punti di forza");
        System.out.println("Inserisci la scelta: 1 -> Scappare | 2 -> Non scappare");
        Scanner sc = new Scanner(System.in);
        int scelta = sc.nextInt();
        switch (scelta) {
            case 1:
                vita -= 10;
                isScappato = r.nextInt(2) == 0;
                if (isScappato) {
                    forza += 10;
                    System.out.println("Sei scappato!");
                } else {
                    System.out.println("Non sei riuscito a scappare");
                }
                break;
            case 2:
                isScappato = false;
                break;
            default:
                System.out.println("Scelta errata");
                break;
        }
        return isScappato;
    }

    @Override
    public void stampaDati() {
        System.out.println("Eroe ha forza: " + forza + " | vita: " + vita);
    }
}//