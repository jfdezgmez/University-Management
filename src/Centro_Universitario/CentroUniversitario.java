package Centro_Universitario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * 
 * @author @jfdezgmez y Hector Gonzalez Blanco
 * 
 */
public class CentroUniversitario {

	public static void main(String[] args) {
		 LinkedHashMap<Integer,Persona> personas=new LinkedHashMap<Integer, Persona>();
		  HashMap<Integer, Asignatura> asignaturas=new HashMap<Integer,Asignatura>();
		  cargarPersonas(personas);
	 cargarAsignaturas(asignaturas,personas);
	 lecturaComandos(personas,asignaturas);
	  escribirFichSal(asignaturas,personas);
	}
	
	
	public static void cargarPersonas(LinkedHashMap<Integer,Persona> personas) {
		int ID_orden;
		String tmp;
		String perfil;
		String ID;
		String nombre;
		String apellidos;
	    int diaNac; int mesNac; int anNac;
		GregorianCalendar fechaDeIngreso;
		GregorianCalendar fechaNacimiento1;
		String claseSegunda;
		String departamento;
		int horas;
	String s[]= new String [10];
	int i=0;
	
	try{	FileInputStream fichero1 = new FileInputStream("personas.txt");
		InputStreamReader reader1 =new InputStreamReader(fichero1,"UTF8");
		BufferedReader fich =new BufferedReader(reader1);
		Scanner input = new Scanner(fich);
	

		while(input.hasNext()){
			i=0;
		s[i]=input.nextLine();
		perfil=s[i].trim();
		while(!(s[i].equals("*")) || (input.hasNext())){
			i++;
			s[i]=input.nextLine();
			if(!(input.hasNext())){ break;}
			if(s[i].equals("*")){ s[i]=null; break;}
		}
		
		for(int l=0;l<s.length;l++){
			if(s[l]==null) continue;
			s[l]=s[l].trim();
		}
	if(perfil.equals("profesor")){
			ID=s[1];
			nombre=s[2];
			apellidos=s[3];
			String fechaNacimiento[]=s[4].split("/");
            diaNac=Integer.parseInt(fechaNacimiento[0]);
            mesNac=Integer.parseInt(fechaNacimiento[1]);
            anNac=Integer.parseInt(fechaNacimiento[2]);
            fechaNacimiento1=new GregorianCalendar(anNac,mesNac-1,diaNac);
            claseSegunda=s[5];
            departamento=s[6];
            horas=Integer.parseInt(s[7]);    
            ArrayList <Grupo> Docencia= new ArrayList<Grupo>();
            if(!s[8].equals("")){
            String docenciaImpartida[]=s[8].split("; ");
            for(int j=0; j<docenciaImpartida.length;j++){
            	String partesDocencia[]=docenciaImpartida[j].split(" ");
            	int ID_asignatura=Integer.parseInt(partesDocencia[0]);	
            	int ID_gr=Integer.parseInt(partesDocencia[2]);
            	Grupo GrupoNuevo=new Grupo(ID_asignatura,ID_gr,partesDocencia[1]);
            	Docencia.add(GrupoNuevo);
            }}else {Docencia=null;}
            Persona Profesor1= new Profesor(nombre,apellidos,ID,perfil, fechaNacimiento1, claseSegunda, departamento, horas, Docencia);
            ID_orden=Alumno.numeroAlumnos+Profesor.numeroProfesores;
            personas.put(ID_orden, Profesor1);
			}
		 
      if(perfil.equals("alumno")){
		ID=s[1];
		nombre=s[2];
		apellidos=s[3];
		String fechaNacimiento[]=s[4].split("/");
        diaNac=Integer.parseInt(fechaNacimiento[0]);
        mesNac=Integer.parseInt(fechaNacimiento[1]);
        anNac=Integer.parseInt(fechaNacimiento[2]);
        fechaNacimiento1=new GregorianCalendar(anNac,mesNac-1,diaNac);
        String fechaIngreso[]=s[5].split("/");  
        int diaIngreso = Integer.parseInt(fechaIngreso[0]);
        int mesIngreso = Integer.parseInt(fechaIngreso[1]);
        int anIngreso = Integer.parseInt(fechaIngreso[2]);
        fechaDeIngreso=new GregorianCalendar(anIngreso,mesIngreso-1,diaIngreso);
        ArrayList <AsignaturaSuperada> Superada= new ArrayList<AsignaturaSuperada>();
        s[6]=s[6].trim();
        if(!s[6].equals("")){
        String AsignaturasSup[]=s[6].split("; ");
        for(int w=0;w<AsignaturasSup.length;w++){
        	String partesSuperada[]=AsignaturasSup[w].split(" ");
        	int ID_superada=Integer.parseInt(partesSuperada[0]);
        	double nota_superada=Double.parseDouble(partesSuperada[2]);
           AsignaturaSuperada nuevaAsignaturaSuperada=new AsignaturaSuperada(ID_superada, partesSuperada[1], nota_superada); 
            Superada.add(nuevaAsignaturaSuperada);
        }}else{ Superada=null;}
        ArrayList<Grupo> DocenciaRecibida=new ArrayList<Grupo>();
        s[7]=s[7].trim();
        if(!s[7].equals("")){
            String DocenciaRecib[]=s[7].split("; ");
            for(int y=0;y<DocenciaRecib.length;y++){
            	int id_grup; String tipo_grup;
            	String partesRec[]=DocenciaRecib[y].split(" ");
            	int ID_recib=Integer.parseInt(partesRec[0]);
            	if(partesRec.length<=1){ tipo_grup=" "; id_grup=0;}
            	else{tipo_grup=partesRec[1]; id_grup=Integer.parseInt(partesRec[2]);}
               Grupo nuevoGrupo=new Grupo(ID_recib, id_grup, tipo_grup);
                DocenciaRecibida.add(nuevoGrupo);
            }}else{ DocenciaRecibida=null;}
        Persona alumno1=new Alumno(nombre, apellidos, ID, perfil,fechaNacimiento1, fechaDeIngreso, Superada, DocenciaRecibida);
        ID_orden=Alumno.numeroAlumnos+Profesor.numeroProfesores;
        personas.put(ID_orden, alumno1);
        
		}
	}	
	}catch (FileNotFoundException e) { System.out.println("No se ha encontrado en fichero personas"); }
	catch (UnsupportedEncodingException e) {
		System.out.println("Error de lectura");
	}

			}
		
	
	public static void cargarAsignaturas(HashMap<Integer, Asignatura> asignaturas, LinkedHashMap<Integer,Persona> personas ){
		int numeroPersonas=personas.size();
	String nombre, siglas, ID_coordinador;
	int curso,ID;
	String s[]=new String[9];
	int i=0; 
	
       try{ FileInputStream fichero1 = new FileInputStream("asignaturas.txt");
  	    InputStreamReader fichero2 = new InputStreamReader(fichero1, "UTF8");
  	  	    BufferedReader fich = new BufferedReader(fichero2);
            Scanner input=new Scanner(fich);
    		while(input.hasNext()){
    			i=0;
    		s[i]=input.nextLine();
    		while(!(s[i].equals("*")) || (input.hasNext())){
    			i++;
    			s[i]=input.nextLine();
    			if(!(input.hasNext())){ break;}
    			if(s[i].equals("*")){ s[i]=null; break;}
    		}
    		for(int p=0;p<8;p++){
    			s[p]=s[p].trim();
    		}
    		s[0]=eliminarEspacios(s[0]);
    		ID=Integer.parseInt(s[0]);
    		nombre=s[1];
    		siglas=s[2];
    		s[3]=s[3].trim();
    		curso=Integer.parseInt(s[3]);
    		s[4]=s[4].trim();
    		if(s[4].equals("")){ ID_coordinador="";}
    		else{ ID_coordinador=s[4]; 
    		for(int r=1;r<=numeroPersonas;r++){
    			if(ID_coordinador.equals(personas.get(r).getDni())){
    				((Profesor)personas.get(r)).setAsignaturasCoordinadas();}}
    		}
    		ArrayList<Integer> prerreq=new ArrayList<Integer>();
    		if(s[5].equals("")){ prerreq.add(-1);}
    		else{ 			
    		String tmp[]=s[5].split(" ");
    			for(int p=0; p<tmp.length;p++){ prerreq.add(Integer.parseInt(tmp[p]));}} //suposicion varios prerrequisitos separados por espacios
    		ArrayList<Grupo> G_A=new ArrayList<Grupo>();
    		if(s[6].equals("")){G_A=null;}
    		else{ 
    			String gruposA[]=s[6].split("; ");
    			for(int a=0;a<gruposA.length;a++){
    			String partesGA[]=gruposA[a].split(" ");
    			int ID_GA=Integer.parseInt(partesGA[0]);
    			String Dia_GA=partesGA[1];
    			int hora_in=Integer.parseInt(partesGA[2]);
    			int hora_fin=Integer.parseInt(partesGA[3]);
    			Grupo Grupo1=new Grupo(ID,ID_GA,"A",hora_in,hora_fin,Dia_GA);
    			G_A.add(Grupo1);
    			}
    		}
    		ArrayList<Grupo> G_B=new ArrayList<Grupo>();
    		if(s[7].equals("")){G_B=null;}
    		else{
    			String gruposB[]=s[7].split("; ");
			for(int b=0;b<gruposB.length;b++){
			String partesGB[]=gruposB[b].split(" ");
			int ID_GB=Integer.parseInt(partesGB[0]);
			String Dia_GB=partesGB[1];
			int hora_inB=Integer.parseInt(partesGB[2]);
			int hora_finB=Integer.parseInt(partesGB[3]);
			Grupo Grupo2=new Grupo(ID,ID_GB,"B",hora_inB,hora_finB,Dia_GB);
			G_B.add(Grupo2);
			}		
    		}
    		Asignatura asignatura1 = new Asignatura(ID,nombre,siglas,curso,ID_coordinador,G_A,G_B,prerreq);
    		asignaturas.put(ID, asignatura1);
    		}       
	}catch (FileNotFoundException e) { System.exit(0);}
	catch (UnsupportedEncodingException e) {
		System.out.println("Error de lectura");;
	}
	}
	

		public static String eliminarEspacios(String og){
		return og.replace(" ","");
	}
	

	public static String validarSt(String s){
		return s.trim();
	}
	

	public static void InsertarPersona(String error, LinkedHashMap<Integer,Persona> personas, String perfil, String dni, String nombre, String apellidos, String fechaNacimiento, String fechaIngreso, String categoria, String departamento, int horasAsignables){
		 int errores=0;
		 int numeroElementos=personas.size();
		 
		 if(dni.length()!=9){errores++; escribirErrores(error,"IP","DNI incorrecto");}
		 if((errores==0) && !comprobarDNI(dni)){errores++; escribirErrores(error,"IP","DNI incorrecto");}
		 if(errores==0 && !comprobarFI(fechaNacimiento)){errores++; escribirErrores(error,"IP","Fecha incorrecta"); }
		 if(errores==0 && perfil.equals("alumno") && (!comprobarFI(fechaNacimiento) || !comprobarFI(fechaIngreso))){errores++; escribirErrores(error,"IP","Fecha incorrecta"); }
		 if(perfil.equals("alumno") && !cFechaIngreso(fechaNacimiento, fechaIngreso) && errores==0){errores++; escribirErrores(error,"IP","Fecha de ingreso incorrecta"); }
		 if(perfil.equals("profesor") && (categoria.equals("titular")) && (errores==0)){
			 if(horasAsignables<0 || horasAsignables>20) {errores++; escribirErrores(error,"IP","Numero de horas incorrecta");}
		 }
		 
		 if(perfil.equals("profesor") && (categoria.equals("asociado")) && (errores==0)){
			 if(horasAsignables<0 || horasAsignables>15) {errores++; escribirErrores(error,"IP","Numero de horas incorrecta");}
		 }
		 
		 if(perfil.equals("profesor") && (errores==0)){
			 for(int i=1; i<numeroElementos;i++){
				if((dni.equals(personas.get(i).getDni())) && (perfil.equals(personas.get(i).getPerfil()))){ errores++;escribirErrores(error,"IP","Profesor ya existente");}
			 }
		 }
		 
		 if(perfil.equals("alumno") && (errores==0)){
			 for(int i=1; i<numeroElementos;i++){
				if((dni.equals(personas.get(i).getDni())) && (perfil.equals(personas.get(i).getPerfil()))){ errores++; escribirErrores(error,"IP","Alumno ya existente");}
			 }
		 }
		if(perfil.equals("profesor")&& (errores==0)){
			String fechaNac[]=fechaNacimiento.split("/");
            int diaNac = Integer.parseInt(fechaNac[0]);
            int mesNac = Integer.parseInt(fechaNac[1]);
            int anNac = Integer.parseInt(fechaNac[2]);
            GregorianCalendar  fecha1=new GregorianCalendar(anNac,mesNac-1,diaNac);
			Persona profesor= new Profesor(nombre,apellidos,dni,"profesor",fecha1, categoria,departamento, horasAsignables);	
		personas.put((Alumno.numeroAlumnos+Profesor.numeroProfesores), profesor);
		escribirErrores(error,"IP","OK");
		}
		else{if(perfil.equals("alumno")&& (errores==0)){
			  	String fechaNac[]=fechaNacimiento.split("/");
       	 int diaNac = Integer.parseInt(fechaNac[0]);
            int mesNac = Integer.parseInt(fechaNac[1]);
            int anNac = Integer.parseInt(fechaNac[2]);
            GregorianCalendar  fecha1=new GregorianCalendar(anNac,mesNac-1,diaNac);
           String fechaIngreso1[]=fechaIngreso.split("/");
           int diaIngreso = Integer.parseInt(fechaIngreso1[0]);
           int mesIngreso = Integer.parseInt(fechaIngreso1[1]);
           int anIngreso = Integer.parseInt(fechaIngreso1[2]);
           GregorianCalendar  fecha2=new GregorianCalendar(anIngreso,mesIngreso-1,diaIngreso);
			Persona alumno=new Alumno(nombre,apellidos,dni,"alumno",fecha1,fecha2);
personas.put((Alumno.numeroAlumnos+Profesor.numeroProfesores), alumno);	
escribirErrores(error,"IP","OK");
		}}
		
	}
	
	
    public static void AsignaCoordinador(String error, LinkedHashMap<Integer,Persona> personas,  HashMap<Integer, Asignatura> asignaturas, String dni, String asignatura){
    	int errores=0;
    	int encontrado=0;
    	int existe=0;
    	int numProf=0;
    	int numeroPersonas=personas.size();
    	int numeroAsignaturas=asignaturas.size();
    	for(int i=1;i<=numeroPersonas;i++){
    		if(personas.get(i) instanceof Profesor){
    	if(dni.equals(personas.get(i).getDni()) && (personas.get(i).getPerfil()).equals("profesor")){ 
    		encontrado=1; numProf=i; }}
    	}
    	if(encontrado==0){escribirErrores(error,"ACOORD","Profesor Inexistente"); errores++;}
    	if(errores==0){
        	if(dni.equals(personas.get(numProf).getDni()) && (((Profesor)personas.get(numProf)).getCategoria()).equals("asociado")){ escribirErrores(error,"ACOORD","Profesor no titular"); errores++;}
    		
    	}
    	if(errores==0){
    	for(int i=1;i<50;i++){
    		if(asignaturas.get(i)!=null)if(asignatura.equals(asignaturas.get(i).getSiglas())){existe=1;}
    	}
    	if (existe==0){escribirErrores(error,"ACOORD","Asignatura inexistente"); errores++;}}
    	if(errores==0){
    		
    		if(((Profesor)personas.get(numProf)).getAsignaturasCoordinadas()==2){ escribirErrores(error,"ACOORD","Profesor ya es coordinador de 2 materias"); errores++;}
    	}
    	if(errores==0){
    	for(int i=1;i<50;i++){
    		if(asignaturas.get(i)!=null)if(asignatura.equals(asignaturas.get(i).getSiglas())){asignaturas.get(i).setCoordinador(dni); escribirErrores(error,"ACOORD","OK");}
    	}}
    }
    

    public static void AsignaCargaDocente(String error,LinkedHashMap<Integer,Persona> personas, HashMap<Integer, Asignatura> asignaturas, String persona, String asignatura, String tipoGrupo, int grupo){
    	
    	int tipoCorrecto=0;
    	int asigBus=-1;
    	int personaBuscada=-1;
    	int numeroGA=0;
    	int numeroGB=0;
    	int errores=0;
    	int existe=0;
    	int encontrado=0;
    	int asignaturaBuscada=-1;
    	int numeroAsignaturas=asignaturas.size();
    	int numeroPersonas=personas.size();
    	for(int i=1;i<=numeroPersonas;i++){
    	if(persona.equals(personas.get(i).getDni()) && (personas.get(i).getPerfil()).equals("profesor")){  personaBuscada=i; encontrado=1;}
    	}
    	if(encontrado==0){escribirErrores(error,"ACDOC","Profesor inexistente"); errores++;}
    	
    	if(errores==0){
    	for(int i=1;i<=50;i++){
    		if(asignaturas.get(i)!=null){if(asignatura.equals(asignaturas.get(i).getSiglas())){asignaturaBuscada=i; existe=1;}
    		}}
    	if (existe==0){escribirErrores(error,"ACDOC","Asignatura inexistente"); errores++;}
    	}
    	if(errores==0){
    	if (tipoGrupo.equals("A") || tipoGrupo.equals("B")){tipoCorrecto=1;}
    	if(tipoCorrecto==0){errores++;escribirErrores(error,"ACDOC","Tipo de grupo incorrecto");}
    	}
    	if(errores==0 && tipoGrupo.equals("A")){ 
    		 numeroGA=asignaturas.get(asignaturaBuscada).getSizeGA();
    		if(grupo>numeroGA){errores ++; escribirErrores(error,"ACDOC","Grupo inexistente");}
    	} 	
    	if(errores==0 && tipoGrupo.equals("B")){ 
    		 numeroGB=asignaturas.get(asignaturaBuscada).getSizeGB();
    		if(grupo>numeroGB){errores++; escribirErrores(error,"ACDOC","Grupo inexistente");}
      	}
    	if(errores==0 && encontrado==1){
    		int numdocencias=0;
    		for(int i=1;i<=numeroPersonas;i++){
    		if(personas.get(i) instanceof Profesor){numdocencias=((Profesor)personas.get(i)).getSizeDocencia();
    		for(int w=0;w<numdocencias;w++){
    			if(tipoGrupo.equals(((Profesor)personas.get(i)).getDocencia().get(w).getTipo()) && asignaturaBuscada==((Profesor)personas.get(i)).getDocencia().get(w).getIDAsig() && grupo==((Profesor)personas.get(i)).getDocencia().get(w).getIdGrupo()){errores++; escribirErrores(error,"ACDOC","Grupo ya asignado");}
    		}
    		}
    		}
    	}
    	
    	if(errores==0 && tipoGrupo.equals("A")){
   	if(((Profesor)personas.get(personaBuscada)).getCategoria().equals("titular")){
   	if((asignaturas.get(asignaturaBuscada).getGrupos_A().get(grupo-1).getNumeroHoras())+(((Profesor)personas.get(personaBuscada)).getHorasTotales(asignaturas))>(((Profesor)personas.get(personaBuscada)).gethorasAsignables())){errores++; escribirErrores(error,"ACDOC","Horas asignables superior al máximo");}
    	}
   	if(((Profesor)personas.get(personaBuscada)).getCategoria().equals("asociado")){	
    if((asignaturas.get(asignaturaBuscada).getGrupos_A().get(grupo-1).getNumeroHoras())	+(((Profesor)personas.get(personaBuscada)).getHorasTotales(asignaturas))>(((Profesor)personas.get(personaBuscada)).gethorasAsignables())){errores++; escribirErrores(error,"ACDOC","Horas asignables superior al máximo");}
    		}
    	}
    	if(errores==0 && tipoGrupo.equals("B")){
    	   	if(((Profesor)personas.get(personaBuscada)).getCategoria().equals("titular")){
    	   	if((asignaturas.get(asignaturaBuscada).getGrupos_B().get(grupo-1).getNumeroHoras())+(((Profesor)personas.get(personaBuscada)).getHorasTotales(asignaturas))>(((Profesor)personas.get(personaBuscada)).gethorasAsignables())){errores++; escribirErrores(error,"ACDOC","Horas asignables superior al máximo");}
    	    	}
    	   	if(((Profesor)personas.get(personaBuscada)).getCategoria().equals("asociado")){	
    	    if((asignaturas.get(asignaturaBuscada).getGrupos_B().get(grupo-1).getNumeroHoras())	+(((Profesor)personas.get(personaBuscada)).getHorasTotales(asignaturas))>(((Profesor)personas.get(personaBuscada)).gethorasAsignables())){errores++; escribirErrores(error,"ACDOC","Horas asignables superior al máximo");}
    	    		}
    	    	}
    	if(errores==0){
    		String dia1="";
    		int Hini=0;
    		if(tipoGrupo.equals("A")){
    		dia1=asignaturas.get(asignaturaBuscada).getGrupos_A().get(grupo-1).getDia();
    		Hini=asignaturas.get(asignaturaBuscada).getGrupos_A().get(grupo-1).getHoraIn();
    		}
    		if(tipoGrupo.equals("B")){
        		dia1=asignaturas.get(asignaturaBuscada).getGrupos_B().get(grupo-1).getDia();
        		Hini=asignaturas.get(asignaturaBuscada).getGrupos_B().get(grupo-1).getHoraIn();
        		}
if(!comprobarSolape(dia1,Hini, ((Profesor)personas.get(personaBuscada)).getDocencia(),asignaturas)){errores++; escribirErrores(error,"ACDOC","Se genera solape");}
    	}
    	if(errores==0){
    		Grupo nuevo=new Grupo(asignaturas.get(asignaturaBuscada).getID(),grupo,tipoGrupo);
    		((Profesor)personas.get(personaBuscada)).setnuevoGrupo(nuevo);
    		escribirErrores(error,"ACDOC","OK");
    	}
    	
    }
    
    
    
    public static void MatricularAlumno(String error,LinkedHashMap<Integer,Persona> personas, HashMap<Integer, Asignatura> asignaturas,String dni, String asignatura){
    	int numeroGrupos=0;
    	int existe=0;
        int asigBus=0;
    	int alBus=0;
    	int encontrada=0;
    	int errores=0;
        int numeroPersonas=personas.size();
    	int numeroAsignaturas=asignaturas.size();
    	for(int i=1;i<=numeroPersonas;i++){
    	if(personas.get(i) instanceof Alumno){
    		if(dni.equals(personas.get(i).getDni())){existe=1; alBus=i;
    		}   		
    		}
    	} 
    	if(existe==0){errores ++; escribirErrores(error,"MAT","Alumno inexistente");}
    	
    	if(errores==0){
    	for(int i=1;i<=50;i++){
    		if(asignaturas.get(i)!=null) if(asignatura.equals(asignaturas.get(i).getSiglas())){ encontrada=1;}
    	}
    	if (encontrada==0) {errores++; escribirErrores(error,"MAT","Asignatura inexistente"); }
    	}
    	if(errores==0){
    		for(int i=1; i<=50;i++){		
        		if(asignaturas.get(i)!=null) if(asignatura.equals(asignaturas.get(i).getSiglas())){asigBus=i;}
        	}
    		
    		numeroGrupos=((Alumno)personas.get(alBus)).getSizeDocencia();
   if(numeroGrupos!=0){
    	for(int i=0;i<numeroGrupos;i++){
    		if((asignaturas.get(asigBus).getID())==((Alumno)personas.get(alBus)).getDocencia().get(i).getIDAsig()){ errores++;escribirErrores(error,"MAT","Ya es alumno de la asignatura indicada"); break;}
    	}
    	}}
    if(errores==0){
    	
    	int existe1=0;
    	int asig=0;
    	for(int i=0;i<asignaturas.get(asigBus).getSizePrerreq();i++){
    		
    		 asig=asignaturas.get(asigBus).getPre().get(i);
             if(asig==-1){break;}
    		 for(int w=0;w<((Alumno)personas.get(alBus)).getSizeAsigSup();w++){
    			 if(asig==((Alumno)personas.get(alBus)).getAsigSup().get(w).getID()){existe1++;}
    		 }
    	}
    	if(asig>0 && existe1< asignaturas.get(asigBus).getSizePrerreq()){errores ++; escribirErrores(error,"MAT","No cumple requisitos");}
    }
    if (errores==0){
    	Grupo nuevo=new Grupo(asigBus);
    	
    	((Alumno)personas.get(alBus)).setDocenciaNueva(nuevo);
    	escribirErrores(error,"MAT","OK");
    }
    }
    
    
    public static void AsignaGrupo(String error, LinkedHashMap<Integer,Persona> personas, HashMap<Integer, Asignatura> asignaturas, String dni, String siglas, String tipoGrupo, int Grupo){
    	int correcto=0;
    	int alBus=0;
    	int asBus=0;
    	int encontrada=0;
    	int errores=0;  
    	int existe=0;
    	int matriculado=0;
    	int lugar=0;
    	int qr=0;
    	int numeroPersonas=personas.size();
      	int numeroAsignaturas=asignaturas.size();
      	for(int i=1;i<=numeroPersonas;i++){
      	if(personas.get(i) instanceof Alumno){if(dni.equals(personas.get(i).getDni())){alBus=i;existe=1;}} }
      	
      	if(existe==0){errores++;escribirErrores(error,"AGRUPO","Alumno inexistente");}
      	if(errores==0){
    	for(int w=1; w<=50;w++){
    		if(asignaturas.get(w)!=null)if(siglas.equals(asignaturas.get(w).getSiglas())){ asBus=w; encontrada=1;}
    	}
    	if (encontrada==0) {errores++; escribirErrores(error,"AGRUPO","Asignatura inexistente");}}
      	
    	if(errores==0){ if (tipoGrupo.equals("A") || tipoGrupo.equals("B")){correcto=1;}
    	if(correcto==0){errores++; escribirErrores(error,"AGRUPO","Tipo de grupo incorrecto");}
    	}
    	
    	if(errores==0){
    		for(int x=0; x<((Alumno)personas.get(alBus)).getDocencia().size();x++){
    		if(asBus==((Alumno)personas.get(alBus)).getDocencia().get(x).getIDAsig()) {lugar=x; matriculado=1;}
    	}
    		if(matriculado==0){errores++; escribirErrores(error,"AGRUPO","Alumno no matriculado");}
    		}
    	if(errores==0){
    		if(tipoGrupo.equals("A")){
    			for(int s=0;s<asignaturas.get(asBus).getSizeGA();s++){
           if(asignaturas.get(asBus).getGrupos_A().get(s).getIdGrupo()==Grupo){qr=1;}
    			}
    			}
    		if(tipoGrupo.equals("B")){
    			for(int a=0;a<asignaturas.get(asBus).getSizeGB();a++){
    		     if(asignaturas.get(asBus).getGrupos_B().get(a).getIdGrupo()==Grupo){qr=1;}	
    		}
    	}
    		if(qr==0){errores++; escribirErrores(error,"AGRUPO","Grupo inexistente");}
    	}
    	
    	if(errores==0){
    		if(tipoGrupo.equals("A")){
    			
    		if(!comprobarSolape(asignaturas.get(asBus).getGrupos_A().get(Grupo-1).getDia(),asignaturas.get(asBus).getGrupos_A().get(Grupo-1).getHoraIn() ,((Alumno)personas.get(alBus)).getDocencia(),asignaturas)){errores++;escribirErrores(error,"AGRUPO","Se genera solape");}
    		}
    		if(tipoGrupo.equals("B")){
        		if(!comprobarSolape(asignaturas.get(asBus).getGrupos_B().get(Grupo-1).getDia(),asignaturas.get(asBus).getGrupos_B().get(Grupo-1).getHoraIn() ,((Alumno)personas.get(alBus)).getDocencia(),asignaturas)){errores++;escribirErrores(error,"AGRUPO","Se genera solape");}
        		}
    	}
    	if(errores==0){
    		Grupo nuevo= new Grupo(asignaturas.get(asBus).getID(), Grupo, tipoGrupo);
    		((Alumno)personas.get(alBus)).setNuevoGrupo(nuevo);
    		escribirErrores(error,"AGRUPO","OK");
    	}
    }

    
    public static void EvaluaAsignatura(String error,LinkedHashMap<Integer,Persona> personas,HashMap<Integer, Asignatura> asignaturas, String asignatura, String cursoAcademico, String fichero){
    	int existe=0;
    	int errores=0;
    	int linea=0;
    	int asBus=0;
    	int alBus=0;
    	int encontrado=0;
    	int a=0;
    	int b=0;
    	int evaluada=0;
    	try{	FileInputStream fichero1 = new FileInputStream(fichero);
		InputStreamReader reader1 =new InputStreamReader(fichero1,"UTF8");
		BufferedReader fich =new BufferedReader(reader1);
		Scanner input = new Scanner(fich);
		
		for(int i=1;i<=50;i++){
			if(asignaturas.get(i)!=null) if(asignatura.equals(asignaturas.get(i).getSiglas())) {existe=1; asBus=i;}
		}
		if(existe==0){errores++; escribirErrores(error,"EVALUA","Asignatura inexistente");}
	
    	
		while(input.hasNext()){
			linea++;
			String temp=(input.nextLine()).replace("  ", " ").replace("  ", " ");
			String tmp[]=temp.split(" ");
			String dni=tmp[0];
			double notaA=Double.parseDouble(tmp[1]);
			double notaB=Double.parseDouble(tmp[2]);
			if(notaA<0 || notaA>5){errores++; errorEvaluar(linea, "Nota grupo A/B incorrecta","");}
			if(notaB<0 || notaB>5){errores++; errorEvaluar(linea, "Nota grupo A/B incorrecta","");}
			double notaTotal=notaA+notaB;
			for(int x=1;x<=50;x++){
			if(asignaturas.get(x)!=null)if(asignatura.equals(asignaturas.get(x).getSiglas())){asBus=x;}
			}
				if(errores==0){
			for(int w=1;w<=personas.size();w++){
				if(dni.equals(personas.get(w).getDni())){encontrado=1; alBus=w;}
			}
			if(encontrado==0){errores++;errorEvaluar(linea,"Alumno inexistente",dni);}
		}
				if(errores==0){
				
				for(int y=0;y<((Alumno)personas.get(alBus)).getSizeAsigSup();y++){
					if(asBus==((Alumno)personas.get(alBus)).getAsigSup().get(y).getID()){errores++; escribirErrores(error,"EVALUA","Asignatura ya evaluada en ese curso academico");}
				}
			if(errores==0){
				for(int t=0;t<((Alumno)personas.get(alBus)).getDocencia().size();t++){
					if(asBus==(((Alumno)personas.get(alBus)).getDocencia().get(t).getIDAsig())){b=1;}
				} if (b==0){errores++;errorEvaluar(linea,"Alumno no matriculado",dni);}
			}
			
			}
			if(errores==0){
			if(notaTotal<5){
				for(int r=0; r<((Alumno)personas.get(alBus)).getDocencia().size();r++){
				if(asBus==((Alumno)personas.get(alBus)).getDocencia().get(r).getIDAsig()){((Alumno)personas.get(alBus)).getDocencia().remove(r); }
				}
			}
			if(5<=notaTotal){
				for(int r=0; r<((Alumno)personas.get(alBus)).getDocencia().size();r++){
					if(asBus==((Alumno)personas.get(alBus)).getDocencia().get(r).getIDAsig()){a=((Alumno)personas.get(alBus)).getDocencia().get(r).getIDAsig(); ((Alumno)personas.get(alBus)).getDocencia().remove(r);}
				AsignaturaSuperada nueva= new AsignaturaSuperada(a,cursoAcademico,notaTotal);
				if(((Alumno)personas.get(alBus)).getSizeAsigSup()==0){ ((Alumno)personas.get(alBus)).nuevoArrayAsigSup(nueva);}
				else{((Alumno)personas.get(alBus)).setNuevaAS(nueva);}
				}
				
			}
			escribirErrores(error,"EVALUA","OK");
		}
		
		}
    	}catch (FileNotFoundException e) { errores++; escribirErrores(error,"EVALUA","Fichero de notas inexistente");}
    	catch (UnsupportedEncodingException e) {
    		System.out.println("Error de lectura");
    	}

    }

    
    public static void errorEvaluar(int linea, String error,String variable){
    	try{
        	FileWriter fichero = null;
            PrintWriter pw = null;
        	fichero = new FileWriter("avisos.txt",true);
        pw = new PrintWriter(fichero);
        if(variable.equals("")){pw.println("Error en la linea"+linea+":"+error);}
        else{pw.println("Error en la linea :"+linea+" "+error+":"+variable);}
        fichero.close();
        
        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();}
        }
    	
    
    public static void ObtenerExpediente(String error,LinkedHashMap<Integer,Persona> personas,HashMap<Integer, Asignatura> asignaturas, String alumno, String salida){
  int alBus=0;
   ArrayList<AsignaturaSuperada> asignaturasaprobadas = new ArrayList<>();
    	int errores=0;
    	int existe=0;
    	for(int i=1;i<=personas.size();i++){
	  if(personas.get(i) instanceof Alumno){
		  if(alumno.equals(personas.get(i).getDni())){alBus=i;existe=1;}
	  }
  }
    if(existe==0){errores++; escribirErrores(error,"EXP","Alumno inexistente");}	
    if(errores==0){
    if(((Alumno)personas.get(alBus)).getAsigSup()==null){errores++; escribirErrores(error,"EXP","Expediente vacio");}
    }
    if(errores==0){
    	for(int i=0; i<((Alumno)personas.get(alBus)).getSizeAsigSup();i++){
    	AsignaturaSuperada x= ((Alumno)personas.get(alBus)).getAsigSup().get(i);
    	asignaturasaprobadas.add(x);
    	}
    	
    	Collections.sort(asignaturasaprobadas, new Comparator<AsignaturaSuperada>() {
			public int compare(AsignaturaSuperada p1, AsignaturaSuperada p2) {
				return new Integer(p1.TransCurso()).compareTo(new Integer(p2.TransCurso()));
			}
		});
    	escribirAsignaturasOrden(asignaturas,asignaturasaprobadas,salida);
    	escribirErrores(error,"EXP","OK");
    }
    }
    

    public static void escribirAsignaturasOrden(HashMap<Integer, Asignatura> asignaturas, ArrayList<AsignaturaSuperada> asignaturasaprobadas,String salida){
		Iterator<AsignaturaSuperada> it=asignaturasaprobadas.iterator();
		int pos=1;
		  try{
    	FileWriter fichero = null;
        PrintWriter pw = null;
    	fichero = new FileWriter(salida,true);
    pw = new PrintWriter(fichero);  
    
		while(it.hasNext()){
			String tmp[]=(it.next().temporal()).split(";");
			int id=Integer.parseInt(tmp[0]);
			double nota=Double.parseDouble(tmp[2]);
			String nombre=asignaturas.get(id).getNombre();
			int curso=asignaturas.get(id).getCurso();
			 pw.println(curso+";"+nombre+";"+nota+";"+tmp[1]);
			pos++;
		}
		fichero.close();
		  } catch (UnsupportedEncodingException e) {
			    e.printStackTrace();
			    } catch (IOException e) {
			    e.printStackTrace();}
			    
		
	}

    
    public static void ObtenerCalendarioClases(String error,LinkedHashMap<Integer,Persona> personas,HashMap<Integer, Asignatura> asignaturas, String profesor, String salida){
    	ArrayList<Grupo> docenciaProfes = new ArrayList<>();
    	int proBus=0;
    	int errores=0;
    	int existe=0;
    	for(int i=1;i<personas.size();i++){
	  if(personas.get(i) instanceof Profesor){
		  if(profesor.equals(personas.get(i).getDni())){proBus=i;existe=1;}
	  }}	
    	if(existe==0){errores++; escribirErrores(error,"CALENP","Profesor inexistente");}
    	if(errores==0){
    	    if(((Profesor)personas.get(proBus)).getDocencia()==null){errores++; escribirErrores(error,"CALENP","Profesor sin asignaciones");}
    	    }
    	if(errores==0){
    		for(int i=0; i<((Profesor)personas.get(proBus)).getSizeDocencia();i++){
    	    	Grupo x= ((Profesor)personas.get(proBus)).getDocencia().get(i);
    	    	docenciaProfes.add(x);
    	    	}
    	    	
    	    	Collections.sort(docenciaProfes, new Comparator<Grupo>() {
    				public int compare(Grupo g1, Grupo g2) {
    					return new Integer(g1.getHoraIn()).compareTo(new Integer(g2.getHoraIn()));
    				}
    			});
    	    	escribirProfesoresOrden(error, asignaturas,docenciaProfes,salida);
    	}
    }
    

    
    public static void escribirProfesoresOrden(String error, HashMap<Integer, Asignatura> asignaturas, ArrayList<Grupo> docenciaProfes,String salida){
    	Iterator<Grupo> it=docenciaProfes.iterator();
    	String siglas="";
    	int hora=0;
    	String dia="";
		int pos=1;
		  try{
    	FileWriter fichero = null;
        PrintWriter pw = null;
    	fichero = new FileWriter(salida,true);
    pw = new PrintWriter(fichero);  
    
		while(it.hasNext()){
			String tmp[]=(it.next().temporal()).split(";");
			int id_asig=Integer.parseInt(tmp[0]);
            if(tmp[1].equals("A")){
            	 dia=asignaturas.get(id_asig).getGrupos_A().get(Integer.parseInt(tmp[2])-1).getDia();
            	 hora=asignaturas.get(id_asig).getGrupos_A().get(Integer.parseInt(tmp[2])-1).getHoraIn();
            	 siglas=asignaturas.get(id_asig).getSiglas();
            }	
            if(tmp[1].equals("B")){
            	 dia=asignaturas.get(id_asig).getGrupos_B().get(Integer.parseInt(tmp[2])-1).getDia();
            	 hora=asignaturas.get(id_asig).getGrupos_B().get(Integer.parseInt(tmp[2])-1).getHoraIn();
            	 siglas=asignaturas.get(id_asig).getSiglas();
            }
			 pw.println(dia+";"+hora+";"+siglas+";"+tmp[1]+";"+tmp[2]);
			pos++;
		}
		fichero.close();
		  } catch (UnsupportedEncodingException e) {
			    e.printStackTrace();
			    } catch (IOException e) {
			    e.printStackTrace();}
		escribirErrores(error,"CALENP","OK");	    
    	
    }

    
    public static void escribirErrores(String codigoIntroducido, String comandoError, String errorEspecifico){
try{
    	FileWriter fichero = null;
        PrintWriter pw = null;
    	fichero = new FileWriter("avisos.txt",true);
    pw = new PrintWriter(fichero);
    pw.println(codigoIntroducido+": "+comandoError+" -- "+errorEspecifico+"\n");
    fichero.close();
    
    } catch (UnsupportedEncodingException e) {
    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();}
    }

    
    
    public static boolean comprobarDNI(String x){
    	int comprobar=0;
    	String parteNum=x.substring(0,8);
    	for(int n=0;n<8;n++){
        if(!Character.isDigit(parteNum.charAt(n))) {comprobar=0;}
        else {comprobar++;}}
    	String parteLet=x.substring(8,9);
    	for(int n=0;n<1;n++){
    		if(!Character.isLetter(parteLet.charAt(n))) comprobar=0;
            else {comprobar++;}}
    	if(comprobar==9) { return true;}
    	else {return false;}
     }
    	

    
   public static void lecturaComandos(LinkedHashMap<Integer,Persona> personas, HashMap<Integer, Asignatura> asignaturas){
	   String[] lineasFichero=new String[100];
	   String tmp;
	   int x=0;
	   int contador=0;
	  try{	FileInputStream fichero2 = new FileInputStream("ejecucion.txt");
		InputStreamReader reader2 =new InputStreamReader(fichero2,"UTF8");
		BufferedReader fich =new BufferedReader(reader2);
		Scanner input = new Scanner(fich);
		
		while(input.hasNext()){
		tmp=input.nextLine();
		if(tmp.charAt(0)!='*'){lineasFichero[x]=tmp.replaceAll(" +"," "); x++;}
		}
		for(int t=0;t<100;t++){
			if(lineasFichero[t]!=null) contador++;
		}
		for(int w=0;w<contador;w++){
		String comando[]=lineasFichero[w].split(" ");
		comando[0]=eliminarEspacios(comando[0]);
		comando[0]=comando[0].toLowerCase();
		
		
		switch(comando[0]){
		case "insertapersona": 
			
        if(comando[1].equals("profesor")){ 
	try{	 String tmp2[]=lineasFichero[w].split("\"");
String primero[]=tmp2[0].split(" ");
primero[1]=primero[1].trim();
primero[2]=primero[2].trim();
tmp2[1]=tmp2[1].trim();
tmp2[3]=tmp2[3].trim();
tmp2[4]=tmp2[4].trim();
String segundo[]=tmp2[4].split(" ");
segundo[0]=segundo[0].trim();
segundo[1]=segundo[1].trim();
tmp2[5]=tmp2[5].trim();
tmp2[6]=tmp2[6].trim();
			InsertarPersona(lineasFichero[w],personas, primero[1], primero[2], tmp2[1], tmp2[3], segundo[0],null, segundo[1],tmp2[5],Integer.parseInt(tmp2[6]));
			}catch( ArrayIndexOutOfBoundsException e){errorComandoIncorrecto(lineasFichero[w],2); break;}
        }
        if(comando[1].equals("alumno")){ 
  try{    String tmp2[]=lineasFichero[w].split("\"");
        	String primero[]=tmp2[0].split(" ");
        	primero[1]=primero[1].trim();
        	primero[2]=primero[2].trim();
        	tmp2[1]=tmp2[1].trim();
        	tmp2[3]=tmp2[3].trim();
        	tmp2[4]=tmp2[4].trim();
        	String segundo[]=tmp2[4].split(" ");
        	segundo[0]=segundo[0].trim();
        	segundo[1]=segundo[1].trim();
            InsertarPersona(lineasFichero[w],personas, primero[1], primero[2], tmp2[1], tmp2[3],segundo[0],segundo[1],"","",0);
  }catch( ArrayIndexOutOfBoundsException e){errorComandoIncorrecto(lineasFichero[w],2); break;}      
  }
			break;
		case "asignacoordinador":
			if(comando.length<3){ errorComandoIncorrecto(lineasFichero[w],2); break;}
			AsignaCoordinador(lineasFichero[w],personas,asignaturas, comando[1], comando[2]);
		break;
		case "asignacargadocente":
			if(comando.length<5){ errorComandoIncorrecto(lineasFichero[w],2); break;}
			AsignaCargaDocente(lineasFichero[w],personas,asignaturas, comando[1], comando[2],comando[3], Integer.parseInt(comando[4]));
			break;
		case "obtenercalendarioclases":
		if (comando.length<3){ errorComandoIncorrecto(lineasFichero[w],2); break;}
		ObtenerCalendarioClases(lineasFichero[w],personas,asignaturas,comando[1],comando[2]);
			break;
		case "matricula":
			if(comando.length<3){ errorComandoIncorrecto(lineasFichero[w],2); break;}
			MatricularAlumno(lineasFichero[w],personas,asignaturas,comando[1],comando[2]);
			break;
		case "asignagrupo":
			if(comando.length<5){ errorComandoIncorrecto(lineasFichero[w],2); break;}
			AsignaGrupo(lineasFichero[w],personas,asignaturas, comando[1], comando[2], comando[3], Integer.parseInt(comando[4]));		
			break;
		case "evalua":
			if(comando.length<4){ errorComandoIncorrecto(lineasFichero[w],2); break;}
			EvaluaAsignatura(lineasFichero[w],personas,asignaturas,comando[1],comando[2],comando[3]);
			break;
		case "expediente":
			if(comando.length<3){ errorComandoIncorrecto(lineasFichero[w],2); break;}
			ObtenerExpediente(lineasFichero[w],personas,asignaturas,comando[1],comando[2]);
			break;
			default:
				errorComandoIncorrecto(comando[0],1);
				break;
		}
		}

		
	   }catch (FileNotFoundException e) { System.out.println("No se ha encontrado el fichero ejecucion"); System.exit(0);}
		catch (UnsupportedEncodingException e) {
			System.out.println("Error de lectura");
		}
   }

   
   public static void errorComandoIncorrecto(String s, int i){
	   try{
	    	FileWriter fichero = null;
	        PrintWriter pw = null;
	    	fichero = new FileWriter("avisos.txt",true);
	    pw = new PrintWriter(fichero);
	    if(i==1){
	    pw.println("Comando incorrecto: "+s+"\n");
	    fichero.close();}
	    if(i==2){
	    	pw.println(s+"- - Numero de argumentos incorrecto\n");
	    	fichero.close();
	    }
	    
	    } catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	    } catch (IOException e) {
	    e.printStackTrace();}
	    }


   
   public static void escribirFichSal(HashMap<Integer, Asignatura> asignaturas,LinkedHashMap<Integer,Persona> personas){
	   try{
     	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("personas.txt"),"UTF8"));
         for(int i=1;i<=personas.size();i++){
        	 if((personas.get(i).getPerfil()).equals("profesor")){out.write(((Profesor)personas.get(i)).toString());}
        	 if((personas.get(i).getPerfil()).equals("alumno")){out.write(((Alumno)personas.get(i)).toString());}              
         	  if(i!=personas.size()) out.write("*");
         	out.newLine();
         }
     	   
	    	 out.close();
     	    
     	    } catch (UnsupportedEncodingException e) {
     	    e.printStackTrace();
     	    } catch (IOException e) {
     	    e.printStackTrace();
     	    }
	
	  try{ int cont=0;
    	    BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("asignaturas.txt"),"UTF8"));
        for(int i=1;i<50;i++){
       	 if((asignaturas.get(i)!=null)){salida.write(asignaturas.get(i).toString()); cont++;        
        	  if(cont!=asignaturas.size()) salida.write("*");
        	salida.newLine();}
        }
    	   
	    	 salida.close();
    	    
    	    } catch (UnsupportedEncodingException e) {
    	    e.printStackTrace();
    	    } catch (IOException e) {
    	    e.printStackTrace();
    	    }
	   
   }
   
   
   
   public static boolean comprobarFI(String fecha){
     int bisiesto=0;
     String fecha1[]=fecha.split("/");
     int dia = Integer.parseInt(fecha1[0]);
     int mes = Integer.parseInt(fecha1[1]);
     int anho = Integer.parseInt(fecha1[2]);
   
     if((anho % 4 == 0) && ((anho % 100 != 0) || (anho % 400 == 0))) bisiesto=1;
     if(mes<0 || mes>12) { return false;}
     if((mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12) && (dia>31 || dia<0)) { return false;}
     if((mes==4 || mes==6 || mes==9 || mes==11 ) && (dia>30 || dia<0)) { return false;}
     if((mes==2) && (bisiesto==1) && (dia>29 || dia<0)){  return false;}
     if((mes==2) && bisiesto==0 && (dia>28 || dia<0)) { return false;}
     if(anho<1950 || anho>2020){ return false;}
     return true;
   }

   
   public static boolean cFechaIngreso(String fecha1, String fecha2){
   	int edad=0;
String tmp1[]=fecha1.split("/");
int anho1=Integer.parseInt(tmp1[2]);
String tmp2[]=fecha2.split("/");
int anho2=Integer.parseInt(tmp2[2]);
edad=anho2-anho1;
        return(edad>15&&edad<65);
   	
   }
   

   
   public static boolean comprobarSolape(String dia1, int Hini, ArrayList<Grupo> docenciaImpartida,  HashMap<Integer, Asignatura> asignaturas){
	   if(docenciaImpartida==null){return true;}
	   int num=docenciaImpartida.size();
	   String dia="";
	   int horaIn=0;
	   int numH=0;
	   for(int i=0; i<num;i++){
		   int ID_a=docenciaImpartida.get(i).getIDAsig();
		   int ID_g=docenciaImpartida.get(i).getIdGrupo();
		   String tipo=docenciaImpartida.get(i).getTipo();
		   if(tipo==null)return true;
		   for(int x=0;x<=50;x++){
			   if(asignaturas.get(x)!=null){
				   if(ID_a==asignaturas.get(x).getID()){
					   if(tipo.equals("A")){
						   dia=asignaturas.get(x).getGrupos_A().get(ID_g-1).getDia();
						   if(asignaturas.get(x).getGrupos_A().get(ID_g-1).getHoraIn()==0) return true;
						   horaIn=asignaturas.get(x).getGrupos_A().get(ID_g-1).getHoraIn();
						   numH=asignaturas.get(x).getGrupos_A().get(ID_g-1).getNumeroHoras();
					   }
					   if(tipo.equals("B")){
						   dia=asignaturas.get(x).getGrupos_B().get(ID_g-1).getDia();
						   horaIn=asignaturas.get(x).getGrupos_B().get(ID_g-1).getHoraIn();
						   if(asignaturas.get(x).getGrupos_B().get(ID_g-1).getHoraIn()==0) return true;
						   numH=asignaturas.get(x).getGrupos_B().get(ID_g-1).getNumeroHoras();
					   }
					   if(dia.equals(dia1) && numH==2){ if(Hini==horaIn-1) return false; 
					   if(Hini==horaIn) return false;
					   if(Hini==horaIn+1) return false;
					   else{ return true;}
					   }
				   }
			   }
			  
		   }
	   }
	   return true;
   }


   
   public static String reempDos(String x){
	return x.replace("  ", " ").replace(" ", " ");   
   }
   
    }

