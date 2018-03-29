package prog2_ProjetGeenson;

public class CaseNormale extends Case {
	
	public CaseNormale(int i) {
		this.nb = i;
		this.nom = "case_normale";
	}
	
	@Override
	public String getMessage() {
		return "Case normale.";
	}


}
