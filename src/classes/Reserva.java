package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
    private String nomeHospede;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private List<Quarto> numeroQuartos;

    public Reserva() {
        this.numeroQuartos = new ArrayList<>();
        this.checkIn = LocalDate.now();
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public List<Quarto> getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(List<Quarto> numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "nomeHospede='" + nomeHospede + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", numeroQuartos=" + numeroQuartos +
                '}';
    }
}
