package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class NuevoProyecto extends JDialog implements ActionListener, KeyListener
{
	private Menu2 padre;
	private String nombre;
	private String descripcion;
	
	private JPanel settingsP;
	private JTextField cuadroFechaInicio;
	private JTextField cuadroFechaFinal;
	private ArrayList<JTextField> cuadrosTipos = new ArrayList<JTextField>();
	private JButton botonAceptar;
	private JLabel textLabel;
	
	
	public NuevoProyecto(String nombre, String descripcion, int numTipos,
							   String fechaHoy, Menu2 padre)
	{
		this.padre = padre;
		this.nombre = nombre;
		this.descripcion = descripcion;
		
		settingsP = new JPanel();
		settingsP.setLayout(null);
		
		JLabel mensajeFechaI = new JLabel("Fecha de inicio:");
		mensajeFechaI.setBounds(20, 35, 150, 30);
		mensajeFechaI.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsP.add(mensajeFechaI);
		
		cuadroFechaInicio = new JTextField();
		cuadroFechaInicio.addKeyListener(this);
		cuadroFechaInicio.setText(fechaHoy);
		cuadroFechaInicio.setBounds(230, 39, 150, 23);
		settingsP.add(cuadroFechaInicio);
		
		JLabel mensajeFechaF = new JLabel("Fecha estimada de finalizacion:");
		mensajeFechaF.setBounds(20, 70, 200, 30);
		mensajeFechaF.setFont(new Font("Bold", Font.PLAIN, 13));
		settingsP.add(mensajeFechaF);
		
		cuadroFechaFinal = new Textos("Ej: 01/01/2023");
		cuadroFechaFinal.addKeyListener(this);
		cuadroFechaFinal.setBounds(230, 74, 150, 23);
		settingsP.add(cuadroFechaFinal);
		
		addCuadrosTipos(numTipos);
		
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(152, 300, 100, 25);
		botonAceptar.addActionListener(this);
		add(botonAceptar);
		
		textLabel = new JLabel("");
		textLabel.setBounds(20, 330, 600, 23);
		textLabel.setForeground(Color.RED);
		this.add(textLabel);
		
		add(settingsP);
		setTitle("Configuracion del proyecto");
		setModal(true);
		setSize(420, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	
	private void addCuadrosTipos(int numTipos)
	{
		int y = 105;
		
		for (int i=1; i<=numTipos; i++)
		{
			JLabel mensajeTipo = new JLabel("Tipo de actividad # " + i + ":");
			mensajeTipo.setBounds(20, y, 200, 30);
			mensajeTipo.setFont(new Font("Bold", Font.PLAIN, 13));
			settingsP.add(mensajeTipo);
			
			JTextField cuadroTipo = new Textos("Ej: Implementacion");
			cuadroTipo.addKeyListener(this);
			cuadroTipo.setBounds(230, y + 4, 150, 23);
			settingsP.add(cuadroTipo);
			
			cuadrosTipos.add(cuadroTipo);
			y += 35;
		}
	}
	
	
	private boolean hayCuadroTipoIncompleto()
	{
		boolean respuesta = false;
		
		for (JTextField cuadroTipo : cuadrosTipos)
		{
			String textoTipo = cuadroTipo.getText();
			
			if (textoTipo.equals(""))
			{
				respuesta = true;
			}
		}
		
		return respuesta;
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
        if (e.getSource()==botonAceptar)
        {
            String fechaInicio = cuadroFechaInicio.getText();
            String fechaFinal = cuadroFechaFinal.getText();
            boolean cuadrosTiposIncompletos = hayCuadroTipoIncompleto();
            
            if (fechaInicio.equals("") || fechaFinal.equals("") || cuadrosTiposIncompletos)
			{
				String texto = "Por favor complete todos los campos";
				textLabel.setText(texto);
    		}   
            
            else
            {
            	ArrayList<String> tiposActividades = new ArrayList<String>();
            	
            	for (JTextField cuadroTipo : cuadrosTipos)
        		{
        			String textoTipo = cuadroTipo.getText();
        			tiposActividades.add(textoTipo);
        			
        		}
            	
            	padre.crearProyecto(nombre, descripcion, fechaInicio,
            						fechaFinal, tiposActividades);
            	this.dispose();
            }            
        }
    }
	
	@Override
	public void keyPressed(KeyEvent e)
	{	
		if (e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			String fechaInicio = cuadroFechaInicio.getText();
            String fechaFinal = cuadroFechaFinal.getText();
            boolean cuadrosTiposIncompletos = hayCuadroTipoIncompleto();
            
            if (fechaInicio.equals("") || fechaFinal.equals("") || cuadrosTiposIncompletos)
			{
				String texto = "Por favor complete todos los campos";
				textLabel.setText(texto);
    		}   
            
            else
            {
            	ArrayList<String> tiposActividades = new ArrayList<String>();
            	
            	for (JTextField cuadroTipo : cuadrosTipos)
        		{
        			String textoTipo = cuadroTipo.getText();
        			tiposActividades.add(textoTipo);
        			
        		}
            	
            	padre.crearProyecto(nombre, descripcion, fechaInicio,
            						fechaFinal, tiposActividades);
            	this.dispose();
            }
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{	
	}

	@Override
	public void keyReleased(KeyEvent e)
	{	
	}
}
