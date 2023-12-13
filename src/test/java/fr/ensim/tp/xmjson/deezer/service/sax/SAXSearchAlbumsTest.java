package fr.ensim.tp.xmjson.deezer.service.sax;

import fr.ensim.tp.xmjson.deezer.data.Album;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SAXSearchAlbumsTest {

  @Test
  void readAlbums() throws ParserConfigurationException, IOException, SAXException {

    InputStream in = getClass().getResourceAsStream("/data/list-album-diiv.xml");

    SAXSearchAlbums sax = new SAXSearchAlbums();
    List<Album> albumList = sax.readAlbums(in);

    assertNotNull(albumList);
    assertEquals(23, albumList.size());
    assertEquals("12107420", albumList.get(0).getId());
    assertEquals("Is the Is Are", albumList.get(0).getTitle());
    assertEquals("2699111", albumList.get(0).getArtist().getId());
    assertEquals("https://api.deezer.com/2.0/album/12107420/image", albumList.get(0).getCover());
    //assertEquals("(Druun)", albumList.get(0).getTracks().get(0).getTitle());
    //assertNotNull(albumList.get(0).getTracks());
    //assertEquals("(Druun)", albumList.get(0).getTracks().get(0).getTitle());
    //assertEquals("https://cdns-preview-5.dzcdn.net/stream/c-5f6892fc926df78cbe81cb5e308f9f8e-3.mp3", albumList.get(0).getTracks().get(0).getPreview());


  }
}
