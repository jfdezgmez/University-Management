package Centro_Universitario;
import java.util.ArrayList;

public class Asignatura {
private String nombre;
private String siglasAsignatura;
private int curso;
private String coordinador;
private ArrayList<Integer> prerrequisitos;
private int ID_asignatura;
private ArrayList<Grupo> Grupos_A;
private ArrayList<Grupo> Grupos_B;
static int numeroAsignaturas=0;

public Asignatura(int ID,String nombre, String siglas,int curso,String Id_coord){
	ID_asignatura=ID;
	this.nombre=nombre;
	siglasAsignatura=siglas;
	this.curso=curso;
	coordinador=Id_coord;
	numeroAsignaturas++;
}
public Asignatura(int ID,String nombre, String siglas,int curso,String Id_coord,ArrayList<Grupo> Grupos_A,ArrayList<Grupo> Grupos_B,ArrayList<Integer> prerr){
	ID_asignatura=ID;
	this.nombre=nombre;
	siglasAsignatura=siglas;
	this.curso=curso;
	coordinador=Id_coord;
	this.Grupos_A=Grupos_A;
	this.Grupos_B=Grupos_B;
    prerrequisitos=prerr;
    numeroAsignaturas++;
}
public String getSiglas(){
	return siglasAsignatura;
}
public String getNombre(){ return nombre;}
public int getCurso(){ return curso;}
public void setCoordinador(String dni){
coordinador=dni;
}
public ArrayList<Grupo> getGrupos_A(){ return Grupos_A;}
public ArrayList<Grupo> getGrupos_B(){ return Grupos_B;}
public int getSizeGA(){
	return Grupos_A.size();
}
public int getSizeGB(){
	return Grupos_B.size();
}
public String escribirPre(ArrayList<Integer> prerrequisitos){ 
	String doc=prerrequisitos.toString();
	doc=doc.replace("[", "").replace("]", "").replace(",", " ").replace("  ", " ");
	return doc;
}
public String escribeGrupos(ArrayList<Grupo> Grupos){

	String doc=Grupos.toString();
	doc=doc.replace("[", "").replace("]", "").replace(",", "").replace("  ", " ");
	return doc;
}
public int getID(){ return ID_asignatura;}
public int getNumeroAsignaturas(){return numeroAsignaturas;} 
public int getSizePrerreq(){ return prerrequisitos.size();}
public ArrayList<Integer> getPre(){ return prerrequisitos;}
public String toString(){
	if(prerrequisitos.contains(-1)){ return ID_asignatura+"\n"+nombre+"\n"+siglasAsignatura+"\n"+curso+"\n"+coordinador+"\n\n"+(escribeGrupos(Grupos_A)).substring(0,((escribeGrupos(Grupos_A)).length())-2)+"\n"+(escribeGrupos(Grupos_B)).substring(0,(escribeGrupos(Grupos_B)).length()-2)+"\n";}
return ID_asignatura+"\n"+nombre+"\n"+siglasAsignatura+"\n"+curso+"\n"+coordinador+"\n"+escribirPre(prerrequisitos)+"\n"+(escribeGrupos(Grupos_A)).substring(0,((escribeGrupos(Grupos_A)).length())-2)+"\n"+(escribeGrupos(Grupos_B)).substring(0,(escribeGrupos(Grupos_B)).length()-2)+"\n";}
}
