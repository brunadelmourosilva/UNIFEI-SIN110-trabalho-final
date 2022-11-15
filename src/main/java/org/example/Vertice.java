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

    @Override
    public String toString() {
        return "Vertice{" +
                "id=" + id +
                ", caminho=" + caminho.stream().map(x -> x.getId()).collect(Collectors.toList()) +
                String.format(", custo= %.2f", custo) +
                '}';
    }
}
