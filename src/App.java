import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {

        Evento evento1 = new Evento("rock", LocalDate.of(2025, 9, 10), 10);

        System.out.println(evento1.toString());

        evento1.prenota();

        System.out.println(evento1.getNumPostiPrenotati());

        evento1.disdici();

        System.out.println(evento1.getNumPostiPrenotati());

        

    }
}
