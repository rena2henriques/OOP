
## Best way to describe the map: Adjacency List vs Adjacency Matrix

O mapa retangular é um grafo esparso, ou seja, o número de arestas não é muito superior ao número de vértices, já que para n e m maiores que 2 o número máximo de arestas que um ponto tem é 4 e o minimo é 2. Isto significa que para ver o número de conexões de um ponto o nº máximo de operações que se tem que fazer é 4. Por este motivo, a vantagem que se teria em usar uma matriz de adjacências (O(1) para descobrir arestas) é pouco significante e não compensa o maior uso de memória (O(n^2)) face ao da lista de adjacências. Por estas razões foi escolhida uma lista de adjacências para representar o mapa.

