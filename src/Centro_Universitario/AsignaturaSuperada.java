package Centro_Universitario;

import java.util.Comparator;

public class AsignaturaSuperada {
	
private int ID_asignatura;
private String curso;
private double nota;

public AsignaturaSuperada(String curso){
	this.curso=curso;
}
public AsignaturaSuperada(int ID_asignatura,String curso, double nota){
	this.ID_asignatura= ID_asignatura;
	this.curso=curso;
	this.nota=nota;
}
public int getID(){return ID_asignatura;}
public String toString(){
	return ID_asignatura+" "+curso+" "+nota+";";
	
}
public int TransCurso(){
String partes[]=curso.split("/");
return Integer.parseInt(partes[0]);
}

public String temporal(){
	return ID_asignatura+";"+curso+";"+nota ;
}

}
