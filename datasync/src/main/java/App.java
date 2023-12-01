import entidades.Sistema;
import entidades.EnvioNotificacao;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        EnvioNotificacao enviar = new EnvioNotificacao();
        Sistema sistema = new Sistema();

        Thread threadSistema = new Thread(() -> {
            sistema.mensagemBoasVindas();
        });
        threadSistema.start();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    enviar.EnvioDados();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 15000); // 30 segundos em milissegundos
    }
}
