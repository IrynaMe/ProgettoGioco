//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Licantropo l = new Licantropo(true);
        Eroe e = new Eroe();
        System.out.println(e.getForza());
        System.out.println(l.getForza());
        l.azzanna();
        e.combatti();
        System.out.println(e.getForza());
        System.out.println(l.getForza());

    }
}
