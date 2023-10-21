/**
 * Classe Local - um local em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Local" representa um lugar no cenario do jogo. Ele eh conectado 
 * aos outros locais atraves de saidas. As saidas sao nomeadas como 
 * norte, sul, leste e oeste. Para cada direcao, o local guarda uma 
 * referencia para o local vizinho, ou null se nao ha saida naquela 
 * direcao.
 * 
 * Traduzido por Julio César Alves. Versão: 2023.10.21
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Local 
{
    public String descricao;
    public Local saidaNorte;
    public Local saidaSul;
    public Local saidaLeste;
    public Local saidaOeste;

    /**
     * Cria um local com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "um jardim aberto".
     * @param descricao A descricao do local.
     */
    public Local(String descricao) 
    {
        this.descricao = descricao;
    }

    /**
     * Define as saidas do local. Cada direcao ou leva a um
     * outro local ou eh null (nenhuma saida para la).
     * @param norte A saida norte.
     * @param leste A saida leste.
     * @param sul A saida sul.
     * @param oeste A saida oeste.
     */
    public void ajustarSaidas(Local norte, Local leste, Local sul, Local oeste) 
    {
        if(norte != null)
            saidaNorte = norte;
        if(leste != null)
            saidaLeste = leste;
        if(sul != null)
            saidaSul = sul;
        if(oeste != null)
            saidaOeste = oeste;
    }

    /**
     * @return A descricao do local.
     */
    public String obterDescricao()
    {
        return descricao;
    }

}
