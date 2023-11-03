import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.janelas.Janela;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Processador processador = new Processador();
        DiscoGrupo disco = new DiscoGrupo();
        Memoria memoria = new Memoria();

        Double usoProcessador = processador.getUso();

        Volume volume = disco.getVolumes().get(0);

        Double discoTotal = volume.getTotal().doubleValue() / 1000000000;
        Double discoDisponivel = volume.getDisponivel().doubleValue() / 1000000000;
        Double discoUso = discoTotal - discoDisponivel;

        Double memoriaTotal = memoria.getTotal().doubleValue() / 1000000000;
        Double memoriaUso = memoria.getEmUso().doubleValue() / 1000000000;
        Double memoriaDisponivel = memoria.getDisponivel().doubleValue() / 1000000000;

        System.out.println(String.format("Uso do processador: %.2f GHz\n", usoProcessador));

        System.out.println(String.format("Disco total: %.2f Gb", discoTotal));
        System.out.println(String.format("Disco disponível: %.2f Gb", discoDisponivel));
        System.out.println(String.format("Disco em uso: %.2f Gb\n", discoUso));

        System.out.println(String.format("Memória RAM total: %.2f Gb", memoriaTotal));
        System.out.println(String.format("Memória RAM disponível: %.2f Gb", memoriaDisponivel));
        System.out.println(String.format("Memória RAM em uso: %.2f Gb\n", memoriaUso));

//        List<Janela> janelas = looca.getGrupoDeJanelas().getJanelasVisiveis();
//        List<String> stringsJanelas = new ArrayList<>();
//
//        for (Janela janela : janelas) {
//            String stringJanela = janela.getTitulo();
//            stringsJanelas.add(stringJanela);
//            System.out.println(stringJanela);
//        }
    }
}
