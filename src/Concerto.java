
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class Concerto extends Evento {

    // fields
    private LocalTime ora;
    private BigDecimal prezzo;

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
    

    // getters
    public LocalTime getOra() {
        return ora;
    }

    public String getOraFormattata() {

        DateTimeFormatter oraFormattata = DateTimeFormatter.ofPattern("HH:mm");
        return ora.format(oraFormattata);

    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public String getPrezzoFormattato() {
        
        Currency valuta = Currency.getInstance("EUR"); // codice ISO 4217
        NumberFormat prezzoFormattato = NumberFormat.getCurrencyInstance(Locale.ITALY);
        prezzoFormattato.setCurrency(valuta);
        return prezzoFormattato.format(prezzo);
        
    }

    
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
    
    @Override
    public String toString() {
        return "Concerto [data=" + super.getDataFormattata() + " ora=" + getOraFormattata() + ", titolo="
                + super.getTitolo() + " prezzo=" + getPrezzoFormattato() + "]";
    }
    
}
