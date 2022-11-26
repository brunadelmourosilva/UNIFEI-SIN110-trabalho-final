#
# import numpy, imageio
# X,Y = 100,100
# image = numpy.zeros((Y, X, 3), dtype=numpy.uint8)
# image2 = numpy.zeros((Y, X, 3), dtype=numpy.uint8)
# image[90, 90, :] = (0xFF, 0xFF, 0xFF)
# image2[1, 1, :] = (0xFF, 0xFF, 0xFF)
# imageio.imwrite('output.png', image, image2)


# Check the documentation for examples of ellipses based on standard deviations
# https://matplotlib.org/stable/gallery/statistics/confidence_ellipse.html
import numpy
import matplotlib.pyplot as plt


# xs = numpy.array([30])
# ys = numpy.array([40])
# plt.ylim(0, 1000)
# plt.xlim(0, 1000)
# plt.axis([0, 50, 0, 50])
# plt.scatter(xs, ys)
# plt.savefig('plot.png')

###########################################

# x = numpy.array([])
# y = numpy.array([])
#
# motes = int(input('Quantidade de motes: '))
#
# for vi in range(0, motes):
#     coordenada = input()
#     result = coordenada.split(", ")
#     x = numpy.append(x, result[0])
#     y = numpy.append(y, result[1])
#
# #plt.figure(figsize=(30, 45))
# # plt.ylim(0, 1000)
# # plt.xlim(0, 1000)
#
# plt.scatter(x, y)
# plt.savefig('plot.png')
# print()
# print('Valores do eixo x: ', x)
# print('Valores do eixo y: ', y)

###########################################
from Inicializacao import (dataSet as ds, grafo as g, visualizacao as vis)

class Vertex:
    def __init__(self, id, caminho, custo, verticesAdjacentes, ordemPesos, conhecido, x, y):
        self.id = id
        self.caminho = caminho
        self.custo = 999999999
        self.verticesAdjacentes = verticesAdjacentes
        self.ordemPesos = ordemPesos
        self.conhecido = conhecido
        self.x = x
        self.y = y

    def getAdjacencyVertex(self):
        return self.verticesAdjacentes

    def getWeightOrder(self):
        return self.ordemPesos

    def getCaminho(self):
        return self.caminho

    def __str__(self):
        return f"\nVértice: {self.id} \nCaminho: {self.printaCaminho()}"

    def printaCaminho(self):
        string = ""
        for vertice in self.caminho:
            string = string + str(vertice.id)

        return string


matriz = ds.criaMatrizAdjacencias("adjacency_matrix")
#print(matriz, '\n')

G = g.criaGrafo(matriz)
#print(G, '\n')
#gerar um grafo ponderado
#vis.visualizarGrafo(True, G, "adjacency_matrix")

###################### tirar depois #########################
vertexStation = Vertex(1, [], 999999999, [], [], False, 0, 1)
vertex1 = Vertex(2, [], 999999999, [], [], False, 3, 4)
vertex2 = Vertex(3, [], 999999999, [], [], False, 2, 3)
vertex3 = Vertex(4, [], 999999999, [], [], False, 3, 1)

vertexStation.caminho.append(vertexStation)
vertex1.caminho.append(vertex1)
vertex2.caminho.append(vertex2)
vertex3.caminho.append(vertex3)

### adjacency vertexes ###
vertexStation.getAdjacencyVertex().append(vertex1)
vertexStation.getAdjacencyVertex().append(vertex2)
vertexStation.getAdjacencyVertex().append(vertex3)

vertexStation.getWeightOrder().append(4)
vertexStation.getWeightOrder().append(1)
vertexStation.getWeightOrder().append(2)

vertex1.getAdjacencyVertex().append(vertexStation)
vertex1.getAdjacencyVertex().append(vertex2)
vertex1.getAdjacencyVertex().append(vertex3)

vertex1.getWeightOrder().append(4)
vertex1.getWeightOrder().append(3)
vertex1.getWeightOrder().append(5)

vertex2.getAdjacencyVertex().append(vertexStation)
vertex2.getAdjacencyVertex().append(vertex1)
vertex2.getAdjacencyVertex().append(vertex3)

vertex2.getWeightOrder().append(13)
vertex2.getWeightOrder().append(3)
vertex2.getWeightOrder().append(8)

vertex3.getAdjacencyVertex().append(vertexStation)
vertex3.getAdjacencyVertex().append(vertex1)
vertex3.getAdjacencyVertex().append(vertex2)

vertex3.getWeightOrder().append(2)
vertex3.getWeightOrder().append(5)
vertex3.getWeightOrder().append(8)

coordenada = input("Coordenadas de onde pegou fogo: ")
coordenada = coordenada.split(", ")

vertices = [vertex1, vertex2, vertex3, vertexStation]

verticeOrigem = None
for vertice in vertices:
    if str(vertice.x) == coordenada[0] and str(vertice.y) == coordenada[1]:
        print("ENCONTROU!")
        verticeOrigem = vertice
        break


print("Vértice de origem: ", verticeOrigem)

filaAnalise = []

verticeOrigem.conhecido = True
filaAnalise.append(verticeOrigem)

while len(filaAnalise) != 0:

    run = -1
    verticeAnalisado = filaAnalise[0]

    for adj in verticeAnalisado.getAdjacencyVertex():

        run = run + 1

        if(adj.conhecido == False):
            filaAnalise.append(adj)
            tempCusto = verticeAnalisado.custo + verticeAnalisado.ordemPesos[run]
            adj.custo = tempCusto
            adj.caminho = verticeAnalisado.caminho
            adj.caminho.append(adj)
            adj.conhecido = True
        else:
            tempCusto = verticeAnalisado.custo + verticeAnalisado.ordemPesos[run]

            if tempCusto < adj.custo:
                adj.custo = tempCusto
                adj.caminho = verticeAnalisado.caminho
                adj.caminho.append(adj)

    filaAnalise.remove(verticeAnalisado)


for vertice in vertices:
    print(vertice)

