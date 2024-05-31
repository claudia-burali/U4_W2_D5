package claudia_burali;
import claudia_burali.entities.Archivio;
import claudia_burali.entities.Catalogo;
import claudia_burali.entities.Libri;
import claudia_burali.entities.Riviste;
import claudia_burali.enums.Periodicita;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();

        Libri libro1 = new Libri("978-3-16-148410-0", "Il Signore degli Anelli", 1954, 1216, "J.R.R. Tolkien", "Fantasy");
        Libri libro2 = new Libri("978-0-00-744083-2", "Harry Potter e la Pietra Filosofale", 1997, 223, "J.K. Rowling", "Fantasy");
        Riviste rivista1 = new Riviste("978-1-23-456789-0", "National Geographic", 2023, 100, Periodicita.MENSILE);

        archivio.aggiungiElemento(libro1);
        archivio.aggiungiElemento(libro2);
        archivio.aggiungiElemento(rivista1);

        System.out.println("Ricerca per ISBN 978-3-16-148410-0:");
        System.out.println(archivio.ricercaPerISBN("978-3-16-148410-0"));

        System.out.println("Ricerca per anno di pubblicazione 1954:");
        List<Catalogo> risultatiAnno = archivio.ricercaPerAnnoPubblicazione(1954);
        risultatiAnno.forEach(System.out::println);

        System.out.println("Ricerca per autore J.K. Rowling:");
        List<Libri> risultatiAutore = archivio.ricercaPerAutore("J.K. Rowling");
        risultatiAutore.forEach(System.out::println);

    }
}

