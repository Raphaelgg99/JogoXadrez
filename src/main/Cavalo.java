public class Cavalo extends Peca {

  public Cavalo(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
    //TODO Auto-generated constructor stub
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] peca, Tabuleiro tabuleiro) {
    int dLinha = Math.abs(novaLinha - linha); 
    int dColuna = Math.abs(novaColuna - coluna); 

    if((dLinha == 2 && dColuna == 1) || (dColuna == 2 && dLinha == 1)){ 
      if(peca[novaLinha][novaColuna] != null && peca[novaLinha][novaColuna].getCor().equals(this.cor)){
        return false;
      } 
      return true;
    } 
    return false;
  }

  @Override
  public String getSimbolo() {
    if(cor.equalsIgnoreCase("Branco") || cor.equalsIgnoreCase("Branca")){
      return "C";
    } 
    return "c";
  }

}
