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
import media_center.Business.Video;

/**
 *
 * @author renat
 */
public class VideoDAO implements Map<Integer, Video> {
    private Connection conn;

    @Override
    public int size() {
        int size = -1;
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps;
            
            ps = conn.prepareStatement("SELECT COUNT(*) FROM videos");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM videos WHERE id = ?");
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
        
        if(value.getClass().getName().equals("media_center.Business.Video")){
            Video v1 = (Video)value;
            Video v2 = this.get(v1.getId());
            if(v1.equals(v2)){
                r=true;
            }
        }
        
       return r;
    }

    @Override
    public Video get(Object key) {
        Video video = new Video();
        
        try {
            conn = SQLConnector.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM videos WHERE id = ?");
            ps.setInt(1,(Integer) key);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                video.setId(rs.getInt("id"));
                video.setNome(rs.getString("name"));
                video.setDono(rs.getString("dono"));
                video.setPath(rs.getString("path"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
               SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return video;
    }

    @Override
    public Video put(Integer key, Video value) {
        Video video;
        
        if(this.containsKey(key)){
            return null;
        }
        else{
            video = value;
        }
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO videos (id, name, dono, path) VALUES (?,?,?,?)");
            ps.setInt(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getDono());
            ps.setString(4, value.getPath());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return video;
    }

    @Override
    public Video remove(Object key) {
        Video v = this.get((Integer) key);
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM videos WHERE id = ?");
            ps.setInt(1, (Integer) key);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return v;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Video> v) {
        for(Video video : v.values()){
            this.put(video.getId(), video);
        }
    }

    @Override
    public void clear() {
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM videos");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM videos");
            ResultSet rs = ps.executeQuery();
            
            set = new HashSet<>();
            while(rs.next()){
                set.add(rs.getInt("id"));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(VideoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Collection<Video> values() {
        Set<Video> set = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());
        
        for(Integer k : keys){
            set.add(this.get(k));
        }
        
        return set;
    }

    @Override
    public Set<Entry<Integer, Video>> entrySet() {
        Set<Integer> keys = new HashSet<>(this.keySet());
        
        HashMap<Integer, Video> map = new HashMap<>();
        
        for(Integer k : keys){
            map.put(k,this.get(k));
        }
        
        return map.entrySet();
    }
    
    public int changeInfo(Integer key, Video value) {
        if(!this.containsKey(key)){
            return -1;
        }
        
        this.remove(key);
        this.put(value.getId(), value);

        return 1;
    }

    
}
