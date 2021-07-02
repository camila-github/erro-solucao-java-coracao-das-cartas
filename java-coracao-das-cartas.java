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