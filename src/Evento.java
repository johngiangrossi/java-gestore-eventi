
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

    // fields
    private String titolo;
    private LocalDate data;
    private int numPostiTotale;
    private int numPostiPrenotati;

    // costruttore
    public Evento(String titolo, LocalDate data, int numPostiTotale) {

        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        if (numPostiTotale <= 0) {
            throw new IllegalArgumentException(
                    "hai inserito un numero di posti non corretto, deve essere maggiore di 0");
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

    public String getDataFormattata() {

        DateTimeFormatter dataFormattata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(dataFormattata);

    }
    
    public int getNumPostiDisponibili() {

        return numPostiTotale - numPostiPrenotati;

    }


    // setters
    public void setTitolo(String titolo) {

        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("hai inserito un titolo non corretto");
        }

        this.titolo = titolo;
    }


    public void setData(LocalDate data) {

        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }

        this.data = data;
    }
    
    
    // metodi
    public int prenota(int numPostiPrenotati) {

        if (numPostiPrenotati <= 0) {
            throw new IllegalArgumentException("numero dei posti da prenotare deve essere maggiore di 0");
        }
        if (numPostiPrenotati > getNumPostiDisponibili()) {
            throw new IllegalArgumentException("non ci sono sufficienti posti disponibili");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        for (int i = 0; i < numPostiPrenotati; i++) {
            this.numPostiPrenotati++;
        }

        return numPostiPrenotati;
    }
    
    public int disdici(int numPostiDisdire) {

        if (numPostiDisdire <= 0) {
            throw new IllegalArgumentException("il numero di posti da disdire deve essere maggiore di 0");
        }
        if (numPostiPrenotati == 0) {
            throw new IllegalArgumentException("non ci sono prenotazioni attive");
        }
        if (numPostiDisdire > numPostiPrenotati) {
            throw new IllegalArgumentException("stai cercando di disdire più posti di quelli prenotati");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("hai inserito una data passata");
        }
        for (int i = 0; i < numPostiDisdire; i++) {
            this.numPostiPrenotati--;
        }

        return numPostiPrenotati;

    }

    // metodi di verifica
    public boolean isVuoiDisdire(String input) {

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

    // override
    @Override
    public String toString() {

        return "Evento [titolo=" + getTitolo() + ", data=" + getDataFormattata() + "]";

    }


}