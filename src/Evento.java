
import java.time.LocalDate;

public class Evento {

    // fields
    private String titolo;
    private LocalDate data;
    private int numPostiTotale;
    private int numPostiPrenotati;

    // costruttore
    public Evento(String titolo, LocalDate data, int numPostiTotale) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        if (numPostiTotale <= 0) {
            throw new IllegalArgumentException("hai inserito un numero di posti non corretto");
        }
        this.titolo = titolo;
        this.data = data;
        this.numPostiTotale = numPostiTotale;
        this.numPostiPrenotati = 0;
    }  

    // getters
    public int getNumPostiTotale() {
        return numPostiTotale;
    }

    public int getNumPostiPrenotati() {
        return numPostiPrenotati;
    }

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }


    // setters
    public void setTitolo(String titolo) {
        if (titolo.isBlank() || titolo == null) {
            throw new IllegalArgumentException("hai inserito un titolo non corretto");
        }
        this.titolo = titolo;
    }


    public void setData(LocalDate data) {
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        this.data = data;
    }
    
    
    // metodi
    public int prenota() {
        if (numPostiPrenotati == numPostiTotale) {
            throw new IllegalArgumentException("non ci sono più posti disponibili");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        return numPostiPrenotati += 1;
    }
    
    public int disdici() {
        if (numPostiPrenotati == 0) {
            throw new IllegalArgumentException("non ci sono più posti disponibili");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        return numPostiPrenotati -= 1;
    }


    @Override
    public String toString() {
        return "Evento [titolo=" + titolo + ", data=" + data + "]";
    }


    
    







}
