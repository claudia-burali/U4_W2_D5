package claudia_burali.entities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Archivio {
    private List<Catalogo> catalogoList;

    public Archivio() {
        this.catalogoList = new ArrayList<>();
    }

    public void aggiungiElemento(Catalogo elemento) {
        catalogoList.add(elemento);
    }

    public void rimuoviElemento(String codiceISBN) {
        boolean rimosso = catalogoList.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
        if (!rimosso) {
            System.out.println("Elemento con ISBN " + codiceISBN + " non trovato.");
        }
    }

    public Catalogo ricercaPerISBN(String codiceISBN) {
        return catalogoList.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Elemento con ISBN " + codiceISBN + " non trovato."));
    }

    public List<Catalogo> ricercaPerAnnoPubblicazione(int anno) {
        List<Catalogo> risultati = catalogoList.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
        if (risultati.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'anno di pubblicazione " + anno);
        }
        return risultati;
    }

    public List<Libri> ricercaPerAutore(String autore) {
        List<Libri> risultati = catalogoList.stream()
                .filter(elemento -> elemento instanceof Libri)
                .map(elemento -> (Libri) elemento)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
        if (risultati.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore " + autore);
        }
        return risultati;
    }

    public void salvaSuDisco(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(catalogoList);
            FileUtils.writeStringToFile(new File(filepath), jsonString, "UTF-8");
            System.out.println("Archivio salvato su disco con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio dell'archivio su disco: " + e.getMessage());
        }
    }

    public void caricaDaDisco(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = FileUtils.readFileToString(new File(filepath), "UTF-8");
            catalogoList = objectMapper.readValue(jsonString, new TypeReference<List<Catalogo>>() {});
            System.out.println("Archivio caricato dal disco con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento dell'archivio dal disco: " + e.getMessage());
        }
    }

    public List<Catalogo> getCatalogoList() {
        return catalogoList;
    }
}



