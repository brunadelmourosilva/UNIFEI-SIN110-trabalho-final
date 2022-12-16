package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        List<Vertice> vertices = new ArrayList<>();
        Vertice baseCentral = null;

        /** realiza a criação dos vértices a partir das coordenadas **/
        System.out.print("Quantidade de motes: ");
        int qtdMotes = SCANNER.nextInt();

        SCANNER.nextLine();
        for (int i = 1; i <= qtdMotes + 1; i++) {
            String coordenada = SCANNER.nextLine();
            String[] coordenadas = coordenada.split(", ");

            vertices.add(i-1, new Vertice(i, Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1])));

            if(i == 1) baseCentral = vertices.get(0);
        }

        /** cálculo da hipotenusa para encontrar a distância entre vértices adjacentes **/
        calculaHipotenusa(vertices);


        System.out.print("\nDigite as coordenadas de onde pegou fogo: ");
        String coordenada = SCANNER.nextLine();
        String[] coordenadas = coordenada.split(", ");

        /** encontra o caminho mínimo entre as coordenadas de onde a queimada começou até a base central **/
        var verticeOrigem = encontraCaminhoMinimo(vertices, coordenadas);

        /** apresenta no console os resultados obtidos **/
        baseCentral.printaCaminhoECusto(verticeOrigem);
    }

    private static void calculaHipotenusa(List<Vertice> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertice verticeAtualSendoAnalisado = vertices.get(i);

            for (int j = 0; j < vertices.size(); j++) {
                Vertice proximoVertice = vertices.get(j);

                if(verticeAtualSendoAnalisado.getId() == proximoVertice.getId()){
                    continue;
                }

                //vertice fixo A e iteravel B
                Double verticeXAnalisando = verticeAtualSendoAnalisado.getX();
                Double verticeYAnalisando = verticeAtualSendoAnalisado.getY();
                Double verticeXProximo = proximoVertice.getX();
                Double verticeYProximo = proximoVertice.getY();

                Double cat1 = 0.0, cat2 = 0.0, hip = 0.0;

                //POSSIBILIDADE 1 - A= 10, 2 | B= 9, 1
                if(verticeXAnalisando <= verticeXProximo &&
                        verticeYAnalisando <= verticeYProximo) {
                    cat1 = verticeYAnalisando - verticeYProximo;
                    cat2 = verticeXAnalisando - verticeXProximo;

                    hip = Math.sqrt(Math.pow(cat1, 2) + Math.pow(cat2, 2));
                }

                //POSSIBILIDADE 2 - A= 2, 3 | B= 5, 1
                if(verticeXAnalisando <= verticeXProximo &&
                        verticeYAnalisando >= verticeYProximo) {
                    cat1 = verticeYAnalisando - verticeYProximo;
                    cat2 = verticeXProximo - verticeXAnalisando;

                    hip = Math.sqrt(Math.pow(cat1, 2) + Math.pow(cat2, 2));
                }

                //POSSIBILIDADE 3 - A= 4, 1 | B= 2, 2
                if(verticeXAnalisando >= verticeXProximo &&
                        verticeYAnalisando <= verticeYProximo) {
                    cat1 = verticeYProximo - verticeYAnalisando;
                    cat2 = verticeXAnalisando - verticeXProximo;

                    hip = Math.sqrt(Math.pow(cat1, 2) + Math.pow(cat2, 2));
                }

                //POSSIBILIDADE 4
                if(verticeXAnalisando >= verticeXProximo &&
                        verticeYAnalisando >= verticeYProximo) {
                    cat1 = verticeYAnalisando - verticeYProximo;
                    cat2 = verticeXAnalisando - verticeXProximo;

                    hip = Math.sqrt(Math.pow(cat1, 2) + Math.pow(cat2, 2));
                }

                //DELIMITADOR EM ESCALA 2 | 1-10
                if(hip <= 100d) {
                    verticeAtualSendoAnalisado.getVerticesAdjacentes().add(proximoVertice);
                    verticeAtualSendoAnalisado.getOrdemPesos().add(hip);
                }
            }
        }
    }

    private static Vertice encontraCaminhoMinimo(List<Vertice> vertices, String[] coordenadas) {
        Vertice verticeOrigem = null;
        for (Vertice vertice : vertices) {
            if(vertice.getX() == Double.parseDouble(coordenadas[0]) &&
                    vertice.getY() == Double.parseDouble(coordenadas[1])){
                verticeOrigem = vertice;
                break;
            }
        }

        // Setando o vértice de origem como conhecido, e o adicionando para a fila de análise
        var filaAnalise = new ArrayList<Vertice>();
        verticeOrigem.setConhecido(true);
        filaAnalise.add(verticeOrigem);

        while(!filaAnalise.isEmpty()) {

            // Vértice atual sendo analisado
            Vertice verticeSendoAnalisado = filaAnalise.get(0);

            int run = -1;


            // Executando para cada vértice na lista de adjacentes do analisado
            for (Vertice adj : verticeSendoAnalisado.getVerticesAdjacentes()) {

                run++;

                // Se o vértice adjacente não for conhecido
                if (adj.getConhecido() == false) {

                    adj.getCaminho().clear();
                    for(Vertice vertice: verticeSendoAnalisado.getCaminho()){
                        adj.getCaminho().add(vertice);
                    }

                    // Adiciona o vértice de origem + o próprio v. adj a variável caminho do v. adj
                    adj.getCaminho().add(adj);

                    // Atualiza a variável custo no vértice adjacente
                    adj.setCusto(verticeSendoAnalisado.getCusto() + verticeSendoAnalisado.getOrdemPesos().get(run));
                    
                    // Adiciona o novo vértice recém-conhecido para a fila de análise
                    filaAnalise.add(adj);
                    adj.setConhecido(true);

                
                // Se o vértice adjacente for conhecido
                }else {

                    // Comparamos o custo atual do vértice adjacente com o custo da nova rota recém-descoberta
                    double custoComparacao = verticeSendoAnalisado.getCusto() + verticeSendoAnalisado.getOrdemPesos().get(run);

                    // Se o custo da nova rota for menor, atualiza o custo e o caminho do vértice adjacente
                    if(custoComparacao < adj.getCusto()){
                        adj.getCaminho().clear();
                        for(Vertice vertice: verticeSendoAnalisado.getCaminho()){
                            adj.getCaminho().add(vertice);
                        }
                        adj.getCaminho().add(adj);
                        adj.setCusto(custoComparacao);
                    }
                }
            }

            // Após finalizar as análises para o vértice n, retira-o da fila de análise
            filaAnalise.remove(verticeSendoAnalisado);
        }

        return verticeOrigem;
    }

}
/*
*
Entradas de teste
*
6.14, 7.98
6.14, 5.98
6.14, 3.98
1, 4
9, 8
* --------
8, 4
7.44, 3.46
7, 1
6.56, 4.58
8.26, 3
6.68, 3.8
5.9, 3.26
 */