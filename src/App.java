import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
//migliorare chiedere prima che tipo e poi creare oggetto giusto direttamente e rendere piu modulare e scalabile e unificare logica creazione evento/concerto

public class App {

    private static final int MAX_TENTATIVI = 3;
    public static void main(String[] args) {

        // ricevo informazioni dall'utente
        Scanner scanner = new Scanner(System.in);
        boolean eventoCreato = false;
        Evento evento1 = null;

        try {

            // faccio scegliere se vuole creare evento
            for (int i = 0; i < MAX_TENTATIVI; i++) {

                System.out.println("vuoi creare un evento? digita si/no");
                String sceltaUtente = scanner.nextLine();

                switch (sceltaUtente.toLowerCase()) {

                    case "si" -> {

                        boolean sceltaTipoValida = false;

                        for (int j = 0; j < MAX_TENTATIVI; j++) {

                            System.out.println("che tipo di evento vuoi creare?");
                            System.out.println("digita 1 per un evento generico \ndigita 2 per un concerto");

                            String sceltaUtenteTipo = scanner.nextLine();

                            if (sceltaUtenteTipo == null || sceltaUtenteTipo.isBlank()) {

                                System.out.println("hai inserito valori errati, riprova");

                            } else if (!sceltaUtenteTipo.equals("1") && !sceltaUtente.equals("2")) {

                                System.out.println("hai inserito valori errati, riprova");

                            } else {

                                evento1 = creaEvento(scanner, sceltaUtenteTipo);
                                System.out.println(evento1.toString());
                                eventoCreato = true;
                                sceltaTipoValida = true;
                                i = MAX_TENTATIVI; 
                                break;

                            }
                        }
                        
                        if (evento1 == null) {
                            
                            System.out.println("creazione fallita");
                            return;

                        }
                        if (!sceltaTipoValida) { //aggiungere anche evento1==null??

                            System.out.println("hai superato i tentativi massimi, operazione annullata");
                            return;

                        }
                        
                        break;
                    }

                    case "no" -> {

                        System.out.println("non vuoi creare nessun evento, termino operazione");
                        return;
                    }

                    default -> {

                        System.err.println("hai inserito valori errati, riprova");

                    }
                }
            }
            
            if (!eventoCreato) {

                System.out.println("hai superato i tentativi massimi, operazione annullata");
                return;

            }

            // faccio operazione di aggiunta o disdetta
            if (evento1 == null) {
                
                System.out.println("impossibile eseguire operazioni, evento non creato");

            } else {

                evento1 = aggiungiPrenotazioni(scanner, evento1);
                evento1 = disdiciPrenotazione(scanner, evento1);

            }

        } finally {

            scanner.close();
        }

    }

    // metodo per creare evento
    public static Evento creaEvento(Scanner scanner, String utenteSceltaTipo) {
        boolean operazione = false;

        // ciclo per verificare se i dati sono corretti e si puo creare evento generico
        while (!operazione) {

            try {

                String titoloEvento = null;
                boolean titoloValido = false;
                // chiedo titolo evento
                for (int i = 0; i < MAX_TENTATIVI; i++) {

                    System.out.println("inserisci il titolo dell'evento");
                    titoloEvento = scanner.nextLine();

                    if (titoloEvento == null || titoloEvento.isBlank()) {

                        System.out.println("hai inserito un titolo non valido, riprova");

                    } else {

                        titoloValido = true;
                        break;

                    }
                }
                if (!titoloValido) {
                    
                    System.out.println(
                            "hai inserito titolo errato per un numero did tentativi massimi, annullo operazione");
                    return null;
                }

                // chiedo data evento
                boolean dataValida = false;
                LocalDate data = null;

                for (int i = 0; i < MAX_TENTATIVI; i++) {

                    System.out.println("inserisci la data dell'evento (formato AAAA-MM-GG)");
                    String dataEventoString = scanner.nextLine();

                    try {

                        data = LocalDate.parse(dataEventoString);
                        dataValida = true;
                        break;

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
                for (int i = 0; i < MAX_TENTATIVI; i++) {

                    System.out.println("inserisci il numero di posti totali");
                    try {

                        int inputPostiTotali = scanner.nextInt();
                        scanner.nextLine();
                        numPostiTotali = inputPostiTotali;
                        numeroValido = true;
                        break;

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

                // creo evento generico o concerto a seconda se utente a seconda della scelta del utente
                switch (utenteSceltaTipo) {

                    case "1" -> {

                        // creo evento generico
                        System.out.println("evento generico creato");
                        return new Evento(titoloEvento, data, numPostiTotali);

                    }
                    case "2" -> {

                        // chiedo dati per il concerto
                        // chiedo prezzo evento
                        System.out.println("Inserisci il prezzo del Concerto:");
                        BigDecimal prezzo = null;
                        boolean prezzoValido = false;

                        for (int i = 0; i < MAX_TENTATIVI; i++) {

                            try {

                                prezzo = scanner.nextBigDecimal();
                                scanner.nextLine();
                                prezzoValido = true;
                                break;

                            } catch (InputMismatchException e) {

                                System.out.println("Hai inserito un valore non valido per il prezzo, riprova");
                                scanner.nextLine();

                            }
                        }

                        if (!prezzoValido) {

                            System.out.println("hai inserito il prezzo non valido, termino operazione");
                            return null;

                        }

                        // chiedo ora evento
                        boolean oraValida = false;
                        LocalTime ora = null;

                        for (int i = 0; i < MAX_TENTATIVI; i++) {

                            System.out.println("inserisci la ora dell'evento (formato HH:mm)");
                            String oraEventoString = scanner.nextLine();

                            try {

                                ora = LocalTime.parse(oraEventoString);
                                oraValida = true;
                                break;

                            } catch (DateTimeParseException e) {

                                System.out.println("hai inserito una ora in un formato non corretto (formato HH:mm)");

                            }
                        }

                        if (!oraValida) {
                            System.out.println(
                                    "hai inserito l'ora in formato errato, annullo operazione di creazione concerto");
                            return null;
                        }

                        // creo concerto
                        System.out.println("concerto creato");
                        return new Concerto(ora, prezzo, titoloEvento, data, numPostiTotali);

                    }
                    
                    default -> {
                        
                        System.err.println("hai inserito valori errati, riprova");
                        break;
                    }
                }

            } catch (IllegalArgumentException e) {

                if (!isRipetiOperazione(scanner, e)) {
                    
                    return null;

                }
            }
        }

        return null;

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
                        "vuoi disdire delle prenotazioni? digita si/no");
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
    
    // metodo per far ripetere operazione
    public static boolean isRipetiOperazione(Scanner scanner, Exception e) {

        System.out.println(
                        "hai inserito dei valori errati, impossibile creare evento, errore: " + e.getMessage());

        // ciclo per chiedere se vuole riprovare a creare evento
        for (int i = 0; i < MAX_TENTATIVI; i++) {

            System.out.println("vuoi riprovare? inserire si/no");
            String inputUtente = scanner.nextLine();

            if (inputUtente == null || inputUtente.isBlank()) {

                System.out.println("Input non valido, usa si o no");
                continue;

            }

            switch (inputUtente) {

                case "si" -> {

                    return true;

                }

                case "no" -> {

                    System.out.println("hai terminato la creazione dell'evento");
                    return false;

                }

                default -> {

                    System.out.println("hai inserito dei valori non corretti, usa y o n");

                }
            }
        }

        System.out.println(
                "non hai inserito nessuna risposta valida, interrompo la creazione del evento");
        return false;

    }
}


