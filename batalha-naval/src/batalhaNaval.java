import java.util.Random;
import java.util.Scanner;

public class batalhaNaval {
    // Função para incializar qualquer tabuleiro
    public static void inicializarTabuleiro(char[][] tabuleiro){
        // Preenche o tabuleiro com "~"
        for(int i=0; i < tabuleiro.length; i++){
            for(int j=0; j < tabuleiro[i].length; j++){
                tabuleiro[i][j] = '~'; 
            }
        }
    }
    // Função para imprimir qualquer tabuleiro
    public static void imprimirTabuleiro(char[][] tabuleiro){
        for(int i=0; i < tabuleiro.length; i++){ // Esse "for" imprime o tabuleiro já preenchido e da um espaço entre as linhas
            for(int j=0; j < tabuleiro[i].length; j++){
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Função para mostrar o Menu
    public static int menu(Scanner scanner){
        System.out.println("============ MENU ============");
        System.out.println("1 - Posionar navios automaticamente");
        System.out.println("2 - Atacar oponente");
        System.out.println("3 - Ver tabuleiro de ataques");
        System.out.println("4 - Ver rodadas restantes");
        System.out.println("5 - Sair do jogo");
        System.out.println("=============================");
        scanner.nextInt();
        /*switch (scanner) {
            case 1 -> posicionaNavioGrande();
            case 2 -> atacarOponente();
            case 3 -> verTabuleiroAtac();
            case 4 -> verRodadasRest();
            case 5 -> sairDoJogo();
            default -> System.out.println("Opção inválida! Por favor, escolha um número de 1 a 5.");
        } //Esse switch-case ficará dentro do loop principal do jogo na main (Função switch-case)*/
        return scanner.nextInt();
    }

    public static int tamanhoNavio(){
        Random rand = new Random();
        
        int tamanhoNavio = rand.nextInt(2)+1;

        return tamanhoNavio;
    }


    public static void posicionaNavioGrande(char[][] tabuleiro, int tamanhoNavio){     
        int sentido = 0;
        int direcao = 0;
        int salvaLg = 0;
        int salvaCg = 0;
        Random rand = new Random();
        int linhaG = 0;
        int colunaG = 0;
        // random para tamanho do navio
        int hor = 1; // 1 significa que os barcos serão colocados na horizontal.
        int ver = 2; // 2 significa que os barcos serão colocados na vertical.

        linhaG = rand.nextInt(8); // Gera um valor aleatório para linha.
        salvaLg = linhaG; // salva o valor aleatório de linha.
        colunaG = rand.nextInt(8); // Gera um valor aleatório para coluna.
        salvaCg = colunaG; // salva o valor aleatório de coluna.
        direcao = rand.nextInt(ver - hor + 1) + 1; // manipulação para gerar a direção(1- Horizontal/ 2- vertical)
        sentido = rand.nextInt(2) * 2 - 1; // -1 -> Esquerda / 1 -> Direita.
        int y = sentido; // Serve para andar para esquerda/direita nos elses que não possuem condições
                         // específicas.


        if(tamanhoNavio == 3){
            for (int cont = 1; cont <= 3; cont++) {
            switch (direcao) {
                case 1: // horizontal
                    if ((colunaG == 1) || (colunaG == 0)) { // Problema 1 dos limites das bordas quando é na horizontal
                        tabuleiro[salvaLg][salvaCg] = 'x';
                        salvaCg += 1; // Coluna anda 1 casa
                    } else if ((colunaG == 6) || (colunaG == 7)) { // Problema 2 dos limites das bordas quando é na
                                                                   // horizontal
                        tabuleiro[salvaLg][salvaCg] = 'x';
                        salvaCg -= 1; // Coluna retrocede 1 casa
                    } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                        tabuleiro[salvaLg][salvaCg] = 'x';
                        salvaCg += y; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja
                                      // informação está gravada na variável "y"
                    }
                    break;
                case 2: // vertical
                    if ((linhaG == 0) || (linhaG == 1)) { // Problema 1 dos limites das bordas quando é na vertical
                        tabuleiro[salvaLg][salvaCg] = 'x';
                        salvaLg += 1;
                    } else if ((linhaG == 7) || (linhaG == 6)) { // Problema 2 dos limites das bordas quando é na
                                                                 // vertical
                        tabuleiro[salvaLg][salvaCg] = 'x';
                        salvaLg -= 1;
                    } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                        tabuleiro[salvaLg][salvaCg] = 'x';
                        salvaLg += y; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja
                                      // informação está gravada na variável "y"
                    }
                    break;
                }continue;
            }
        }else if(tamanhoNavio == 2){
            for (int cont = 1; cont <= 2; cont++) {
                switch (direcao) {
                    case 1: // horizontal
                        if ((colunaG == 1) || (colunaG == 0)) { // Problema 1 dos limites das bordas quando é na horizontal
                            tabuleiro[salvaLg][salvaCg] = '*';
                            salvaCg += 1; // Coluna anda 1 casa
                        } else if ((colunaG == 6) || (colunaG == 7)) { // Problema 2 dos limites das bordas quando é na
                                                                    // horizontal
                            tabuleiro[salvaLg][salvaCg] = '*';
                            salvaCg -= 1; // Coluna retrocede 1 casa
                        } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                            tabuleiro[salvaLg][salvaCg] = '*';
                            salvaCg += y; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja
                                        // informação está gravada na variável "y"
                        }
                        break;
                    case 2: // vertical
                        if ((linhaG == 0) || (linhaG == 1)) { // Problema 1 dos limites das bordas quando é na vertical
                            tabuleiro[salvaLg][salvaCg] = '*';
                            salvaLg += 1;
                        } else if ((linhaG == 7) || (linhaG == 6)) { // Problema 2 dos limites das bordas quando é na
                                                                    // vertical
                            tabuleiro[salvaLg][salvaCg] = '*';
                            salvaLg -= 1;
                        } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                            tabuleiro[salvaLg][salvaCg] = '*';
                            salvaLg += y; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja
                                        // informação está gravada na variável "y"
                        }
                        break;
                }continue;
            }
        }else{
            for (int cont = 0; cont < 3; cont++) {
                linhaG = rand.nextInt(8);
                colunaG = rand.nextInt(8);
                tabuleiro[linhaG][colunaG] = '%';
            }
        }
        
    }

    public static boolean atacarOponente(Scanner scanner, char[][] tabuleiroDef, char[][] tabuleiroAtac){        
        int linha = -1; // Evita que a linha comece com uma coordenada válida
        int coluna = -1; // Evita que a coluna comece com uma coordenada válida
        boolean entradaValida = false; // Valor booleano que controla o loop até tornar true

        do{
            System.out.println("Informe a Linha que deseja atacar (0 a 7): ");
            linha = scanner.nextInt();
            System.out.println("Informe a Coluna que deseja atacar (0 a 7): ");
            coluna = scanner.nextInt();
            if(linha >= 0 && linha <= 7 && coluna >=0 && coluna <= 7){
                if(tabuleiroAtac[linha][coluna] == '~'){
                    entradaValida = true;
                }else{
                    System.out.println("Coordenada já atacada! Escolha outra posição.");
                }
            }else{               
                System.out.println("Coordenadas inválidas! Por favor digite linhas e colunas dentro do intervalo de 0 a 7.");
            }
        }while(entradaValida != true); // Loop que verifica se a entrada das coordenadas são válidas
        


        return entradaValida;
    }
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        // char tabuleiro[][] = new char[8][8]; Cria um tabuleiro 8x8
        // Criar tabuleiros
        char tabuleiroDef01[][] = new char[8][8]; //Tabuleiro de defesa do jogador 01
        char tabuleiroAtac01[][] = new char[8][8]; //Tabuleiro de ataque do jogador 01
        char tabuleiroDef02[][] = new char[8][8]; //Tabuleiro de defesa do jogador 02
        char tabuleiroAtac02[][] = new char[8][8]; //Tabuleiro de ataque do jogador 02

        int contRodada = 1;
        final int maxRodadas = 5;
        int linhaAtac = 0;
        int colunaAtac = 0;

        // Inicialização dos tabuleiros usando a função inicializarTabuleiro
        inicializarTabuleiro(tabuleiroAtac01);
        inicializarTabuleiro(tabuleiroDef01);
        inicializarTabuleiro(tabuleiroAtac02);
        inicializarTabuleiro(tabuleiroDef02);


        imprimirTabuleiro(tabuleiroAtac01);
        System.out.println("=============");
        posicionaNavioGrande(tabuleiroAtac01, tamanhoNavio());
        imprimirTabuleiro(tabuleiroAtac01);
        System.out.println(">>> BEM-VINDO A BATALHA NAVAL <<<");
        System.out.println("Pressione a tecla Enter para iniciar uma partida...");
        scanner.nextLine();
        System.out.println("Jogador 1, escolha uma opção: ");
        menu(scanner); // Menu antes da partida começar mostrar só posicionar navios ou sair do jogo
        System.out.println("Jogador 2, escolha uma opção: ");
        menu(scanner);
        System.out.println("O jogo vai começar!");

        // while(contRodada <= maxRodadas){
        //     System.out.println("-- " + contRodada + "ª Rodada --");
        //     imprimirTabuleiro(tabuleiroAtac02);
        //     contRodada++;
        // }

        // Imprime os tabuleiros
        /*System.out.println("--- Tabuleiro de Defesa 01 ---");
        imprimirTabuleiro(tabuleiroDef01);
        System.out.println("\n--- Tabuleiro de Ataque 01 ---");
        imprimirTabuleiro(tabuleiroAtac01);
        System.out.println("\n--- Tabuleiro de Defesa 02 ---");
        imprimirTabuleiro(tabuleiroDef02);
        System.out.println("\n--- Tabuleiro de Ataque 02 ---");
        imprimirTabuleiro(tabuleiroAtac02);*/

        scanner.close();
    }
}
