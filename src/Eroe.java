import java.util.Random;

public class Eroe implements Umano {
    Random x=new Random();
    protected int forza;
    protected int vite;

    public Eroe() {
        forza = 10;
        vite = 4;
    }

    public void combatti() {
        if (forza > 0) {
            forza-=x.nextInt(6)+5;
            System.out.println("L'eroe attacca!");
        } else {
            vite-=1;
            forza=10;
            System.out.println("Non posso attacare");
        }
    }

    @Override
    public String stampaForza() {
        return "Forza rimanente come eroe:" + forza;
    }

    public int getForza() {
       return forza;
    }

    @Override
    public int getVita() {
        return vite;
    }

    @Override
    public void attacca(Personaggio p) {
        if (vite>0){
            p.ricevidanno(getForza());
        }
        combatti();
    }


    @Override
    public void ricevidanno(int danno) {
        forza -= danno;
        if (forza < 0) {
           nuovaVita();
        }
    }

    @Override
    public void nuovaVita() {
        vite-=1;
        forza=10;
        System.out.println("le vite rimangono: "+vite);
    }

}//