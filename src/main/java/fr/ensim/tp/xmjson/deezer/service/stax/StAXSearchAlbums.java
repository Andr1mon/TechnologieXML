package fr.ensim.tp.xmjson.deezer.service.stax;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.service.AbstractSearchAlbum;
import fr.ensim.tp.xmjson.deezer.service.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Apparicio
 * 
 */
public class StAXSearchAlbums extends AbstractSearchAlbum {
  private static final Logger LOG = LogManager.getLogger();

  /*
   * (non-Javadoc)
   * 
   * @see
   * fr.ensim.xml.deezer.AbstractSearchAlbum#readAlbums(java.io.InputStream)
   */
  @Override
  public List<Album> readAlbums(InputStream in) throws XMLStreamException {
    LOG.debug(">>readAlbums");

    // recuperation d'un parser DOM
    XMLInputFactory xmlif = XMLInputFactory.newInstance();
    XMLStreamReader xmlsr = xmlif.createXMLStreamReader(in, "UTF-8");

    // parsing
    List<Album> listAlbums = new ArrayList<Album>();

    //TODO

    LOG.debug("<<readAlbums");
    return null;
  }

  @Override
  public Output format() {
    return Output.XML;
  }
}
