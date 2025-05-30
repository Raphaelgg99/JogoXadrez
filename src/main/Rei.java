public class Rei extends Peca {

  public Rei(String cor, int coluna, int linha) {
    super(cor, coluna, linha);
  }

  @Override
  public boolean movimentoValido(int novaLinha, int novaColuna, Peca[][] tabuleiro) {
    int dLinha = Math.abs(novaLinha - linha); 
    int dColuna = Math.abs(novaColuna - coluna);  

    if(dLinha <= 1 && dColuna <= 1){
      if(tabuleiro[novaLinha][novaColuna]!=null && tabuleiro[novaLinha][novaColuna].getCor().equals(this.cor)){
        return false;
      } 
      return true;
    } 

    if (dLinha == 0 && dColuna == 2 && !this.jaMoveu) {
            // Roque pequeno (lado do rei)
            if (novaColuna > coluna) {
                return podeFazerRoquePequeno(tabuleiro);
            }
            // Roque grande (lado da dama)
            else {
                return podeFazerRoqueGrande(tabuleiro);
            }
        }

        return false;
      } 
    
private boolean podeFazerRoqueGrande(Peca[][] tabuleiro) {
        int linhaRei = linha;
        // Verificar se as casas entre rei e torre estão livres
        if (tabuleiro[linhaRei][coluna - 1] != null) return false;
        if (tabuleiro[linhaRei][coluna - 2] != null) return false;
        if (tabuleiro[linhaRei][coluna - 3] != null) return false;

        Peca torre = tabuleiro[linhaRei][coluna - 4];
        if (torre == null || !(torre instanceof Torre) || torre.getCor() != this.cor || torre.isJaMoveu()) {
            return false;
        }

        // Aqui pode adicionar a verificação de xeque, que é mais complexa
        // Para começo, vamos deixar passar

        return true;
    } 

private boolean podeFazerRoquePequeno(Peca[][] tabuleiro) {
        int linhaRei = linha;
        // Verificar se as casas entre rei e torre estão livres
        if (tabuleiro[linhaRei][coluna + 1] != null) return false;
        if (tabuleiro[linhaRei][coluna + 2] != null) return false;

        Peca torre = tabuleiro[linhaRei][coluna + 3];
        if (torre == null || !(torre instanceof Torre) || torre.getCor() != this.cor || torre.isJaMoveu()) {
            return false;
        }

        // Aqui pode adicionar a verificação de xeque, que é mais complexa
        // Para começo, vamos deixar passar

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
