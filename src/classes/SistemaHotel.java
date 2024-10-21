package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SistemaHotel {
    static Scanner scanner = new Scanner(System.in);
    static List<Quarto> quartos = new ArrayList<>();
    static List<Reserva> reservas = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void iniciarSistema() {
        System.out.println("Iniciando sistema!");
        menuSistema();
    }

    private static void menuSistema() {
        while (true) {
            System.out.println("""
                    Escolha uma das opções:
                    1 - Cadastrar Quarto
                    2 - Cadastrar Reserva
                    3 - Listar Quartos
                    4 - Listar Reservas
                    5 - Registrar Check-in ou Check-out
                    6 - Visualizar Histórico de Reservas do Hóspede
                    9 - Sair""");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrarQuarto();
                    break;
                case 2:
                    cadastrarReserva();
                    break;
                case 3:
                    listarQuartos();
                    break;
                case 4:
                    listarReservas();
                    break;
                case 5:
                    registrarCheckInOuCheckOut();
                    break;
                case 6:
                    visualizarHistoricoReservaHospede();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    menuSistema();
            }
        }
    }

    public static Reserva getReservaByCpfHospede(Integer cpf) {
        for (Reserva reserva : reservas) {
            if (Objects.equals(reserva.getCpfHospede(), cpf)) {
                return reserva;
            }
        }
        return new Reserva();
    }

    private static void visualizarHistoricoReservaHospede() {
        System.out.println("Digite o número do CPF do hóspede: ");
        Integer cpf = scanner.nextInt();

        System.out.println("Histórico de reservas do hóspede: " + getReservaByCpfHospede(cpf).getNomeHospede());
        for (Reserva reserva : reservas) {
            if (Objects.equals(reserva.getCpfHospede(), cpf)) {
                System.out.println("Data reserva: " + reserva.getCheckIn() + " até " + reserva.getCheckOut());
                System.out.print("Quartos reservados: ");
                for (Integer numeroQuarto : reserva.getNumeroQuartos()) {
                    for (Quarto quarto : quartos) {
                        if (Objects.equals(quarto.getNumero(), numeroQuarto)) {
                            System.out.print("N°" + numeroQuarto + " " + quarto.getTipo() + ", \n");
                        }
                    }
                }
            }
        }
    }

    private static void registrarCheckInOuCheckOut() {
        System.out.println("Digite o número do CPF do hóspede: ");
        Integer cpf = scanner.nextInt();
        for (Reserva reserva : reservas) {
            if (reserva.getCpfHospede().equals(cpf)) {
                if (reserva.getCheckIn() == null) {
                    reserva.setCheckIn(LocalDate.now());
                    System.out.println("Check-in registrado com sucesso!");
                    System.out.println(reserva);
                    for (Integer numeroQuarto : reserva.getNumeroQuartos()) {
                        for (Quarto quarto : quartos) {
                            if (Objects.equals(numeroQuarto, quarto.getNumero())) {
                                quarto.setDisponivel(false);
                            }
                        }
                    }
                    return;
                } else if (reserva.getCheckOut() == null) {
                    reserva.setCheckOut(LocalDate.now());
                    System.out.println("Check-out registrado com sucesso!");
                    double valorReserva = getValorReserva(reserva);
                    System.out.println("Valor total da reserva: R$" + valorReserva);
                    System.out.println(reserva);
                    for (Integer numeroQuarto : reserva.getNumeroQuartos()) {
                        for (Quarto quarto : quartos) {
                            if (Objects.equals(numeroQuarto, quarto.getNumero())) {
                                quarto.setDisponivel(true);
                            }
                        }
                    }
                    return;
                }
            }
        }
        System.out.println("Nenhuma reserva em aberto com o CPF informado!");
    }

    private static void listarReservas() {
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private static void cadastrarReserva() {
        Reserva reserva = new Reserva();
        System.out.println("Digite o primeiro nome do hóspede: ");
        String nome = scanner.next();
        System.out.println("Digite o sobrenome do hóspede: ");
        String sobrenome = scanner.next();
        System.out.println("Digite o CPF do hóspede (sem pontuação): ");
        int cpf = scanner.nextInt();
        if (verificaHospedeNaoTemReservaAtivaPorCpf(cpf)) {
            reserva.setCpfHospede(cpf);
            reserva.setNomeHospede(nome + " " + sobrenome);
        } else {
            System.out.println("Hóspede já tem uma reserva ativa! CPF: " + cpf);
            return;
        }
        int opcao = 0;
        List<Integer> numeroQuartos = reserva.getNumeroQuartos();
        while (opcao != -1) {
            System.out.println("Digite o número do quarto: ");
            int numero = scanner.nextInt();
            if (verificaDisponibilidadeQuartoPorNumero(numero) && !reserva.getNumeroQuartos().contains(numero)) {
                numeroQuartos.add(numero);
            } else {
                System.out.println("Quarto já ocupado, ou já adicionado para essa reserva!");
            }
            System.out.println("Deseja adicionar outro quarto:\n1 - Sim\n2 - Não");
            if (scanner.nextInt() == 2) opcao = -1;
        }
        reservas.add(reserva);
        System.out.println("Reserva cadastrada com sucesso!");
        System.out.println(reserva);
    }

    private static void listarQuartos() {
        for (Quarto quarto : quartos) {
            System.out.println(quarto);
        }
    }

    private static void cadastrarQuarto() {
        Quarto quarto = new Quarto();
        System.out.println("Digite o número:");
        int numero = scanner.nextInt();
        if (verificaQuartoJaCadastradoPorNumer(numero)) {
            quarto.setNumero(numero);
        } else {
            System.out.println("Quarto já cadastrado! Número: " + numero);
            return;
        }
        System.out.println("Digite o preço da diária: ");
        quarto.setPrecoDiaria(scanner.nextDouble());
        int opcao = 0;
        while (opcao != -1) {
            System.out.println("Escolha o tipo:\n1 - Solteiro\n2 - Casal\n3 - Suite");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    quarto.setTipo("Solteiro");
                    opcao = -1;
                    break;
                case 2:
                    quarto.setTipo("Casal");
                    opcao = -1;
                    break;
                case 3:
                    quarto.setTipo("Suite");
                    opcao = -1;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        quartos.add(quarto);
        System.out.println("Quarto cadastrado com sucesso!");
        System.out.println(quarto);
    }

    // Utilitários
    private static boolean verificaQuartoJaCadastradoPorNumer(Integer numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero().equals(numero)) {
                return false;
            }
        }
        return true;
    }

    private static double getValorReserva(Reserva reserva) {
        int diasReservados = (reserva.getCheckOut().getDayOfYear() + 1) - (reserva.getCheckIn().getDayOfYear());
        double valorReserva = 0.0;
        for (Integer numeroQuarto : reserva.getNumeroQuartos()) {
            for (Quarto quarto : quartos) {
                if (Objects.equals(quarto.getNumero(), numeroQuarto)) {
                    valorReserva += (quarto.getPrecoDiaria() * diasReservados);
                }
            }
        }
        return valorReserva;
    }

    private static boolean verificaHospedeNaoTemReservaAtivaPorCpf(Integer cpf) {
        for (Reserva reserva : reservas) {
            if (reserva.getCpfHospede().equals(cpf)) {
                if (reserva.getCheckOut() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean verificaDisponibilidadeQuartoPorNumero(Integer numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero().equals(numero)) {
                return quarto.getDisponivel();
            }
        }
        return false;
    }
}
