package pruebabd;

import com.istloja.conexion.PruebaBD;
import com.istloja.controlador.Personabd;
import com.istloja.modelo.Persona;


public class Main {
    public static void main(String[] args) {
        
    
        Personabd perbd = new Personabd();
        //Persona per = new Persona();
                
        /*per.setIdPersona(2);
        per.setCedula("1150460762");
        per.setNombre("Andres");
        per.setApellidos("Morocho");
        per.setDireccion("Elvalle");
        per.setCorreo("jonasjtan");
        per.setTelefono("0969084769");
        
        if(perbd.CrearPersona(per)){
            System.out.println("Persona guardada con exito");
        }else{
            System.out.println("Error");
        }*/
        
        
        /*Persona perEli = new Persona();
                
        perEli.setIdPersona(1);
        
        if(perbd.EliminarPersona(perEli)){
            System.out.println("Person eliminada con exito");
        }else{
            System.out.println("Error al eliminar la persona");
        }*/
        
        Persona personaEdi = new Persona();
                        
        if(perbd.EditarPersona(personaEdi)){
            System.out.println("Person editada con exito");
        }else{
            System.out.println("Error al editar la persona");
        }
    }
}
