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

  private List<Album> listAlbum = new ArrayList<Album>();
  private String content;
  private boolean isAlbum = false;
  private boolean isArtist = false;
  private boolean isIdArtist = false;
  private boolean isName = false;
  private boolean isLink = false;
  private boolean isPicture = false;
  private boolean isIdAlbum = false;
  private boolean isTitleAlbum = false;
  private boolean isCover = false;
  private boolean isTrack = false;
  private boolean isTitleTrack = false;
  private boolean isPreview = false;

  /**
   * Restitue la liste des albums.
   *
   * @return
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
                           Attributes attributes) throws SAXException {

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
    if (!isArtist && localName.equals("id"))
      isIdAlbum = true;
    if (!isTrack && localName.equals("title"))
      isTitleAlbum = true;
    if (localName.equals("artist")) {
      isArtist = true;
      listAlbum.get(listAlbum.size() - 1).setArtist(new Artist());
    }
    if (isArtist && localName.equals("id"))
      isIdArtist = true;
    if (localName.equals("name"))
      isName = true;
    if (localName.equals("link"))
      isLink = true;
    if (localName.equals("picture"))
      isPicture = true;
    if (isTrack && localName.equals("title"))
      isTitleTrack = true;
    if (localName.equals("track")) {
      isTrack = true;
      listAlbum.get(listAlbum.size() - 1).addTrack(new Track());
    }
    if (localName.equals("cover"))
      isCover = true;

  }

  /*
   * (non-Javadoc) BufferedReader
   *
   * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
   * java.lang.String, java.lang.String)
   */
  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (log.isDebugEnabled()) {
      StringBuilder st = new StringBuilder();
      st.append("</");
      st.append(localName);
      st.append('>');
      log.debug(st);
    }

    if (localName.equals("album"))
      isAlbum = false;
    if (!isArtist && localName.equals("id"))
      isIdAlbum = false;
    if (localName.equals("artist"))
      isArtist = false;
    if (isArtist && localName.equals("id"))
      isIdArtist = false;
    if (localName.equals("name"))
      isName = false;
    if (localName.equals("link"))
      isLink = false;
    if (localName.equals("picture"))
      isPicture = false;

  }

  /*
   * (non-Javadoc)
   *
   * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
   */
  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    content = new String(ch, start, length);
    if (isIdAlbum)
      listAlbum.get(listAlbum.size()-1).setId(content);
    if (isIdArtist)
      listAlbum.get(listAlbum.size()-1).getArtist().setId(content);
    if (isName)
      listAlbum.get(listAlbum.size()-1).getArtist().setName(content);
    if (isLink)
      listAlbum.get(listAlbum.size()-1).getArtist().setLink(content);
    if (isPicture)
      listAlbum.get(listAlbum.size()-1).getArtist().setPicture(content);
  }

}
