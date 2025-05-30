public class Peao extends Peca {

  public Peao(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
    //TODO Auto-generated constructor stub
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] tabuleiro) { 

    int direcao = this.cor.equals("Branca") ? -1 : 1; // Brancas sobem (linha decresce), pretas descem (linha cresce)
    int dLinha = novaLinha - linha;
    int dColuna = Math.abs(novaColuna - coluna);

    // Movimento para frente 1 casa
    if (dLinha == direcao && dColuna == 0) {
      if (tabuleiro[novaLinha][novaColuna] == null) {
        return true;
      }
    }

    // Movimento para frente 2 casas (primeiro movimento)
    if (dLinha == 2 * direcao && dColuna == 0) {
      int linhaIntermediaria = linha + direcao;
      if (!this.jaMoveu && tabuleiro[linhaIntermediaria][coluna] == null && tabuleiro[novaLinha][novaColuna] == null) {
        return true;
      }
    }

    // Captura diagonal (1 casa para frente e 1 para lado)
    if (dLinha == direcao && dColuna == 1) {
      Peca pecaDestino = tabuleiro[novaLinha][novaColuna];
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

}
