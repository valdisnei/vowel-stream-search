# Busca de um caracter vogal em uma Stream

Sistema que, dada uma stream, encontra o primeiro caracter Vogal, após uma consoante, 
onde a mesma é antecessora a uma vogal e que não se repita no resto da stream. Caso não encontre
o caracter vogal, uma mensagem amigável é informada. 
O sistema foi desenvolvido utilizando Java 1.8 e Gradle para *build* do projeto.

## Pré-requisitos

- Java 1.8 (de preferência 1.8.0_111)
- Definição da variável de ambiente *JAVA_HOME*, que aponta para a instalação do JAVA

## Configuração e Build

As seguintes tecnologias foram utilizadas:

- Java 8;
- JUnit 4;
- Hamcrest matchers para tornar os *asserts* nos testes mais elegantes;
- Gradle para *build* do projeto

O sistema deve ser construído na raiz do projeto vowel-stream-search com o seguinte comando:

***./gradlew clean build*** (em sistemas Unix) e ***./gradlew.bat clean build*** (Windows)

Esse comando utiliza o Gradle *wrapper* para baixar a distribuição do Gradle apropriada.

## Executando o sistema

O sistema deve ser executado na raiz do projeto vowel-stream-search com o seguinte comando Gradle: ***./gradlew run -PappArgs={stream}***

Esse comando realiza as seguintes tarefas: 

- *build* do sistema, execução dos testes unitários;
- Execução da classe principal *MainApplication* passe todos os testes unitários forem executados com sucesso;

O parâmetro **{stream}** deve ser substituído pela stream que se deseja buscar o caracter vogal.

Caso o parâmetro **{stream}** não seja passado para o sistema, uma mensagem de como deve ser feita a execução é mostrada.

A saída do sistema tanto no sucesso quanto insucesso na busca do caracter vogal ocorre no *console*.

## Testes unitários

Os testes unitários podem ser executados individualmente através do seguinte comando Gradle: 

***./gradlew test***

## Premissas

Uma chamada para ``` hasNext() ``` irá retornar se a stream ainda contém caracteres para processar.

Uma chamada para ``` getNext() ``` irá retornar o próximo caracter a ser processado na stream.

Não será possível reiniciar o fluxo da leitura da stream.

## Arquitetura e padrões

A classe *CharSequenceStream* representa uma implementação da interface *Stream* de String.

A estratégia utilizada para a resolução do problema foi a seguinte:

1. Duas estruturas foram criadas:

    - Um *LinkedHashMap* que armazena uma marcação se uma vogal ocorreu mais de uma vez na stream. Foi utilizado um 
        *LinkedHashMap* pois a varredura dessa estrutura deve ocorrer na ordem em que os caracteres vogais
        aparecem na *Stream*.
   
    - Um *HashMap* que armazena a lista de antecessores na stream para cada caracter.
   
2. Ao percorrer a *Stream*, as duas estruturas acima são preenchidas. Para preencher a estrutura
dos antecessores, o último caracter processado da *Stream* é guardado.

3. Ao final do preenchimento das estruturas, inicia-se a tentativa de busca do caracter Vogal:
    a. Para cada vogal da estrutura de marcação de repetição de vogais
  
        a.1. Se o vogal corrente se repetir na *`Stream`*, retorna-se ao passo a
    
        a.2. Para cada antecessor da vogal corrente na *`Stream`*
    
            a.2.1 Se for uma vogal ou caracter especial, retorna-se ao passo a.2
        
            a.2.2 Sendo então uma consoante, para cada antecessor da consoante corrente na *`Stream`*
        
                a.2.2.1 Se for um caracter vogal, esse é retornado como resultado da busca 
            
                a.2.2.2 Caso não o seja, retorna-se o passo a.2.2

4. Ao final do processo, o caracter vogal encontrado ou um caracter vazio (indicando que o vogal não foi encontrado)
é retornado como resultado da busca.

## Entradas e saídas

Exemplo (em sistemas Unix):

cd /*{PATH_DO_PROJETO}*/vowel-stream-search

**./gradlew run -PappArgs=aAbBABacafe**

**Output:** e

**./gradlew run -PappArgs=casa**

**Output:** Caracter vogal nao localizado. Por favor, tente outra stream.

**./gradlew run**

**Output:** Executar o sistema da seguinte forma: ./gradlew run -PappArgs={stream}. Substituir {stream} pela stream desejada.

## Cenários não implementados

Suporte a *Streams* acentuadas.