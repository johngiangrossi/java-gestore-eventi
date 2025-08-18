
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    // // Creare una classe Evento che abbia le seguenti proprietà: titolo , data , numero di posti in totale , numero di posti prenotati
    // fields
    private String titolo;
    private LocalDate data;
    private int numPostiTotale;
    private int numPostiPrenotati;

    // // si istanzia un nuovo evento questi attributi devono essere tutti valorizzati nel costruttore, tranne posti prenotati che va inizializzato a 0.
    // costruttore
    public Evento(String titolo, LocalDate data, int numPostiTotale) {

        // // Inserire il controllo che la data non sia già passata e che il numero di posti totali sia positivo. In caso contrario sollevare eccezione.
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        if (numPostiTotale <= 0) {
            throw new IllegalArgumentException(
                    "hai inserito un numero di posti non corretto, deve essere maggiore di 0");
        }
        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("hai inserito una titolo non valido");
        }

        this.titolo = titolo;
        this.data = data;
        this.numPostiTotale = numPostiTotale;
        this.numPostiPrenotati = 0;

    }

    // // Aggiungere metodi getter e setter in modo che: titolo sia in lettura e in scrittura , data sia in lettura e scrittura , numero di posti totale sia solo in lettura , numero di posti prenotati sia solo in lettura
    // getters
    // // numero di posti totale sia solo in lettura
    public int getNumPostiTotale() {

        return numPostiTotale;

    }

    // // numero di posti prenotati sia solo in lettura
    public int getNumPostiPrenotati() {

        return numPostiPrenotati;

    }

    // // titolo sia in lettura
    public String getTitolo() {

        return titolo;

    }

    // // data sia in lettura 
    public LocalDate getData() {

        return data;

    }

    // // Aggiungere i metodi per restituire data formattata
    public String getDataFormattata() {

        DateTimeFormatter dataFormattata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(dataFormattata);

    }
    
    public int getNumPostiDisponibili() {

        return numPostiTotale - numPostiPrenotati;

    }

    // setters
    // // titolo sia in scrittura
    public void setTitolo(String titolo) {

        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("hai inserito un titolo non corretto");
        }

        this.titolo = titolo;
    }

    // // data sia in scrittura
    public void setData(LocalDate data) {

        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }

        this.data = data;
    }
    
    
    // metodi
    // // prenota: aggiunge uno ai posti prenotati. Se l’evento è già passato o non ha posti disponibili deve sollevare eccezione.
    public int prenota() {

        // // Se l’evento è già passato o non ha posti disponibili deve sollevare eccezione.
        if (getNumPostiDisponibili() <= 0) {
            throw new IllegalArgumentException("non ci sono sufficienti posti disponibili");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        
        // // aggiunge uno ai posti prenotati
        this.numPostiPrenotati++;
        return this.numPostiPrenotati;
    }
    
    // // disdici: riduce di uno i posti prenotati. Se l’evento è già passato o non ci sono prenotazioni solleva eccezione.
    public int disdici() {

        // // Se l’evento è già passato o non ci sono prenotazioni solleva eccezione.
        if (numPostiPrenotati == 0) {
            throw new IllegalArgumentException("non ci sono prenotazioni attive");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        
        // // riduce di uno i posti prenotati
        this.numPostiPrenotati--;
        return this.numPostiPrenotati;

    }

    // metodi di verifica
    public boolean isVuoiFareOperazioni(String input) {

        if (input == null || input.isBlank()) {

            throw new IllegalArgumentException(
                    "hai inserito dei valori non corretti");

        } else {

            switch (input) {

                case "si" -> {

                    return true;

                }

                case "no" -> {

                    return false;

                }

                default -> {

                    throw new IllegalArgumentException(
                            "hai inserito dei valori non corretti");

                }
            }
        }
    }
    


    // // l’override del metodo toString() in modo che venga restituita una stringa contenente: data formattata - titolo
    // override
    @Override
    public String toString() {

        return "Evento [data=" + getDataFormattata() + " - titolo=" + getTitolo() + "]";

    }


}