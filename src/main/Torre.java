public class Torre extends Peca {

  public Torre(String cor, int coluna, int linha) {
    super(cor, coluna, linha);

  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] tabuleiro) {

    if (novaLinha != linha && novaColuna != coluna) {
      return false;
    }

    int passoLinha = Integer.compare(novaLinha, linha);
    int passoColuna = Integer.compare(novaColuna, coluna);

    int atualLinha = linha + passoLinha;
    int atualColuna = coluna + passoColuna;

    while (atualLinha != novaLinha) {

      if (tabuleiro[atualLinha][atualColuna] != null) {
        return false;
      }
      atualLinha += passoLinha;
      atualColuna += passoColuna;
    }

    if (tabuleiro[novaLinha][novaColuna] != null && tabuleiro[novaLinha][novaColuna].getCor().equals(this.cor)) {
      return false;
    }
    return true;

  }

  @Override
  public String getSimbolo() {
    if(cor.equalsIgnoreCase("Branco") || cor.equalsIgnoreCase("Branca")){
      return "T";
    } 
    return "t";
  }
}
