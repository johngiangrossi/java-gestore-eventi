
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

// // Creare una classe Concerto che estende Evento, che ha anche gli attributi : ora (LocalTime) , prezzo
public class Concerto extends Evento {

    // // che ha anche gli attributi : ora (LocalTime) , prezzo
    // fields
    private LocalTime ora;
    private BigDecimal prezzo;

    // // Aggiungere questi attributi nel costruttore
    // costruttore
    public Concerto(LocalTime ora, BigDecimal prezzo, String titolo, LocalDate data, int numPostiTotale) {
        super(titolo, data, numPostiTotale);
        if (ora == null) {
            throw new IllegalArgumentException("hai inserito un ora sbagliata");
        }
        if (prezzo == null || prezzo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("hai inserito un prezzo non valido");
        }
        this.ora = ora;
        this.prezzo = prezzo;
    }
    

    // // e implementarne getter 
    // getters
    public LocalTime getOra() {
    return ora;
}

    // // Aggiungere i metodi per restituire ora formattata
    public String getOraFormattata() {

        DateTimeFormatter oraFormattata = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(oraFormattata);

    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    // // Aggiungere i metodi per restituire prezzo formattato (##,##€)
    public String getPrezzoFormattato() {
        
        Currency valuta = Currency.getInstance("EUR"); // codice ISO 4217
        NumberFormat prezzoFormattato = NumberFormat.getCurrencyInstance(Locale.ITALY);
        prezzoFormattato.setCurrency(valuta);
        return prezzoFormattato.format(prezzo);
        
    }

    
    // // e implementarne setter
    // setters
    public void setOra(LocalTime ora) {
        if (ora == null) {
            throw new IllegalArgumentException("hai inserito un ora sbagliata");
        }
        this.ora = ora;
    }

    public void setPrezzo(BigDecimal prezzo) {
        if (prezzo == null || prezzo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("hai inserito un prezzo non valido");
        }
        this.prezzo = prezzo;
    }
    
    
    // // Fare l’ override del metodo toString() in modo che venga restituita una stringa del tipo: data e ora formattata - titolo - prezzo formattato
    // override
    @Override
    public String toString() {
        return "Concerto [data=" + super.getDataFormattata() + " ora=" + getOraFormattata() + ", titolo="
                + super.getTitolo() + " prezzo=" + getPrezzoFormattato() + "]";
    }
    
}
