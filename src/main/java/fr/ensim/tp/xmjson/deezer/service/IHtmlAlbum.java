package fr.ensim.tp.xmjson.deezer.service;

import fr.ensim.tp.xmjson.deezer.data.Album;

import java.io.OutputStream;

public interface IHtmlAlbum {

  void write(Album album, OutputStream outputStream) throws Exception;
}
