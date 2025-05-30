public class Tabuleiro { 

  Peca[][] peca = new Peca[8][8]; 

  public Tabuleiro() {
    iniciar();
  }

  public void iniciar(){
    // Peças pretas na linha 0
    peca[0][0] = new Torre("Preta", 0, 0);  
    peca[0][1] = new Cavalo("Preta", 1, 0);
    peca[0][2] = new Bispo("Preta", 2, 0); 
    peca[0][3] = new Rainha("Preta", 3, 0); 
    peca[0][4] = new Rei("Preta", 4, 0); 
    peca[0][5] = new Bispo("Preta", 5, 0); 
    peca[0][6] = new Cavalo("Preta", 6, 0);  
    peca[0][7] = new Torre("Preta", 7, 0); 

    // Peões pretos na linha 1
    for(int i = 0; i < 8; i++){  
      peca[1][i] = new Peao("Preta", i, 1); 
    } 

    // Peças brancas na linha 7
    peca[7][0] = new Torre("Branca", 0, 7);  
    peca[7][1] = new Cavalo("Branca", 1, 7);  
    peca[7][2] = new Bispo("Branca", 2, 7); 
    peca[7][3] = new Rainha("Branca", 3, 7); 
    peca[7][4] = new Rei("Branca", 4, 7); 
    peca[7][5] = new Bispo("Branca", 5, 7); 
    peca[7][6] = new Cavalo("Branca", 6, 7); 
    peca[7][7] = new Torre("Branca", 7, 7);  
    // Peões brancos na linha 6
    for(int i = 0; i < 8; i++){  
      peca[6][i] = new Peao("Branca", i, 6); 
    } 
  } 

  public void imprimirTabuleiro(){
    for(int l = 0; l < 8; l++){  
      System.out.print((8 - l) + " ");
      for(int c = 0; c < 8; c++){
        if(peca[l][c] == null){
          System.out.print("- ");
        } 
        else{
          System.out.print(peca[l][c].getSimbolo() + " ");
        }
      } 
      System.out.println();
    } 
    System.out.println("  a b c d e f g h");
  } 

  public boolean moverPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino){
    Peca p = peca[linhaOrigem][colunaOrigem]; 
    if(!p.movimentoValido(linhaDestino, colunaDestino, peca))return false; 
    if(p==null)return false; 
    peca[linhaDestino][colunaDestino] = p; 
    peca[linhaOrigem][colunaOrigem] = null; 
    p.setPosicao(linhaDestino, colunaDestino); 
    return true;
  }

}
