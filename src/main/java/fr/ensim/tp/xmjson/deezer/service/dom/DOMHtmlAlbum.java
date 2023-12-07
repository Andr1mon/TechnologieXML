package fr.ensim.tp.xmjson.deezer.service.dom;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.service.IHtmlAlbum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.OutputStream;

public class DOMHtmlAlbum implements IHtmlAlbum {
  private static Logger log = LogManager.getLogger();

  @Override
  public void write(Album album, OutputStream outputStream) throws Exception {
    log.debug(">>write");

    //TODO
    
    log.debug("<<write");
  }
}
