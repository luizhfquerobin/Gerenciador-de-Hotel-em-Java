package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("Escolha uma das opções:\n1 - Cadastrar quarto\n2 - Cadastrar Reserva\n3 - Listar quartos\n9 - Sair");
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
                case 9:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    menuSistema();
            }
        }
    }

    private static void cadastrarReserva() {
        Reserva reserva = new Reserva();
        System.out.println("Digite o primeiro nome do hóspede: ");
        String nome = scanner.next();
        System.out.println("Digite o sobrenome do hóspede: ");
        String sobrenome = scanner.next();
        System.out.println("Quantos quartos foram");
    }

    private static void listarQuartos() {
        for (Quarto quarto : quartos) {
            System.out.println(quarto);
        }
    }

    private static void cadastrarQuarto() {
        Quarto quarto = new Quarto();
        System.out.println("Digite o número:");
        quarto.setNumero(scanner.nextInt());
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

}
