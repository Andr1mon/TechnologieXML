package fr.ensim.tp.xmjson.deezer.service.sax;

import fr.ensim.tp.xmjson.deezer.data.Album;
import fr.ensim.tp.xmjson.deezer.data.Artist;
import fr.ensim.tp.xmjson.deezer.data.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Apparicio
 */
class SAXSearchAlbumsHandler extends DefaultHandler {
  private static final Logger log = LogManager.getLogger();

  private List<Album> listAlbum = new ArrayList<>();
  private String content;
  private boolean isAlbum = false;
  private boolean isArtist = false;
  private boolean isTrack = false;

  /**
   * Restitue la liste des albums.
   * <p>
   * return
   */
  public List<Album> getListAlbum() {
    return listAlbum;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
   * java.lang.String, java.lang.String, org.xml.sax.Attributes)
   */
  @Override
  public void startElement(String uri,
                           String localName,
                           String qName,
                           Attributes attributes) {

    if (log.isDebugEnabled()) {
      StringBuilder st = new StringBuilder();
      st.append('<');
      st.append(localName);
      // Attributs
      for (int i = 0; i < attributes.getLength(); i++) {
        st.append(' ');
        st.append(attributes.getLocalName(i));
        st.append('=');
        st.append(attributes.getValue(i));
      }
      st.append('>');
      log.debug(st);
    }

//    if (isAlbum) {
//      switch (localName) {
//        case "id":
//          listAlbum.get(listAlbum.size()-1).setId(characters());
//          break;
//      }
//    }

    if (localName.equals("album")) {
      isAlbum = true;
      listAlbum.add(new Album());
    }
    if (isAlbum && localName.equals("artist")) {
      isArtist = true;
      listAlbum.get(listAlbum.size() - 1).setArtist(new Artist());
    }
    if (localName.equals("track")) {
      isTrack = true;
      listAlbum.get(listAlbum.size() - 1).addTrack(new Track());
    }

  }

  /*
   * (non-Javadoc) BufferedReader
   *
   * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public void endElement(String uri, String localName, String qName) {
    if (log.isDebugEnabled()) {
      StringBuilder st = new StringBuilder();
      st.append("</");
      st.append(localName);
      st.append('>');
      log.debug(st);
    }

    int indexTrack = 0;
    int indexAlbum = listAlbum.size() - 1;
    if (indexAlbum > 0)
      indexTrack = listAlbum.get(listAlbum.size() - 1).getTracks().size() - 1;
    if (localName.equals("album"))
      isAlbum = false;
    if (!isArtist && localName.equals("id"))
      listAlbum.get(indexAlbum).setId(content);
    if (!isTrack && localName.equals("title"))
      listAlbum.get(indexAlbum).setTitle(content);
    if (isAlbum && localName.equals("cover"))
      listAlbum.get(indexAlbum).setCover(content);
    if (localName.equals("artist"))
      isArtist = false;
    if (isArtist && localName.equals("id"))
      listAlbum.get(indexAlbum).getArtist().setId(content);
    if (isArtist && localName.equals("name"))
      listAlbum.get(indexAlbum).getArtist().setName(content);
    if (isArtist && localName.equals("link"))
      listAlbum.get(indexAlbum).getArtist().setLink(content);
    if (isArtist && localName.equals("picture"))
      listAlbum.get(indexAlbum).getArtist().setPicture(content);
    if (isTrack && localName.equals("title"))
      listAlbum.get(indexAlbum).getTracks().get(indexTrack).setTitle(content);
    if (localName.equals("track"))
      isTrack = false;
    if (isTrack && localName.equals("preview"))
      listAlbum.get(indexAlbum).getTracks().get(indexTrack).setPreview(content);

  }

  /*
   * (non-Javadoc)
   *
   * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
   */
  @Override
  public void characters(char[] ch, int start, int length) {
    content = new String(ch, start, length);
  }
}
