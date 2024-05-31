package claudia_burali.entities;
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
        catalogoList.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
    }

    public Catalogo ricercaPerISBN(String codiceISBN) {
        return catalogoList.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst()
                .orElse(null);
    }

    public List<Catalogo> ricercaPerAnnoPubblicazione(int anno) {
        return catalogoList.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<Libri> ricercaPerAutore(String autore) {
        return catalogoList.stream()
                .filter(elemento -> elemento instanceof Libri)
                .map(elemento -> (Libri) elemento)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

}

