package pruebas;

import modelo.Actividad;
import modelo.PqtTrabajo;
import modelo.Proyecto;
import modelo.Tarea;
import procesamiento.DatosProyectos;

public class PruebaArchivador
{	
	public static void main(String[] args)
	{
		DatosProyectos a = new DatosProyectos();
		System.out.println("ProyectoPrueba1");
		Proyecto p1 = a.getProyecto("ProyectoPrueba1");
		PqtTrabajo mainWBS1 = p1.getWBS().getPaquete(0);
		Tarea tarea1P1 = mainWBS1.getTarea("Tarea1P1");

		System.out.println(tarea1P1.getNombreTarea() +  " - " + tarea1P1.esBorrable());

		for (Actividad act : tarea1P1.getActividades())
		{
			System.out.println(act.getDescripcion());
		}


		PqtTrabajo pqt = p1.getWBS().getPaquete(2);
		Tarea tarea2P1 = pqt.getTarea("Tarea2P1");

		System.out.println("\n" + tarea2P1.getNombreTarea() +  " - " + tarea2P1.esBorrable());

		for (Actividad act : tarea2P1.getActividades())
		{
			System.out.println(act.getDescripcion());
		}


		System.out.println("ProyectoPrueba2");
		Proyecto p2 = a.getProyecto("ProyectoPrueba2");
		PqtTrabajo mainWBS2 = p2.getWBS().getPaquete(0);
		Tarea tarea1P2 = mainWBS2.getTarea("Tarea1P2");

		System.out.println(tarea1P2.getNombreTarea() +  " - " + tarea1P2.esBorrable());

		for (Actividad act : tarea1P2.getActividades())
		{
			System.out.println(act.getDescripcion());
		}

		Tarea tarea2P2 = mainWBS2.getTarea("Tarea2P2");
		System.out.println("\n" + tarea2P2.getNombreTarea() +  " - " + tarea2P2.esBorrable());

		a.guardarInfoProyecto(p1);	
	}
}
