public class Licantropo implements Mostro, Umano {
    private boolean isUomo;
    protected int forzaUmano, forzaMostro;
    protected int vita;

    public Licantropo(boolean luna) {
        isUomo = !luna;
        if (luna) {
            forzaMostro = 15;
            forzaUmano = 0;
            vita = 70;
        } else {
            forzaUmano = 10;
            forzaMostro = 0;
            vita = 100;
        }

    }

    public String getForza() {
        return
                "Forza rimanente come umano:" + forzaUmano +
                        "Forza rimanente come mostro" + forzaMostro;
    }

    @Override
    public int getVita() {
        return vita;
    }

    @Override
    public void attacca(Personaggio p) {
        if (isUomo) {
           combatti();
        } else {
            azzanna();
        }
    }

    public void azzanna() {
        if (!isUomo) forzaMostro = forzaMostro - 2;
    }

    public void combatti() {
        if (isUomo) forzaUmano = forzaUmano - 3;
    }
}