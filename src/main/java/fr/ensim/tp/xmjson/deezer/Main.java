package fr.ensim.tp.xmjson.deezer;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.data.Track;
import fr.ensim.tp.xmjson.deezer.service.IHtmlAlbum;
import fr.ensim.tp.xmjson.deezer.service.stax.StAXHtmlAlbum;
import fr.ensim.tp.xmjson.deezer.service.dom.DOMSearchTrack;
import fr.ensim.tp.xmjson.deezer.service.sax.SAXSearchAlbums;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


/**
 * @author Denis Apparicio
 *
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // configuration du proxy
    ProxyConfiguration.configure();

    Logger log = LogManager.getLogger();

    log.debug(">>main");
    try {
      List<Album> listAlbum = new SAXSearchAlbums().find("diiv");

      // recuperation du 1er album
      Album album = listAlbum.get(2);

      // recuperation des titres de l album
      List<Track> tracks = new DOMSearchTrack().find(album.getId());
      album.setTracks(tracks);

      // Ecriture de la page html
      File fileHtml = new File(album.getId() + ".html");
      try (OutputStream out = new FileOutputStream(fileHtml)) {
        IHtmlAlbum html = new StAXHtmlAlbum();
        html.write(album, out);
      }

      // Ouverture de la page
      if (fileHtml.isFile()) {
        if (Desktop.isDesktopSupported()
            && Desktop.getDesktop().isSupported(Action.BROWSE)) {
          Desktop.getDesktop().browse(fileHtml.toURI());
        }
      }
    }
    catch (Throwable e) {
      log.error("", e);
    }

    log.debug("<<main");
  }

}
