/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.Business;

/**
 *
 * @author renat
 */
public class Video {
    private int id;
    private String nome;
    private String dono;
    private String path;

    public Video(int id, String nome, String dono, String path) {
        this.id = id;
        this.nome = nome;
        this.dono = dono;
        this.path = path;
    }
    
    public Video() {
        this.id = -1;
        this.nome="";
        this.dono="";
        this.path="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Video{" + "id=" + id + ", nome=" + nome + ", dono=" + dono + ", path=" + path + '}';
    }
}
