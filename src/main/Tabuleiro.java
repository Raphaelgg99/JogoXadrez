import java.util.Scanner;

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

  public boolean moverPeca(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, Tabuleiro tabuleiro){
    Peca p = peca[linhaOrigem][colunaOrigem]; 
    if(!p.movimentoValido(linhaDestino, colunaDestino, peca, tabuleiro))return false; 
    if(p==null)return false; 
    peca[linhaDestino][colunaDestino] = p; 
    peca[linhaOrigem][colunaOrigem] = null; 
    p.setPosicao(linhaDestino, colunaDestino); 
    return true;
  } 

  public Peca getPeca(int linha, int coluna){
    return peca[linha][coluna];
  } 

  public Peca[][] getTabuleiro() {
    return peca;
} 

  public boolean estaEmXeque(String cor, Tabuleiro tabuleiro){
    int reiLinha = -1, reiColuna = -1; 

    for(int i = 0; i<8; i++){
      for(int j = 0; j < 8; j++){
        Peca p = peca[i][j]; 
        if(p != null && p instanceof Rei && p.getCor().equals(cor)){
          reiLinha = i; 
          reiColuna = j; 
          break;
        } 
      } 
      if(reiLinha !=-1){
          break;
        } 
      if(reiLinha == 1){
        return false;
      } 
    } 
    String corAdversaria = cor.equals("Branca") ? "Preta" : "Branca"; 
    
    for(int i = 0; i<8;i++){
      for(int j = 0; j < 8; j++){
        Peca p = peca[i][j]; 
        if(p!=null && !p.getCor().equals(corAdversaria)){
          if(p.movimentoValido(reiLinha, reiColuna, peca, tabuleiro)){
            return true;
          }
        } 
      }
    }  
    return false;
  }  

  public boolean movimentoLegal(String cor, Tabuleiro tabuleiro){  
    for(int l = 0; l < 8; l++ ){
      for(int c = 0; c < 8; c++){
        Peca p = peca[l][c]; 
        if(p != null && p.getCor().equals(cor)){
          for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
              if(!p.movimentoValido(x,y, peca, tabuleiro)){ 
              Peca temp = peca[x][y];
              peca[x][y] = p; 
              peca[l][c] = null; 
              boolean xeque = estaEmXeque(cor, tabuleiro);  
              peca[l][c]  = p; 
              peca[x][y] = temp; 
              if(!xeque){ 
                return true;
              }
              }
            }
          }
        }
      } 
    } 
    return false;
  }   

  public void promocaoPeao(String cor){ 
    // Criar um scanner fora do loop para não criar múltiplos
    Scanner scanner = new Scanner(System.in);

    // Verificar promoção dos peões na linha 0 (branca)
    for(int c = 0; c < 8; c++){ 
        Peca p = peca[0][c]; 
        if(p != null && p instanceof Peao){   
            // O peão pode ser promovido
            System.out.println("Seu peão foi promovido! Agora escolha o que ele vai se tornar:");
            String pecaEscolhida = scanner.nextLine().toLowerCase();  

            // Repetir até o jogador escolher uma peça válida
            while(!pecaEscolhida.equals("rainha") && !pecaEscolhida.equals("torre") && !pecaEscolhida.equals("bispo") && !pecaEscolhida.equals("cavalo")) {
                System.out.print("Essa peça não está disponível. Escolha novamente (Rainha, Torre, Bispo, Cavalo): ");
                pecaEscolhida = scanner.nextLine().toLowerCase();
            }

            // Criar a peça escolhida
            Peca novaPeca = escolherPeça(pecaEscolhida, cor, c, 0);  // Linha 0 para peões brancos
            peca[0][c] = novaPeca;  // Coloca a nova peça no lugar do peão
        }
    }

    // Verificar promoção dos peões na linha 7 (preta)
    for(int c = 0; c < 8; c++){ 
        Peca p = peca[7][c]; 
        if(p != null && p instanceof Peao){   
            // O peão pode ser promovido
            System.out.println("Seu peão foi promovido! Agora escolha o que ele vai se tornar:");
            String pecaEscolhida = scanner.nextLine().toLowerCase();  

            // Repetir até o jogador escolher uma peça válida
            while(!pecaEscolhida.equals("rainha") && !pecaEscolhida.equals("torre") && !pecaEscolhida.equals("bispo") && !pecaEscolhida.equals("cavalo")) {
                System.out.print("Essa peça não está disponível. Escolha novamente (Rainha, Torre, Bispo, Cavalo): ");
                pecaEscolhida = scanner.nextLine().toLowerCase();
            }

            // Criar a peça escolhida
            Peca novaPeca = escolherPeça(pecaEscolhida, cor, c, 7);  // Linha 7 para peões pretos
            peca[7][c] = novaPeca;  // Coloca a nova peça no lugar do peão
        }
    }
}

  

// Método para escolher a peça a ser promovida
public Peca escolherPeça(String pecaEscolhida, String cor, int coluna, int linha){  
    if(pecaEscolhida.equals("rainha")) {
        return new Rainha(cor, coluna, linha); 
    } 
    if(pecaEscolhida.equals("torre")) {
        return new Torre(cor, coluna, linha); 
    } 
    if(pecaEscolhida.equals("bispo")) {
        return new Bispo(cor, coluna, linha); 
    } 
    if(pecaEscolhida.equals("cavalo")) {
        return new Cavalo(cor, coluna, linha); 
    } 
    return null;  // Se for inválido, retorna null (já tratado pelo loop)
}

 

  public boolean tentarMover(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino, String corJogador, Tabuleiro tabuleiro){ 
    
    Peca pecaOrigem = peca[linhaOrigem][colunaOrigem]; 
    Peca pecaDestino = peca[linhaDestino][colunaDestino]; 

    if(pecaOrigem == null || !pecaOrigem.getCor().equals(corJogador)){ 
      return false;
    } 
    if(!pecaOrigem.movimentoValido(linhaDestino, colunaDestino, peca, tabuleiro)){
      return false;
    } 

    peca[linhaDestino][colunaDestino] = pecaOrigem;
    peca[linhaOrigem][colunaOrigem] = null; 

    boolean estaEmXeque = estaEmXeque(corJogador, tabuleiro); 

    peca[linhaDestino][colunaDestino] = pecaDestino;
    peca[linhaOrigem][colunaOrigem] = pecaOrigem; 

    return !estaEmXeque;

  } 
}
