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
        for(int i=0; i < tabuleiro.length; i++){
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
        return scanner.nextInt();
        /*switch (opcao) {
            case 1:
                posicionaNavio();
                break;

            case 2:
                atacarOponente();
                break;
            case 3:
                verTabuleiroAtac();
                break;
            case 4:
                verRodadasRest();
                break;
            case 5:
                sairDoJogo();
                break;
            default:
            System.out.println("Opção inválida! Por favor, escolha um número de 1 a 5.");
                break;
        } Esse switch-case ficará dentro do loop principal do jogo na main*/
    }
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        // char tabuleiro[][] = new char[8][8]; Cria um tabuleiro 8x8
        // Criar tabuleiros
        char tabuleiroDef01[][] = new char[8][8]; //Tabuleiro de defesa do jogador 01
        char tabuleiroAtac01[][] = new char[8][8]; //Tabuleiro de ataque do jogador 01
        char tabuleiroDef02[][] = new char[8][8]; //Tabuleiro de defesa do jogador 02
        char tabuleiroAtac02[][] = new char[8][8]; //Tabuleiro de ataque do jogador 02

        // Inicialização dos tabuleiros usando a função inicializarTabuleiro
        inicializarTabuleiro(tabuleiroAtac01);
        inicializarTabuleiro(tabuleiroDef01);
        inicializarTabuleiro(tabuleiroAtac02);
        inicializarTabuleiro(tabuleiroDef02);

        menu(scanner);
        // Imprime os tabuleiros
        System.out.println("--- Tabuleiro de Defesa 01 ---");
        imprimirTabuleiro(tabuleiroDef01);
        System.out.println("\n--- Tabuleiro de Ataque 01 ---");
        imprimirTabuleiro(tabuleiroAtac01);
        System.out.println("\n--- Tabuleiro de Defesa 02 ---");
        imprimirTabuleiro(tabuleiroDef02);
        System.out.println("\n--- Tabuleiro de Ataque 02 ---");
        imprimirTabuleiro(tabuleiroAtac02);


        scanner.close();
    }
}
