package fr.ensim.tp.xmjson.deezer.service.dom;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.service.AbstractSearchAlbum;
import fr.ensim.tp.xmjson.deezer.service.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Denis Apparicio
 * 
 */
public class DOMSearchAlbums extends AbstractSearchAlbum {
  private static Logger log = LogManager.getLogger();

  /*
   * (non-Javadoc)
   * 
   * @see
   * fr.ensim.xml.deezer.AbstractSearchAlbum#readAlbums(java.io.InputStream)
   */
  @Override
  public List<Album> readAlbums(InputStream in) throws ParserConfigurationException,
                                               SAXException,
                                               IOException {
    log.debug(">>readAlbums");

    // recuperation d'un parser DOM
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    dbFactory.setNamespaceAware(true);
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(in);
    doc.normalize();

    
    // TODO

    log.debug("<<readAlbums");
    return null;
  }

  @Override
  public Output format() {
    return Output.XML;
  }
}
