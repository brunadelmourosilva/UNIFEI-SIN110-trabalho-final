package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        List<Vertice> vertices = new ArrayList<>();
        Vertice baseCentral = null;

        //inicializar vértices com com x e y
        System.out.print("Quantidade de motes: ");
        int qtdMotes = scanner.nextInt();

        scanner.nextLine();
        for (int i = 1; i <= qtdMotes + 1; i++) {
            String coordenada = scanner.nextLine();
            String[] coordenadas = coordenada.split(", ");

            vertices.add(i-1, new Vertice(i, Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1])));
            
            if(i == 1) baseCentral = vertices.get(0);
        }


        //calculo da hipotenusa para distancia entre vertices adjacentes
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

        List<String> letras = List.of("A", "B", "C", "D", "E", "F", "G");
//        for(int i=0; i<vertices.size(); i++){
//            System.out.println(letras.get(i) + " - " + vertices.get(i));
//        }


        //----------------------------------------------------------------
        //----------------------------------------------------------------

        System.out.print("\nDigite as coordenadas de onde pegou fogo: ");
        String coordenada = scanner.nextLine();
        String[] coordenadas = coordenada.split(", ");

        Vertice verticeOrigem = null;
        for (Vertice vertice : vertices) {
            if(vertice.getX() == Double.parseDouble(coordenadas[0]) &&
                    vertice.getY() == Double.parseDouble(coordenadas[1])){
                verticeOrigem = vertice;
                break;
            }
        }

        var filaAnalise = new ArrayList<Vertice>();
        verticeOrigem.setConhecido(true);
        filaAnalise.add(verticeOrigem);
        //System.out.println("VÉRTICE DE ORIGEM: " + verticeOrigem);


        while(!filaAnalise.isEmpty()) {
            Vertice verticeSendoAnalisado = filaAnalise.get(0);

            int run = -1;

            for (Vertice adj : verticeSendoAnalisado.getVerticesAdjacentes()) {

                run++;

                if (adj.getConhecido() == false) {

                    for(Vertice vertice: verticeSendoAnalisado.getCaminho()){
                        adj.getCaminho().clear();
                        adj.getCaminho().add(vertice);
                    }
                    adj.getCaminho().add(adj);
                    adj.setCusto(verticeSendoAnalisado.getCusto() + verticeSendoAnalisado.getOrdemPesos().get(run));
                    filaAnalise.add(adj);
                    adj.setConhecido(true);

                }else {
                    double custoComparacao = verticeSendoAnalisado.getCusto() + verticeSendoAnalisado.getOrdemPesos().get(run);

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
            filaAnalise.remove(verticeSendoAnalisado);
        }

        //vertices.forEach(System.out::println);
        baseCentral.printaCaminhoECusto(verticeOrigem);
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