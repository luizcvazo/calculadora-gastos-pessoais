import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Salva e carrega os gastos em um arquivo de texto simples (formato CSV com ";").
 * Cada linha representa um gasto: mes;nome;valor;totalParcelas;parcelaAtual
 */
public class Persistencia {

    private static final String ARQUIVO = "gastos.dat";

    public static void salvar(Mes[] meses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Mes mes : meses) {
                for (Gasto g : mes.getGastos()) {
                    writer.write(mes.getNome() + ";" + g.getNome() + ";" + g.getValor()
                            + ";" + g.getTotalParcelas() + ";" + g.getParcelaAtual());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível salvar os dados: " + e.getMessage());
        }
    }

    public static void carregar(Mes[] meses) {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return; // primeira execução, ainda não existe nada salvo
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                carregarLinha(linha, meses);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar os dados salvos: " + e.getMessage());
        }
    }

    private static void carregarLinha(String linha, Mes[] meses) {
        String[] partes = linha.split(";");
        if (partes.length < 5) {
            return; // linha corrompida ou incompleta, ignora
        }

        try {
            String nomeMes = partes[0];
            String nomeGasto = partes[1];
            double valor = Double.parseDouble(partes[2]);
            int totalParcelas = Integer.parseInt(partes[3]);
            int parcelaAtual = Integer.parseInt(partes[4]);

            Mes mes = encontrarMes(meses, nomeMes);
            if (mes == null) {
                return;
            }

            Gasto gasto = new Gasto(nomeGasto, valor, totalParcelas);
            for (int i = 1; i < parcelaAtual; i++) {
                gasto.avancarParcela();
            }
            mes.adicionarGasto(gasto);
        } catch (NumberFormatException e) {
            System.out.println("Linha ignorada (dado inválido): " + linha);
        }
    }

    private static Mes encontrarMes(Mes[] meses, String nome) {
        for (Mes mes : meses) {
            if (mes.getNome().equals(nome)) {
                return mes;
            }
        }
        return null;
    }
}
