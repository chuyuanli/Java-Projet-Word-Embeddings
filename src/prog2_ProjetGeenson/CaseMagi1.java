package prog2_ProjetGeenson;

public class CaseMagi1 extends Case{
	
	public CaseMagi1(int i) {
		this.nb = i;
		this.nom = "case_magi1";
	}

	@Override
	public String getMessage() {
		return "Relancer le de";
	}


}
