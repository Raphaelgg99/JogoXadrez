public abstract class Peca { 

  protected String cor;   
  protected int coluna; 
  protected int linha;
  protected boolean jaMoveu = false; 

  public Peca(String cor, int coluna, int linha){
    this.cor = cor; 
    this.coluna = coluna; 
    this.linha = linha;
  } 

  public String getCor(){
  return cor;
  } 

  public int coluna(){
    return coluna;
  } 

  public int linha(){
    return linha;
  } 

  public void setPosicao(int linha, int coluna) {
      this.linha = linha;
      this.coluna = coluna;
   } 

  public abstract boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] tabuleiro);   

  public abstract String getSimbolo();

  public void setJaMoveu(boolean jaMoveu) {
    this.jaMoveu = jaMoveu;
    }  

  public boolean isJaMoveu(){
    return jaMoveu;
  }

}
