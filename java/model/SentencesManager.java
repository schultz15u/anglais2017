package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.database.entries.PackageEntry;
import model.database.entries.RuleEntry;
import model.database.entries.SentenceEntry;
import model.database.tables.PackageTable;
import model.database.tables.RuleTable;
import model.database.tables.SentenceTable;
import sun.plugin.com.Packager;

public class SentencesManager {
	
	public SentencesManager() {
	}

	public List<String> getPackagesNames() {

		try {
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getAll();

			ArrayList<String> packagesNames = new ArrayList<>();
			for (PackageEntry entry : packages)
				packagesNames.add(entry.getName());

			return packagesNames;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<String> getRulesNames(String packageName) {

		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("getRulesNames : package not found");
				return new ArrayList<>();
			}


			// Rules retrieval
			RuleTable ruleTable = new RuleTable();
			List<RuleEntry> rules = ruleTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);

			ArrayList<String> rulesNames = new ArrayList<>();
			rulesNames.add("-");
			for (RuleEntry entry : rules)
				rulesNames.add(entry.getName());

			return rulesNames;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public boolean addPackage(String name, boolean canBeModifiedOutside) {

		try {
			PackageEntry packageEntry = new PackageEntry(0, name, canBeModifiedOutside);

			PackageTable packageTable = new PackageTable();
			packageTable.insert(packageEntry);

			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addSentence(String sentence, String correctAnswer, String wrongAnswer, String ruleName, String ruleDetails, String packageName) {
		
		try {
			// Package retrieval
			int packageId = 0;
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() > 0)
				packageId = packages.get(0).getIdPack();
			else {
				System.err.println("addSentence : package not found");
				return false;
			}

			// Rule creation or retrieval
			RuleTable ruleTable = new RuleTable();
			List<RuleEntry> rules = ruleTable.getByProperty("name", () -> ruleName, true);
			RuleEntry ruleEntry = null;

			if (rules.size() == 0)
				ruleTable.insert(new RuleEntry(0, ruleName, ruleDetails, packageId));

			rules = ruleTable.getByProperty("name", () -> ruleName, true);
			
			for (RuleEntry entry : rules) {
				if (entry.getPack() == packageId) {
					ruleEntry = entry;
					break;
				}
			}

			// Sentence creation
			if (ruleEntry != null && ruleEntry.getIdRule() != 0) {
				SentenceTable sentenceTable = new SentenceTable();
				SentenceEntry sentenceEntry = new SentenceEntry(0, sentence.replace('@', '¤').replaceAll("\"", "\'\'"), correctAnswer.replaceAll("\"", "\'\'"), wrongAnswer.replaceAll("\"", "\'\'"), ruleEntry.getIdRule(), packageId);
				sentenceTable.insert(sentenceEntry);
			}
			else {
				System.err.println("addSentence : rule not found");
				return false;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	public boolean removeAllSentences() {
		
		try {
			(new RuleTable()).deleteAll();
			(new SentenceTable()).deleteAll();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
