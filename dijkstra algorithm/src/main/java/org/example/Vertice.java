package org.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Vertice {

    private Integer id;
    private List<Vertice> caminho;
    private Double custo = 0d;
    private List<Vertice> verticesAdjacentes = new ArrayList<>();
    private List<Double> ordemPesos = new ArrayList<>();
    private Boolean conhecido = false;
    private Double x;
    private Double y;

    {
        this.caminho = new ArrayList<>();
        caminho.add(this);
    }

    public Vertice() {
    }

    public Vertice(final Integer id, final Double x, final Double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "id=" + id +
                ", x = " + x +
                ", y = " + y +
                ", caminho=" + caminho.stream().map(x -> x.getId()).collect(Collectors.toList()) +
                String.format(", custo= %.2f", custo) +
                ", verticesAdjacentes=" + verticesAdjacentes.stream().map(x -> x.getId()).collect(Collectors.toList()) +
                ", ordemPesos=" + ordemPesos +
                '}';
    }

    public void printaCaminhoECusto(Vertice verticeOrigem){
        System.out.println("\n******************************************");
        System.out.println("\t\tSilvanet Wildfire Sensor Info\n");
        System.out.println("Power source | Solar-powered, battery-free");
        System.out.println("Distance between Sensors (Radius) | 100m radius for 60min detection of 2x2m fire");
        System.out.println("******************************************\n");

        System.out.println("\n******************************************");
        System.out.printf("Coordenadas do incêndio: (X = %.2f, Y = %.2f)", verticeOrigem.getX(), verticeOrigem.getY());
        System.out.printf("\n\nCoordenadas da base central: (X = %.2f, Y = %.2f)", x, y);
        System.out.printf("\n\nCaminho mínimo de (X = %.2f, Y = %.2f) até (X = %.2f, Y = %.2f): ", verticeOrigem.getX(), verticeOrigem.getY(), x, y);
        caminho.forEach(letraCaminho -> System.out.print(letraCaminho.getId() + " "));
        System.out.printf("\n\nCusto: %.3f", custo);
        System.out.println("\n\nTotal de energia gasto: " + null);
        System.out.println("******************************************");
    }
}
