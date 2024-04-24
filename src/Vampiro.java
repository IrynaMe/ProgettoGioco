public class Vampiro implements Mostro {
    protected int forza;
    protected int vita;

    public Vampiro() {
        forza = 15;
        vita = 70;
    }

    public void azzanna() {
        if (vita > 0) {
            vita -= 10;
            System.out.println("Il vampiro attacca!");
        } else{
            System.out.println("Non posso attacare");
        }
            // forza = forza - 2;
    }

    public String getForza() {
        return "Forza rimanente come vampiro:" + forza;
    }

    @Override
    public int getVita() {
       return vita;
    }

    @Override
    public void attacca(Personaggio p) {
        azzanna();
    }

}
