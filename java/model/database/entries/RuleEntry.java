package model.database.entries;

public class RuleEntry {

	private int idRule;
	private String name;
	private String detail;
	private int pack;

	public RuleEntry(int id, String nom, String detail, int pack) {
		this.idRule = id;
		this.name = nom;
		this.pack = pack;
		this.detail = detail;
	}

	public RuleEntry() {

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

	@Override
	public String toString() {
		return "RuleModel [idRule=" + idRule + ", name=" + name + ", detail=" + detail + ", pack=" + pack + "]";
	}

}
