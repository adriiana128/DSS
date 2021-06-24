/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.Business;

/**
 *
 * @author pacifico
 */
public class UserOwnsMusic {
    private int music_id;
    private String user_nick;

    public UserOwnsMusic(int music_id, String user_nick) {
        this.music_id = music_id;
        this.user_nick = user_nick;
    }

    public UserOwnsMusic() {
        music_id = -1;
        user_nick = "";
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public String getUser_nick() {
        return user_nick;
    }

    public void setUser_nick(String user_nick) {
        this.user_nick = user_nick;
    }

    @Override
    public String toString() {
        return "UserOwnsMusic{" + "music_id=" + music_id + ", user_nick=" + user_nick + '}';
    }

    
}
