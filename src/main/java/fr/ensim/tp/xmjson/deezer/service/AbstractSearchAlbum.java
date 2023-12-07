package fr.ensim.tp.xmjson.deezer.service;

import fr.ensim.tp.xmjson.deezer.data.Album;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class AbstractSearchAlbum {
  private static final Logger LOG = LogManager.getLogger();

  /**
   * Restitue la liste des albums d'un auteur.
   * 
   * @param author
   *          l'auteur.
   * @throws Exception
   */
  public List<Album> find(String author) throws Exception {
    LOG.info(">>find author="+author);
    
    // Constitution de l'URL
    StringBuilder sUrl = new StringBuilder();
    sUrl.append("http://api.deezer.com/2.0/search/album?q=");
    sUrl.append(author);
    sUrl.append("&output=");
    sUrl.append(format().getName());

    URL url = new URL(sUrl.toString());
    LOG.info(url);

    HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
    
    List<Album> albums = null;
    try {

      // recuperation du flux xml
      cnx.setConnectTimeout(5000);
      cnx.setReadTimeout(5000);
      cnx.setRequestMethod("GET");
      cnx.setDoInput(true);
      cnx.addRequestProperty("Accept-Language", "en;q=0.6,en-us;q=0.4,sv;q=0.2");
      if (cnx.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new IOException("http status code " + cnx.getResponseCode());
      }

      InputStream in = cnx.getInputStream();

      // Lecture
      albums = readAlbums(in);
    }
    catch(Exception e) {
      LOG.error("", e);
      throw e;
    }
    finally {
      cnx.disconnect();
    }
    
    LOG.info("<<find");
    return albums;
  }

  /**
   * Restitue le format de réponse. XML ou JSON.
   * @return le format de réponse.
   */
  public abstract Output format();

  /**
   * Restitue la liste des albums à partir du flux xml.
   * 
   * @param in
   *          le flux xml.
   * @return la liste des albums.
   */
  public abstract List<Album> readAlbums(InputStream in) throws Exception;

}
