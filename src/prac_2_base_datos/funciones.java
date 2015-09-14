
package prac_2_base_datos;

import java.util.Scanner;

public class funciones {

    Scanner leer=new Scanner(System.in);
    String nombre;
    String cantidad_aux;
    String costo_aux;
    int cantidad;
    int costo;
    int cant_ventas;
    int cant_gan;

    public funciones() {
     
    }

    void inicio() {
      System.out.println("======================");
      System.out.println("Por favor seleccion una opcion:");
      System.out.println("|1| Agregar producto");
      System.out.println("|2| Buscar Producto");
      System.out.println("|3| Eliminar producto");
      System.out.println("|4| Mostrar Inventario");
      System.out.println("|5| Realizar venta");
      System.out.println("|6| Ganacias Totales");
      System.out.println("|7| Salir");
       
    }

    void agregar() {
          boolean Flag=true;
System.out.println("======================");
System.out.println("Por favor Ingrese:");
do{
  try{
        System.out.println("Nombre Producto:");
        nombre=leer.nextLine();
        if(nombre.matches("[a-z]*")){Flag=true;}
        else {System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
        if(nombre.equals("")) { System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
   }catch(Exception e) {System.out.println("Error de Entrada  "+e.getMessage());Flag=false;}//problema en el enlace de conexion
}while(Flag==false);
do{
 try{
        System.out.println("Cantidad:");
        this.cantidad_aux=leer.nextLine();
        if(this.cantidad_aux.matches("[0-9]*")){Flag=true;cantidad=Integer.parseInt(cantidad_aux);}
        else {System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
        if(this.cantidad_aux.equals("")) { System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
 }catch(Exception e) {System.out.println("Error de Entrada  "+e.getMessage());Flag=false;}//problema en el enlace de conexion
 }while(Flag==false);
do{
 try{
        System.out.println("Costo:");
        this.costo_aux=leer.nextLine();
        if(this.costo_aux.matches("[0-9]*")){Flag=true;costo=Integer.parseInt(costo_aux);}
        else {System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
        if(this.nombre.equals("")) { System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
 }catch(Exception e) {System.out.println("Error de Entrada  "+e.getMessage());Flag=false;}//problema en el enlace de conexion
 }while(Flag==false);
    }

    void mostrar() {
    System.out.println("Nombre Producto: "+this.nombre+" Cantidad: "+this.cantidad+" Costo: "+this.costo);
    }

    void mostrar_2() {
     System.out.println("Nombre Producto: "+this.nombre+" Cantidad Vendidas: "+this. cant_ventas+" Ganacia: "+this.cant_gan);  
    }

    String validacion_nombre() {
        boolean Flag=true;
        String Buscar="nada";
    do{
        try{
            System.out.println("Ingrese Nombre producto: ");
            Buscar=leer.nextLine();
            if(Buscar.matches("[a-z]*")){Flag=true;}
            else {System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
            if(Buscar.equals("")) { System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
            }catch(Exception e) {System.out.println("Error de Entrada  "+e.getMessage());Flag=false;}//problema en el enlace de conexion
         }while(Flag==false);
    return Buscar;
    }
}

