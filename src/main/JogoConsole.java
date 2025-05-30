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
    while(true){
      tabuleiro.imprimirTabuleiro();  
      System.out.print("Vez da " + vez); 
      int[] jogada = lerJogada(); 
      
      int linhaOrigem = jogada[0]; 
      int colunaOrigem = jogada[1]; 
      int linhaDestino = jogada[2]; 
      int colunaDestino = jogada[3]; 

      


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
