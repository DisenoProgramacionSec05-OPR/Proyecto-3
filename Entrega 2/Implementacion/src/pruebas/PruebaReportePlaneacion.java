package pruebas;

import modelo.Proyecto;
import modelo.Planeacion;
import modelo.WBS;
import procesamiento.DatosProyectos;

public class PruebaReportePlaneacion
{
	public static void main(String[] args)
	{
		DatosProyectos a = new DatosProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		
		Planeacion calidad = wbs.calcularCalidadPlaneacion();
		System.out.println("Reporte Calidad Planeacion ");
		System.out.println("Tiempo planeado: " + calidad.tiempoPlaneado + " minutos");
		System.out.println("Tiempo real: " + calidad.tiempoReal + " minutos");
		System.out.println("# de tareas que cumplen planeacion: " + calidad.tareasCumplenTiempo);
		System.out.println("# total de tareas: " + calidad.totalTareas);
	}
}
