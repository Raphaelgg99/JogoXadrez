public class Peao extends Peca {

  public Peao(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
    //TODO Auto-generated constructor stub
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] peca, Tabuleiro tabuleiro) { 

    int direcao = this.cor.equals("Branca") ? -1 : 1; // Brancas sobem (linha decresce), pretas descem (linha cresce)
    int dLinha = novaLinha - linha;
    int dColuna = Math.abs(novaColuna - coluna);

    // Movimento para frente 1 casa
    if (dLinha == direcao && dColuna == 0) {
      if (peca[novaLinha][novaColuna] == null) {
        return true;
      }
    }

    // Movimento para frente 2 casas (primeiro movimento)
    if (dLinha == 2 * direcao && dColuna == 0) {
      int linhaIntermediaria = linha + direcao;
      if (!this.jaMoveu && peca[linhaIntermediaria][coluna] == null && peca[novaLinha][novaColuna] == null) {
        return true;
      }
    }

    // Captura diagonal (1 casa para frente e 1 para lado)
    if (dLinha == direcao && dColuna == 1) {
      Peca pecaDestino = peca[novaLinha][novaColuna];
      if (pecaDestino != null && !pecaDestino.getCor().equals(this.cor)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public String getSimbolo() {
    if(cor.equalsIgnoreCase("Branco") || cor.equalsIgnoreCase("Branca")){
      return "P";
    } 
    return "p";
  } 

  public boolean enPassant(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Peca[][] tabuleiro) {
        // Verificar se o peão adversário se moveu duas casas
        if (Math.abs(colunaDestino - colunaOrigem) == 1 && linhaDestino == linhaOrigem + (this.getCor().equals("Branca") ? 1 : -1)) {
            // Pega a peça adversária na casa de destino
            Peca pecaAdversario = tabuleiro[linhaDestino][colunaDestino]; // Acessando a peça no tabuleiro

            // Verificar se é um peão adversário e não da mesma cor
            if (pecaAdversario != null && pecaAdversario instanceof Peao 
                && !pecaAdversario.getCor().equals(this.getCor())) { // Verifica se é o peão adversário

                // Verificar se a casa ao lado está vazia para permitir a captura
                if (tabuleiro[linhaOrigem][colunaDestino] == null) {
                    // Realiza a captura En Passant
                    tabuleiro[linhaDestino][colunaDestino] = null; // Remove o peão adversário
                    tabuleiro[linhaDestino][colunaDestino] = this; // Coloca o peão no lugar do adversário
                    return true;
                }
            }
        }
        return false;
    }

}
