package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        var vertice1 = new Vertice();
        vertice1.setId(1);
        vertice1.setX(3d);
        vertice1.setY(4d);

        var vertice2 = new Vertice();
        vertice2.setId(2);
        vertice2.setX(2d);
        vertice2.setY(3d);

        var vertice3 = new Vertice();
        vertice3.setId(3);
        vertice3.setX(3d);
        vertice3.setY(1d);

        var verticeEstacao = new Vertice();
        verticeEstacao.setId(4);
        verticeEstacao.setX(0d);
        verticeEstacao.setY(1d);

        verticeEstacao.getVerticesAdjacentes().add(vertice1);
        verticeEstacao.getVerticesAdjacentes().add(vertice2);
        verticeEstacao.getVerticesAdjacentes().add(vertice3);

        verticeEstacao.getOrdemPesos().add(4d);
        verticeEstacao.getOrdemPesos().add(13d);
        verticeEstacao.getOrdemPesos().add(2d);

        vertice1.getVerticesAdjacentes().add(verticeEstacao);
        vertice1.getVerticesAdjacentes().add(vertice2);
        vertice1.getVerticesAdjacentes().add(vertice3);

        vertice1.getOrdemPesos().add(4d);
        vertice1.getOrdemPesos().add(3d);
        vertice1.getOrdemPesos().add(1d);

        vertice2.getVerticesAdjacentes().add(verticeEstacao);
        vertice2.getVerticesAdjacentes().add(vertice1);
        vertice2.getVerticesAdjacentes().add(vertice3);

        vertice2.getOrdemPesos().add(13d);
        vertice2.getOrdemPesos().add(3d);
        vertice2.getOrdemPesos().add(8d);

        vertice3.getVerticesAdjacentes().add(verticeEstacao);
        vertice3.getVerticesAdjacentes().add(vertice1);
        vertice3.getVerticesAdjacentes().add(vertice2);

        vertice3.getOrdemPesos().add(2d);
        vertice3.getOrdemPesos().add(1d);
        vertice3.getOrdemPesos().add(8d);

        var vertices = List.of(vertice1, vertice2, vertice3, verticeEstacao);

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
* while (!filaAnalise.isEmpty()) {
            int run = -1;
            var verticeAnalisado = filaAnalise.get(0);

            for (Vertice adjacente: verticeAnalisado.getVerticesAdjacentes()) {
                run++;

                if (!adjacente.getConhecido()) {
                    filaAnalise.add(adjacente);
                    double tempCusto = verticeAnalisado.getCusto() + verticeAnalisado.getOrdemPesos().get(run);

                    adjacente.setCusto(tempCusto);
                    adjacente.setCaminho(verticeAnalisado.getCaminho());
                    adjacente.getCaminho().add(adjacente);
                    adjacente.setConhecido(true);
                } else {
                    double tempCusto = verticeAnalisado.getCusto() + verticeAnalisado.getOrdemPesos().get(run);

                    if(tempCusto < adjacente.getCusto()) {
                        adjacente.setCusto(tempCusto);
                        adjacente.setCaminho(verticeAnalisado.getCaminho());
                        adjacente.getCaminho().add(adjacente);
                    }
                }
            }

            filaAnalise.remove(verticeAnalisado);
        }*/