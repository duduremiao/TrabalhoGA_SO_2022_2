public class Main {

    public static void main(String[] args) {

        //BUFFER
        Buffer buffer = new Buffer(4);


        //THREADS PRODUTORES
        Thread produtor1 = new Produtor(buffer, "[PRODUTOR CÍRCULO]", 'C');
        Thread produtor2 = new Produtor(buffer, "[PRODUTOR TRIÂNGULO]", 'T');
        Thread produtor3 = new Produtor(buffer, "[PRODUTOR QUADRADO]", 'Q');

        //THREADS CONSUMIDORES
        Thread consumidor1 = new Consumidor(buffer,"[CONSUMIDOR BOM]", 'B');
        Thread consumidor2 = new Consumidor(buffer, "[CONSUMIDOR RUIM]", 'R');


        //PRODUTORES
        produtor1.start();
        produtor2.start();
        produtor3.start();

        //CONSUMIDORES
        consumidor1.start();
        consumidor2.start();

    }
}