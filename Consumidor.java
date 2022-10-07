import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

class Consumidor extends Thread {
    private final Buffer buffer;
    private final String nome;
    private final char classificacao;
    public static final String RESET = "\033[0m";
    public static final String YELLOW = "\033[0;33m";

    private int totalConsumidos = 0;
    private int triangulosConsumidos = 0;
    private int circulosConsumidos = 0;
    private int quadradosConsumidos = 0;
    public void esperaTempoAleatorioConsumidor() throws InterruptedException {
        Random random = new Random();

        int tempoEspera = (random.nextInt((3 - 1) + 1) + 3) ;
        SECONDS.sleep(tempoEspera);
    }

    public Consumidor(Buffer buffer, String nome, char classificacao) {
        super(nome);
        this.buffer = buffer;
        this.nome = nome;
        this.classificacao = classificacao;
    }


    private void incrementaConsumidos(char tipo){
        switch (tipo){
            case 'T':
                triangulosConsumidos++;
                break;
            case 'Q':
                quadradosConsumidos++;
                break;
            case 'C':
                circulosConsumidos++;
                break;
        }

        totalConsumidos++;
    }

    @Override
    public void run() {

        while (true) {

            try {
                esperaTempoAleatorioConsumidor();
                if (!buffer.isEmpty()) {
                    Produto consumido = buffer.getProduto();
                    if (consumido != null) {
                        if (consumido.getQualidade() == this.classificacao) {
                            consumido = buffer.consomeProduto();
                            incrementaConsumidos(consumido.getTipo());
                            System.out.println("\n" + this.nome + ">> consumindo produto \n" + consumido+ "\n"+
                                    YELLOW + this.nome + "--->" + totalConsumidos + " produtos consumidos\n" +
                                    "Triângulos consumidos: " + triangulosConsumidos + " || Círculos consumidos: " + circulosConsumidos +
                                    " || Quadrados consumidos: " + quadradosConsumidos + "\n"
                                    + "PRODUTOS NO BUFFER: " + buffer.size() + " ||| CAPACIDADE: " + buffer.getCapacidade());
                            System.out.print(RESET);
                        }
                    }
                }
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}


