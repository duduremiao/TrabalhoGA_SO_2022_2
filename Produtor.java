import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Produtor extends Thread {

    public static final String RESET = "\033[0m";
    public static final String VERMELHO = "\033[0;31m";
    public static final String VERDE = "\033[0;32m";
    public static final String AZUL = "\033[0;34m";

    private final Buffer buffer;

    private final String nome;
    private final char tipoProduto;
    private int valorID;

    public Produtor(Buffer buffer, String nome, char tipoProduto) {
        super(nome);
        this.buffer = buffer;
        this.nome = nome;
        this.valorID = 1;
        this.tipoProduto = tipoProduto;
    }


    public int geraTempoAleatorioProdutor() {
        Random random = new Random();

        return (random.nextInt((10 - 5) + 1) + 5);
    }

    public char geraQualidade(){
        char qualidade = 'R';
        Random random = new Random();
        int num = random.nextInt(10 + 1) + 1;
        if (num % 2 == 0)
            qualidade = 'B';

        return qualidade;
    }


    @Override
    public void run() {
        while (true) {

            String cor;
            int id = valorID;
            valorID++;
            int tempoEspera = geraTempoAleatorioProdutor();
            char qualidadeProduto = geraQualidade();
            Produto produto = new Produto(id, tempoEspera, qualidadeProduto, this.tipoProduto, this.nome);

            switch (tipoProduto){
                case 'T':
                    cor = VERDE;
                    break;
                case 'Q':
                    cor = VERMELHO;
                    break;
                case 'C':
                    cor = AZUL;
                    break;
                default:
                    cor = RESET;
                    break;
            }

            try {
                SECONDS.sleep(tempoEspera);
                Produto ret = null;
                if (!buffer.isFull())
                    ret = buffer.insereProduto(produto);
                if (ret != null)
                    System.out.println("\n"+cor+this.nome + ">>> produzindo:\n " + ret);
                System.out.print(RESET);

            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

    }
}

