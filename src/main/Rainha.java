public class Rainha extends Peca {

  public Rainha(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
    //TODO Auto-generated constructor stub
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] tabuleiro) { 
    
    if((Math.abs(novaLinha - linha) != Math.abs(novaColuna - coluna)) && (novaLinha != linha && novaColuna != coluna) ){
        return false;
    } 
    
    int passoLinha = Integer.compare(novaLinha, linha);
    int passoColuna = Integer.compare(novaColuna, coluna);

    int atualLinha = linha + passoLinha;
    int atualColuna = coluna + passoColuna; 

    while(atualLinha != novaLinha || atualColuna != novaColuna){ 

      if(tabuleiro[atualLinha][atualColuna]!=null){
        return false;
      }
      
      atualLinha += passoLinha;  
      atualColuna += passoColuna;
    } 
    
    if(tabuleiro[novaLinha][novaColuna] != null && tabuleiro[novaLinha][novaColuna].getCor().equals(this.cor)){
    return false;
    } 
    return true;  
  }

  @Override
  public String getSimbolo() {
    if(cor.equalsIgnoreCase("Branco") || cor.equalsIgnoreCase("Branca")){
      return "Ra";
    } 
    return "ra";
  } 

}
