
## Best way to describe the map: Adjacency List vs Adjacency Matrix

O mapa retangular é um grafo esparso, ou seja, o número de arestas não é muito superior ao número de vértices, já que para n e m maiores que 2 o número máximo de arestas que um ponto tem é 4 e o minimo é 2. Isto significa que para ver o número de conexões de um ponto o nº máximo de operações que se tem que fazer é 4. Por este motivo, a vantagem que se teria em usar uma matriz de adjacências (O(1) para descobrir arestas) é pouco significante e não compensa o maior uso de memória (O(n^2)) face ao da lista de adjacências. Por estas razões foi escolhida uma lista de adjacências para representar o mapa.

## Reset method in PathSimulation Class
Podemos querer correr a mesma simula��o (com os mesmos par�metros) mais do que uma vez, assim sempre que se faz simulate garantimos que os par�metros din�micos da simula��o s�o os iniciais(tempo=0, e coisas assim)

## Generic Type na interface PECI
Caso queiramos ter outro tipo e containers com os Events ou caso queiramos ter outro tipo de events que implementem a interface EventI. Deste modo aceita qualquer tipo de eventos e n�o est� t�o restrito a eventos que sejam conduzidos pelo tempo.

## Estrutura de dados para guardar a lista de individuos (população)
LinkedList porque o nº de individuos não é fixa. ArrayList não é ideal porque a capacity ia ter que ser várias vezes
ajustavel aumentando bastante a complexidade da inserção de individuos.

## Lista de individuos: PriorityQueue vs Collection::Sort da LinkedList
Collection::Sort porque a priorityQueue tem uma capacity ajustável e como temos que recalcular o conforto depois da execução
de cada evento teriamos que dar sort da priority queue de qualquer forma, o que não se justifica.

## Guardar o evento de cada individuo para Epidemia
Optou-se por guardar a referência para cada evento de um individuo em cada objeto Individual, de forma a tornar a epidemia mais eficiente. Uma alternativa seria, caso o individuo morre-se percorrer o pec de forma a encontrar cada evento pertencente a esse individuo e retirá-lo da pec. Mas isso implicaria percorrer realizar uma cópia da pec para os N individuos e percorrê-la N vezes...

## Retornar lista de eventos no simulateEvent
Optou-se por ter o simulateEvent a retornar uma lista de eventos. Assim, caso não se queira retornar nenhum evento pode-se mandar a lista a null ou empty, mas caso se queira retornar torna mais fácil o processo de adicionar eventos à pec já que o evento não tem que ter acesso à pec(ou pecs) onde está a ser adicionado.

## Interface Event e PEC
Optou-se também por fornecer uma interface para a pec, caso se pretenda ter um containet para outros eventos que implementem a interface EventI mas que não sejam descendam da classe Event e para não limitar a pec a ser ordenada pelo tempo. Dar exemplos de casos em que isto pode ser util

## SimulationA
Providencia já algumas funcionalidades que podem ser extendidas por outra classe para serem utilizadas noutros exemplos que façam uso de uma pec, cujos eventos são simulados por ordem temporal, como por exemplo, a simulação da auto-estrada. Providencia o método addNewEvents que adiciona os novos eventos à pec, e o método Init que dá reset às variaveis para o caso em que se pretende correr várias simulações sequencialmente. Fornece como métodos abstract: simulate e initialize..

## Observação como evento
Observação é definida por um tempo espeficifico por isso faz sentido adicioná-la como um evento à pec. Adicionamos uma observação de cada vez para não encher demasiado a pec..

## Individuos possume referência para a população da Simulação
População da simulação possui parâmetros informativos da simulação necessários aos individuos, por isso esta foi a melhor alternativa arranjada para poder passar esses parâmetros aos individuos e aos eventos, sem ter que estar a copiar todos os parâmetros necessários. Deste modo, guarda-se apenas uma única referência para a população em cada individuo!

#RESULTADOS DA SIMULACAO

## Mapa muito grande
Mapa de dimensoes maiores -> dist na formula do comforto maior -> comfort maior -> reproduzem-se e morre menos
Mas se metermos ponto final mais perto do inicial já é o mesmo que normalmente

K maior -> menor comfort -> tempo para reprodução e move maior -> menos eventos
                          -> tempo de death menor
                         -> mas morre mais gente nas epidemias quando existem epidemias - por exemplo quando initpop é maior que maxpop 






