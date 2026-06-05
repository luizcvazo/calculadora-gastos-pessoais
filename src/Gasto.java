public class Gasto {

    String nome;
    double valor;
    int parcelas;
    int parcelaAtual;

    public Gasto(String nome, double valor, int parcelas){
        this.nome = nome;
        this.valor = valor;
        this.parcelas = parcelas;
        this.parcelaAtual = 1;
    }

    public String getNome(){
        return nome;
    }

    public double getValor(){
        return valor;
    }

    public int getParcelas(){
        return parcelas;
    }

    public int getParcelaAtual(){
        return parcelaAtual;
    }

    public void avancarParcela() {
        parcelaAtual++;
    }

}

