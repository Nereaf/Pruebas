package pruebas;
import java.util.ArrayList;

public class Equipo {

    ArrayList <MiembroEquipo> equipo;

    public Equipo() {
        equipo = new ArrayList<MiembroEquipo>();
    }

    public void addMiembro(MiembroEquipo miembro) {
        equipo.add(miembro);
    }

    public void removeMiembro(MiembroEquipo miembro) {
        equipo.remove(miembro);
    }

    public MiembroEquipo getMiembro(int i) {
        return equipo.get(i);
    }
    
    public ArrayList<MiembroEquipo> getMiembros(){
    	return this.equipo;
    }

    public int getSize() {
        return equipo.size();
    }

    public void setMiembro(int i, MiembroEquipo miembro) {
        equipo.set(i, miembro);
    }   

    public MiembroEquipo getMiembro(String id) {
        for (int i = 0; i < equipo.size(); i++) {
            if (equipo.get(i).getId().equals(id)) {
                return equipo.get(i);
            }
        }
        return null;
    }
    
}
