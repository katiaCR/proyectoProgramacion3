/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.IOException;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Alumno
 */
public class DocumentoXML {

    public static void escribo(String nombreDocumento, ResultSet listado){
        
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            
            try {
                db = dbf.newDocumentBuilder();
                //Creamos el documento XML y le pasamos la etiqueta raiz
                DOMImplementation implementation = db.getDOMImplementation();
                Document document = implementation.createDocument(null, nombreDocumento, null);
                document.setXmlVersion("1.0");
                //Main Node: Primer ejemplos, sólo con el elemento raíz
                Element raiz = document.getDocumentElement();
                //ahora creamos un elemento con los datos del array
                System.out.println("Raiz: " + raiz.getNodeName());
                                
                
                //ME RECORRO EL RESULTSET
                ////////////////////////////////EXAMEN/////////////////////////////////////
                //ahora creamos un elemento con los datos del array 
                //por cada nombre crearemos una <almacen>
                while(listado.next()){
                    Element etiquetaAlumno= document.createElement("Alumno");
                    Element etiquetaNombre = document.createElement("Nombre");
                    Element etiquetaNota = document.createElement("Nota_media");
                    
                    //Creamos los nodos del texto
                    Text valorNombre= document.createTextNode(listado.getString(2));
                    Text valorNota= document.createTextNode((listado.getInt(3)+ listado.getInt(4) + listado.getInt(5))/3 + "");
                    //añadimos el texto a las etiquetas
                    etiquetaNombre.appendChild(valorNombre);
                    etiquetaNota.appendChild(valorNota);
                    
                    System.out.println("nombre: " + etiquetaNombre.getTextContent());
                    System.out.println("nota_media: " + etiquetaNota.getTextContent());
                    
                    //añadimos las etiquetas nombre y  nota al alumno
                    etiquetaAlumno.appendChild(etiquetaNombre);
                    etiquetaAlumno.appendChild(etiquetaNota);
                    //añadimos el atributo id al alumno
                    etiquetaAlumno.setAttribute("id",Integer.toString(listado.getInt(1)));
                    
                    //añadimos la etiqueta persona a la etiqueta raiz
                    System.out.println("alumno: " + etiquetaAlumno.getChildNodes().item(0).getTextContent());
                    raiz.appendChild(etiquetaAlumno);//pegamos el elemento a la raiz "Documento"
                    
                }
                //Generate XML
                Source source = new DOMSource(document);
                System.out.println("documento: " + document.getDocumentElement().getNodeName());
                //indicamos donde lo queremos almacenar
                Result result = new StreamResult(new java.io.File(nombreDocumento+".xml"));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);
                
                listado.close();
                
            } catch (ParserConfigurationException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (TransformerException ex) {
                System.out.println("Error escribiendo Fichero");
            } catch (SQLException ex) {
            Logger.getLogger(DocumentoXML.class.getName()).log(Level.SEVERE, null, ex);
            }     
            
        
    }

}
