import java.util.Random;
import java.util.Scanner;

public class batalhaNaval {
    final static int HORIZONTAL = 1;
    final static int VERTICAL = 2;
    final static int ESQUERDA_OU_CIMA = -1;
    final static int DIREITA_OU_BAIXO = 1;

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
        for(int i = 0; i <= 7; i++){
            if(i == 0){
                System.out.print("  " + i + " ");
            }else{
                System.out.print(i + " ");
            }
            
        }
        System.out.println();
        
        for(int i=0; i < tabuleiro.length; i++){ // Esse "for" imprime o tabuleiro já preenchido e da um espaço entre as linhas
            System.out.print(i + " ");
            for(int j=0; j < tabuleiro[i].length; j++){
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }
    // Função para mostrar o Menu
    public static int menu(Scanner scanner, boolean jogoComecou){
        int resp = 0;
        boolean entradaValida = false;

        if(jogoComecou == false){
            do{
                System.out.println("============ MENU ============");
                System.out.println("1 - Posionar navios automaticamente");
                System.out.println("5 - Sair do jogo");
                System.out.println("==============================");
                resp = scanner.nextInt();
                if(resp == 1 || resp == 5){
                    entradaValida = true;
                }else{
                    System.out.println("Opção invalída! Escolha apenas as opções disponíveis (1 ou 5).");
                }
            }while(!entradaValida);
        }else{
            do{
                System.out.println("============ MENU ============");
                System.out.println("2 - Atacar oponente");
                System.out.println("3 - Ver tabuleiro de ataque");
                System.out.println("4 - Ver rodadas restantes");
                System.out.println("5 - Sair do jogo");
                System.out.println("==============================");
                resp = scanner.nextInt();
                if(resp >= 2 && resp <= 5){
                    entradaValida = true;
                }else{
                    System.out.println("Opção invalída! Escolha apenas as opções disponíveis (1 ou 5).");
                }
            }while(!entradaValida);
        }
        int opcao = resp;
        return opcao;
    }
    // Função para gerar coordenadas aleatorias para o posicionamento dos barcos
    public static int[] gerarCoordenadas(){
        int coordenadas[] = new int[4]; // Array para guardar as coordenadas geradas aleatoriamente.
        Random rand = new Random();
        int ver = 2;
        int hor = 1;
        coordenadas[0] = rand.nextInt(8); // Gera um valor aleatório para linha.
        coordenadas[1] = rand.nextInt(8); // Gera um valor aleatório para coluna.
        coordenadas[2] = rand.nextInt(ver - hor + 1) + 1; // direção(1- Horizontal/ 2- vertical)
        coordenadas[3] = rand.nextInt(2) * 2 - 1; // -1 -> Esquerda / 1 -> Direita.

        return coordenadas;
    }
    // Função para posicionar o barco grande (3 posições)
    public static void barcoGrande(char[][] tabuleiro){
        int[] coordenadasGrande = gerarCoordenadas();
       
        int linhaInicial = coordenadasGrande[0];
        int colunaInicial = coordenadasGrande[1];
        int direcao = coordenadasGrande[2];
        int sentido = coordenadasGrande[3];

        int linhaAtual = linhaInicial;
        int colunaAtual = colunaInicial;

        for (int cont = 0; cont < 3; cont++) {
            switch (direcao) {
                case HORIZONTAL: // horizontal
                    if ((colunaInicial == 1) || (colunaInicial == 0)) { // Problema 1 dos limites das bordas quando é na horizontal.
                        tabuleiro[linhaAtual][colunaAtual] = '#';
                        colunaAtual += 1; // Coluna anda 1 casa
                    } else if ((colunaInicial == 6) || (colunaInicial == 7)) { // Problema 2 dos limites das bordas quando é na horizontal.
                        tabuleiro[linhaAtual][colunaAtual] = '#';
                        colunaAtual -= 1; // Coluna retrocede 1 casa.
                    } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                        tabuleiro[linhaAtual][colunaAtual] = '#';
                        colunaAtual += sentido; // Como não há limitação das bordas, pode andar para esquerda ou direita.
                    }
                    break;
                case VERTICAL: // vertical
                    if ((linhaInicial == 0) || (linhaInicial == 1)) { // Problema 1 dos limites das bordas quando é na vertical.
                        tabuleiro[linhaAtual][colunaInicial] = '#';
                        linhaAtual += 1;
                    } else if ((linhaInicial == 7) || (linhaInicial == 6)) { // Problema 2 dos limites das bordas quando é na vertical.
                        tabuleiro[linhaAtual][colunaInicial] = '#';
                        linhaAtual -= 1;
                    } else { // Quando não se encaixa em nenhum dos problemas anteriores.
                        tabuleiro[linhaAtual][colunaInicial] = '#';
                        linhaAtual += sentido; // Como não há limitação das bordas, pode andar para esquerda ou direita.
                    }
                    break;
            }
        }
    }
    // Função para posicionar o barco médio (2 posições)
    public static void barcoMedio(char[][] tabuleiro){
        boolean posicaoLivre = false;

        for (int i = 0; i < 2; i++) {
            do {
                posicaoLivre = false;
                int[] coordenadasMedio = gerarCoordenadas();

                int linhaInicial = coordenadasMedio[0];
                int colunaInicial = coordenadasMedio[1];
                int direcao = coordenadasMedio[2];
                int sentido = coordenadasMedio[3];
                
                switch (direcao) {
                    case HORIZONTAL:
                        if (colunaInicial == 0) { // Problema 1 dos limites das bordas quando é na horizontal
                            if (tabuleiro[linhaInicial][colunaInicial] == '~' && tabuleiro[linhaInicial][colunaInicial + 1] == '~') {
                                tabuleiro[linhaInicial][colunaInicial] = '#';
                                tabuleiro[linhaInicial][colunaInicial + 1] = '#';
                                posicaoLivre = true;
                            }
                        } else if (colunaInicial == 7) {

                             if (tabuleiro[linhaInicial][colunaInicial] == '~' && tabuleiro[linhaInicial][colunaInicial - 1] == '~') {
                                tabuleiro[linhaInicial][colunaInicial] = '#';
                                tabuleiro[linhaInicial][colunaInicial - 1] = '#';
                                posicaoLivre = true;
                             }
                        } else { // Problema 2 dos limites das bordas quando é na

                            if (tabuleiro[linhaInicial][colunaInicial] == '~' && tabuleiro[linhaInicial][colunaInicial + sentido] == '~') {
                                tabuleiro[linhaInicial][colunaInicial] = '#';
                                tabuleiro[linhaInicial][colunaInicial + sentido] = '#';
                                posicaoLivre = true;
                             }
                        }  
                        break;
                    case VERTICAL: 
                        if (linhaInicial == 0) {

                            if (tabuleiro[linhaInicial][colunaInicial] == '~' && tabuleiro[linhaInicial + 1][colunaInicial] == '~') {
                                
                                tabuleiro[linhaInicial][colunaInicial] = '#';
                                tabuleiro[linhaInicial + 1][colunaInicial] = '#';
                                posicaoLivre = true;
                            } 
                        } else if (linhaInicial == 7) {
                            if (tabuleiro[linhaInicial][colunaInicial] == '~' && tabuleiro[linhaInicial - 1][colunaInicial] == '~') {
                                
                                tabuleiro[linhaInicial][colunaInicial] = '#';
                                tabuleiro[linhaInicial - 1][colunaInicial] = '#';
                                posicaoLivre = true;
                            } 
                        } else {
                             if (tabuleiro[linhaInicial][colunaInicial] == '~' && tabuleiro[linhaInicial + sentido][colunaInicial] == '~') {
                                
                                tabuleiro[linhaInicial][colunaInicial] = '#';
                                tabuleiro[linhaInicial + sentido][colunaInicial] = '#';
                                posicaoLivre = true;
                            } 
                        } 
                        break;
                }
            } while (!posicaoLivre);
        }
    }
    // Função para posicionar o barco pequeno (1 posição)
    public static void barcoPequeno(char[][] tabuleiro){
        boolean resp = false;

        for (int i = 0; i < 3; i++) {
            do {
                resp = false;
                int[] coordenadasPequeno = gerarCoordenadas();

                int linhaInicial = coordenadasPequeno[0];
                int colunaInicial = coordenadasPequeno[1];

                if (tabuleiro[linhaInicial][colunaInicial] == '~') {

                    tabuleiro[linhaInicial][colunaInicial] = '#';
                    resp = true;
                }

            } while (resp == false);
        }
    }
    // Função para atacar o oponente
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
        }while(!entradaValida); // Loop que verifica se a entrada das coordenadas são válidas
        
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
        final int MAX_ROD = 5; // Maximo de rodadas
        final int MAX_PONT = 10; // Maximo de pontos para finalizar a partida
        int opcao = 0;
        boolean jogoComecou = false; // Controla o Menu que vai aparecer, antes da partida começar e durante a partida
        boolean jogoAtivo = true; // Condição para manter o jogo rodando
        int pontosJ01 = 0; // Contador de pontos do jogador 01
        int pontosJ02 = 0; // Contador de pontos do jogador 02
        int jogadorAtual = 1; // Controla quem é o jogador da vez 
        String nomeJ01; // Nome jogador 1
        String nomeJ02; // Nome jogador 2 
        String nomeAtual; // Variavel que atualiza o nome em cada rodada

        // Inicialização dos tabuleiros usando a função inicializarTabuleiro
        inicializarTabuleiro(tabuleiroAtac01);
        inicializarTabuleiro(tabuleiroDef01);
        inicializarTabuleiro(tabuleiroAtac02);
        inicializarTabuleiro(tabuleiroDef02);
        
        System.out.println(">>> BEM-VINDO A BATALHA NAVAL <<<");
        System.out.println("Pressione a tecla Enter para iniciar uma partida...");
        scanner.nextLine();
        System.out.println("==============================================\n");
        System.out.println("Jogador 1, informe seu nome: ");
        nomeJ01 = scanner.next();
        System.out.println(nomeJ01 + ", escolha uma opção: ");
        opcao = menu(scanner, jogoComecou); // Menu antes da partida começar mostrar só posicionar navios ou sair do jogo
        switch (opcao) {
            case 1:
                barcoGrande(tabuleiroDef01);
                barcoMedio(tabuleiroDef01);
                barcoPequeno(tabuleiroDef01);
                break;
            case 5: 
                System.out.println("Jogo finalizado!");
                System.exit(0); // Encerra o programa 
                break;
            default: 
                System.out.println("Opção inválida! Por favor, escolha um número de 1 ou 5.");
                break;
        } 
        System.out.println("Jogador 2, informe seu nome: ");
        nomeJ02 = scanner.next();
        System.out.println(nomeJ02 +", escolha uma opção: ");
        menu(scanner, jogoComecou);
        switch (opcao) {
            case 1:
                barcoGrande(tabuleiroDef02);
                barcoMedio(tabuleiroDef02);
                barcoPequeno(tabuleiroDef02);
                break;
            case 5: 
                System.out.println("Jogo finalizado!");
                System.exit(0);
                break;
            default: 
                System.out.println("Opção inválida! Por favor, escolha um número de 1 ou 5.");
                break;
        } 
        System.out.println("\n>>>>> O jogo vai começar! <<<<<\n");
        
        // Loop principal do jogo: 
        while (jogoAtivo && contRodada <= MAX_ROD){
            char[][] tabuleiroDefOp;
            char[][] tabuleiroAtacAtual;
            jogoComecou = true;

            if(contRodada%2 == 1){ // Define o jogador da vez, rodadas impares jogador 01, rodadas pares jogador 2.
                jogadorAtual = 1; 
                nomeAtual = nomeJ01;
            }else{
                jogadorAtual = 2;
                nomeAtual = nomeJ02;
            }
            
            if(jogadorAtual == 1){ // Define os tabuleiros de ataque e defesa que serão utilizados na rodada atual.
                tabuleiroDefOp = tabuleiroDef02;
                tabuleiroAtacAtual = tabuleiroAtac01;
                System.out.println("Jogador da vez: " + nomeAtual);
            }else{
                tabuleiroDefOp = tabuleiroDef01;
                tabuleiroAtacAtual = tabuleiroAtac02; 
                System.out.println("Jogador da vez: " + nomeAtual);
            }
            do{
                opcao = menu(scanner, jogoComecou);
                switch (opcao) {
                    case 2: 
                        boolean acerto = atacarOponente(scanner, tabuleiroDefOp, tabuleiroAtacAtual);
                        if(acerto == true && jogadorAtual == 1){
                            pontosJ01++;
                        }else if(acerto == true && jogadorAtual == 2){
                            pontosJ02++;
                        }

                        if(pontosJ01 >= MAX_PONT || pontosJ02 >= MAX_PONT){
                            jogoAtivo = false;
                        }
                        break;
                    case 3: 
                        imprimirTabuleiro(tabuleiroAtacAtual);
                        break;
                    case 4: 
                        System.out.println("Rodadas restantes: " + (MAX_ROD - contRodada));
                        break;
                    case 5: 
                        jogoAtivo = false;
                        break;
                    default: 
                        System.out.println("Opção inválida! Por favor, escolha um número de 1 a 5.");
                        break;
                }
                if(opcao == 5){
                    break;
                }
            }while(opcao != 2);
            contRodada++;
        }
        // Saídas finais.
        System.out.println("\nJogo finalizado!\n");
        System.out.println("\n======== Tabuleiros final ========\n");
        System.out.println("Tabuleiro de defesa do(a) " + nomeJ01);
        imprimirTabuleiro(tabuleiroDef01);
        System.out.println("\n======================================\n");
        System.out.println("Tabuleiro de ataque do(a) " + nomeJ01);
        imprimirTabuleiro(tabuleiroAtac01);
        System.out.println("\n======================================\n");
        System.out.println("Tabuleiro de defesa do(a) " + nomeJ02);
        imprimirTabuleiro(tabuleiroDef02);
        System.out.println("Tabuleiro de ataque do(a) " + nomeJ02);
        System.out.println("\n======================================\n");
        imprimirTabuleiro(tabuleiroAtac02);
        System.out.println("\n======== Resultado ========\n");
        if(pontosJ01 > pontosJ02){
            System.out.println(nomeJ01 + " venceu com " + pontosJ01 + " ponto(s)!");
        }else if(pontosJ02 > pontosJ01){
            System.out.println(nomeJ02 + " venceu com " + pontosJ02 + " ponto(s)!");
        }else{
            System.out.println("Empate técnico: ");
            System.out.println(nomeJ01 + " fez " + pontosJ01 + " ponto(s).");
            System.out.println(nomeJ02 + " fez " + pontosJ02 + " ponto(s).");
        }
        scanner.close();
    }
}
