package prog2_ProjetGeenson;

public class CaseMagi2 extends Case {
	
	public CaseMagi2(int i) {
		this.nb = i;
		this.nom = "case_magi2";
	}

	@Override
	public String getMessage() {
		return "Reculer de tros cases";
	}


}
