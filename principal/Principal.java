package principal;
import geo.*;
import adt.*;

public class Principal {
    public static void main(String[] args) {	
        /*List L1=new List();
        L1.insert(2);
        L1.insert(5);
        L1.insert(4);
        L1.insert(3);
        L1.print();
        L1.recortar(0,3);
        L1.print();*/

        Tablero T1=new Tablero(6);
        Linea L0=new Linea(33,33,T1);
        List Li=new List();
        
        T1.gen(00, 10);
        T1.gen(10,11);
        T1.gen(11,01);
        T1.gen(01, 00);
        T1.gen(10, 20);
        T1.gen(20, 11);
        //T1.getFtmp().getPuntos().print();
        //T1.getFtmp().bloqueoL(01,10);
        //T1.gen(00, 11);
        


        /*Linea L1=new Linea(00,10,T1);
        Linea L2=new Linea(10,11,T1);
        Linea L3=new Linea(11,01,T1);
        Linea L4=new Linea(01,00,T1);
        Linea L5=new Linea(01,10,T1);*/
        /*Linea L4=new Linea(30,31,T1);
        Linea L5=new Linea(31,22,T1);
        Linea L6=new Linea(22,12,T1);
        Linea L7=new Linea(12,01,T1);
        T1.recorrer2(12, 01, L7, Li.copy(),0);*/
        /*Linea L5=new Linea(00,10,T1);
        Linea L6=new Linea(00,01,T1);
        Linea L7=new Linea(01,11,T1);*/
        //Linea L7=new Linea(10,21,T1);
        //T1.recorrer2(10, 21, L7, Li.copy(),0);
        //Figura Fact=T1.getFtmp();
        //T1.recorrer3(10, 21, L7, Li.copy(),Fact);
        //Fact.bloqueoL(11, 20);



        /*Linea L5=new Linea(11,01,T1);
        Linea L6=new Linea(11,20,T1);
        Linea L7=new Linea(11,10,T1);
        T1.recorrido(11,11, Li.copy(), L0, 0);

        Linea L8=new Linea(01,00,T1);
        T1.recorrido(00,00, Li.copy(), L0, 0);*/

        /*Linea L9=new Linea(01,02,T1);
        Linea L10=new Linea(02,11,T1);
        T1.recorrido(02,02, Li.copy(), L0, 0);*/

        /*Linea L9=new Linea(02,11,T1);*/
        //T1.show2();
        //T1.recorrido(10,10, Li, L0, 0);
        //T1.recorrido(11, Li, L0, 0);




        //Se pueden imprimir solo los vértices de todas las figuras
        //Se prueba la identificación de figuras
        /*Node tmp=T1.getFiguras().getFirst();

        while (tmp!=null) {
        ((Figura)tmp.getInfo()).getVertices().print();
        System.out.println(((Figura)tmp.getInfo()).getArea());
        ((Figura)tmp.getInfo()).rotacion(11);
        tmp=tmp.getNext();
        }*/

    }

}
