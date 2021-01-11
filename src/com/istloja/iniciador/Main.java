package com.istloja.iniciador;


import com.istloja.controlador.PersonaBD;
import com.istloja.modelo.Persona;


public class Main {
    public static void main(String[] args) {
        
    
        PersonaBD perbd = new PersonaBD();
        Persona per = new Persona();
                
        //AGREGAR PERSONA
            //Esta parte sirve para colocarle cada atributo de la persona y guardar en la base de datos
        /*per.setIdPersona(2);
        per.setCedula("1150707345");
        per.setNombre("Daniela");
        per.setApellidos("Torres");
        per.setDireccion("Centro");
        per.setCorreo("Daniela@gmail.com");
        per.setTelefono("0967777982");
        
        if(perbd.CrearPersona(per)){
            System.out.println("Persona guardada con exito");
        }else{
            System.out.println("Error");
        }*/
        //FIN AGREGAR PERSONA
        
        
        //ELIMINAR PERSONA
            //En esta parte tenemso que colocar el id de la persona para poder eliminarla
            //por que si colocamos el nombre pueden aver dos personas con el mismo nombre
            //otra opcion seria colocar el numero de cedula por que esas no se repetirian
            //eso queda a disposicion del usuario.
            
        /*Persona perEli = new Persona();
                
        perEli.setIdPersona(1); //Aqui es donde agregariamos el idPersona para poder eliminarlo.
                                //Y si queremos eliminar por medio de la cedula tenemos que modicar tanto en esa
                                //parte del codigo como en la clase PersonaBD.
        
        if(perbd.EliminarPersona(perEli)){
            System.out.println("Person eliminada con exito");
        }else{
            System.out.println("Error al eliminar la persona");
        }*/
        //FIN ELIMINAR PERSONA
        
        
        //EDITAR PERSONA
            //En esta parte solo comprobamos si la coxion fue exitosa y si los cambios realizados se guardaron
            //y damos un mensaje de persona editada correctamente.
        /*Persona personaEdi = new Persona();
                        
        if(perbd.EditarPersona(personaEdi)){
            System.out.println("Person editada con exito");
        }else{
            System.out.println("Error al editar la persona");
        }*/
        //FIN EDITAR PERSONA.
    }
}

