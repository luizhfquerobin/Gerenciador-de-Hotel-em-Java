package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
    private String nomeHospede;
    private Integer cpfHospede;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private List<Integer> numeroQuartos;

    public Reserva() {
        this.numeroQuartos = new ArrayList<>();
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public Integer getCpfHospede() {
        return cpfHospede;
    }

    public void setCpfHospede(Integer cpfHospede) {
        this.cpfHospede = cpfHospede;
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

    public List<Integer> getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(List<Integer> numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    @Override
    public String toString() {
        return "Nome do Hóspede: " + nomeHospede +
                ", CPF do Hóspede: " + cpfHospede +
                ", Data de Check-in: " + (checkIn == null ? "não regitrada" : checkIn) +
                ", Data de Check-out: " + (checkOut == null ? "não regitrada" : checkOut) +
                ", Número dos Quartos Reservados: " + numeroQuartos;
    }
}
