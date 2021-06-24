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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import media_center.Business.UserOwnsMusic;

/**
 *
 * @author pacifico
 */
public class UserOwnsMusicDAO implements List<UserOwnsMusic> {
    private Connection conn;

    @Override
    public int size() {
        int size = -1;
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps;
            
            ps = conn.prepareStatement("SELECT COUNT(*) FROM musicas_has_users");
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
    public boolean contains(Object o) {
        boolean r = false;
        
        UserOwnsMusic um = (UserOwnsMusic) o;
        int music_id = um.getMusic_id();
        String user_nick = um.getUser_nick();
        
        try{
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM musicas_has_users WHERE musicas_id = ? AND users_user_nick = ?");
            ps.setInt(1,music_id);
            ps.setString(2, user_nick);
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
    public boolean add(UserOwnsMusic e) {
        boolean r = false;
        
        if(this.contains(e)){
            return r;
        }
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO musicas_has_users (musicas_id, users_user_nick) VALUES (?,?)");
            ps.setInt(1, e.getMusic_id());
            ps.setString(2, e.getUser_nick());
            ps.executeUpdate();
            r = true;
        } catch (SQLException ex) {
            Logger.getLogger(MusicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }

    @Override
    public boolean remove(Object o) {
        UserOwnsMusic um = (UserOwnsMusic) o;
        boolean r = false;
        
        if(!this.contains(um)){
            return r;
        }
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM musicas_has_users WHERE musicas_id = ? AND users_user_nick = ?");
            ps.setInt(1, um.getMusic_id());
            ps.setString(2, um.getUser_nick());
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

        return true;
    }
    
    public boolean musicHasOwners(int music_id){
        boolean r = false;
        
        try{
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM musicas_has_users WHERE musicas_id = ?");
            ps.setInt(1,music_id);
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
    public Iterator<UserOwnsMusic> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends UserOwnsMusic> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int i, Collection<? extends UserOwnsMusic> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserOwnsMusic get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserOwnsMusic set(int i, UserOwnsMusic e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int i, UserOwnsMusic e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserOwnsMusic remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<UserOwnsMusic> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<UserOwnsMusic> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserOwnsMusic> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
