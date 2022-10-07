class Buffer {
    private final Produto[] produtos;      // produtos para armazenar os elementos da fila
    private int topo;      //  elemento topo da fila
    private int base;       // elemento base da fila
    private final int capacidade;   // capacidade máxima da fila
    private int tamanhoFila;      // tamanho atual da fila

    // Construtor para inicializar uma fila de buffer
    Buffer(int capacidadeBuffer) {
        produtos = new Produto[capacidadeBuffer];
        capacidade = capacidadeBuffer;
        topo = 0;
        base = -1;
        tamanhoFila = 0;
    }

    public int size() {
        return tamanhoFila;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public Boolean isEmpty() {
        return (size() == 0);
    }

    public Boolean isFull() {
        return (size() == getCapacidade());
    }

    //synchronized: mutex
    public synchronized Produto insereProduto(Produto p) {
        while (isFull()) {
            try {
                wait();//agurada esvaziar
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

        base = (base + 1) % capacidade;
        produtos[base] = p;
        tamanhoFila++;

        notifyAll();//acorda todos os encadeamentos aguardando o objeto
        return p;

    }

    public synchronized Produto consomeProduto() {
        while (isEmpty()) {
            try {
                wait();//aguarda possuir algum produto
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        Produto result = produtos[topo];

        topo = (topo + 1) % capacidade;
        tamanhoFila--;
        notifyAll();
        return result;
    }

    public synchronized Produto getProduto() {
        while (isEmpty()) {
            try {
                wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return produtos[topo];
    }


}

