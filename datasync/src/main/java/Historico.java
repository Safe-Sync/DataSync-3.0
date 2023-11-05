import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import conexao.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.Timer;
import java.sql.Timestamp;
import java.util.TimerTask;


public class Historico {
        Looca looca = new Looca();
        Conexao conectar = new Conexao();
        JdbcTemplate conexao = conectar.getConexao();
        Date data = new Date();
        Timer tempoInsercao = new Timer();

        Processador processador = new Processador();
        DiscoGrupo disco = new DiscoGrupo();
        Memoria memoria = new Memoria();
        Sistema sistemaOperacional = new Sistema();

        String nomeSistema = sistemaOperacional.getSistemaOperacional();
        String versaoSistema = sistemaOperacional.getFabricante();

        Double usoProcessador = processador.getUso();

        Volume volume = disco.getVolumes().get(0);

        Double discoTotal = volume.getTotal().doubleValue() / 1000000000;
        Double discoDisponivel = volume.getDisponivel().doubleValue() / 1000000000;
        Double discoUso = discoTotal - discoDisponivel;

        Double memoriaTotal = memoria.getTotal().doubleValue() / 1000000000;
        Double memoriaUso = memoria.getEmUso().doubleValue() / 1000000000;
        Double memoriaDisponivel = memoria.getDisponivel().doubleValue() / 1000000000;

        Integer totalJanelas = looca.getGrupoDeJanelas().getTotalJanelas();


    public void inserirHardware() {
        conexao.update("INSERT INTO hardwares (idHardware, sistemaOperacional, totalCpu, totalDisco, totalRam) VALUES (?, ?, ?, ?, ?)",
                1, nomeSistema, usoProcessador, discoTotal, memoriaTotal);
    }

    Integer idInsercao = 0;
    public void inserirVolatil() {
        tempoInsercao.schedule(new TimerTask() {
            @Override
            public void run() {
                Date data = new Date(); // Obtenha a hora da coleta dentro do método run()
                conexao.update("INSERT INTO volateis (idVolateis, consumoCpu, consumoDisco, consumoRam, totalJanelas, dataHora) VALUES (?, ?, ?, ?, ?, ?)",
                        idInsercao, usoProcessador, discoUso, memoriaUso, totalJanelas, new Timestamp(data.getTime()));
                System.out.println(String.format("""
        |===========================================|
        |        Sistema de captura Safe Sync       |
        |===========================================|
        |                                           |
        | Data Coleta: %s      |
        |                                           |
        | Sistema Operacional: %s               |
        | Fabricante: %s                     |
        |                                           |
        | Uso do processador: %.2f                 |
        |                                           |
        | Espaço de disco total: %.2f Gb          |
        | Espaço de disco em uso: %.2f Gb          |
        | Espaço disponível no disco: %.2f Gb      |
        |                                           |
        | Memória RAM total: %.2f Gb                | 
        | Memória RAM disponível: %.2f Gb           |
        | Memória RAM em uso: %.2f Gb               |             
        |                                           |
        |===========================================|
        """, new Timestamp(data.getTime()), nomeSistema, versaoSistema, usoProcessador, discoTotal, discoUso, discoDisponivel, memoriaTotal, memoriaDisponivel, memoriaUso));
            }
        }, 10000, 10000);
    }



}




