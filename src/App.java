import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static final int MAX_TENTATIVI = 3;

    // // Creare una classe Main di test, in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.
    public static void main(String[] args) {

        // apro scanner
        Scanner scanner = new Scanner(System.in);

        // in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.
        
        try {

            // chiedo titolo programma eventi
            String inputProgrammaEventi = null;
            boolean titoloProgrammaValido = false;          
            
            for (int i = 0; i < MAX_TENTATIVI; i++) {
                
                System.out.println("inserisci il titolo del programma eventi");
                inputProgrammaEventi = scanner.nextLine();
                
                if (inputProgrammaEventi == null || inputProgrammaEventi.isBlank()) {
                    
                    System.out.println("hai inserito un titolo non valido (vuoto), riprova");
                    
                } else {
                    
                    titoloProgrammaValido = true;
                    break;
                    
                }
            }
            if (!titoloProgrammaValido) {
                
                System.out.println(
                    "hai inserito titolo errato per un numero did tentativi massimi, annullo operazione");
                return;

            }
            
            ProgrammaEventi programmaEventi1 = new ProgrammaEventi(inputProgrammaEventi);

            // creo evento
            Evento evento1 = gestisciCreazioneEvento(scanner);

            // faccio operazione di aggiunta o disdetta
            if (isCreatoEvento(evento1)) {

                return;

            } 

            // // Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
            evento1 = aggiungiPrenotazioni(scanner, evento1);

            // // Provare ad effettuare le disdette, implementando opportuni controlli
            evento1 = disdiciPrenotazione(scanner, evento1);

            // aggiungo evento alla lista
            programmaEventi1.aggiungiEvento(evento1);

            // stampo la lista
            System.out.println("la lista degli eventi è: " + programmaEventi1.getEventiLista());

            // mostro lunghezza lista
            System.out.println("nella lista ci sono: " + programmaEventi1.numeroEventiLista() + " eventi");


            // aggiungo e stampo un altro evento
            Evento evento2 = gestisciCreazioneEvento(scanner);

            if (isCreatoEvento(evento2)){

                return;

            } 

            evento2 = aggiungiPrenotazioni(scanner, evento2);

            evento2 = disdiciPrenotazione(scanner, evento2);

            programmaEventi1.aggiungiEvento(evento2);


            System.out.println("la lista degli eventi è: " + programmaEventi1.getEventiLista());

            System.out.println("nella lista ci sono: " + programmaEventi1.numeroEventiLista() + " eventi");

            System.out.println(programmaEventi1.stringaOrdinaLista());

        } catch (NullPointerException e) {

            System.out.println("uno o più elementi da confrontare non sono stati creati (null)");

        } finally {

            scanner.close();
        }

    }

    
    // metodo per gestire la creazione del evento
    public static Evento gestisciCreazioneEvento(Scanner scanner) {

        // in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.                 
        // faccio scegliere se vuole creare evento
        for (int i = 0; i < MAX_TENTATIVI; i++) {

            System.out.println("vuoi creare un evento? digita si/no");
            String sceltaUtente = scanner.nextLine();
            
            switch (sceltaUtente.toLowerCase()) {

                case "si" -> {
                                       
                    // faccio scegliere che tipo di evento vuole creare
                    for (int j = 0; j < MAX_TENTATIVI; j++) {
                        
                        System.out.println("che tipo di evento vuoi creare?");
                        System.out.println("#digita 1 per un evento generico \n#digita 2 per un concerto");
                        
                        String sceltaUtenteTipo = scanner.nextLine();
                        
                        if (sceltaUtenteTipo == null || sceltaUtenteTipo.isBlank()) {
                            
                            System.out.println("hai inserito valori errati, riprova");
                            
                        } else if (!sceltaUtenteTipo.equals("1") && !sceltaUtenteTipo.equals("2")) {
                            
                            System.out.println("hai inserito valori errati, riprova");
                            
                        } else {
                            
                            // // in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.
                            Evento evento1 = creaEvento(scanner, sceltaUtenteTipo);
                            System.out.println(evento1.toString());
                            return evento1;
                            
                        }
                    }
                        
                    System.out.println("hai superato i tentativi massimi, operazione annullata");
                    return null;
                    
                }

                case "no" -> {

                    System.out.println("non vuoi creare nessun evento, termino operazione");
                    return null;
                }
                
                default -> {
                    
                    System.err.println("hai inserito valori errati, riprova");
                    
                }
            }
        }
         
        System.out.println("hai superato i tentativi massimi, operazione annullata");
        return null;

    }
    
    // metodo per creare evento
    public static Evento creaEvento(Scanner scanner, String utenteSceltaTipo) {
        boolean operazione = false;
        
        // // in cui si chiede all’utente di inserire un nuovo evento con tutti i parametri.
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
                
                // creo evento generico o concerto a seconda della scelta del utente
                switch (utenteSceltaTipo) {

                    case "1" -> {

                        // creo evento generico
                        System.out.println("evento generico creato");
                        return new Evento(titoloEvento, data, numPostiTotali);
                        
                    }
                    case "2" -> {
                        
                        // // Testare la classe Concerto, utilizzando TUTTI i suoi metodi.
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
    // Dopo che l’evento è stato istanziato, chiedere all’utente se e quante prenotazioni vuole fare e provare ad effettuarle, implementando opportuni controlli
    public static Evento aggiungiPrenotazioni(Scanner scanner, Evento evento) {

        // se evento non viene creato non prenoto nulla
        if (evento == null) {

            System.out.println("impossibile prenotare posti di un evento che non è stato creato");
            return evento;

        } else {

            try {

                // // chiedere all’utente se
                // chiedo utente se vuole prenotare delle prenotazioni
                System.out.println(
                        "vuoi prenotare delle prenotazioni? digita si/no");
                String inputRisposta = scanner.nextLine();
                boolean prenotare = evento.isVuoiFareOperazioni(inputRisposta);
                
                if (prenotare) {
                    
                    // // e quante prenotazioni vuole fare
                    // chiedo utente quante prenotazioni vuole fare
                    System.out.println("quanti posti vuoi prenotare?\n #numero posti disponibili: " + evento.getNumPostiDisponibili());
                    int numPostiPrenotare = scanner.nextInt();
                    scanner.nextLine();
                    
                    // // implementando opportuni controlli
                    if (numPostiPrenotare <= 0) {
                        
                        System.out.println("il numero di posti da prenotare deve essere maggiore di 0");
                        System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili()
                                + " - numero posti totali: " + evento.getNumPostiTotale());
                        return evento;
                        
                    }
                    if (numPostiPrenotare > evento.getNumPostiDisponibili()) {
                        
                        System.out.println("stai cercando di prenotare più posti di quelli disponibili");
                        System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili()
                                + " - numero posti totali: " + evento.getNumPostiTotale());
                        return evento;

                    }
                    if (numPostiPrenotare > evento.getNumPostiTotale()) {
                        
                        System.out.println("stai cercando di prenotare più posti di quelli totali");
                        System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili()
                                + " - numero posti totali: " + evento.getNumPostiTotale());
                        return evento;
                        
                    }
                    
                    // // e provare ad effettuarle
                    // prenoto i posti
                    for (int i = 0; i < numPostiPrenotare; i++) {
                        
                        evento.prenota();
                        
                    }
                    System.out.println("prenotazione effettuata, numero posti prenotati: " + numPostiPrenotare);

                } else {

                    System.out.println("non vuoi prenotare nessun posto");
                    System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili()
                            + " - numero posti totali: " + evento.getNumPostiTotale());
                    return evento;

                }
                
                // // Stampare a video il numero di posti prenotati e quelli disponibili
                // stampo numero posti totali prenotati e disponibili
                System.out.println("numero di posti prenotati totali: " + evento.getNumPostiPrenotati()
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

                // // Chiedere all’utente se
                // chiedo utente se vuole disdire delle prenotazioni
                System.out.println(
                        "vuoi disdire delle prenotazioni? digita si/no");
                String inputRisposta = scanner.nextLine();
                boolean disdire = evento.isVuoiFareOperazioni(inputRisposta);

                if (disdire) {

                    // // e quanti posti vuole disdire
                    // chiedo utente quante prenotazioni vuole disdire
                    System.out.println(
                            "quanti posti vuoi disdire?\n #numero posti prenotati: " + evento.getNumPostiPrenotati());
                    int numPostiDisdire = scanner.nextInt();
                    scanner.nextLine();

                    // // implementando opportuni controlli
                    if (numPostiDisdire <= 0) {
                        
                        System.out.println("il numero di posti da disdire deve essere maggiore di 0");
                        System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili());
                        return evento;
                        
                    }
                    if (numPostiDisdire > evento.getNumPostiPrenotati()) {
                        
                        System.out.println("stai cercando di disdire più posti di quelli prenotati");
                        System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili());
                        return evento;
                        
                    }
                    
                    // // Provare ad effettuare le disdette
                    // disdico i posti
                    for (int i = 0; i < numPostiDisdire; i++) {
                        
                        evento.disdici();
                        
                    }
                    System.out.println("disdetta posti fatta, numero posti disdetti: " + numPostiDisdire);

                } else {
                    
                    System.out.println("non vuoi disdire nessun posto");
                    System.out.println("numero di posti disponibili: " + evento.getNumPostiDisponibili());
                    return evento;
                    
                }
                
                // // Stampare a video il numero di posti prenotati e quelli disponibili
                // stampo numero posti totali prenotati e disponibili
                System.out.println("numero di posti prenotati totali: " + evento.getNumPostiPrenotati()
                + " ,numero posti disponibili: " + evento.getNumPostiDisponibili());
                return evento;
                
            } catch (InputMismatchException e) {

                System.out.println("hai inserito dei valori errati, annullo operazione");
                scanner.nextLine();
                return evento;
                
            } catch (IllegalArgumentException e) {
                
                System.out.println(
                    "hai inserito dei valori errati, impossibile disdire dei posti, errore: " + e.getMessage());
                System.out.println("esco dall'operazione");
                return evento;
                    
            }
        }
    }
        
        
    // metodo per far ripetere operazione
    public static boolean isRipetiOperazione(Scanner scanner, Exception e) {
            
        System.out.println("hai inserito dei valori errati, impossibile creare evento, errore: " + e.getMessage());
            
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
                    
                    System.out.println("hai inserito dei valori non corretti, usa si o no");
                    
                }
            }
        }
        
        System.out.println(
            "non hai inserito nessuna risposta valida, interrompo la creazione del evento");
        return false;

    }
    

    public static boolean isCreatoEvento(Evento evento) {
            if (evento == null) {

                System.out.println("impossibile eseguire operazioni, evento non creato");
                return true;

            }
            return false; 
    }
}