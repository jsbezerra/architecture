package br.com.architecture.usuario;

public enum TipoUsuario {
	
	ADMIN("administrador"), NORMAL("normal");
	
	private String label;

    private TipoUsuario(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
