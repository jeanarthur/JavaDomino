# Dominó Java
#### Jogo de dominó jogavél no terminal, feito em Java.

---

#### O projeto tem como objetivo explorar os conceitos de estrutura de dados, em específico o uso de Lista duplamente encadeada.

### Construção da Lista

- #### Por definição a lista possui variáveis que representam o `inicio` e `fim`, são da classe `No` que representam um nó da fila e carregam a referência do elemento da lista e dos nós, `anterior` e `próximo`.
- #### Fiz a classe com o uso de `Generic`, ou seja, tanto a `Lista` quanto o `No` podem ser utilizados para armazenar qualquer outra classe.
- #### Além disso inclui um atributo `tamanho` para facilitar algumas comparações.
- #### Também criei um `ListaIterator` para melhorar o processo de percorrer os elementos.
- #### Para inserir um elemento na lista criei dois métodos, um que recebe somente o elemento e por sua vez o inclui no final da lista. E outro que recebe além do elemento um inteiro que represente a posição que ocupará.
- #### Para remover um elemento na lista criei dois métodos, um que não recebe parâmetro e por sua vez remove o último elemento da lista. E outro que recebe um inteiro que represente a posição do elemento que será removido.

### Uso da Lista

- #### A classe `Jogador` possui uma `Lista` de `Peca` que representa as peças na mão do jogador.
- #### A classe `Jogo` possui uma `Lista` de `Peca` que representa as peças na mesa (onde serão inseridas novas peças, no inicio ou fim, no decorrrer dos turnos) e outra `Lista` de `Peca` que representa as peças no monte de compras (onde serão removidas peças, eventualmente, no decorrer dos turnos).
- #### Além disso a lista é utilizada na lógica de forma auxiliar através de uma `Lista` de `Integer` para guardar as posições das peças jogáveis do jogador durante uma avaliação feita em cada turno.

### Lógica geral de execução

- #### Uma classe `Jogo` é instânciada informando o valor da maior peça, para um jogo tradicional esse valor é `6`.
- #### O construtor da classe inicializa as varíaveis e executa o método de criação das peças, que são inseridas no monte e depois executa a distribuiçã das peças, removendo de forma aleatória do monte e adicionando aproximadamente um quarto do total de peças na mão do Jogador e do Computador.
- #### Após a execução do construtor é necessário chamar o método iniciar da classe `Jogo`.
- #### Assim que iniciado, o método que avalia e executa a primeira jogada é chamado.
- #### Pelas regras o jogador que possuir a maior dupla deve iniciar jogando essa peça.
- #### Se nenhum jogador possuir peças duplas, então joga aquele que possuir a peça de maior valor, sendo o valor a soma de ambos os lados, inicia jogando essa peça.
- #### Após a primeira jogadas segue-se para o método que executará em loop até que um jogador vença.
- #### É apresentado o estado do jogo (Mesa + Mão do Jogador) e definido o jogador da vez.
- #### As peças na mão do jogador da vez são avaliadas, se possuir uma ou mais peças válidas é solicitado que o jogador selecione uma para jogar (No caso do Computador é selecionado a última peça jogável do resultado da validação)
- #### Se o jogador da vez não possuir peças jogáveis, então será perguntado se deseja comprar do monte ou passar a vez (No caso do Computador é selecionado uma das opções de forma aleaatória)
- #### Se o monte não possuir peças então a vez será passada ao tentar comprar do monte.
- #### Ao comprar uma peça é feita novamente a análise, podendo o jogador da vez jogá-la, se aplicável, ou selecionar dentre as opções de compra ou passagem da vez.
- #### Selecionando uma peça para jogar é avaliada se essa peça pode ser colocada no inicio ou fim da lista de peças da mesa, com base no valor de determinado lado das peças que ocupam essas posições.
- #### Se a peça pode ser colocada em somente um lado, ela será colocada automaticamente.
- #### Se a peça pode ser colocada em ambos os lados, será solicitado que o jogador selecione o lado desejado.(No caso do Computadoor é escolhido o lado de forma aleatória)
- #### Se não pode ser colocada em nenhum dos lados, será exibida uma mensagem soicitado que selecione uma peça válida.
- #### O jogo segue dessa forma até que um dos jogadores tenha zerado o número de peças na mão, sendo o vencedor da partida.
- #### Se o monte de compra estiver vazio e ambos os jogadores não puderem jogar, vence aquele que possuir a menor pontuação (soma dos valores de todas as peças em mão)
- #### Se a pontuação for igual para ambos jogadores é declarado empate.