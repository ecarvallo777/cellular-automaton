package paquete;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Regla extends JFrame{
	private JPanel contentPane;
	int automataCelular[][];
	int reglas[][];
	int reglaElegida[];
	private JTextField textField;
	
	public Regla()
	{
		automataCelular = new int[21][41];
		reglas = new int[256][8];
		reglaElegida = new int[8];
		leerReglas();
		
		for(int i=0;i<41;i++)
    	{
    		automataCelular[0][i] = 1;
    		automataCelular[0][20] = 0;
    	}
		
		setTitle("Automatas Celulares");
		setBackground(Color.BLACK);
		setSize(1100,650);
	    setLocationRelativeTo(null);
	    setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escoger();
				transformar();
				mostrar();
			}
		});
		btnNewButton.setBounds(540, 558, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(410, 559, 102, 22);
		contentPane.add(textField);
		textField.setColumns(10);
	
	}
	
	private int getCasilla(int A,int B ,int C) {
	    int r = 0;
	    
	    	 if(A==0 && B==0 && C==0)r=reglaElegida[0];
	    else if(A==0 && B==0 && C==1)r=reglaElegida[1];
	    else if(A==0 && B==1 && C==0)r=reglaElegida[2];
	    else if(A==0 && B==1 && C==1)r=reglaElegida[3];
        else if(A==1 && B==0 && C==0)r=reglaElegida[4];
	    else if(A==1 && B==0 && C==1)r=reglaElegida[5];
	    else if(A==1 && B==1 && C==0)r=reglaElegida[6];
	    else if(A==1 && B==1 && C==1)r=reglaElegida[7];
	    
	    return r;
	}
	
	private void definirRegla(int regla)
	{
		for (int i = 0; i < 8; i++) 
		{
			reglaElegida[i] = reglas[regla][i];
		}
	}
	public void inicio(){
		

			int regla =0;

		
		while( regla < 0 || regla > 255);

		definirRegla(regla);	}
	
	public void escoger(){
		String numRegla;
		int regla;
		
		do
		{
			do 
			{
			
				numRegla= textField.getText();

				//numRegla = JOptionPane.showInputDialog("Inserta el numero de regla a mostrar:",null);
			}
			while(numRegla.equals(""));

			regla = Integer.parseInt(numRegla);

		}
		while( regla < 0 || regla > 255);

		definirRegla(regla);
	}
	public void transformar()
	{


		int a, b, c;
		for (int i = 1; i < automataCelular.length; i++) 
		{
			for (int j = 0; j < automataCelular[i].length; j++) 
			{
				if( j > 0 && j < 39)
				{
					a = automataCelular[i-1][j-1];
					b = automataCelular[i-1][j];
					c = automataCelular[i-1][j+1];
					automataCelular[i][j] = getCasilla(a, b, c);
				}
				else if(j == 0)
				{
					a = 1;
					b = automataCelular[i-1][j];
					c = automataCelular[i-1][j+1];
					automataCelular[i][j] = getCasilla(a, b, c);
				}
				else
				{
					a = automataCelular[i-1][j-1];
					b = automataCelular[i-1][j];
					c = 1;
					automataCelular[i][j] = getCasilla(a, b, c);
				}

			}		
		}
	}
	
	private void leerReglas()
	{
		int contador = 0;
		String fichero = "C:/Users/Fercho Calle/workspace/proyecto/src/paquete/automata.txt";
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);

			String linea;
			while((linea = br.readLine()) != null)
			{
				String[] campos = linea.split(" "); 
				for (int i = 0; i < campos.length; i++) 
				{
					reglas[contador][i] = Integer.parseInt(campos[i]);
				}
				contador++;
			}

			fr.close();
		}
		catch(Exception e) 
		{
			System.out.println("Excepcion leyendo fichero "+ fichero + ": " + e);
		}
	}
	
	  public void paint(Graphics g){
			super.paint(g);
			Graphics2D g2d = (Graphics2D)g;
			int t = 25, a = 1, b = 1;

	        for(int i = 0 ; i < automataCelular.length ; i++)
	        {
	        	a = 7;
	        	b += 26;
	        	try
	        	{
					for(int j = 0 ; j < 41; j++) 
					{
						if(automataCelular[i][j] == 1)
							g.setColor(Color.WHITE);
						else 
							g.setColor(Color.BLACK);	
						g.fillRect(a, b, t, t);	
						a += 26;
						
		      		}
					Thread.sleep(100);
				} catch(InterruptedException e){ 
					System.out.println("Excepcion: " + e.getMessage());
				}	

			}
	  }
	  
	    public void mostrar() {
	     
	        setVisible(true);
	    
	        
	    }
}


