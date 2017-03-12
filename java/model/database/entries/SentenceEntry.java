package model.database.entries;

public class SentenceEntry {

	private int idSen;
	private String detail;
	private String propOk;
	private String propNo;
	private int idRule;
	private int pack;

	public SentenceEntry() {

	}

	public SentenceEntry(int idSen, String detail, String prop_ok, String prop_no, int idRule, int pack) {
		super();
		this.idSen = idSen;
		this.detail = detail;
		this.propOk = prop_ok;
		this.propNo = prop_no;
		this.idRule = idRule;
		this.pack = pack;
	}

	public int getIdSen() {
		return idSen;
	}

	public void setIdSen(int idSen) {
		this.idSen = idSen;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPropOk() {
		return propOk;
	}

	public void setPropOk(String propOk) {
		this.propOk = propOk;
	}

	public String getPropNo() {
		return propNo;
	}

	public void setPropNo(String propNo) {
		this.propNo = propNo;
	}

	public int getIdRule() {
		return idRule;
	}

	public void setIdRule(int idRule) {
		this.idRule = idRule;
	}
	
	public int getPack() {
		return pack;
	}

	public void setPack(int pack) {
		this.pack = pack;
	}


	@Override
	public String toString() {
		return "SentenceModel [idSen=" + idSen + ", detail=" + detail + ", propOk=" + propOk + ", propNo=" + propNo
				+ ", idRule=" + idRule + ", pack=" + pack + "]";
	}

}
