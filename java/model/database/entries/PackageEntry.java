package model.database.entries;

public class PackageEntry {

	private int idPack;
	private String name;
	private boolean canBeModifiedOutside;

	public PackageEntry(int id, String name, boolean canBeModifiedOutside) {
		this.idPack = id;
		this.name = name;
		this.canBeModifiedOutside = canBeModifiedOutside;
	}

	public PackageEntry() {
		this.idPack = 0;
		this.name = "";
		this.canBeModifiedOutside = true;
	}

	public int getIdPack() {
		return idPack;
	}

	public void setIdPack(int idPack) {
		this.idPack = idPack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean canBeModifiedOutside() {
		return canBeModifiedOutside;
	}

	public void setCanBeModifiedOutside(boolean canBeModifiedOutside) {
		this.canBeModifiedOutside = canBeModifiedOutside;
	}

	@Override
	public String toString() {
		return "PackageModel [id_pack=" + idPack + ", name=" + name + ", can_be_modified_outside=" + canBeModifiedOutside + "]";
	}

}
