/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 */
package media_center.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import media_center.Business.User;

/**
 *
 * @author pacifico
 */
public class UserDAO implements Map<String, User> {
    private Connection conn;
    
    @Override
    public int size() {
        int size = -1;
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps;
            
            ps = conn.prepareStatement("SELECT COUNT(*) FROM users");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                size = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_nick = ?");
            ps.setString(1,(String) key);
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
        
        if(value.getClass().getName().equals("media_center.Business.User")){
            User u1 = (User)value;
            User u2 = this.get(u1.getUsername());
            if(u1.equals(u2)){
                r=true;
            }
        }
        
       return r;

    }

    @Override
    public User get(Object key) {
        User user = new User();
        
        try {
            conn = SQLConnector.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_nick = ?");
            ps.setString(1,(String) key);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user.setUsername(rs.getString("user_nick"));
                user.setPassword(rs.getString("user_pass"));
                user.setEmail(rs.getString("user_email"));
                user.setNome(rs.getString("user_name"));
                user.setIs_admin(rs.getBoolean("admin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
               SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return user;
    }

    @Override
    public User put(String key, User value) {
        User user;
        
        if(this.containsKey(key)){
            return null;
        }
        else{
            user = value;
        }
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (user_nick, user_name, user_email, user_pass, admin) VALUES (?,?,?,?,?)");
            ps.setString(1, key);
            ps.setString(2, value.getNome());
            ps.setString(3, value.getEmail());
            ps.setString(4, value.getPassword());
            ps.setBoolean(5, value.isAdmin());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public int changeInfo(String key, User value) {
        if(!this.containsKey(key)){
            return -1;
        }
        
        this.remove(key);
        this.put(value.getUsername(), value);

        return 1;
    }


    @Override
    public User remove(Object key) {
        User user = this.get((String) key);
        
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE user_nick = ?");
            ps.setString(1, (String) key);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                SQLConnector.close(conn);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        return user;
    }

    @Override
    public void putAll(Map<? extends String, ? extends User> map) {
        for(User u : map.values()){
            this.put(u.getUsername(), u);
        }
    }

    @Override
    public void clear() {
        try {
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Set<String> keySet() {
        Set<String> set = null;
        
        try{
            conn = SQLConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            
            set = new HashSet<>();
            while(rs.next()){
                set.add(rs.getString("user_nick"));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    public Collection<User> values() {
        Set<User> set = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());
        
        for(String k : keys){
            set.add(this.get(k));
        }
        
        return set;
    }

    @Override
    public Set<Entry<String, User>> entrySet() {
        Set<String> keys = new HashSet<>(this.keySet());
        
        HashMap<String, User> map = new HashMap<>();
        
        for(String k : keys){
            map.put(k,this.get(k));
        }
        
        return map.entrySet();
    }
}
