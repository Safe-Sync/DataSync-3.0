package entidades;

import SlackConfig.Slack;
import org.json.JSONObject;
import java.io.IOException;
public class EnvioNotificacao {
    public void EnvioDados ()  throws IOException, InterruptedException{
        JSONObject json = new JSONObject();

        DadosVolateis dados = new DadosVolateis();
        Double consumoRam = dados.getConsumoRam();
        Double consumoCpu = dados.getConsumoCpu();


        if(consumoRam > 13.0){
            String mensagem = String.format("A sua memória RAM ultrapassou o Limite definido pela empresa CUIDADO! \uD83D\uDEA8 %.1f Gb \n", consumoRam);
            json.put("text", mensagem);
            Slack.enviarMensagem(json);
        }else if(consumoRam <= 12.9) {
            String mensagem = String.format("A memória RAM está perto do limite ATENÇÂO \uD83D\uDEA7 %.1f Gb", consumoRam);
            json.put("text", mensagem);
            Slack.enviarMensagem(json);
        }

        if(consumoCpu > 2.0 && consumoCpu < 4.0){
            String mensagem = String.format("O seu consumo da CPU está perto do limite, atenção!  \uD83D\uDEA7 %.1f Ghz", consumoCpu);
            json.put("text", mensagem);
            Slack.enviarMensagem(json);
        }else if(consumoCpu >= 4.1){
            String mensagem = String.format("O seu consumo da CPU está perto do limite, atenção! \uD83D\uDEA8  %.1f Ghz", consumoCpu);
            json.put("text", mensagem);
            Slack.enviarMensagem(json);
        }
    }
}
