public class Eroe implements Umano {
    protected int forza;
    protected int vita;
    public Eroe() {
        forza = 10;
        vita=40;
    }

    public void combatti() {
        if (vita > 0) {
            vita -= 7;
            System.out.println("L'eroe attacca!");
        } else{
            System.out.println("Non posso attacare");
        }
        // forza = forza - 2;
    }


    public String getForza() {
        return "Forza rimanente come eroe:" + forza;
    }

    @Override
    public int getVita() {
        return vita;
    }

    @Override
    public void attacca(Personaggio p) {
    }
}