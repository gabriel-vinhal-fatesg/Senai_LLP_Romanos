import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, int[]> times = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("jogos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains("ADI") || !linha.contains("x")) continue;
                linha = linha.replaceAll("\\\\s*", "");
                String[] partes = linha.split(",");
                if (partes.length < 4) continue;
                String time1 = partes[1].trim().replace("_", " ");
                String time2 = partes[2].trim().replace("_", " ");
                String[] placar = partes[3].trim().split("x");
                int gols1 = Integer.parseInt(placar[0].trim());
                int gols2 = Integer.parseInt(placar[1].trim());
                times.putIfAbsent(time1, new int[2]);
                times.putIfAbsent(time2, new int[2]);
                times.get(time1)[0] += (gols1 > gols2) ? 3 : (gols1 == gols2 ? 1 : 0);
                times.get(time2)[0] += (gols2 > gols1) ? 3 : (gols1 == gols2 ? 1 : 0);
                times.get(time1)[1] += (gols1 - gols2);
                times.get(time2)[1] += (gols2 - gols1);
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            return;
        }

        ArrayList<String> tabela = new ArrayList<>(times.keySet());
        tabela.sort((a, b) -> {
            int[] dados1 = times.get(a);
            int[] dados2 = times.get(b);
            if (dados2[0] != dados1[0]) return Integer.compare(dados2[0], dados1[0]);
            return Integer.compare(dados2[1], dados1[1]);
        });

        System.out.println("+----+-----------------------+--------+-------+");
        System.out.printf("| %-2s | %-21s | %-6s | %-5s |\n", "#", "Time", "Pontos", "Saldo");
        System.out.println("+----+-----------------------+--------+-------+");
        for (int i = 0; i < tabela.size(); i++) {
            String nome = tabela.get(i);
            System.out.printf("| %-2s | %-21s | %-6d | %-5d |\n", 
                    (i + 1) + "º", nome, times.get(nome)[0], times.get(nome)[1]);
        }
        System.out.println("+----+-----------------------+--------+-------+");
    }
}