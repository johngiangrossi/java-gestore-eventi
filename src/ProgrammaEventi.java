
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// // Creare una classe ProgrammaEventi con i seguenti attributi: 
// // - titolo: String  (una variabile di istanza chiamata titolo, di tipo String)
// // - eventi: List<Evento>  (una variabile di istanza chiamata eventi, di tipo “implementatore di” List<Event>)
public class ProgrammaEventi {
    
    // fields
    private String titolo;
    private List<Evento> eventi;

    // costruttore
    // costruttore valorizzare il titolo, passato come parametro, e inizializzare la lista di eventi come una nuova ArrayList
    public ProgrammaEventi(String titolo) {

        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("hai inserito una titolo non valido");
        }
        this.titolo = titolo;
        this.eventi = new ArrayList<>();

    }
    
    // getters
    public String getTitolo() {

        return titolo;

    }

    // setters
    public void setTitolo(String titolo) {

        if (titolo == null || titolo.isBlank()) {
            throw new IllegalArgumentException("hai inserito una titolo non valido");
        }
        this.titolo = titolo;

    }


    // metodi
    // un metodo che aggiunge alla lista un Evento, passato come parametro
    public ProgrammaEventi aggiungiEvento(Evento evento) {

        eventi.add(evento);
        return this;

    }
    
    // un metodo che restituisce una lista con tutti gli eventi presenti in una certa data
    public List<Evento> getEventiLista() {

        return (List<Evento>) Collections.unmodifiableList(this.eventi);

    }

    // un metodo che restituisce quanti eventi sono presenti nel programma
    public int numeroEventiLista() {
        
        return eventi.size();

    } 
    // un metodo che svuota la lista di eventi
    public ProgrammaEventi svuotaLista() {

        eventi.clear();
        return this;

    }
    
    // un metodo che restituisce una stringa che mostra il titolo del programma e tutti gli eventi ordinati per data nella forma data1- titolo1
    public String stringaOrdinaLista() {
        Collections.sort(eventi);
        StringBuilder sb = new StringBuilder();
        sb.append("Programma: ").append(titolo).append("\n");
        for (Evento e : eventi) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProgrammaEventi{");
        sb.append("titolo=").append(titolo);
        sb.append(", eventi=").append(eventi);
        sb.append('}');
        return sb.toString();
    }

}
