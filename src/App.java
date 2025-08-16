import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {

        // ricevo informazioni dall'utente
        Scanner scanner = new Scanner(System.in);

        Evento evento1 = creaEvento(scanner);

        // verifico che evento sia stato creato per poter fare le operazioni di gestione, se non c'è termino operazione
        if (evento1 == null) {
            System.out.println("non cè nessun evento creato");
            return;
        }

        // faccio operazione di aggiunta o disdetta
        evento1 = aggiungiPrenotazioni(scanner, evento1);
        evento1 = disdiciPrenotazione(scanner, evento1);

    }
    
    // metodo per creare evento
    public static Evento creaEvento(Scanner scanner) {
        Evento evento = null;
        boolean operazione = false;

        // ciclo per verificare se i dati sono corretti e si puo creare evento
        while (!operazione) {

            try {

                // chiedo titolo evento
                System.out.println("inserisci il titolo dell'evento");
                String titoloEvento = scanner.nextLine();

                // chiedo data evento

                boolean dataValida = false;
                LocalDate data = null;
                  
                for (int i = 0; i < 3; i++) {

                    System.out.println("inserisci la data dell'evento (formato AAAA-MM-GG)");
                    String dataEventoString = scanner.nextLine();

                    try {

                        data = LocalDate.parse(dataEventoString);
                        dataValida = true;
                        i = 3;

                    } catch (DateTimeParseException e) {

                        System.out.println("hai inserito una data in un formato non corretto (formato AAAA-MM-GG)");

                    }
                }
                
                if (!dataValida) {
                    System.out.println(
                            "hai inserito la data in formato errato, annullo operazione di creazione evento");
                    return null;
                }

                // chiedo numero posti totali

                int numPostiTotali = 0;
                boolean numeroValido = false;
                for (int i = 0; i < 3; i++) {

                    System.out.println("inserisci il numero di posti totali");
                    try {

                        int inputPostiTotali = scanner.nextInt();
                        scanner.nextLine();
                        numPostiTotali = inputPostiTotali;
                        numeroValido = true;
                        i = 3;

                    } catch (InputMismatchException e) {

                        System.out.println("hai inserito dei valori non corretti");
                        scanner.nextLine();

                    }

                }
                
                if (!numeroValido) {

                    System.out.println(
                        "hai inserito un numero totale di posti errato, annullo operazione di creazione evento");
                    return null;

                }

                // creo evento
                evento = new Evento(titoloEvento, data, numPostiTotali);
                System.out.println("evento creato");

                // stampo la classe con le informazioni inserite dal utente
                System.out.println(evento.toString());

                // esco dal ciclo
                return evento;

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "hai inserito dei valori errati, impossibile creare evento, errore: " + e.getMessage());

                boolean rispostaValida = false;

                // ciclo per chiedere se vuole riprovare a creare evento
                for (int i = 0; i < 3; i++) {

                    System.out.println("vuoi riprovare? inserire (y/n)");
                    String inputUtente = scanner.nextLine();

                    if (inputUtente == null || inputUtente.isBlank()) {

                        System.out.println("Input non valido, usa y o n");
                        continue;

                    }

                    char inputUtenteChar = inputUtente.toLowerCase().charAt(0);

                    switch (inputUtenteChar) {

                        case 'y' -> {

                            rispostaValida = true;
                            i = 3;
                            break;

                        }

                        case 'n' -> {

                            System.out.println("hai terminato la creazione dell'evento");
                            return null;

                        }

                        default -> {

                            System.out.println("hai inserito dei valori non corretti, usa y o n");

                        }
                    }
                }

                if (!rispostaValida) {

                    System.out.println(
                            "non hai inserito nessuna risposta valida, interrompo la creazione del evento");
                    return null;
                }
            }
        }

        return evento;

    }
    
    // metodo per aggiungere prenotazioni
    public static Evento aggiungiPrenotazioni(Scanner scanner, Evento evento) {

        // se evento non viene creato non prenoto nulla
        if (evento == null) {

            System.out.println("impossibile prenotare posti di un evento che non è stato creato");
            return evento;

        } else {

            try {

                // chiedo utente quante prenotazioni vuole fare
                System.out.println("quante prenotazioni devi fare?");
                
                int numPostiPrenotati = scanner.nextInt();
                scanner.nextLine();

                // aggiungo il numero di prenotazioni
                evento.prenota(numPostiPrenotati);

                System.out.println("prenotazione effettuata");

                // stampo numero posti totali prenotati e disponibili
                System.out.println("numero di posti prenotati: " + evento.getNumPostiPrenotati()
                        + " ,numero posti disponibili: " + evento.getNumPostiDisponibili());

                return evento;

            } catch (InputMismatchException e) {

                System.out.println("hai inserito un valore non corretto, annullo la prenotazione");
                scanner.nextLine();
                return evento;

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "hai inserito dei valori errati, impossibile prenotare dei posti, errore: " + e.getMessage());
                System.out.println("numero posti disponibili " + evento.getNumPostiDisponibili());
                return evento;

            }
        }
    }
    
    
    // metodo per disdire delle prenotazioni
    public static Evento disdiciPrenotazione(Scanner scanner, Evento evento) {
        
        // se evento non viene creato non disdico nulla
        if (evento == null) {
            
            System.out.println("impossibile disdire prenotazioni di un evento che non è stato creato");
            return evento;
            
        } else {
            
            try {

                // chiedo utente se vuole disdire delle prenotazioni
                System.out.println(
                        "vuoi disdire delle prenotazioni? digita (y/n)");
                String inputRisposta = scanner.nextLine();
                boolean disdire = evento.isVuoiDisdire(inputRisposta);

                if (disdire) {
                    
                    // chiedo utente quante prenotazioni vuole disdire
                    System.out.println("quanti posti vuoi disdire?");
                    int numPostiDisdire = scanner.nextInt();
                    scanner.nextLine();
                    
                    // disdico i posti
                    evento.disdici(numPostiDisdire);

                } else {
                
                    System.out.println("non vuoi disdire nessun posto");
                    return evento;
                
                }
                
                // stampo numero posti totali prenotati e disponibili
                System.out.println("numero di posti prenotati totali: " + evento.getNumPostiPrenotati()
                        + " ,numero posti disponibili: " + evento.getNumPostiDisponibili());
                return evento;

            } catch (InputMismatchException e) {
                
                System.out.println("hai inserito dei valori errati, annullo operazione");
                scanner.nextLine();
                return evento;

            } catch (IllegalArgumentException e) {
                
                System.out.println("errore: " + e.getMessage());
                System.out.println("esco dall'operazione");
                return evento;
                
                
            } 
        }
    }
}