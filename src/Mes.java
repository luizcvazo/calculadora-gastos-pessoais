import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mes {

    private final String nome;
    private final List<Gasto> gastos;

    public Mes(String nome) {
        this.nome = nome;
        this.gastos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Gasto> getGastos() {
        return Collections.unmodifiableList(gastos);
    }

    public void adicionarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void zerarGastos() {
        gastos.clear();
    }
}
