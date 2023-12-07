package fr.ensim.tp.xmjson.deezer.service.sax;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.service.AbstractSearchAlbum;
import fr.ensim.tp.xmjson.deezer.service.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Denis Apparicio
 * 
 */
public class SAXSearchAlbums extends AbstractSearchAlbum {
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

    // recuperation d'un parser SAX
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setNamespaceAware(true);
    SAXParser parser = factory.newSAXParser();

    // constitution du flux xml
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    InputSource source = new InputSource(reader);

    // parsing
    SAXSearchAlbumsHandler handler = new SAXSearchAlbumsHandler();
    parser.parse(source, handler);

    log.debug("<<readAlbums");
    return handler.getListAlbum();
  }

  @Override
  public Output format() {
    return Output.XML;
  }
}
