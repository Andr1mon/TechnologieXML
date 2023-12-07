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
    assertEquals("12107420",albumList.get(0).getId());
  }
}
