import java.util.Scanner;

public class JogoConsole { 

  private static final Scanner scanner = new Scanner(System.in); 
  private String vez = "Branca"; 
  private Tabuleiro tabuleiro; 
  
  public JogoConsole(){
    tabuleiro = new Tabuleiro();
  }

  public static int colunaParaIndice(char coluna){
    return coluna - 'a';
  } 

  public static int linhaParaIndice(char linha){
    return 8 - (linha - '0');
  }  

  public void iniciar(){
    tabuleiro.imprimirTabuleiro(); 
    while(true){  
      System.out.print("Vez da " + vez); 
      int[] jogada = lerJogada(); 
      
      int linhaOrigem = jogada[0]; 
      int colunaOrigem = jogada[1]; 
      int linhaDestino = jogada[2]; 
      int colunaDestino = jogada[3];  

      Peca pecaSelecionada = tabuleiro.getPeca(linhaOrigem, colunaOrigem); 

      if(pecaSelecionada == null){
        System.out.print("Não há peça localizada nessa posição");
        continue;
      } 
      if(!pecaSelecionada.getCor().equals(vez)){ 
        System.out.print("Você deve mover uma peça da cor " + vez); 
        continue;
      }  

      if (pecaSelecionada instanceof Rei) {
            Rei rei = (Rei) pecaSelecionada;
            if (Math.abs(colunaDestino - colunaOrigem) == 2) { // roque
                if (colunaDestino > colunaOrigem) {
                    if (!rei.podeFazerRoquePequeno(tabuleiro.getTabuleiro(), tabuleiro)) {
                        System.out.println("Roque pequeno inválido!");
                        continue;
                    }
                } else {
                    if (!rei.podeFazerRoqueGrande(tabuleiro.getTabuleiro(), tabuleiro)) {
                        System.out.println("Roque grande inválido!");
                        continue;
                    }
                }
            }
        }  

    if (pecaSelecionada instanceof Peao) {
    Peao peao = (Peao) pecaSelecionada;
    if (peao.enPassant(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, tabuleiro.getTabuleiro())) {
        System.out.println("Captura En Passant realizada!");
        continue;  // Pede nova jogada
    } 
  }

      if(!pecaSelecionada.movimentoValido(linhaDestino, colunaDestino, tabuleiro.getTabuleiro(), tabuleiro)){ 
        System.out.print("Movimento inválido"); 
        continue;
      } 

      if(tabuleiro.tentarMover(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, vez, tabuleiro)){
        System.out.println("Movimento inválido: deixa o rei em xeque!");
        continue; // pede nova jogada
      }  

      if (tabuleiro.estaEmXeque(vez, tabuleiro)) {
      if (!tabuleiro.movimentoLegal(vez, tabuleiro)) {
        System.out.println("Xeque-mate! Vitória das " + (vez.equals("Branca") ? "Pretas" : "Brancas") + "!");
        break; // Encerra o jogo
    } else {
        System.out.println("Xeque!");
    }
} else if (!tabuleiro.movimentoLegal(vez, tabuleiro)) {
    System.out.println("Empate por afogamento!");
    break; // Encerra o jogo
}
      
      tabuleiro.moverPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, tabuleiro); 

      tabuleiro.promocaoPeao(vez);

      vez = vez.equals("Branca") ? "Preta" : "Branca";  
      tabuleiro.imprimirTabuleiro(); 
    } 
  }

  public static int[] lerJogada(){
    while(true){
       System.out.print("Digite a jogada : (ex: e2 e4)"); 
       String entrada = scanner.nextLine().toLowerCase().trim(); 
       
       if(entrada.matches("[a-h][1-8]\\s[a-h][1-8]")){
       String[] posicoes = entrada.split(" ");  
       int colunaOrigem = colunaParaIndice(posicoes[0].charAt(0));  
       int linhaOrigem = linhaParaIndice(posicoes[0].charAt(1));   

       int colunaDestino = colunaParaIndice(posicoes[1].charAt(0));  
       int linhaDestino = linhaParaIndice(posicoes[1].charAt(1)); 
       
       int [] jogada = {linhaOrigem, colunaOrigem, linhaDestino, colunaDestino}; 

       return jogada;

      } 
      else{
         System.out.println("Entrada inválida. Use o formato correto, por exemplo: e2 e4");
      }
       
    }
  }
 
}
