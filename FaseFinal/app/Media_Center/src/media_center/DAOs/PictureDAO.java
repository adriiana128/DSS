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
import media_center.Business.Picture;

/**
 *
 * @author renat
 */
public class PictureDAO implements Map<Integer, Picture> {
    private Connection conn;

    @Override
    public int size() {
        int size = -1;
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps;
            
            ps = conn.prepareStatement("SELECT COUNT(*) FROM pictures");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pictures WHERE id = ?");
            ps.setInt(1, (Integer)key);
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
        
        if(value.getClass().getName().equals("media_center.Business.Picture")){
            Picture p1 = (Picture)value;
            Picture p2 = this.get(p1.getId());
            if(p1.equals(p2)){
                r=true;
            }
        }
        
       return r;
    }

    @Override
    public Picture get(Object key) {
        Picture pic = new Picture();
        
        try {
            conn = SQLConnector.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pictures WHERE id = ?");
            ps.setInt(1,(Integer) key);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pic.setId(rs.getInt("id"));
                pic.setNome(rs.getString("pic_name"));
                pic.setDono(rs.getString("dono"));
                pic.setPath(rs.getString("path"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
               SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return pic;
    }

    @Override
    public Picture put(Integer key, Picture value) {
        Picture picture;
        
        if(this.containsKey(key)){
            return null;
        }
        else{
            picture = value;
        }
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pictures (id,pic_name, dono, path) VALUES (?,?,?,?)");
            ps.setInt(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getDono());
            ps.setString(4, value.getPath());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return picture;
    }

    @Override
    public Picture remove(Object key) {
        Picture p = this.get((Integer) key);
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM pictures WHERE id = ?");
            ps.setInt(1, (Integer) key);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return p;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Picture> p) {
        for(Picture picture : p.values()){
            this.put(picture.getId(), picture);
        }
    }

    @Override
    public void clear() {
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM pictures");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pictures");
            ResultSet rs = ps.executeQuery();
            
            set = new HashSet<>();
            while(rs.next()){
                set.add(rs.getInt("id"));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(PictureDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Collection<Picture> values() {
        Set<Picture> set = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());
        
        for(Integer k : keys){
            set.add(this.get(k));
        }
        
        return set;
    }

    @Override
    public Set<Map.Entry<Integer, Picture>> entrySet() {
        Set<Integer> keys = new HashSet<>(this.keySet());
        
        HashMap<Integer, Picture> map = new HashMap<>();
        
        for(Integer k : keys){
            map.put(k,this.get(k));
        }
        
        return map.entrySet();
    }
    
    public int changeInfo(Integer key, Picture value) {
        if(!this.containsKey(key)){
            return -1;
        }
        
        this.remove(key);
        this.put(value.getId(), value);

        return 1;
    }

    
}
