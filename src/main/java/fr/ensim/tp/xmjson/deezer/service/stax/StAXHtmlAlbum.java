package fr.ensim.tp.xmjson.deezer.service.stax;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.service.IHtmlAlbum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Denis Apparicio
 * 
 */
public class StAXHtmlAlbum implements IHtmlAlbum {
  private static Logger log = LogManager.getLogger();

  /**
   * Ecriture de la page HTML avec StaX.
   * 
   * @param album
   * @throws XMLStreamException
   * @throws IOException
   */
  @Override
  public void write(Album album, OutputStream outputStream) throws Exception {
    log.debug(">>write");

    XMLStreamWriter xmlWriter = null;
    

    log.debug("<<write");
  }
}
