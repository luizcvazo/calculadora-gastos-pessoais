import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        Mes [] meses = new Mes[12];
        meses [0] = new Mes("Janeiro");
        meses [1] = new Mes("Fevereiro");
        meses [2] = new Mes("Março");
        meses [3] = new Mes("Abril");
        meses [4] = new Mes("Maio");
        meses [5] = new Mes("Junho");
        meses [6] = new Mes("Julho");
        meses [7] = new Mes("Agosto");
        meses [8] = new Mes("Setembro");
        meses [9] = new Mes("Outubro");
        meses [10] = new Mes("Novembro");
        meses [11] = new Mes("Dezembro");

        int pagina = 1;

        while (true) {
            if (pagina == 1) {
                System.out.println("=== Calculadora de Gastos Mensais. ===");
                System.out.println("Selecione o mês:");
                System.out.println("1 - Janeiro");
                System.out.println("2 - Fevereiro");
                System.out.println("3 - Março");
                System.out.println("4 - Abril");
                System.out.println("5 - Maio");
                System.out.println("6 - Junho");
                System.out.println("7 - Julho");
                System.out.println("8 - Agosto");
                System.out.println("9 - Próximo");
            } else {
                System.out.println("=== Calculadora de Gastos Mensais. ===");
                System.out.println("Selecione o mês:");
                System.out.println("1 - Setembro");
                System.out.println("2 - Outubro");
                System.out.println("3 - Novembro");
                System.out.println("4 - Dezembro");
                System.out.println("5 - Voltar");
                System.out.println("0 - Sair");
            }

            int opcao = scanner.nextInt();

            if (opcao == 0) break;

            if (pagina == 1) {
                switch (opcao){
                    case 1:
                        menuMes(scanner, meses[0], meses, 0);
                        break;
                    case 2:
                        menuMes(scanner, meses[1], meses, 1);
                        break;
                    case 3:
                        menuMes(scanner, meses[2], meses, 2);
                        break;
                    case 4:
                        menuMes(scanner, meses[3], meses, 3);
                        break;
                    case 5:
                        menuMes(scanner, meses[4], meses, 4);
                        break;
                    case 6:
                        menuMes(scanner, meses[5], meses, 5);
                        break;
                    case 7:
                        menuMes(scanner, meses[6], meses, 6);
                        break;
                    case 8:
                        menuMes(scanner, meses[7], meses, 7);
                        break;
                    case 9:
                        System.out.println("Próximo");
                        pagina = 2;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } else {
                switch (opcao) {
                    case 1:
                        menuMes(scanner, meses[8], meses, 8);
                        break;
                    case 2:
                        menuMes(scanner, meses[9], meses, 9);
                        break;
                    case 3:
                        menuMes(scanner, meses[10], meses, 10);
                        break;
                    case 4:
                        menuMes(scanner, meses[11], meses, 11);
                        break;
                    case 5:
                        System.out.println("Voltar");
                        pagina = 1;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }

    }

}   public static void menuMes(Scanner scanner, Mes mes, Mes[] meses, int indice){

        while (true) {
            System.out.println("=== " + mes.getNome() + " - Gastos ===");
            System.out.println("1 - Adicionar gasto.");
            System.out.println("2 - Ver gastos.");
            System.out.println("3 - Zerar gastos.");
            System.out.println("4 - Voltar.");
            System.out.println("0 - Sair.");

            int opcao = scanner.nextInt();

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
                    return;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }

        }

    }

    public static void adicionarGasto(Scanner scanner, Mes mes, Mes[] meses, int indice) {

        scanner.nextLine();

        System.out.println("Insira o nome do gasto: ");
        String nome = scanner.nextLine();

        System.out.println("Insira o valor do gasto: ");
        double valor = scanner.nextDouble();

        System.out.println("Quantidade de parcelas: ");
        int parcela = scanner.nextInt();

        Gasto gasto = new Gasto(nome, valor, parcela);
        mes.adicionarGasto(gasto);

        for (int i = 1; i < parcela; i++){
            if (indice + i < 12) {
                Gasto novoGasto = new Gasto(nome, valor, parcela);

                for (int j = 0; j < i; j++){
                    novoGasto.avancarParcela();
                }
                meses[indice + i].adicionarGasto(novoGasto);

            }
        }

        if (parcela > 1) {
            System.out.println("Parcelas do gasto " + nome + "adicionados: " + parcela);
        } else {
            System.out.println("Valor à vista.");
        }

    }

    public static void verGastos(Mes mes) {

        System.out.println("=== " + mes.getNome() + " - Gastos ===");
        if (mes.getGastos().isEmpty()) {
            System.out.println("Nenhum gasto adicionado ao mês.");
        } else {
            for (Gasto g : mes.getGastos()) {
                if (g.getParcelas() == 0) {
                    System.out.println(g.getNome() + " - R$" + g.getValor());
                } else {
                    System.out.println(g.getNome() + " " + g.getParcelaAtual() + "/" + g.getParcelas() + " - R$" + g.getValor());
                }
            }
        }
    }
    public static void zerarGastos(Mes mes) {
        mes.zerarGastos();
        System.out.println("Gastos de " + mes.getNome() + " zerados com sucesso!");
    }
}
