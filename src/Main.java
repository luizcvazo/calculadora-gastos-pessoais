import java.util.Scanner;

public class Main {

    private static final String[] NOMES_MESES = {
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mes[] meses = criarMeses();

        boolean sair = false;
        while (!sair) {
            exibirMenuPrincipal();
            int opcao = lerInteiro(scanner);

            if (opcao == 0) {
                sair = true;
            } else if (opcao >= 1 && opcao <= meses.length) {
                sair = menuMes(scanner, meses[opcao - 1], meses, opcao - 1);
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
        System.out.println("Programa encerrado.");
    }

    private static Mes[] criarMeses() {
        Mes[] meses = new Mes[NOMES_MESES.length];
        for (int i = 0; i < NOMES_MESES.length; i++) {
            meses[i] = new Mes(NOMES_MESES[i]);
        }
        return meses;
    }

    private static void exibirMenuPrincipal() {
        System.out.println("=== Calculadora de Gastos Mensais ===");
        System.out.println("Selecione o mês:");
        for (int i = 0; i < NOMES_MESES.length; i++) {
            System.out.println((i + 1) + " - " + NOMES_MESES[i]);
        }
        System.out.println("0 - Sair");
    }

    private static boolean menuMes(Scanner scanner, Mes mes, Mes[] meses, int indice) {
        while (true) {
            System.out.println("=== " + mes.getNome() + " - Gastos ===");
            System.out.println("1 - Adicionar gasto");
            System.out.println("2 - Ver gastos");
            System.out.println("3 - Zerar gastos");
            System.out.println("4 - Voltar");
            System.out.println("0 - Sair");

            int opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    adicionarGasto(scanner, mes, meses, indice);
                    break;
                case 2:
                    verGastos(mes);
                    break;
                case 3:
                    zerarGastos(mes);
                    break;
                case 4:
                    return false;
                case 0:
                    return true;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarGasto(Scanner scanner, Mes mes, Mes[] meses, int indice) {
        scanner.nextLine();

        System.out.println("Insira o nome do gasto: ");
        String nome = scanner.nextLine();

        System.out.println("Insira o valor do gasto: ");
        double valor = lerDouble(scanner);

        System.out.println("Quantidade de parcelas (0 ou 1 = à vista): ");
        int totalParcelas = lerInteiro(scanner);

        Gasto gasto = new Gasto(nome, valor, totalParcelas);
        mes.adicionarGasto(gasto);

        for (int i = 1; i < totalParcelas; i++) {
            if (indice + i < meses.length) {
                Gasto novaParcela = new Gasto(nome, valor, totalParcelas);
                for (int j = 0; j < i; j++) {
                    novaParcela.avancarParcela();
                }
                meses[indice + i].adicionarGasto(novaParcela);
            }
        }

        if (totalParcelas > 1) {
            System.out.println("Parcelas do gasto " + nome + " adicionadas: " + totalParcelas);
        } else {
            System.out.println("Valor à vista adicionado.");
        }
    }

    private static void verGastos(Mes mes) {
        System.out.println("=== " + mes.getNome() + " - Gastos ===");
        if (mes.getGastos().isEmpty()) {
            System.out.println("Nenhum gasto adicionado ao mês.");
            return;
        }
        for (Gasto g : mes.getGastos()) {
            if (g.isAVista()) {
                System.out.println(g.getNome() + " - R$" + g.getValor());
            } else {
                System.out.println(g.getNome() + " " + g.getParcelaAtual() + "/" + g.getTotalParcelas()
                        + " - R$" + g.getValor());
            }
        }
    }

    private static void zerarGastos(Mes mes) {
        mes.zerarGastos();
        System.out.println("Gastos de " + mes.getNome() + " zerados com sucesso!");
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Digite um número válido.");
            scanner.next(); // descarta o token inválido
        }
        return scanner.nextInt();
    }

    private static double lerDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Digite um valor válido (ex: 49.90).");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
