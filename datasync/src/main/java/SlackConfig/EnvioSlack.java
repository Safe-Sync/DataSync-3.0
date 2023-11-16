package SlackConfig;

import org.json.JSONObject;
import java.io.IOException;



public class EnvioSlack {
    public static void main(String[] args) throws IOException, InterruptedException {
        JSONObject json = new JSONObject();


        Double cpu = 89.03;
        String mensagem = String.format("Seu uso atual de CPU é %.2f%% Ultrapassando o Limite de 60%% definido pela empresa \uD83D\uDE15", cpu);



        json.put("text", mensagem);
        Slack.enviarMensagem(json);
    }
}
