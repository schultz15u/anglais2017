package model.object;

public class RuleModel {

	private int idRule;
	private String name;
	private String detail;
	private int pack;

	public RuleModel(int id, String nom, String detail, int pack) {
		this.idRule = id;
		this.name = nom;
		this.pack = pack;
		this.detail = detail;
	}

	public RuleModel() {

	}

	public int getIdRule() {
		return idRule;
	}

	public void setIdRule(int idRule) {
		this.idRule = idRule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPack() {
		return pack;
	}

	public void setPack(int pack) {
		this.pack = pack;
	}

}
