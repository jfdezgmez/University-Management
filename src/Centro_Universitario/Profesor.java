package Centro_Universitario;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Profesor extends Persona{
	private String categoria;
	private String departamento;
	private int horasAsignables;
	private ArrayList<Grupo> docenciaImpartida;
	private int asignaturasCoordinadas=0; 
	static int numeroProfesores=0;

	public Profesor(String nombre,String apellidos, String dni, String perfil, GregorianCalendar fecha1, String categoria, String departamento, int horasAsignables, ArrayList<Grupo> docenciaImpartida){
		super(nombre,apellidos,dni,"profesor",fecha1);
		this.categoria=categoria;
		this.departamento=departamento;
		this.horasAsignables=horasAsignables;
		this.docenciaImpartida=docenciaImpartida;
		numeroProfesores++;
	}
	public Profesor(String nombre,String apellidos, String dni, String perfil, GregorianCalendar fecha1, String categoria, String departamento, int horasAsignables){
		super(nombre,apellidos,dni,"profesor",fecha1);
		this.categoria=categoria;
		this.departamento=departamento;
		this.horasAsignables=horasAsignables;
		numeroProfesores++;
	}
	public int gethorasAsignables(){
		return horasAsignables;
	}
	public void setnuevoGrupo(Grupo x){
		if(docenciaImpartida==null){
			ArrayList<Grupo> w=new ArrayList<Grupo>();
			docenciaImpartida=w;
		}
		docenciaImpartida.add(x);
	}
	public int getnumeroProfesores(){ return numeroProfesores;}
	public String getCategoria(){return categoria;}
	
	public ArrayList<Grupo> getDocencia(){
		return docenciaImpartida;
	}
	public int getSizeDocencia(){
		if (docenciaImpartida==null){ return 0;}
		else{return docenciaImpartida.size();}}
	
	public void setAsignaturasCoordinadas(){ asignaturasCoordinadas++;}
	
	public int getAsignaturasCoordinadas(){ return asignaturasCoordinadas;}
	
	public int getHorasTotales(HashMap<Integer, Asignatura> asignaturas){
		int numero=0;
		if(docenciaImpartida==null){return 0;}
		for(int i=0;i<docenciaImpartida.size();i++){
			int ID_asig= docenciaImpartida.get(i).getIDAsig();
			String tipo= docenciaImpartida.get(i).getTipo();
			int ID_grupo=docenciaImpartida.get(i).getIdGrupo();
			
			if(tipo.equals("A")){
			numero=numero+(asignaturas.get(ID_asig).getGrupos_A().get(ID_grupo-1).getNumeroHoras());
			}
			if(tipo.equals("B")){
				numero=numero+(asignaturas.get(ID_asig).getGrupos_B().get(ID_grupo-1).getNumeroHoras());
			}
		}
		return numero;
	}
	public String toString(){
		if(docenciaImpartida==null){return super.toString()+categoria+"\n"+departamento+"\n"+horasAsignables+"\n\n";}
		return super.toString()+categoria+"\n"+departamento+"\n"+horasAsignables+"\n"+(escribeDocencia(docenciaImpartida)).substring(0,(escribeDocencia(docenciaImpartida)).length()-2 )+"\n";
	}
}
