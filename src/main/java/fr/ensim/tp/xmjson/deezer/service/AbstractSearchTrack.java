package fr.ensim.tp.xmjson.deezer.service;

import fr.ensim.tp.xmjson.deezer.data.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class AbstractSearchTrack {
  private static final Logger LOG = LogManager.getLogger();

  /**
   * Restitue la liste des chansons d'un albums.
   *
   * @param albumId
   *          ID de l'album.
   * @throws Exception
   */
  public List<Track> find(String albumId) throws Exception {
    LOG.info(">>find albumId="+albumId);

    // Constitution de l'URL
    StringBuilder sUrl = new StringBuilder();
    sUrl.append("http://api.deezer.com/2.0/album/");
    sUrl.append(albumId);
    sUrl.append("?output=");
    sUrl.append(format().getName());

    URL url = new URL(sUrl.toString());
    LOG.info(url);

    HttpURLConnection cnx = (HttpURLConnection) url.openConnection();

    List<Track> tracks = null;
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
      tracks = readTracks(in);
    }
    catch(Exception e) {
      LOG.error("", e);
      throw e;
    }
    finally {
      cnx.disconnect();
    }

    LOG.info("<<find");
    return tracks;
  }


  /**
   * Restitue le format de réponse. XML ou JSON.
   * @return le format de réponse.
   */
  public abstract Output format();

  /**
   * Restitue la liste des chansons à partir du flux xml ou json.
   *
   * @param in
   *          le flux xml ou json.
   * @return la liste des albums.
   */
  public abstract List<Track> readTracks(InputStream in) throws Exception;
}
