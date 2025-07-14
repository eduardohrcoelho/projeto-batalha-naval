public class batalhaNaval {
    public static void main(String[] args) throws Exception {
        // String tabuleiro[][] = new String[8][8]; Cria um tabuleiro 8x8
        String tabuleiroDef01[][] = new String[8][8]; //Tabuleiro de defesa do jogador 01
        String tabuleiroAtac01[][] = new String[8][8]; //Tabuleiro de ataque do jogador 01
        String tabuleiroDef02[][] = new String[8][8]; //Tabuleiro de defesa do jogador 02
        String tabuleiroAtac02[][] = new String[8][8]; //Tabuleiro de ataque do jogador 02


        // Preenche o tabuleiro de Defesa com "~"
        for(int i=0; i < tabuleiroDef01.length; i++){
            for(int j=0; j < tabuleiroDef01[i].length; j++){
                tabuleiroDef01[i][j] = "~";
                tabuleiroAtac01[i][j] = "~";
                tabuleiroDef02[i][j] = "~";
                tabuleiroAtac02[i][j] = "~";
            }
        }

        // Imprime o tabuleiro de Defesa 01
        for(int i=0; i < tabuleiroDef01.length; i++){
            for(int j=0; j < tabuleiroDef01[i].length; j++){
                System.out.print(tabuleiroDef01[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("=================================");

        // Imprime o tabuleiro de Defesa 02
        for(int i=0; i < tabuleiroDef02.length; i++){
            for(int j=0; j < tabuleiroDef02[i].length; j++){
                System.out.print(tabuleiroDef02[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("=================================");

        // Imprime o tabuleiro de Ataque 01
        for(int i=0; i < tabuleiroAtac01.length; i++){
            for(int j=0; j < tabuleiroAtac01[i].length; j++){
                System.out.print(tabuleiroAtac01[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("=================================");

        // Imprime o tabuleiro de Ataque 02
        for(int i=0; i < tabuleiroAtac02.length; i++){
            for(int j=0; j < tabuleiroAtac02[i].length; j++){
                System.out.print(tabuleiroAtac02[i][j] + " ");
            }
            System.out.println();
        }
    }
}
