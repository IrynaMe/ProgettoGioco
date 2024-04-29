import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Utilita {
    Random r = new Random();
    private boolean isAttaccante = r.nextInt(2) == 0;
    List<Personaggio> personaggi = new ArrayList<>(3);
    Personaggio giocatore;
    Personaggio avversario1;
    Personaggio avversario2;
    int durata = 0;
    LocalDateTime localDateTime;

    public Personaggio getAvversario1() {
        return avversario1;
    }

    public Personaggio getAvversario2() {
        return avversario2;
    }

    public int moonPhase(int year, int month, int day) {
    /*k
      Calculates the moon phase (0-7), accurate to 1 segment.
      0 = > new moon.
      4 => Full moon.
    */

        int g, e;

        if (month == 1) --day;
        else if (month == 2) day += 30;
        else // m >= 3
        //boolean bRet=!(year & 3);
        {
            day += 28 + (month - 2) * 3059 / 100;

            // adjust for leap years
            if ((year & 3) == 0) ++day;
            if ((year % 100) == 0) --day;
        }

        g = (year - 1900) % 19 + 1;
        e = (11 * g + 18) % 30;
        if ((e == 25 && g > 11) || e == 24) e++;
        return ((((e + day) * 6 + 11) % 177) / 22 & 7);
    }

    public boolean prendiLuna(int durata) {
        localDateTime = LocalDateTime.now().plusHours(durata);
        boolean isNotte = localDateTime.getHour() <= 6||localDateTime.getHour() >= 21;

        int moonPhase = moonPhase(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        return isNotte && (moonPhase == 4);
    }

    public void creaPersonaggi() {
        personaggi.add(new Eroe());
        personaggi.add(new Vampiro());
        personaggi.add(new Licantropo(prendiLuna(durata)));
        // personaggi.add(new Licantropo(r.nextInt(2) == 0));//old version

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

    //aggiorna Stato Licantropo+schelta casuale chi attacca il primo, cambio turni sucessivamente
    public void comincaAttaco(Personaggio avversario) {
        //aggiorno isUomo di Licantropo prima di nuovo attaco
        if (giocatore instanceof Licantropo) {
            ((Licantropo) giocatore).setUomo(prendiLuna(durata));
        }
        if (avversario instanceof Licantropo) {
            ((Licantropo) avversario).setUomo(prendiLuna(durata));
        }
        //aggiorno chi attaca
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
       // localDateTime = LocalDateTime.now().plusHours(durata);
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
                    comincaAttaco(avversario);
                    // aggiorno durata
                    durata = r.nextInt(100) + 1;
                    break;
                case 2:
                    if (giocatore.scappare()) {
                        System.out.println("Sei riuscito a fuggire!");
                        return 1; // giocatore scappato, avversario ottiene vita=0-> conta come sconfitto
                    } else {
                        System.out.println("Non sei riuscito a fuggire!");
                        break;
                    }
                default:
                    System.out.println("Scelta non valida!");
                    break;
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
