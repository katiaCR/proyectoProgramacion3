package Datos;

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
 * Esta clase se encarga de generar el documento XML del listado de almacenes
 * @author katia abigail
 * @version 28/05/2016
 */
public class DocumentoXML {

    /**
     * Genera el documento XML del ArrayList de almacenes existentes en ese momento
     * @param nombreDocumento
     * @param listado 
     */
    public static void escribo(String nombreDocumento, ArrayList <Almacen> listado){
        
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db;
            
            try {
                db = dbf.newDocumentBuilder();
                
                //Creamos el documento XML y le pasamos la etiqueta raiz                
                DOMImplementation implementation = db.getDOMImplementation();
                Document document = implementation.createDocument(null, nombreDocumento, null);
                document.setXmlVersion("1.0");                
                Element raiz = document.getDocumentElement();
                             
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
                    raiz.appendChild(etiquetaAlmacen);                                
                }
                
                //Genero el XML
                Source source = new DOMSource(document);
                //indicamos donde lo queremos almacenar
                Result result = new StreamResult(new java.io.File(nombreDocumento+".xml"));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);    
                
                System.out.println("Se ha creado el documento: " + document.getDocumentElement().getNodeName());
            
            } catch (ParserConfigurationException ex) {
                System.out.println("Error escribiendo 1");
            } catch (TransformerConfigurationException ex) {
                System.out.println("Error escribiendo 2");
            } catch (TransformerException ex) {
                System.out.println("Error escribiendo 3");
            }       
    }

}
