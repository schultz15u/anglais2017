package model;

import java.sql.SQLException;
import java.util.List;

import model.database.entries.RuleEntry;
import model.database.entries.SentenceEntry;
import model.database.tables.RuleTable;
import model.database.tables.SentenceTable;

public class SentencesManager {
	
	public SentencesManager() {
	}
	
	public boolean addSentence(String sentence, String correctAnswer, String wrongAnswer, String rule) {

		int packageId = 1;
		
		try {
			RuleTable ruleTable = new RuleTable();
			RuleEntry ruleEntry = new RuleEntry(0, "", rule, 1);
			ruleTable.insert(ruleEntry);
			
			List<RuleEntry> rules = ruleTable.getByProperty("detail", () -> rule, true);
			
			for (RuleEntry entry : rules) {
				if (entry.getPack() == packageId) {
					ruleEntry = entry;
					break;
				}
			}
			
			if (ruleEntry.getIdRule() != 0) {
				SentenceTable sentenceTable = new SentenceTable();
				SentenceEntry sentenceEntry = new SentenceEntry(0, sentence.replace('@', '¤').replaceAll("\"", "\'\'"), correctAnswer.replaceAll("\"", "\'\'"), wrongAnswer.replaceAll("\"", "\'\'"), ruleEntry.getIdRule(), packageId);
				sentenceTable.insert(sentenceEntry);
			}
			else {
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
