package pruebas;

import modelo.Proyecto;
import modelo.ResumenProy;
import modelo.WBS;
import procesamiento.DatosProyectos;

public class PruebaReporteResumen
{
	public static void main(String[] args)
	{
		DatosProyectos a = new DatosProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		
		ResumenProy resumen = wbs.calcularResumenProyecto();
		
		System.out.println("Resumen del Proyecto");

		for (String tipoTarea : resumen.tiempoPorTipo.keySet())
		{
			int tiempo = resumen.tiempoPorTipo.get(tipoTarea);
			int numPendientes = resumen.pendientesPorTipo.get(tipoTarea);
			
			System.out.println("\nTipo: " + tipoTarea);
			System.out.println("Tiempo invertido: " + tiempo + " minutos");
			System.out.println("Tareas pendientes: " + numPendientes);
		}
	}
}
