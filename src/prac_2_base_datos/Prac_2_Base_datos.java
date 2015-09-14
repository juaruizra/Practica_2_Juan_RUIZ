
package prac_2_base_datos;
import java.sql.*;
import java.util.Scanner;
import prac_2_base_datos.funciones;

public class Prac_2_Base_datos {

    
    public static void main(String[] args) {
         Scanner leer=new Scanner(System.in);
        funciones producto=new funciones();
    //---------------------------------------- 
    int  opc_int=0,aux=0,cant=0,venta=0,CU=0;
    String opc="NULL", pantalla,buscar; 
    boolean Flag=true;
    //----- CONEXION BASE DE DATOS----------------------------------------------
   try{ 
       String user="root";//local
       String contra="1234";
       // String url="jdbc:mysql://db4free.net/nombre_data_base";
       Class.forName("com.mysql.jdbc.Driver");//llamar drive conection
       Connection con= DriverManager.getConnection("jdbc:mysql://localhost/almacen_jr",user,contra);//creo objeto de coneccion
      //Connection con= DriverManager.getConnection("ur1,user,contra);//creo objeto de coneccion
       Statement estado=con.createStatement();//para ver la conexion
       ResultSet resultado;//contiene todos los resusltados de todas las operaciones que haga en SQL
       System.out.println("CONEXION OK...");
    //-------INICIO JUNTO A SU VERIFICACION-------------------------------------
    do{
      producto.inicio();
     opc=leer.nextLine();
     opc_int=90;
    try{
        if(opc.matches("[0-9]*")){
            opc_int=Integer.parseInt(opc);
        if(opc_int<1 || opc_int >7)System.out.println("Elección NO valida,intente de nuevo!!"); }
        else {System.out.println("Error de entrada, intente de nuevo!!");}
    } catch(Exception e){System.out.println("Error de Entrada  "+e.getMessage());}
    //--------------------------------------------------------------------------                     
    switch(opc_int){
        case 1:{// Agregar dato
            System.out.println("=======================* |AGREGAR| *=======================");
            producto.agregar();
            // verificando si el nuevo producto esta en la base de datos
            resultado = estado.executeQuery("SELECT * FROM `producto` WHERE `Nombre` LIKE '"+producto.nombre+"'");//OJo pilas con las comillas
            aux=0;
            while(resultado.next()){
                pantalla= "Producto: "+resultado.getString("Nombre")+"\t"+" Cantidad: "+resultado.getString("Cantidad")+"\t"+" Precio: "+resultado.getString("Precio");
                System.out.println(pantalla);
                aux++;
              }
            aux--;
            if(aux==-1){
            System.out.println("Producto |NUEVO|..");
            estado.executeUpdate("INSERT INTO `producto` VALUES(NULL,'"+producto.nombre+"','"+producto.cantidad+"','"+producto.costo+"')");//linea de ingreso
            }
            else{
            do{//Validaciòn entrada entero
                   try{
                        System.out.println("El producto |YA EXISTE|, ¿desea reemplazarlo?.. |SI->1||NO->2|");
                        opc=leer.nextLine();
                        if(opc.matches("[0-9]*")){
                                            aux=Integer.parseInt(opc);
                                           if(aux==1||aux==2) {Flag=true;}
                                           else{Flag=false;}
                                        }
                        else {System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
                        if(opc.equals("")) { System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
                        }catch(Exception e) {System.out.println("Error de Entrada  "+e.getMessage());Flag=false;}//problema en el enlace de conexion
               }while(Flag==false);
                    
                    if(aux==1){//sobre escribir el dato existente
                            estado.executeUpdate("UPDATE `almacen_jr`.`producto` SET `Cantidad` = '"+producto.cantidad+"', `Precio` = '"+producto.costo+"' WHERE `producto`.`Nombre` = '"+producto.nombre+"'");
                            System.out.println("|REEMPLAZO OK| ...");
                             }
                    
            }
            //------------------------------------------
            
            
            break;
        }
        case 2:{//Buscar dato
            System.out.println("=======================* |BUSCAR| *=======================");
            buscar= producto.validacion_nombre();
            resultado = estado.executeQuery("SELECT * FROM `producto` WHERE `Nombre` LIKE '"+buscar+"'");//OJo pilas con las comillas
            aux=0;
            while(resultado.next()){
                pantalla= "Producto: "+resultado.getString("Nombre")+"\t"+" Cantidad: "+resultado.getString("Cantidad")+"\t"+" Precio: "+resultado.getString("Precio");
                System.out.println(pantalla);
                aux++;
              }
            aux--;
            if(aux==-1){
            System.out.println("Producto |NO| encontrado");
            }
        break;
        }
        case 3:{//eliminar producto
            System.out.println("=======================* |ELIMINAR| *=======================");
            buscar= producto.validacion_nombre();
            //buscando en la tabla
            resultado = estado.executeQuery("SELECT * FROM `producto` WHERE `Nombre` LIKE '"+buscar+"'");//OJo pilas con las comillas
            aux=0;
            while(resultado.next()){
                pantalla= "Producto: "+resultado.getString("Nombre")+"\t"+" Cantidad: "+resultado.getString("Cantidad")+"\t"+" Precio: "+resultado.getString("Precio");
                System.out.println(pantalla);
                aux++;
              }
            aux--;
            if(aux==-1){System.out.println("Producto |NO| encontrado"); }
            else {
                   estado.executeUpdate("DELETE FROM `producto` WHERE `Nombre` LIKE '"+buscar+"' ");
                   System.out.println("Eliminacion OK..");
                 }
            
        break;
        }
        
        case 4:{//Ver inventario
            System.out.println("=======================* |INVENTARIO| *=======================");
            resultado= estado.executeQuery("SELECT * FROM `producto`");//busqueda de todos los contactos, es el codigo en sql de una busqueda
            while(resultado.next()){//pregunta a resultado que si hay algo despues se su primera posicion
          //      System.out.println(resultado.getString("Nombre")+"\t"+resultado.getString("Cantidad")+"\t"+resultado.getString("Precio"));// ojo, entre comillas los nombres de las columnas tal cual
             pantalla= "Producto: "+resultado.getString("Nombre")+"\t"+" Cantidad: "+resultado.getString("Cantidad")+"\t"+" Precio: "+resultado.getString("Precio");
             System.out.println(pantalla);
            }
            
        break;
        }
        
        case 5:{//realizar venta
            System.out.println("=======================* |VENTAS| *=======================");
            buscar= producto.validacion_nombre();
            //buscando en la tabla
            resultado = estado.executeQuery("SELECT * FROM `producto` WHERE `Nombre` LIKE '"+buscar+"'");//OJo pilas con las comillas
            aux=0;
            while(resultado.next()){
                pantalla= "Producto: "+resultado.getString("Nombre")+"\t"+" Cantidad: "+resultado.getString("Cantidad")+"\t"+" Precio: "+resultado.getString("Precio");
                System.out.println(pantalla);
                aux++;
              }
            aux--;
            if(aux==-1){System.out.println("Producto |NO| encontrado"); }
            else {  
                   do{//Validaciòn entrada entero
                        try{
                            System.out.println("|CANTIDAD| a vender:");
                            opc=leer.nextLine();
                            if(opc.matches("[0-9]*")){Flag=true;venta=Integer.parseInt(opc);}
                            else {System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
                            if(opc.equals("")) { System.out.println("Error de entrada, intente de nuevo!!");Flag=false;}
                            }catch(Exception e) {System.out.println("Error de Entrada  "+e.getMessage());Flag=false;}//problema en el enlace de conexion
                      }while(Flag==false);
                // Procesamiento y escritura de la venta
                 resultado.beforeFirst();//ubica el apuntador al inicio
                 resultado.next();// se ubica el apuntador donde esta el producto encontrado
                cant=Integer.parseInt(resultado.getString("Cantidad"));//pasar cantidad en existencia a entero
                if(cant<venta){ System.out.println("ERROR: Cantidad |NO| disponible !!");}
                else{
                CU=Integer.parseInt(resultado.getString("Precio"));//pasar cantidad en existencia a entero
                estado.executeUpdate("INSERT INTO `ventas` VALUES('"+resultado.getString("Nombre")+"','"+venta+"','"+(venta*CU)+"')");
                
                //  UPDATE `clientes` SET `pais`="Suiza" WHERE `nombredecliente`="Edwin"
                //  SELECT * FROM `clientes` WHERE `poblacion`=”rionegro”
                //estado.executeUpdate("INSERT INTO `ventas` VALUES('"+resultado.getString("Nombre")+"','"+venta+"','"+(venta*CU)+"')");
                estado.executeUpdate("UPDATE `almacen_jr`.`producto` SET `Cantidad` = '"+(cant-venta)+"' WHERE `producto`.`Nombre` = '"+buscar+"'");
                System.out.println("|VENTA OK| ...");
                }
                
                 }
         break;
        }
        
        case 6:{
           cant=0;
           venta=0;
            System.out.println("=======================* |VENTAS REALIZADAS| *=======================");
            resultado= estado.executeQuery("SELECT * FROM `ventas`");
            while(resultado.next()){//pregunta a resultado que si hay algo despues se su primera posicion
             pantalla= "Producto: "+resultado.getString("Nombre_vendido")+"\t"+" Cantidad: "+resultado.getString("Cantidad_vendido")+"\t"+" Total Venta: "+resultado.getString("Total_vendido");
             System.out.println(pantalla);
             //cant y venta
             cant+=Integer.parseInt(resultado.getString("Cantidad_vendido"));
             venta+=Integer.parseInt(resultado.getString("Total_vendido"));
            }
            System.out.println("=======================* |GANANCIAS| *=======================");
            System.out.println("Cantidades VENDIDAS: "+cant); 
            System.out.println("Total Ganancias: "+venta); 
        
        break;
        }
            
    
    
    
    }
    
    
    
    
    }while(opc_int!=7);
   
    }
    catch(SQLException ex){ System.out.println("Error de mySQL");}//capa 8necesita una excepcion sql, cuando hay error en el objeto creado o la base de datos no existe
    catch(Exception e){System.out.println("Error de tipo  "+e.getMessage());}//problema en el enlace de conexion
    
 }
    
}