import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        Historico historicoConsumo = new Historico();
        Historico pegarLogin = new Historico();

        String caminhoPasta = System.getProperty("user.home") + "/Desktop/" + "logs";
        File pasta = new File(caminhoPasta);

        if (!pasta.exists()) {
            if (pasta.mkdir()) {
                System.out.println();
            } else {
                System.out.println();
            }
        } else {
            System.out.println();
        }

        try {
            historicoConsumo.logarFuncionario();
        } catch (Exception e) {
            String erro = e.getMessage();
            String horaColeta = new SimpleDateFormat(" HH:mm:ss  yyyy-MM-dd").format(new Date());


            File logFile = new File(System.getProperty("user.home") + "/Desktop/logs/logsBanco.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write("Erro: " + erro + " - Hora da coleta: " + horaColeta);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("Erro na inserção " + erro);
            System.exit(1);
        }

    }
}
