public class Main {
    public static void main(String[] args) {
        int conto = 0;
        Utilita u = new Utilita();
        u.creaPersonaggi();
        u.stampaIntroduzione();
        System.out.println("--------------------------");
        System.out.println("Il primo giro");
        System.out.println("Stai per combatere con " + u.getAvversario1().getClass().getName());
        System.out.println("--------------------------");

        conto += u.combattere(u.getAvversario1());
        if (conto != 0) {
            System.out.println("--------------------------");
            System.out.println("Il secondo giro");
            System.out.println("Stai per combatere con " + u.getAvversario2().getClass().getName());
            System.out.println("--------------------------");
            conto += u.combattere(u.getAvversario2());
        }
        if (conto == 2) {
            System.out.println("AUGURI! Hai vinto nel gioco!");
        } else {
            System.out.println("Hai perso....GAME OVER");
        }
    }


}

