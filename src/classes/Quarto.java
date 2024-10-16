package classes;

public class Quarto {
    private Integer numero;
    private String tipo;
    private Double precoDiaria;
    private Boolean isDisponivel;

    public Quarto() {
        this.isDisponivel = false;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(Double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public Boolean getDisponivel() {
        return isDisponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        isDisponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", precoDiaria=" + precoDiaria +
                ", isDisponivel=" + isDisponivel +
                '}';
    }
}
