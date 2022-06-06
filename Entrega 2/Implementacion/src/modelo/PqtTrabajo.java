package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class PqtTrabajo
{	
	private String titulo;
	private String descripcion;
	private int indexPadre;
	private ArrayList<Integer> subPaquetes;
	private HashMap<String, Tarea> tareas;
	
	
	public PqtTrabajo(String titulo, String descripcion,
							int indexPadre)
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.indexPadre = indexPadre;
		this.subPaquetes  = new ArrayList<Integer>();
		this.tareas  = new HashMap<String, Tarea>();
	}
	
	public void agregarSubPaquete(int indexHijo)
	{
		subPaquetes.add(indexHijo);
	}
	
	public void agregarTarea(Tarea tarea)
	{
		tareas.put(tarea.getNombreTarea(), tarea);
	}
	
	public void borrarTarea(String nombreTarea) throws Exception
	{
		Tarea laTarea = tareas.get(nombreTarea);
		
		if (laTarea != null)
		{
			if (laTarea.esBorrable())
			{
				tareas.remove(nombreTarea);
			}
			else
			{
				throw new Exception("La tarea no es borrable");
			}
		}
		
		else
		{
			throw new Exception("No existe una tarea con tal nombre en el paquete");
		}
	}
	
	
	//GETTERS
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public Integer getIndexPadre()
	{
		return indexPadre;
	}
	
	public ArrayList<Integer> getSubPaquetes()
	{
		ArrayList<Integer> copia = new ArrayList<Integer>(subPaquetes);
		return copia;
	}
	
	public Tarea getTarea(String tituloTarea)
	{
		return tareas.get(tituloTarea);
	}
	
	public ArrayList<Tarea> getTareas()
	{
		ArrayList<Tarea> listaTareas = new ArrayList<Tarea>(tareas.values());
		return listaTareas;
	}
}
