public class Gasto {

    private final String nome;
    private final double valor;
    private final int totalParcelas;
    private int parcelaAtual;

    public Gasto(String nome, double valor, int totalParcelas) {
        this.nome = nome;
        this.valor = valor;
        this.totalParcelas = totalParcelas;
        this.parcelaAtual = 1;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public int getTotalParcelas() {
        return totalParcelas;
    }

    public int getParcelaAtual() {
        return parcelaAtual;
    }

    public void avancarParcela() {
        parcelaAtual++;
    }

    public boolean isAVista() {
        return totalParcelas <= 1;
    }
}
