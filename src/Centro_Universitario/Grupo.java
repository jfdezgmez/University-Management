package Centro_Universitario;
public class Grupo {
	private int ID_asignatura;
	private String tipoGrupo;
	private int id_Grupo;
	private int horaIn=0;
	private int horaFin=0;
	private String Dia;
	
	public Grupo(int ID_asignatura){
		this.ID_asignatura=ID_asignatura;
	}
	public String temporal(){ return ID_asignatura+";"+tipoGrupo+";"+id_Grupo;}
	public Grupo(int ID_asignatura, int id_Grupo, String tipo){
		this.ID_asignatura=ID_asignatura;
		this.id_Grupo=id_Grupo;
		tipoGrupo=tipo;
	}
	public Grupo(int ID_asignatura, int id_Grupo, String tipo, int horaIn, int horaFin, String Dia){
		this.ID_asignatura=ID_asignatura;
		this.id_Grupo=id_Grupo;
		tipoGrupo=tipo;
		this.horaIn=horaIn;
		this.horaFin=horaFin;
		this.Dia=Dia;
	}
	public String getDia(){return Dia;}
	public int getIDAsig(){ return ID_asignatura;}
	public int getIdGrupo(){ return id_Grupo;}
	public String getTipo(){return tipoGrupo;}

	public int getNumeroHoras(){ return horaFin-horaIn;}
	public int getHoraIn(){return horaIn;}

	public String toString(){ 
		if(horaIn!=0 && horaFin!=0){return id_Grupo+" "+Dia+" "+horaIn+" "+horaFin+"; ";}
		if(id_Grupo==0){return ID_asignatura+"; "; }
		else{if(ID_asignatura==0){return "";}
		else{return ID_asignatura+" "+tipoGrupo+" "+id_Grupo+"; ";}}}
} 
