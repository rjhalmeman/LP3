

package myUtil;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
public class UltimaExecucao {

    public String ultimaExecucao(){
        return null;
    }
    public static void main(String[] args) {
        UltimaExecucao u = new UltimaExecucao();
        for (int i = 48; i < (97+26); i++) {
            char x =(char) i;
            System.out.println(i+" - "+x);
        }
        char a = 97 ;
        char b = 'b' ;
        String ab = Character.toString(a)+Character.toString(b);
        System.out.println("ab "+ab);
    }
    
}
