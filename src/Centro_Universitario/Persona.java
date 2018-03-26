package Centro_Universitario;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Persona {
	
	
	private String nombre;
	private String apellidos;
	private String dni;
	private String perfil;
	private GregorianCalendar fecha1;
	
	public Persona(String nombre,String apellidos, String dni, String perfil, GregorianCalendar fecha1){
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.dni=dni;
		this.perfil=perfil;
		this.fecha1=fecha1;
	}

	public String getNombre(){
		return nombre;
	}
	public String getApellidos(){
		return apellidos;
	}
	public String getDni(){
		return dni;
	}
public String getPerfil(){
	return perfil;
}
public GregorianCalendar getFechaNacimiento(){
	return fecha1;
}

public String escribeFechas(GregorianCalendar FechadeNacimiento){
	return FechadeNacimiento.get(GregorianCalendar.DATE)+"/"+(FechadeNacimiento.get(GregorianCalendar.MONTH)+1)+"/"+FechadeNacimiento.get(GregorianCalendar.YEAR);
}
public String escribeDocencia(ArrayList<Grupo> Docencia){

	String doc=Docencia.toString();
	doc=doc.replace("[", "").replace("]", "").replace(",", "").replace("  ", " ");
	return doc;
}
public String escribirAsigSup(ArrayList<AsignaturaSuperada> Superada){ 
	String doc=Superada.toString();
	doc=doc.replace("[", "").replace("]", "").replace(",", "").replace("  ", " ");
	return doc;
}

public String toString (){
return perfil+"\n"+dni+"\n"+nombre+"\n"+apellidos+"\n"+escribeFechas(fecha1)+"\n";
}
}
