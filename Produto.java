public class Produto {


    private final int id;
    private final double tempo;

    private final char qualidade;
    private final char tipo;
    private final String origem;

    public Produto(int id, double tempo, char qualidade, char tipo, String origem) {
        this.id = id;
        this.tempo = tempo;
        this.qualidade = qualidade;
        this.tipo = tipo;
        this.origem = origem;
    }

    public char getTipo() {
        return tipo;
    }

    public char getQualidade() {
        return qualidade;
    }

    @Override
    public String toString() {
        String qualidadeStr= "";
        String tipoStr= "";

        if (qualidade == 'B')
            qualidadeStr = "Boa";
        else if (qualidade == 'R')
            qualidadeStr = "Ruim";

        if (tipo == 'C')
            tipoStr = "Circulo";
        else if (tipo == 'T')
            tipoStr = "Triângulo";
        else if (tipo == 'Q')
            tipoStr = "Quadrado";


        return "[\n" +
                "ID: " + id +
                "\nTEMPO: "+ tempo +" s"+
                " || QUALIDADE: " + qualidadeStr +
                " || TIPO: " + tipoStr +
                "\nORIGEM: " + origem +
                "\n]";
    }
}
