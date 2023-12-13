package fr.ensim.tp.xmjson.deezer.service.dom;

import fr.ensim.tp.xmjson.deezer.data.Track;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DOMSearchTrackTest {

    @Test
    void readTracks() throws Exception {
        InputStream in = getClass().getResourceAsStream("/data/album-6332024.xml");

        DOMSearchTrack domSearchTrack = new DOMSearchTrack();
        List<Track> trackList = domSearchTrack.readTracks(in);

        assertNotNull(trackList);
        assertEquals(13, trackList.size());
        assertEquals("(Druun)", trackList.get(0).getTitle());
        assertEquals("https://cdns-preview-5.dzcdn.net/stream/c-5f6892fc926df78cbe81cb5e308f9f8e-3.mp3", trackList.get(0).getPreview());
    }
}





