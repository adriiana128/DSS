/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import media_center.Business.Music;

/**
 *
 * @author pacifico
 */
public class MusicDAO implements Map<Integer, Music> {
    private Connection conn;
    
    @Override
    public int size() {
        int size = -1;
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps;
            
            ps = conn.prepareStatement("SELECT COUNT(*) FROM musicas");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r = false;
        
        try{
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM musicas WHERE id = ?");
            ps.setInt(1,(Integer) key);
            ResultSet rs = ps.executeQuery();
            r = rs.next();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean r = false;
        
        if(value.getClass().getName().equals("media_center.Business.Music")){
            Music m1 = (Music)value;
            Music m2 = this.get(m1.getId());
            if(m1.equals(m2)){
                r=true;
            }
        }
        
       return r;
    }

    @Override
    public Music get(Object key) {
        Music musica = new Music();
        
        try {
            conn = SQLConnector.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM musicas WHERE id = ?");
            ps.setInt(1,(Integer) key);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                musica.setTitle(rs.getString("nome"));
                musica.setAlbum_title(rs.getString("album"));
                musica.setArtist(rs.getString("artista"));
                musica.setAno(rs.getObject("ano", Integer.class));
                musica.setGenre(rs.getString("genero"));
                musica.setId(rs.getInt("id"));
                musica.setPath(rs.getString("path"));
                musica.setNr(rs.getInt("numero"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
               SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return musica;
    }

    @Override
    public Music put(Integer key, Music value) {
        Music musica;
        
        if(this.containsKey(key)){
            return null;
        }
        else{
            musica = value;
        }
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO musicas (id, path, nome, album, genero, ano, artista, numero) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, key);
            ps.setString(2, value.getPath());
            ps.setString(3, value.getTitle());
            ps.setString(4, value.getAlbum_title());
            ps.setString(5, value.getGenre());
            ps.setInt(6, value.getAno());
            ps.setString(7, value.getArtist());
            ps.setInt(8, value.getNumero());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return musica;
    }

    @Override
    public Music remove(Object key) {
        Music m = this.get((Integer) key);
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM musicas WHERE id = ?");
            ps.setInt(1, (Integer) key);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return m;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Music> m) {
        for(Music music : m.values()){
            this.put(music.getId(), music);
        }
    }

    @Override
    public void clear() {
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM musicas");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> set = null;
        
        try{
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM musicas");
            ResultSet rs = ps.executeQuery();
            
            set = new HashSet<>();
            while(rs.next()){
                set.add(rs.getInt("id"));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return set;
    }

    @Override
    public Collection<Music> values() {
        Set<Music> set = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());
        
        for(Integer k : keys){
            set.add(this.get(k));
        }
        
        return set;
    }

    @Override
    public Set<Entry<Integer, Music>> entrySet() {
        Set<Integer> keys = new HashSet<>(this.keySet());
        
        HashMap<Integer, Music> map = new HashMap<>();
        
        for(Integer k : keys){
            map.put(k,this.get(k));
        }
        
        return map.entrySet();
    }
    
    public Set<Music> getFiltered(String filter, String filter_name) {
        Set<Music> musicas = new HashSet<>();
        
        try {
            conn = SQLConnector.getConnection();
            
            PreparedStatement ps = null;
            switch(filter){
                case "genre":
                    ps = conn.prepareStatement("SELECT * FROM musicas WHERE genero = ?");
                    ps.setString(1, filter_name);
                    break;
                case "artist":
                    ps = conn.prepareStatement("SELECT * FROM musicas WHERE artista = ?");
                    ps.setString(1, filter_name);
                    break;
                case "year":
                    ps = conn.prepareStatement("SELECT * FROM musicas WHERE ano = ?");
                    ps.setInt(1, Integer.parseInt(filter_name));
                    break;
                case "album":
                    ps = conn.prepareStatement("SELECT * FROM musicas WHERE album = ?");
                    ps.setString(1, filter_name);
                    break;
            }
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Music musica = new Music();
                musica.setTitle(rs.getString("nome"));
                musica.setAlbum_title(rs.getString("album"));
                musica.setArtist(rs.getString("artista"));
                musica.setAno(rs.getObject("ano", Integer.class));
                musica.setGenre(rs.getString("genero"));
                musica.setId(rs.getInt("id"));
                musica.setPath(rs.getString("path"));
                musica.setNr(rs.getInt("numero"));
                musicas.add(musica);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
               SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return musicas;
    }
    
    public int changeInfo(Integer key, Music value) {
        if(!this.containsKey(key)){
            return -1;
        }
        
        this.remove(key);
        this.put(value.getId(), value);

        return 1;
    }
    
    public int checkDuplicate(Music m){
        int r = -1;
        
        try{
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM musicas WHERE nome = ? AND album = ? AND artista = ?");
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getAlbum_title());
            ps.setString(3, m.getArtist());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                r = rs.getInt("id"); 
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return r;
    }
}
