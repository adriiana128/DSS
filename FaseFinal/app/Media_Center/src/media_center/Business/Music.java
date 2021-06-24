/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.Business;


/**
 *
 * @author pacifico
 */
public class Music {
    private int id;
    private String title;
    private String album_title;
    private String artist;
    private String path;
    private int ano;
    private String genre;
    private int nr;

    public Music(int id, String title, String album_title, String artist, String path, int ano, String genre, int nr) {
        this.id = id;
        this.title = title;
        this.album_title = album_title;
        this.artist = artist;
        this.path = path;
        this.ano = ano;
        this.genre = genre;
        this.nr = nr;
    }

    public Music() {
        id = -1;
        title = "";
        album_title = "";
        artist = "";
        genre = "";
        path = "";
        ano = 0;
        nr = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum_title() {
        return this.album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getNumero(){
        return this.nr;
    }
    
    public void setNumero(int nr){
        this.nr = nr;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return "Music{" + "id=" + id + ", title=" + title + ", album_title=" + album_title + ", artist=" + artist + ", path=" + path + ", ano=" + ano + ", genre=" + genre + ", nr=" + nr + '}';
    }
}
