import java.util.Random;
import java.util.Scanner;

public class Vampiro implements Mostro {
    Random r = new Random();
    protected int forza;
    protected int vita;
    protected boolean isGiocatore;

    public Vampiro() {
        this.forza = 5;
        this.vita = 70;
    }

    public int getForza() {
        return forza;
    }

    @Override
    public int getVita() {
        return vita;
    }

    @Override
    public void setVita(int vita) {

        this.vita = vita;
    }

    public void setGiocatore(boolean giocatore) {
        isGiocatore = giocatore;
    }


    @Override
    public void attacca(Personaggio p) {
        if (vita > 0) {
            azzanna(p);
        } else {
            vita = 0;
            System.out.println("Vampiro non può attacare");
        }
    }

    public void azzanna(Personaggio p) {
        System.out.println("Il vampiro azzanna!");
        p.riceviDanno(getForza());
        forza += 10;
    }

    @Override
    public void riceviDanno(int danno) {
        if (vita > 0) {
            vita -= danno;
            forza -= danno / 3;
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
                    System.out.println("Non sei riuscito a scapare");
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

    public void stampaDati() {
        System.out.println("Vampiro ha forza: " + forza + " | vita: " + vita);
    }
}//
