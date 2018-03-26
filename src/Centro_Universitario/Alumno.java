package Centro_Universitario;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Alumno extends Persona {
	private ArrayList<Grupo> DocenciaRecibida;
	private ArrayList<AsignaturaSuperada> AsignaturasSuperadas;
	private GregorianCalendar fecha2;
static int numeroAlumnos=0;
	
	public Alumno(String nombre,String apellidos, String dni, String perfil, GregorianCalendar fecha1,GregorianCalendar matricula, ArrayList<AsignaturaSuperada> AsignaturasSuperadas,ArrayList<Grupo> DocenciaRecibida){
		super(nombre,apellidos,dni,"alumno",fecha1);
		fecha2=matricula;
		this.AsignaturasSuperadas=AsignaturasSuperadas;
		this.DocenciaRecibida=DocenciaRecibida;
		numeroAlumnos++;
	}
	public Alumno(String nombre,String apellidos, String dni, String perfil, GregorianCalendar fecha1,GregorianCalendar matricula){
		super(nombre,apellidos,dni,"alumno",fecha1);
		this.fecha2=matricula;
		numeroAlumnos++;
	}
	public void setNuevaAS(AsignaturaSuperada x){
		AsignaturasSuperadas.add(x);
	}
	public void setNuevoGrupo(Grupo x){
		DocenciaRecibida.add(x);
	}

	public int getnumeroAlumnos(){ return numeroAlumnos;}
	public int getSizeDocencia(){
		if (DocenciaRecibida==null) return 0;
		else{return DocenciaRecibida.size();}
	}
	
	public ArrayList<Grupo> getDocencia(){
		return DocenciaRecibida;
	}
	
	public String toString(){
		if(DocenciaRecibida==null){return super.toString()+escribeFechas(fecha2)+"\n\n\n"; }
		if(AsignaturasSuperadas==null){return super.toString()+escribeFechas(fecha2)+"\n\n"+(escribeDocencia(DocenciaRecibida)).substring(0,(escribeDocencia(DocenciaRecibida)).length()-2 )+"\n";}
		else{return super.toString()+escribeFechas(fecha2)+"\n"+(escribirAsigSup(AsignaturasSuperadas)).substring(0,(escribirAsigSup(AsignaturasSuperadas)).length()-1)+"\n"+(escribeDocencia(DocenciaRecibida)).substring(0,(escribeDocencia(DocenciaRecibida)).length()-2 )+"\n";}
	}
	public void nuevoArrayAsigSup(AsignaturaSuperada nuevo){
		ArrayList<AsignaturaSuperada> x=new ArrayList<AsignaturaSuperada>();
		x.add(nuevo);
		AsignaturasSuperadas=x;
	}
	public int getSizeAsigSup(){ if(AsignaturasSuperadas==null) return 0;
		return AsignaturasSuperadas.size();}
	
	public ArrayList<AsignaturaSuperada> getAsigSup(){ return AsignaturasSuperadas; }
	public void setDocenciaNueva(Grupo x){
		if(DocenciaRecibida==null){
			 ArrayList<Grupo> b= new  ArrayList<Grupo>();
			 b.add(x);
			 DocenciaRecibida=b;
		}
		else{DocenciaRecibida.add(x);}
	}
}
