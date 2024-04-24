public class Licantropo implements Mostro, Umano {
    private boolean isUomo;
    protected int forzaUmano, forzaMostro;
    protected int vite;

    public Licantropo(boolean luna) {
        isUomo = !luna;
        if (luna) {
            forzaMostro = 15;
            forzaUmano = 0;
            vite = 3;
        } else {
            forzaUmano = 10;
            forzaMostro = 0;
            vite = 4;
        }

    }

    @Override
    public String stampaForza() {
        return
                "Forza rimanente come umano:" + forzaUmano +
                        "Forza rimanente come mostro" + forzaMostro;
    }

    public int getForza() {
        if (isUomo) {
            return forzaUmano;
        } else {
            return forzaMostro;
        }

    }

    @Override
    public int getVita() {
        return vite;
    }

    @Override
    public void attacca(Personaggio p) {
        p.ricevidanno(12);
        if (isUomo) {
            combatti();
        } else {
            azzanna();
        }

    }

    @Override
    public void ricevidanno(int danno) {
        vite -= danno;
        if (vite< 0) {
            vite = 0;
        }
    }

    @Override
    public void nuovaVita() {
        vite-=1;
        if (isUomo) {
            forzaUmano=10;
        } else {
            forzaMostro=15;
        }
        System.out.println("le vite rimangono: "+vite);
    }

    public void azzanna() {
        if (!isUomo) forzaMostro = forzaMostro - 2;
    }

    public void combatti() {
        if (isUomo) forzaUmano = forzaUmano - 3;
    }
}