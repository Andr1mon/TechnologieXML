package fr.ensim.tp.xmjson.deezer.service.json;

import fr.ensim.tp.xmjson.deezer.data.Track;
import fr.ensim.tp.xmjson.deezer.service.AbstractSearchTrack;
import fr.ensim.tp.xmjson.deezer.service.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class JSONSearchTrack extends AbstractSearchTrack {
  private static Logger log = LogManager.getLogger();

  @Override
  public Output format() {
    return Output.JSON;
  }

  @Override
  public List<Track> readTracks(InputStream in) throws Exception {
    log.debug(">>readTracks");

    //TODO
    log.debug("<<readTracks");
    
    return null;
  }
}
