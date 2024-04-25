import java.util.*;

public class Utilita {
    Random r = new Random();
    private boolean isAttaccante = r.nextInt(2) == 0;
    List<Personaggio> personaggi = new ArrayList<>(3);
    Personaggio giocatore;
    Personaggio avversario1;
    Personaggio avversario2;

    public Personaggio getPersonaggioPrincipale() {
        return giocatore;
    }

    public Personaggio getAvversario1() {
        return avversario1;
    }

    public Personaggio getAvversario2() {
        return avversario2;
    }

    public void creaPersonaggi() {
        personaggi.add(new Eroe());
        personaggi.add(new Vampiro());
        personaggi.add(new Licantropo(r.nextInt(2) == 0));

        Collections.shuffle(personaggi, r);
        giocatore = personaggi.get(0);
        avversario1 = personaggi.get(1);
        avversario2 = personaggi.get(2);
        giocatore.setGiocatore(true);
        avversario1.setGiocatore(false);
        avversario2.setGiocatore(false);
    }

    public void stampaIntroduzione() {
        System.out.println("Il tuo personaggio è: " + giocatore.getClass().getName());
        if (giocatore instanceof Licantropo && ((Licantropo) giocatore).isUomo()) {
            System.out.println("Licantropo è in forma di Uomo");
        } else {
            System.out.println("Licantropo è in forma di Mostro");
        }
        System.out.println("Per vincere gioco devi combatere con: ");
        System.out.println("1." + avversario1.getClass().getName());
        System.out.println("2." + avversario2.getClass().getName());
    }

    //schelta casuale chi attacca il primo, cambio turni sucessivamente
    public void comincaAttaco(Personaggio avversario) {
        if (isAttaccante) {
            giocatore.attacca(avversario);
        } else {
            avversario.attacca(giocatore);
        }
        isAttaccante = !isAttaccante;
    }


    //return 1 se vinto 0 se perso
    public int combattere(Personaggio avversario) {
        int result = -1;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Stato del tuo personaggio: ");
            giocatore.stampaDati();
            System.out.println("Stato del tuo avversario:");
            avversario.stampaDati();
            System.out.println("-----------------------");
            if (isAttaccante) {
                System.out.println("Tu sei per attacare");
            } else {
                System.out.println("Tu sei per defenderti");
            }
            System.out.println("Cosa vuoi fare?");
            System.out.println("1. Combatere");
            System.out.println("2. Prova a fuggire");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                  //  System.out.println("Il combattimento continua!");
                    comincaAttaco(avversario);
                    break;
                case 2:
                    if (giocatore.scappare()) {
                        System.out.println("Sei riuscito a fuggire!");
                        return 1; // giocatore scappato, avversario ottiene vita=0->è conta come sconfitto
                    } else {
                        System.out.println("Non sei riuscito a fuggire!");
                        break; // continua la battaglia
                    }
                default:
                    System.out.println("Scelta non valida!");
                    break; // continua la battaglia
            }
        } while (giocatore.getVita() > 0 && giocatore.getForza() > 0 && avversario.getVita() > 0 && avversario.getForza() > 0);

        if (avversario.getVita() <= 0 || avversario.getForza() <= 0) {
            System.out.println("Hai sconfitto l'avversario " + avversario.getClass().getName());
            result = 1;
        }

        if (giocatore.getVita() <= 0) {
            System.out.println("Sei sconfitto...");
            result = 0;
        }

        return result;
    }

//metodo alternativo -gioco in automatico con input solo per sfuggire
/*    public int combatere(Personaggio avversario) {
        int result = -1;

        do {
            comincaAttaco(avversario);
            giocatore.stampaDati();
            avversario.stampaDati();
        } while (giocatore.getVita() > 0 && giocatore.getForza() > 0 && avversario.getVita() > 0 && avversario.getForza() > 0);

        if (giocatore.getForza() <= 0 && giocatore.getVita() > 0) {
            boolean isScappato = giocatore.scappare();
            if (isScappato == true) {
                result = 1;

            } else {
                combatere(avversario);
            }
        }
        if (avversario.getVita() <= 0 || avversario.getForza() <= 0) {
            System.out.println("Hai sconfitto l'avversario " + avversario.getClass().getName());
            result = 1;
        }
        if (giocatore.getVita() <= 0) {
            System.out.println("Sei sconfitto...");
            result = 0;
        }
        return result;
    }*/

}//
