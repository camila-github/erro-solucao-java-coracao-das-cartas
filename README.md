## Exercicio (Java): Coração das cartas

O exercicio publicado é referente ao treinamento do Bootcamp Java - Resolvendo Algoritmos com Java 
(https://digitalinnovation.one)


#### Descrição do Desafio:

Marcos decidiu abandonar o bar da faculdade onde jogava truco para dedicar-se ao mundo da programação. Para que isso fosse mais fácil, decidiu criar um novo jogo de cartas. 

O coração das cartas, como Marcos apelidou o jogo, é individual e jogado com três pilhas, inicialmente com o mesmo número de cartas. Cada carta tem um valor numérico inteiro de 0 até 9. O jogador pode, a qualquer momento ver o valor de qualquer carta, mas só pode jogar com as cartas que estão no topo das pilhas. Em cada rodada, o jogador pode remover qualquer combinação de cartas que estejam no topo da pilha (pode escolher 1, 2 ou até 3 cartas) cuja soma dos valores seja múltiplo de 3. O jogo é ganho quando todas as cartas forem removidas das pilhas. Se alguma carta não puder ser removida, perde-se o jogo.

#### Entrada: 

A entrada é composta por várias instâncias Cada instância é iniciada por um inteiro N (0 ≤ N ≤ 100), que identifica o número de cartas em cada pilha. A entrada termina quando N = 0. Cada uma das N linhas seguintes contém três inteiros A, B e C, que descrevem os valores numéricos das cartas em um nível da pilha (0 ≤ A, B, C ≤  9). As pilhas são descritas do topo até o fundo.

#### Saída: 

Para cada instância, imprima uma linha contendo o número 1 se o jogador pode ganhar a instância do jogo ou o número 0 se o jogo for impossível.

Exemplos de Entrada  | Exemplos de Saída
------------- | -------------
2 | 1
1 1 1 | 0
2 0 4
3
1 0 0
0 1 0
0 0 0
0

#### Java　

```java
//SOLUCAO 1

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CoracaoDasCartas {
    
    static Map<String, Integer> arrAdicionarCartas = new HashMap<String, Integer>();
    static int quantLinhas;
    static int[][] cartas;
    
    static public boolean calc(int num) {
      return (num % 3 == 0) ? true : false;
    }

    static public boolean verificarInstancia(int a, int b, int c) {
        
      String auxCartas = "" + a + b + c;
        
      int x = arrAdicionarCartas.getOrDefault(auxCartas, 0);
      if (x > 0) return (x == 1? true : false);
      
      if(a == b && b == c && c == quantLinhas) return true;
          
      if( a < quantLinhas && calc(cartas[0][a]) && verificarInstancia(a + 1, b, c)) return true;
        
      if( b < quantLinhas && calc(cartas[1][b]) && verificarInstancia(a, b + 1, c)) return true;
        
      if( c < quantLinhas && calc(cartas[2][c]) && verificarInstancia(a, b, c + 1)) return true;
      
      if( a < quantLinhas && b < quantLinhas && calc(cartas[0][a] + cartas[1][b]) && verificarInstancia(a + 1, b + 1, c)) return true;
  
      if( a < quantLinhas && c < quantLinhas && calc(cartas[0][a] + cartas[2][c]) && verificarInstancia(a + 1, b, c + 1)) return true;

      if( b < quantLinhas && c < quantLinhas && calc(cartas[1][b] + cartas[2][c]) && verificarInstancia(a, b + 1, c + 1)) return true;
      
      if( a < quantLinhas && b < quantLinhas && c < quantLinhas && calc(cartas[0][a] + cartas[1][b] + cartas[2][c]) && verificarInstancia(a + 1, b + 1, c + 1)) return true;

      arrAdicionarCartas.putIfAbsent(auxCartas, 2);
      return false;
    }

    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);

      while(true) {
          
        quantLinhas = Integer.parseInt(in.nextLine());
        if (quantLinhas == 0) break;
          
        cartas = new int[3][quantLinhas];
          
        for (int i = 0 ; i < quantLinhas; i++) {
          StringTokenizer st = new StringTokenizer(in.nextLine());
          for (int x = 0 ; x <= 2; x++) cartas[x][i] = Integer.parseInt(st.nextToken());
        }
          
        if (verificarInstancia(0,0,0) == true) System.out.println("1");
        else System.out.println("0");
        arrAdicionarCartas.clear();
      }
    }
}
```

