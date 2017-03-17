package model;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.deploy.security.ruleset.Rule;
import model.database.entries.PackageEntry;
import model.database.entries.RuleEntry;
import model.database.entries.ScoreEntry;
import model.database.entries.SentenceEntry;
import model.database.tables.PackageTable;
import model.database.tables.RuleTable;
import model.database.tables.ScoreTable;
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public RuleEntry getRule(int ruleId) {

		try {
			RuleTable ruleTable = new RuleTable();
			return ruleTable.getById(ruleId);
		} catch (SQLException e) {
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
			List<SentenceEntry> sentences = sentenceTable.getByProperty("pack", () -> packages.get(0).getIdPack(),
					true);

			ArrayList<String> sentencesNames = new ArrayList<>();
			for (SentenceEntry entry : sentences)
				sentencesNames.add(entry.getDetail());

			return sentencesNames;
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * sentence : with @
	 */
	public boolean addSentence(String sentence, String correctAnswer, String wrongAnswer, String ruleName,
			String ruleDetails, String packageName) {

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
				SentenceEntry sentenceEntry = new SentenceEntry(0, sentence.replaceAll("\"", "\'\'"),
						correctAnswer.replaceAll("\"", "\'\'"), wrongAnswer.replaceAll("\"", "\'\'"),
						ruleEntry.getIdRule(), packageId);
				sentenceTable.insert(sentenceEntry);
			} else {
				System.err.println("addSentence : rule not found");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/*
	 * sentence : with @
	 */
	public boolean setSentence(String sentence, String correctAnswer, String wrongAnswer, String ruleName,
			String ruleDetails, String packageName) {

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
			} else {
				System.err.println("setSentence : rule not found");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/*
	 * sentence : with @
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
		} catch (SQLException e) {
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
			List<SentenceEntry> sentencesEntries = sentenceTable.getByProperty("pack",
					() -> packages.get(0).getIdPack(), true);
			for (SentenceEntry entry : sentencesEntries)
				sentenceTable.delete(entry);

			RuleTable ruleTable = new RuleTable();
			List<RuleEntry> rulesEntries = ruleTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);
			for (RuleEntry entry : rulesEntries)
				ruleTable.delete(entry);

			packageTable.delete(packages.get(0));

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean exportPackage(String packageName, String filePath) {

		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("exportPackage : package not found");
				return false;
			}

			// Creation of the file
			StringBuilder fileContent = new StringBuilder(
					packages.get(0).getName() + "|" + packages.get(0).canBeModifiedOutside() + "\n");

			RuleTable ruleTable = new RuleTable();
			List<RuleEntry> rules = ruleTable.getByPack(packages.get(0).getIdPack());
			for (RuleEntry rule : rules) {
				fileContent.append(rule.getName() + "|" + rule.getDetail() + "\n");
			}

			SentenceTable sentenceTable = new SentenceTable();
			List<SentenceEntry> sentences = sentenceTable.getByProperty("pack", () -> packages.get(0).getIdPack(),
					true);
			for (SentenceEntry sentence : sentences) {

				String ruleName = "";
				for (RuleEntry rule : rules) {
					if (rule.getIdRule() == sentence.getIdRule()) {
						ruleName = rule.getName();
						break;
					}
				}

				fileContent.append(sentence.getDetail() + "|" + sentence.getPropOk() + "|" + sentence.getPropNo() + "|"
						+ ruleName + "\n");
			}

			PrintWriter out = new PrintWriter(filePath);
			out.println(fileContent);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean importPackage(String filePath) {

		try {

			List<String> fileLines = Files.readAllLines(Paths.get(filePath));
			ArrayList<String[]> fileLinesDetails = new ArrayList<>();
			for (String line : fileLines) {
				fileLinesDetails.add(line.split("\\|"));
			}

			PackageTable packageTable = new PackageTable();
			RuleTable ruleTable = new RuleTable();
			SentenceTable sentenceTable = new SentenceTable();
			PackageEntry packageEntry = null;

			for (String[] details : fileLinesDetails) {

				System.err.println(details.length);

				if (details.length == 2 && packageEntry == null) {  // package

					packageTable.insert(new PackageEntry(0, details[0], Boolean.parseBoolean(details[1])));
					List<PackageEntry> packagesEntries = packageTable.getByProperty("name", () -> details[0], true);

					if (packagesEntries.size() == 0) {
						System.err.println("importPackage : package not found");
						return false;
					}
					else {
						packageEntry = packagesEntries.get(0);
					}
				}
				else if (details.length == 2 && packageEntry != null) {   // rule

					ruleTable.insert(new RuleEntry(0, details[0], details[1], packageEntry.getIdPack()));
				}
				else if (details.length == 4) {

					List<RuleEntry> ruleEntries = ruleTable.getByProperty("name", () -> details[3], true);
					RuleEntry ruleEntry = null;
					for (RuleEntry entry : ruleEntries) {
						if (entry.getPack() == packageEntry.getIdPack()) {
							ruleEntry = entry;
						}
					}

					if (ruleEntry == null)
						System.out.println("importPackage : rule not found");
					else
						sentenceTable.insert(new SentenceEntry(0, details[0], details[1], details[2], ruleEntry.getIdRule(), packageEntry.getIdPack()));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public float getBestScore(String packageName) {

		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("getBestScore : package not found");
				return -1;
			}

			// Best score retrieval
			ScoreTable scoreTable = new ScoreTable();
			List<ScoreEntry> scoresEntries = scoreTable.getByProperty("pack", () -> packages.get(0).getIdPack(), true);

			float maximumScore = 0;
			for (ScoreEntry entry : scoresEntries) {
				if (maximumScore < entry.getScore())
					maximumScore = entry.getScore();
			}

			return maximumScore;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public boolean addScore(String packageName, float score) {
		try {
			// Package retrieval
			PackageTable packageTable = new PackageTable();
			List<PackageEntry> packages = packageTable.getByProperty("name", () -> packageName, true);

			if (packages.size() == 0) {
				System.err.println("addScore : package not found");
				return false;
			}


			// Addition of the score
			ScoreTable scoreTable = new ScoreTable();
			scoreTable.insert(new ScoreEntry(0, score, packages.get(0).getIdPack()));
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}