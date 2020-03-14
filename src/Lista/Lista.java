/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author jprod
 */
public class Lista extends Thread{
    private ArrayList<String> personas=new ArrayList<String>();
    JProgressBar pb;

    public Lista(JProgressBar pb) {
        this.pb = pb;
    }    
    
    public void cargarDatos(){
        try {
            File f= new File("D:\\Lista Entera.txt");
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String linea;
            System.out.println("Cargando...");
            do{
                linea=br.readLine();
                if (linea!=null){
                    String datos[]=linea.split(",");
                    String cedula=datos[0].trim();
                    String nombre=datos[5].trim();
                    String apellido1=datos[6].trim();
                    String apellido2=datos[7].trim();
                    personas.add(cedula+","+nombre+","+apellido1+","+apellido2);
                }
            }while (linea!=null);
            this.pb.setMaximum(personas.size());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarDatos(){
        try {
            File f= new File("D:\\Lista.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            int c=0;
            System.out.println("Escribiendo...");
            do{
                bw.write(personas.get(c));
                bw.newLine();
                c++;
                this.pb.setValue(c);
            }while (c<this.pb.getMaximum());
            bw.flush();
            bw.close();
            fw.close();
            System.out.println("Listo...");
        } catch (IOException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void run(){
        this.cargarDatos();
        this.guardarDatos();
    }
    
    
    
}
