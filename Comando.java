/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Essa classe guarda informacoes sobre um comando que foi digitado pelo 
 * usuario. Um comando atualmente consiste em duas strings: uma palavra
 * de comando e uma segunda palavra (por exemplo, se o comando for 
 * "pegar mapa", entao as duas strings obviamente serao "pegar" e "mapa").
 * 
 * Isso eh usado assim: comandos ja estao validados como comandos validos
 * Se o usuario entrou um comando invalido (uma palavra que nao eh
 * conhecida) entao o a palavra de comando eh <null>.
 *
 * Se o comando tem so uma palavra, a segunda palavra eh <null>
 * 
 * Traduzido por Julio César Alves. Versão: 2023.10.21
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Comando
{
    private String palavraDeComando;
    private String segundaPalavra;

    /**
     * Cria um objeto comando. Primeira e segunda palavra devem ser 
     * fornecidas, mas qualquer uma (ou ambas) pode ser null.
     * @param primeiraPalavra A primeira palavra do comando. Null se
     * 						  o comando nao foi reconhecido
     * @param segundaPalavra A segunda palavra do comando.
     */
    public Comando(String primeiraPalavra, String segundaPalavra)
    {
        palavraDeComando = primeiraPalavra;
        this.segundaPalavra = segundaPalavra;
    }

    /**
     * Retorna a palavra de comando (a primeira palavra) deste comando.
     * Se o comando nao foi entendido, o resultado eh null.
     * @return A palavra de comando.
     */
    public String obterPalavraDeComando()
    {
        return palavraDeComando;
    }

    /**
     * @return A segunda palavra deste comando. Retorna null se 
     * nao existe segunda palavra.
     */
    public String obterSegundaPalavra()
    {
        return segundaPalavra;
    }

    /**
     * @return true se o comando nao foi entendido.
     */
    public boolean ehDesconhecido()
    {
        return (palavraDeComando == null);
    }

    /**
     * @return true se o comando tem uma segunda palavra.
     */
    public boolean temSegundaPalavra()
    {
        return (segundaPalavra != null);
    }
}

