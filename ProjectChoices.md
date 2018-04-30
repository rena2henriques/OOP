
## Best way to describe the map: Adjacency List vs Adjacency Matrix

O mapa retangular é um grafo esparso, ou seja, o número de arestas não é muito superior ao número de vértices, já que para n e m maiores que 2 o número máximo de arestas que um ponto tem é 4 e o minimo é 2. Isto significa que para ver o número de conexões de um ponto o nº máximo de operações que se tem que fazer é 4. Por este motivo, a vantagem que se teria em usar uma matriz de adjacências (O(1) para descobrir arestas) é pouco significante e não compensa o maior uso de memória (O(n^2)) face ao da lista de adjacências. Por estas razões foi escolhida uma lista de adjacências para representar o mapa.

## Reset method in PathSimulation Class
Podemos querer correr a mesma simula��o (com os mesmos par�metros) mais do que uma vez, assim sempre que se faz simulate garantimos que os par�metros din�micos da simula��o s�o os iniciais(tempo=0, e coisas assim)

##Generic Type na interface PECI
Caso queiramos ter outro tipo e containers com os Events ou caso queiramos ter outro tipo de events que implementem a interface EventI. Deste modo aceita qualquer tipo de eventos e n�o est� t�o restrito a eventos que sejam conduzidos pelo tempo.

## Estrutura de dados para guardar a lista de individuos (população)
LinkedList porque o nº de individuos não é fixa. ArrayList não é ideal porque a capacity ia ter que ser várias vezes
ajustavel aumentando bastante a complexidade da inserção de individuos.

## Lista de individuos: PriorityQueue vs Collection::Sort da LinkedList
Collection::Sort porque a priorityQueue tem uma capacity ajustável e como temos que recalcular o conforto depois da execução
de cada evento teriamos que dar sort da priority queue de qualquer forma, o que não se justifica.


