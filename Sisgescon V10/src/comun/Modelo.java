package comun;

/**
 *
 * @author juan
 */
public abstract class Modelo {
    
    private int id;
    
    public Modelo() {
        this.id = 0;
    }
    
    public int getId() { //throws Exception{
        //if (id == 0) throw new Exception("Id es 0");
        return id;
    }
    
    public void setId(int id) {
        //if (this.id == 0) {
            this.id = id;
        //}
    }

    public boolean esIgual(Modelo m) {
        return m.getId() == id;
    }
    
}
