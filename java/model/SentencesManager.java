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

	public RuleEntry getRule(int ruleId) {

		try {
			RuleTable ruleTable = new RuleTable();
			return ruleTable.getById(ruleId);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getSentenceNames(String packageName) {

		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("getSentenceNames : package not found");
				return new ArrayList<>();
			}


			// Sentence retrieval
			SentenceTable sentenceTable = new SentenceTable();
			List<SentenceEntry> sentences = sentenceTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);

			ArrayList<String> sentencesNames = new ArrayList<>();
			for (SentenceEntry entry : sentences)
				sentencesNames.add(entry.getDetail());

			return sentencesNames;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<SentenceEntry> getSentences(String packageName) {

		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("getSentences : package not found");
				return new ArrayList<>();
			}


			// Sentence retrieval
			SentenceTable sentenceTable = new SentenceTable();
			return sentenceTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public SentenceEntry getSentence(String packageName, String sentence) {

		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("getSentenceNames : package not found");
				return null;
			}


			// Sentence retrieval
			SentenceTable sentenceTable = new SentenceTable();
			List<SentenceEntry> sentences = sentenceTable.getByProperty("detail", () -> sentence, true);

			for (SentenceEntry entry : sentences)
				if (entry.getPack() == packages.get(0).getIdPack())
					return entry;

			System.err.println("getSentenceNames : no sentence found");
			return null;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
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

	/*
		sentence : with @
	*/
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
				SentenceEntry sentenceEntry = new SentenceEntry(0, sentence.replaceAll("\"", "\'\'"), correctAnswer.replaceAll("\"", "\'\'"), wrongAnswer.replaceAll("\"", "\'\'"), ruleEntry.getIdRule(), packageId);
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

	/*
		sentence : with @
	*/
	public boolean setSentence(String sentence, String correctAnswer, String wrongAnswer, String ruleName, String ruleDetails, String packageName) {

		try {
			// Package retrieval
			int packageId = 0;
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() > 0)
				packageId = packages.get(0).getIdPack();
			else {
				System.err.println("setSentence : package not found");
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
				List<SentenceEntry> sentencesEntries = sentenceTable.getByProperty("detail", () -> sentence, true);
				SentenceEntry sentenceEntry = null;

				for (SentenceEntry entry : sentencesEntries) {
					if (entry.getPack() == packageId) {
						sentenceEntry = entry;
						break;
					}
				}

				if (sentenceEntry == null) {
					System.err.println("setSentence : sentence not found");
					return false;
				}

				sentenceEntry.setDetail(sentence.replaceAll("\"", "\'\'"));
				sentenceEntry.setIdRule(ruleEntry.getIdRule());
				sentenceEntry.setPropOk(correctAnswer.replaceAll("\"", "\'\'"));
				sentenceEntry.setPropNo(wrongAnswer.replaceAll("\"", "\'\'"));
				sentenceTable.update(sentenceEntry);
			}
			else {
				System.err.println("setSentence : rule not found");
				return false;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


		return true;
	}

	/*
		sentence : with @
	*/
	public boolean removeSentence(String sentence, String packageName) {

		try {
			// Package retrieval
			int packageId = 0;
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() > 0)
				packageId = packages.get(0).getIdPack();
			else {
				System.err.println("removeSentence : package not found");
				return false;
			}

			SentenceTable sentenceTable = new SentenceTable();
			List<SentenceEntry> sentencesEntries = sentenceTable.getByProperty("detail", () -> sentence, true);
			SentenceEntry sentenceEntry = null;
			System.err.println(sentence);

			for (SentenceEntry entry : sentencesEntries) {
				if (entry.getPack() == packageId) {
					sentenceEntry = entry;
					break;
				}
			}

			if (sentenceEntry == null) {
				System.err.println("removeSentence : sentence not found");
				return false;
			}

			sentenceTable.delete(sentenceEntry);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


		return true;
	}
	
	public boolean removePackage(String packageName) {

		try {
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("removeSentence : package not found");
				return false;
			}

			SentenceTable sentenceTable = new SentenceTable();
			List<SentenceEntry> sentencesEntries = sentenceTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);
			for (SentenceEntry entry : sentencesEntries)
				sentenceTable.delete(entry);

			RuleTable ruleTable = new RuleTable();
			List<RuleEntry> rulesEntries = ruleTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);
			for (RuleEntry entry : rulesEntries)
				ruleTable.delete(entry);

			packageTable.delete(packages.get(0));

			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
