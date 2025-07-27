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
    public static int menu(Scanner scanner, boolean jogoComecou){
        if(jogoComecou == false){
            System.out.println("============ MENU ============");
            System.out.println("1 - Posionar navios automaticamente");
            System.out.println("5 - Sair do jogo");
            System.out.println("=============================");
        }else{
            System.out.println("============ MENU ============");
            System.out.println("2 - Atacar oponente");
            System.out.println("3 - Ver tabuleiro de ataque");
            System.out.println("4 - Ver rodadas restantes");
            System.out.println("5 - Sair do jogo");
            System.out.println("=============================");
        }
        
        int opcao = scanner.nextInt();

        return opcao;
    }

    public static void posicionaNavios(char[][] tabuleiro){     
        
        int sentido = 0;
        int direcao = 0;
        int salvaLg = 0;
        int salvaCg = 0;
        Random rand = new Random();
        int linhaG = 0;
        int colunaG = 0;

        int hor = 1; // 1 significa que os barcos serão colocados na horizontal.
        int ver = 2; // 2 significa que os barcos serão colocados na vertical.

        linhaG = rand.nextInt(8); // Gera um valor aleatório para linha.
        salvaLg = linhaG; // salva o valor aleatório de linha.
        colunaG = rand.nextInt(8); // Gera um valor aleatório para coluna.
        salvaCg = colunaG; // salva o valor aleatório de coluna.
        direcao = rand.nextInt(ver - hor + 1) + 1; // manipulação para gerar a direção(1- Horizontal/ 2- vertical)
        sentido = rand.nextInt(2) * 2 - 1; // -1 -> Esquerda / 1 -> Direita.
        int y = sentido; // Serve para andar para esquerda/direita nos elses que não possuem condições específicas.

        for(int tamanhoNavio = 3; tamanhoNavio >= 1; tamanhoNavio--){
            if(tamanhoNavio == 3){
                for (int cont = 1; cont <= 3; cont++) {
                    switch (direcao) {
                        case 1: // horizontal
                            if ((colunaG == 1) || (colunaG == 0)) { // Problema 1 dos limites das bordas quando é na horizontal
                                tabuleiro[salvaLg][salvaCg] = '#';
                                salvaCg += 1; // Coluna anda 1 casa
                            } else if ((colunaG == 6) || (colunaG == 7)) { // Problema 2 dos limites das bordas quando é na horizontal
                                tabuleiro[salvaLg][salvaCg] = '#';
                                salvaCg -= 1; // Coluna retrocede 1 casa
                            } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                                tabuleiro[salvaLg][salvaCg] = '#';
                                salvaCg += y; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja informação está gravada na variável "y"
                            }
                            break;
                        case 2: // vertical
                            if ((linhaG == 0) || (linhaG == 1)) { // Problema 1 dos limites das bordas quando é na vertical
                                tabuleiro[salvaLg][salvaCg] = '#';
                                salvaLg += 1;
                            } else if ((linhaG == 7) || (linhaG == 6)) { // Problema 2 dos limites das bordas quando é na vertical
                                tabuleiro[salvaLg][salvaCg] = '#';
                                salvaLg -= 1;
                            } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                                tabuleiro[salvaLg][salvaCg] = '#';
                                salvaLg += y; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja informação está gravada na variável "y"
                            }
                            break;
                    }
                }
            }else if(tamanhoNavio == 2){
                for (int i = 0; i < 2; i++){
                    linhaG = rand.nextInt(8);
                    colunaG = rand.nextInt(8);
                    direcao = rand.nextInt(ver - hor + 1) + 1;
                    sentido = rand.nextInt(2) * 2 - 1;
                    for (int cont = 0; cont < 2; cont++) {
                                    switch (direcao) {
                                        case 1: // horizontal
                                            if ((colunaG == 1) || (colunaG == 0)) { // Problema 1 dos limites das bordas quando é na horizontal
                                                if(tabuleiro[linhaG][colunaG] == '~' && tabuleiro[linhaG][colunaG + 1] == '~'){
                                                    tabuleiro[linhaG][colunaG] = '#';
                                                    colunaG += 1; // Coluna anda 1 casa
                                                }else{
                                                    cont = -1;
                                                }continue;
                                            } else if ((colunaG == 6) || (colunaG == 7)) { // Problema 2 dos limites das bordas quando é na horizontal
                                                if(tabuleiro[linhaG][colunaG] == '~' && tabuleiro[linhaG][colunaG - 1] == '~'){
                                                    tabuleiro[linhaG][colunaG] = '#';
                                                    colunaG -= 1; // Coluna anda 1 casa
                                                }
                                            } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                                                if(tabuleiro[linhaG][colunaG] == '~' && tabuleiro[linhaG][colunaG + sentido] == '~'){
                                                    tabuleiro[linhaG][colunaG] = '#';
                                                    colunaG += sentido; // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja informação está gravada na variável "y"
                                                }
                                            }
                                            break;
                                        case 2: // vertical
                                            if ((linhaG == 0) || (linhaG == 1)) { // Problema 1 dos limites das bordas quando é na vertical
                                                    if(tabuleiro[linhaG][colunaG] == '~' && tabuleiro[linhaG + 1][colunaG] == '~'){
                                                        tabuleiro[linhaG][colunaG] = '#';
                                                        linhaG += 1;
                                                    }
                                            } else if ((linhaG == 7) || (linhaG == 6)) { // Problema 2 dos limites das bordas quando é na vertical                     
                                                if(tabuleiro[linhaG][colunaG] == '~' && tabuleiro[linhaG - 1][colunaG] == '~'){
                                                    tabuleiro[linhaG][colunaG] = '#';
                                                    linhaG -= 1;
                                                }
                                            } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                                                if(tabuleiro[linhaG][colunaG] == '~' && tabuleiro[linhaG + sentido][colunaG] == '~'){
                                                    tabuleiro[linhaG][colunaG] = '#';
                                                    linhaG += sentido;
                                                }
                                                 // Como não há limitação das bordas, pode andar para esquerda ou direita, cuja informação está gravada na variável "y"        
                                            }
                                        break;
                                    }
                    }
                }
            }else{ 
                for (int cont = 0; cont < 3; cont++) {
                    linhaG = rand.nextInt(8);
                    colunaG = rand.nextInt(8);
                    for(int i=0; i < tabuleiro.length; i++){
                        for(int j=0; j < tabuleiro[i].length; j++){
                            if(tabuleiro[linhaG][colunaG] == '~'){
                                tabuleiro[linhaG][colunaG] = '#';
                            }
                        }
                    }
                }break;
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
        
        if(tabuleiroDef[linha][coluna] == '#'){
            tabuleiroAtac[linha][coluna] = 'X';
            System.out.println("Acertou em cheio!");
            return true;
        }else{
            tabuleiroAtac[linha][coluna] = 'O';
            System.out.println("Errou!");
            return false;
        }
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
        final int MAX_ROD = 5;
        int opcao = 0;
        boolean jogoComecou = false;
        boolean jogoAtivo = true;
        int pontosJ01 = 0;
        int pontosJ02 = 0;
        int jogadorAtual = 1;

        // Inicialização dos tabuleiros usando a função inicializarTabuleiro
        inicializarTabuleiro(tabuleiroAtac01);
        inicializarTabuleiro(tabuleiroDef01);
        inicializarTabuleiro(tabuleiroAtac02);
        inicializarTabuleiro(tabuleiroDef02);
        
        System.out.println(">>> BEM-VINDO A BATALHA NAVAL <<<");
        System.out.println("Pressione a tecla Enter para iniciar uma partida...");
        scanner.nextLine();
        System.out.println("=============");
        System.out.println("Jogador 1, escolha uma opção: ");
        opcao = menu(scanner, jogoComecou); // Menu antes da partida começar mostrar só posicionar navios ou sair do jogo
        switch (opcao) {
            case 1 -> posicionaNavios(tabuleiroDef01);
            //case 5 -> sairDoJogo();
            default -> System.out.println("Opção inválida! Por favor, escolha um número de 1 a 5.");
        } 
        System.out.println("Jogador 2, escolha uma opção: ");
        menu(scanner, jogoComecou);
        switch (opcao) {
            case 1 -> posicionaNavios(tabuleiroDef02);
            //case 5 -> sairDoJogo();
            default -> System.out.println("Opção inválida! Por favor, escolha um número de 1 a 5.");
        } 
        System.out.println("O jogo vai começar!");
        
        // Loop principal do jogo: 
        while (jogoAtivo && contRodada <= MAX_ROD){
            char[][] tabuleiroDefOp;
            char[][] tabuleiroAtacAtual;
            jogoComecou = true;
            
            if(jogadorAtual == 1){
                tabuleiroDefOp = tabuleiroDef02;
                tabuleiroAtacAtual = tabuleiroAtac01; 
            }else{
                tabuleiroDefOp = tabuleiroDef01;
                tabuleiroAtacAtual = tabuleiroAtac02; 
            }
            opcao = menu(scanner, jogoComecou);
            switch (opcao) {
                case 2 -> atacarOponente(scanner, tabuleiroDefOp, tabuleiroAtacAtual);
                case 3 -> imprimirTabuleiro(tabuleiroAtacAtual);
                // case 4 -> verRodadasRest();
                // case 5 -> sairDoJogo();
                default -> System.out.println("Opção inválida! Por favor, escolha um número de 1 a 5.");
            }
            imprimirTabuleiro(tabuleiroAtacAtual);
        }

        imprimirTabuleiro(tabuleiroDef01);
        System.out.println("=============");
        posicionaNavios(tabuleiroDef01);
        imprimirTabuleiro(tabuleiroDef01);
        System.out.println("\n===========\n");
        atacarOponente(scanner, tabuleiroDef01, tabuleiroAtac01);
        imprimirTabuleiro(tabuleiroAtac01);

        scanner.close();
    }
}
