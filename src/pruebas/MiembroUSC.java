package pruebas;
public class MiembroUSC extends Usuario{
	
    private Float[] localizacion;

    public MiembroUSC(String nombre, String DNI, String usuario, String contrasena, String email, Integer telefono, Float[] localizacion) {
        super(nombre, DNI, usuario, contrasena, email, telefono);
        this.localizacion = localizacion;
    }

  
    public Float[] getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Float[] localizacion) {
        this.localizacion = localizacion;
    }

    public String getLocalizacionString() {
        return localizacion[0] + "," + localizacion[1];
    }

    public void setLocalizacionString(String localizacion) {
        String[] localizacionSplit = localizacion.split(",");
        this.localizacion[0] = Float.parseFloat(localizacionSplit[0]);
        this.localizacion[1] = Float.parseFloat(localizacionSplit[1]);
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(localizacion);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MiembroUSC other = (MiembroUSC) obj;
		return Arrays.equals(localizacion, other.localizacion);
	}

}

