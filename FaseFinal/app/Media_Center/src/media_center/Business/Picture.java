/*
 * DSS Media Center
 * Grupo 53
 */
package media_center.Business;

/**
 *
 * @author renat
 */
public class Picture {
    private int id;
    private String nome;
    private String path;
    private String dono;
    
    public Picture(int id, String n, String d, String p) {
        this.id = id;
        this.nome=n;
        this.dono=d;
        this.path=p;
    }

    public Picture() {
        id = -1;
        this.nome="";
        this.dono="";
        this.path="";
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String n){
        this.nome=n;
    }
    
    public String getDono(){
        return this.dono;
    }
    
    public void setDono(String d){
        this.dono=d;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Picture{" + "id=" + id + ", nome=" + nome + ", path=" + path + ", dono=" + dono + '}';
    }
}
