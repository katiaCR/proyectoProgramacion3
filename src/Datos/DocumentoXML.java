/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public static void escribo(String nombreDocumento, ArrayList <Almacen> listado){
        
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
                
                for(Almacen al : listado){
                    Element etiquetaAlmacen= document.createElement("Almacen");
                                       
                    Element etiquetaRazonSocial = document.createElement("Razon_Social");
                    Element etiquetaSedeSocial = document.createElement("Sede_Social");
                    Element etiquetaTelf = document.createElement("Telefono");
                    Element etiquetaCoPo = document.createElement("Codigo_Postal");
                    
                    //Creamos los nodos del texto
                    Text valorRazonSocial= document.createTextNode(al.getRazonSocial());
                    Text valorSedeSocial= document.createTextNode(al.getSedeSocial());
                    Text valorTelf= document.createTextNode(al.getTelf());
                    Text valorCoPo= document.createTextNode(Integer.toString(al.getCodPostal()));
                    
                    //añadimos el texto a las etiquetas
                    etiquetaRazonSocial.appendChild(valorRazonSocial);
                    etiquetaSedeSocial.appendChild(valorSedeSocial);
                    etiquetaTelf.appendChild(valorTelf);
                    etiquetaCoPo.appendChild(valorCoPo);
                                        
                    
                                        
                    //añadimos las etiquetas al Almacén
                    etiquetaAlmacen.appendChild(etiquetaRazonSocial);
                    etiquetaAlmacen.appendChild(etiquetaSedeSocial);
                    etiquetaAlmacen.appendChild(etiquetaTelf);
                    etiquetaAlmacen.appendChild(etiquetaCoPo);
                    
                    //añadimos el atributo id al Almacén
                    etiquetaAlmacen.setAttribute("id",Integer.toString(al.getId()));
                    
                    //añadimos la etiqueta almacén a la etiqueta raiz
                    System.out.println("almacén: " + etiquetaAlmacen.getChildNodes().item(0).getTextContent());
                    System.out.println("razon_social: " + etiquetaRazonSocial.getTextContent());
                    
                    //pegamos el elemento a la raiz "Documento"
                    raiz.appendChild(etiquetaAlmacen);                                        
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
            
            } catch (ParserConfigurationException ex) {
                System.out.println("Error escribiendo 1");
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error escribiendo 2");
            } catch (TransformerException ex) {
                System.out.println("Error escribiendo 3");
            }       
    }

}
