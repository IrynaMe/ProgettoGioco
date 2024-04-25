import java.util.Random;
import java.util.Scanner;

public class Licantropo implements Mostro, Umano {
    Random r = new Random();
    private boolean isUomo;
    protected int forzaUmano, forzaMostro, forza;
    protected int vita;
    protected boolean isGiocatore;

    public Licantropo(boolean luna) {
        this.isUomo = !luna;
        this.vita = 80;
        if (luna) {
            this.forzaMostro = 10;
            this.forzaUmano = 5;
        } else {
            this.forzaUmano = 10;
            this.forzaMostro = 5;
        }
        forza = forzaUmano + forzaMostro;

    }

    public boolean isUomo() {
        return isUomo;
    }

    @Override
    public int getVita() {
        return vita;
    }

    public int getForza() {
        return forza;
    }

    public int getForzaUmano() {
        return forzaUmano;
    }

    public int getForzaMostro() {
        return forzaMostro;
    }

    public void setGiocatore(boolean giocatore) {
        isGiocatore = giocatore;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    @Override
    public void attacca(Personaggio p) {
        if (isUomo && forzaUmano <= 0 && forzaMostro > 0 || !isUomo && forzaMostro <= 0 && forzaUmano > 0) {
            trasformare();
        }
        if (isUomo && vita > 0) {
            combatti(p);
        } else if (!isUomo && vita > 0) {
            azzanna(p);
        } else {
            forzaUmano = 0;
            forzaMostro = 0;
            System.out.println("Il Licantropo non può attacare");
        }
    }

    @Override
    public void azzanna(Personaggio p) {
        System.out.println("Il Licantropo azzanna!");
        p.riceviDanno(getForzaMostro());
        forzaMostro += 5;
    }

    public void combatti(Personaggio p) {
        if (forzaUmano >= 3) {
            System.out.println("Il Licantropo combatte!");
            p.riceviDanno(getForzaUmano());
            forzaUmano -= 3;
            vita += 5;
        } else {
            forzaUmano = 0;
        }
    }

    public void trasformare() {
        if (isUomo) {
            isUomo = false;
            System.out.println("*********Il Licantropo si è trasformato in Mostro************");
            stampaDati();
        } else if (!isUomo) {
            isUomo = true;
            System.out.println("*************Il Licantropo si è trasformato in Uomo*************");
            stampaDati();
        }
    }

    @Override
    public void riceviDanno(int danno) {
        if (isUomo && forzaUmano <= 0 && forzaMostro > 0 || !isUomo && forzaMostro <= 0 && forzaUmano > 0) {
            trasformare();
        }
        if (vita > 0) {
            vita -= danno;
            if (!isUomo) forzaUmano -= danno / 3;
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
                    System.out.println("Hai scappato!");
                } else {
                    System.out.println("Non hai riuscito a scapare");
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
        if (isUomo) {
            System.out.println("Licantropo ha forza umana: " + forzaUmano + " | vita: " + vita);
        } else {
            System.out.println("Licantropo ha forza di mostro: " + forzaMostro + " | vita: " + vita);
        }
    }
}//