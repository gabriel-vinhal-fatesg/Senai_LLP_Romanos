import java.util.Scanner;

public class RomanoParaInt {
    public static int obterValor(char caractere) {
        switch (caractere) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10; 
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default : return 0;
        }
        
    }

    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        System.out.println("Digite um número romano: ");
        String numeroRomano = leia.nextLine().toUpperCase();
        int resultado = 0;

        for(int i = 0; i < numeroRomano.length(); i++) {
            int valorAtual = obterValor(numeroRomano.charAt(i));
            int valorProximo = 0;

            if (i + 1 < numeroRomano.length()) {
                valorProximo = obterValor(numeroRomano.charAt(i + 1));
            }

            if (valorAtual < valorProximo) {
                resultado -= valorAtual;
            } else {
                resultado += valorAtual;
            }
        }
        System.out.println(resultado);

    }
}