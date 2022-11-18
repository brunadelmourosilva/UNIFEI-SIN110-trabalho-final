package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);
        List<Vertice> vertices = new ArrayList<>();

        //inicalizar vertives com com x e y
        System.out.print("Quantidade de MOTES: ");
        int qtdMotes = scanner.nextInt();

        scanner.nextLine();
        for (int i = 1; i <= qtdMotes + 1; i++) {
            String coordenada = scanner.nextLine();
            String[] coordenadas = coordenada.split(", ");

            vertices.add(i-1, new Vertice(i, Double.parseDouble(coordenadas[0]), Double.parseDouble(coordenadas[1])));
        }

//        for (Vertice vertice : verticesIniciais) {
//            System.out.println(vertice.getId() + " | " + vertice.getX() + ", " + vertice.getY());
//        }

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

                verticeAtualSendoAnalisado.getVerticesAdjacentes().add(proximoVertice);
                verticeAtualSendoAnalisado.getOrdemPesos().add(hip);
            }
        }

        List<String> letras = List.of("A", "B", "C", "D", "E");
        for(int i=0; i<vertices.size(); i++){
            System.out.println(letras.get(i) + " - " + vertices.get(i));
        }


        //----------------------------------------------------------------
//        var vertice1 = new Vertice();
//        vertice1.setId(1);
//        vertice1.setX(3d);
//        vertice1.setY(4d);
//
//        var vertice2 = new Vertice();
//        vertice2.setId(2);
//        vertice2.setX(2d);
//        vertice2.setY(3d);
//
//        var vertice3 = new Vertice();
//        vertice3.setId(3);
//        vertice3.setX(3d);
//        vertice3.setY(1d);
//
//        var verticeEstacao = new Vertice();
//        verticeEstacao.setId(4);
//        verticeEstacao.setX(0d);
//        verticeEstacao.setY(1d);
//
//        verticeEstacao.getVerticesAdjacentes().add(vertice1);
//        verticeEstacao.getVerticesAdjacentes().add(vertice2);
//        verticeEstacao.getVerticesAdjacentes().add(vertice3);
//
//        verticeEstacao.getOrdemPesos().add(4d);
//        verticeEstacao.getOrdemPesos().add(13d);
//        verticeEstacao.getOrdemPesos().add(2d);
//
//        vertice1.getVerticesAdjacentes().add(verticeEstacao);
//        vertice1.getVerticesAdjacentes().add(vertice2);
//        vertice1.getVerticesAdjacentes().add(vertice3);
//
//        vertice1.getOrdemPesos().add(4d);
//        vertice1.getOrdemPesos().add(3d);
//        vertice1.getOrdemPesos().add(1d);
//
//        vertice2.getVerticesAdjacentes().add(verticeEstacao);
//        vertice2.getVerticesAdjacentes().add(vertice1);
//        vertice2.getVerticesAdjacentes().add(vertice3);
//
//        vertice2.getOrdemPesos().add(13d);
//        vertice2.getOrdemPesos().add(3d);
//        vertice2.getOrdemPesos().add(8d);
//
//        vertice3.getVerticesAdjacentes().add(verticeEstacao);
//        vertice3.getVerticesAdjacentes().add(vertice1);
//        vertice3.getVerticesAdjacentes().add(vertice2);
//
//        vertice3.getOrdemPesos().add(2d);
//        vertice3.getOrdemPesos().add(1d);
//        vertice3.getOrdemPesos().add(8d);

//        var vertices = List.of(vertice1, vertice2, vertice3, verticeEstacao);

        //----------------------------------------------------------------


        System.out.println("Coordenadas de onde pegou fogo: ");
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
        System.out.println("VÉRTICE DE ORIGEM: " + verticeOrigem);


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

        vertices.forEach(System.out::println);


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
 */