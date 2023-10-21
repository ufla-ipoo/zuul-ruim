/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * Traduzido por Julio César Alves. Versão: 2023.10.21
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Jogo 
{
    private Analisador analisador;
    private Local localAtual;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os locais e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Local fora, anfiteatro, cantina, laboratorio, escritorio;
      
        // cria os locais
        fora = new Local("do lado de fora da entrada principal de uma universidade");
        anfiteatro = new Local("no anfiteatro");
        cantina = new Local("na cantina do campus");
        laboratorio = new Local("no laboratorio de computacao");
        escritorio = new Local("na sala de administracao dos computadores");
        
        // inicializa as saidas dos locais
        fora.ajustarSaidas(null, anfiteatro, laboratorio, cantina);
        anfiteatro.ajustarSaidas(null, null, null, fora);
        cantina.ajustarSaidas(null, fora, null, null);
        laboratorio.ajustarSaidas(fora, escritorio, null, null);
        escritorio.ajustarSaidas(null, null, null, laboratorio);

        localAtual = fora;  // o jogo comeca do lado de fora
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        System.out.println("Voce esta " + localAtual.getDescricao());
    
        System.out.print("Saidas: ");
        if(localAtual.saidaNorte != null) {
            System.out.print("norte ");
        }
        if(localAtual.saidaLeste != null) {
            System.out.print("leste ");
        }
        if(localAtual.saidaSul != null) {
            System.out.print("sul ");
        }
        if(localAtual.saidaOeste != null) {
            System.out.print("oeste ");
        }
        System.out.println();
    }

    /**
     * Dado um comando, processa (executa) o comando.
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaLocal(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Imprime informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo local, caso contrario imprime mensagem de erro.
     */
    private void irParaLocal(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do local atual
        Local proximoLocal = null;
        if(direcao.equals("norte")) {
            proximoLocal = localAtual.saidaNorte;
        }
        if(direcao.equals("leste")) {
            proximoLocal = localAtual.saidaLeste;
        }
        if(direcao.equals("sul")) {
            proximoLocal = localAtual.saidaSul;
        }
        if(direcao.equals("oeste")) {
            proximoLocal = localAtual.saidaOeste;
        }

        if (proximoLocal == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            localAtual = proximoLocal;
            
            System.out.println("Voce esta " + localAtual.getDescricao());
            
            System.out.print("Saidas: ");
            if(localAtual.saidaNorte != null) {
                System.out.print("norte ");
            }
            if(localAtual.saidaLeste != null) {
                System.out.print("leste ");
            }
            if(localAtual.saidaSul != null) {
                System.out.print("sul ");
            }
            if(localAtual.saidaOeste != null) {
                System.out.print("oeste ");
            }
            System.out.println();
        }
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }
}
