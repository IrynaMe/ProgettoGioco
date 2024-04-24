public class Vampiro implements Mostro {
    protected int forza;
    protected int vite;
    protected int protezione;//

    public Vampiro() {
        forza = 10;
        vite = 3;
        protezione=5;
    }

    public void azzanna() {
        //prende forza quando azzanna
        //quando forza finisce(=0)-> nuova vita + vita diminuisce
        if (forza> 0) {
            forza += 10;
            System.out.println("Il vampiro attacca!");
        } else{
            nuovaVita();
            System.out.println("Non posso attacare");
        }
    }

    public String stampaForza() {
        return "Forza rimanente come vampiro: " + forza;
    }
    public int getForza(){
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
        azzanna();
    }

    @Override
    public void ricevidanno(int danno) {
        forza-=danno;
        if (forza<0){
           nuovaVita();
        }
    }

    @Override
    public void nuovaVita() {
        vite-=1;
        forza=10;
        System.out.println("le vite rimangono: "+vite);
    }


}
