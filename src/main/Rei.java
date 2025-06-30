public class Rei extends Peca {

  public Rei(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] peca, Tabuleiro tabuleiro) {
    int dLinha = Math.abs(novaLinha - linha); 
    int dColuna = Math.abs(novaColuna - coluna);  

    if(dLinha <= 1 && dColuna <= 1){
      if(peca[novaLinha][novaColuna]!=null && peca[novaLinha][novaColuna].getCor().equals(this.cor)){
        return false;
      } 
      return true;
    } 

    if (dLinha == 0 && dColuna == 2 && !this.jaMoveu) {
            // Roque pequeno (lado do rei)
            if (novaColuna > coluna) {
                return podeFazerRoquePequeno(peca, tabuleiro);
            }
            // Roque grande (lado da dama)
            else {
                return podeFazerRoqueGrande(peca, tabuleiro);
            }
        }

        return false;
      } 
    
public boolean podeFazerRoqueGrande(Peca[][] peca, Tabuleiro tabuleiro) {
    int linhaRei = linha;
    
    // Verificar se as casas entre o rei e a torre estão livres
    if (peca[linhaRei][coluna - 1] != null) return false;
    if (peca[linhaRei][coluna - 2] != null) return false;
    if (peca[linhaRei][coluna - 3] != null) return false;
    
    // Verificar se a torre está na posição correta e não se moveu
    Peca torre = peca[linhaRei][coluna - 4];
    if (torre == null || !(torre instanceof Torre) || torre.getCor() != this.cor || torre.isJaMoveu()) {
        return false;
    }

    // Verificar se o rei não está em xeque
    if (tabuleiro.estaEmXeque(this.cor, tabuleiro)) {
        return false;
    }

    return true;
    } 

public boolean podeFazerRoquePequeno(Peca[][] peca, Tabuleiro tabuleiro) {
    int linhaRei = linha;

    // Verificar se as casas entre o rei e a torre estão livres
    if (peca[linhaRei][coluna + 1] != null) return false;
    if (peca[linhaRei][coluna + 2] != null) return false;
    
    // Verificar se a torre está na posição correta e não se moveu
    Peca torre = peca[linhaRei][coluna + 3];
    if (torre == null || !(torre instanceof Torre) || torre.getCor() != this.cor || torre.isJaMoveu()) {
        return false;
    }

    // Verificar se o rei não está em xeque
    if (tabuleiro.estaEmXeque(this.cor, tabuleiro)) {
        return false;
    } 
    return true;
    }

@Override
public String getSimbolo() {
  if(cor.equalsIgnoreCase("Branco") || cor.equalsIgnoreCase("Branca")){
      return "R";
    } 
    return "r";
}
      
  

  
  
}
