package modelo;

import java.util.ArrayList;

public abstract class WBS
{
	private int numPaquetes = 1;
	protected Proyecto elProyecto;
	protected ArrayList<PqtTrabajo> listaPaquetes;
	
	public WBS(Proyecto elProyecto)
	{
		String nombreProyecto = elProyecto.getNombre();
		PqtTrabajo mainWBS = new PqtTrabajo("WBS_" + nombreProyecto,
		   	   	  "Work Breakdown Structure del proyecto " + nombreProyecto, -1);
		
		this.elProyecto = elProyecto;
		this.listaPaquetes = new ArrayList<PqtTrabajo>();
		
		listaPaquetes.add(mainWBS);
	}
	
	
	public void agregarPaquete(PqtTrabajo paquete)
	{
		if (!paquete.getIndexPadre().equals(-2))
		{
			PqtTrabajo padre = listaPaquetes.get(paquete.getIndexPadre());
			padre.agregarSubPaquete(numPaquetes);
		}
		
		listaPaquetes.add(paquete);
		numPaquetes++;
	
	}
	
	
	public void agregarTarea(Tarea tarea)
	{
		PqtTrabajo padre = listaPaquetes.get(tarea.getIndexPadre());
		padre.agregarTarea(tarea);
	}
	
	
	public void borrarPaquete(int indexPaquete) throws Exception
	{	
		
		if (paqueteEsBorrable(indexPaquete))
		{
			PqtTrabajo paqueteVacio = new PqtTrabajo("PaqueteVacio", "", -2);
			listaPaquetes.set(indexPaquete, paqueteVacio);
		}
		
		else
		{
			throw new Exception("El paquete no es borrable");
		}
	}
	
	
	public void borrarTarea(int indexPaquete, String nombreTarea) throws Exception
	{
		PqtTrabajo elPaquete = listaPaquetes.get(indexPaquete);
		elPaquete.borrarTarea(nombreTarea);
	}
	
	
	public abstract Avances calcularAvancePaquete(int indexPaquete);
	public abstract Planeacion calcularCalidadPlaneacion();
	public abstract DesEqp calcularDesempenoEquipo();
	public abstract ResumenProy calcularResumenProyecto();
	
	
	private boolean paqueteEsBorrable(int indexPaquete)
	{
		PqtTrabajo paquete = listaPaquetes.get(indexPaquete);
		
		for (Tarea tarea : paquete.getTareas())
		{
			if (!tarea.esBorrable())
			{
				return false;
			}
		}
		
		for (int indexSubPaquete : paquete.getSubPaquetes())
		{
			return paqueteEsBorrable(indexSubPaquete);
		}
		
		return true;
	}
	
	
	public PqtTrabajo getPaquete(int index)
	{
		return listaPaquetes.get(index);
	}
	
	public int getNumPaquetes()
	{
		return listaPaquetes.size();
	}
	
	public ArrayList<PqtTrabajo> getListaPaquetes()
	{
		ArrayList<PqtTrabajo> copia = new ArrayList<PqtTrabajo>(listaPaquetes);
		return copia;
	}
	
}
