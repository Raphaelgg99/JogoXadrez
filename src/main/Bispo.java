public class Bispo extends Peca {

  public Bispo(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
    //TODO Auto-generated constructor stub
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] peca, Tabuleiro tabuleiro) { 

    if (Math.abs(novaLinha - linha) != Math.abs(novaColuna - coluna)) {
    return false;
    }


    int passoLinha = Integer.compare(novaLinha, linha);  
    int passoColuna = Integer.compare(novaColuna, coluna); 
    
    int atualLinha = linha + passoLinha;  
    int atualColuna = coluna + passoColuna;
    
    while(atualLinha != novaLinha || atualColuna != novaColuna){ 

      if(peca[atualLinha][atualColuna]!=null){
        return false;
      }
      
      atualLinha += passoLinha;  
      atualColuna += passoColuna;
    
    }  

    if(peca[novaLinha][novaColuna] != null && peca[novaLinha][novaColuna].getCor().equals(this.cor)){
        return false;
      } 

    return true;
  }

  @Override
  public String getSimbolo() {
    if(cor.equalsIgnoreCase("Branco") || cor.equalsIgnoreCase("Branca")){
      return "B";
    } 
    return "b";
  }
  
}
