package claudia_burali.entities;
import claudia_burali.enums.Periodicita;
import java.io.Serializable;

public class Riviste extends Catalogo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Periodicita periodicita;

    public Riviste(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    // Getters and Setters
    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "periodicita=" + periodicita +
                "} " + super.toString();
    }
}


