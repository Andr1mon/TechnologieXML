package fr.ensim.tp.xmjson.deezer.service.stax;

import fr.ensim.tp.xmjson.deezer.data.Track;
import fr.ensim.tp.xmjson.deezer.service.AbstractSearchTrack;
import fr.ensim.tp.xmjson.deezer.service.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

public class StAXSearchTrack extends AbstractSearchTrack {
  private static final Logger LOG = LogManager.getLogger();

  @Override
  public Output format() {
    return Output.XML;
  }

  @Override
  public List<Track> readTracks(InputStream in) throws Exception {
    LOG.debug(">>readTracks");

    // recuperation d'un parser DOM
    XMLInputFactory xmlif = XMLInputFactory.newInstance();
    XMLStreamReader xmlsr = xmlif.createXMLStreamReader(in, "UTF-8");

    // Lecture
    // TODO

    LOG.debug("<<readTracks");
    return null;
  }
}
