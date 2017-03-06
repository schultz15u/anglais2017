package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.DAO.DAOException;
import model.DAO.RuleDAO;
import model.DAO.RuleDAOJDBC;
import model.DAO.SentenceDAO;
import model.DAO.SentenceDAOJDBC;
import model.object.RuleModel;
import model.object.SentenceModel;

public class Sentences {
	
	private List<SentenceModel> sentences;
	private int currentSentenceId;
	private String currentWrongWord;
	private int score;
	
	public Sentences() {
		sentences = null;
		currentSentenceId = 0;
		currentWrongWord = "";
		score = 0;
	}
	
	public void initialize() {		
		try {
			SentenceDAO sentenceTable = new SentenceDAOJDBC();
			sentences = sentenceTable.getAll();
		}
		catch (DAOException e) {
			e.printStackTrace();
			sentences = null;
		}
		
		currentSentenceId = 0;
		score = 0;
		generateWrongWord();
		
		// Random sort of the sentences list
		ArrayList<SentenceModel> sentencesTemp = new ArrayList<SentenceModel>();
		while (sentences.size() > 0) {
			int sentenceIdToTransfer = (int) (Math.random() * sentences.size());
			sentencesTemp.add(sentences.get(sentenceIdToTransfer));
			sentences.remove(sentenceIdToTransfer);
		}
		
		sentences = sentencesTemp;
	}
	
	public boolean isFinished() {
		return currentSentenceId >= sentences.size();
	}
	
	public String getRule() {
		
		try
		{
			RuleDAO ruleTable = new RuleDAOJDBC();
			RuleModel ruleModel = ruleTable.getById(sentences.get(currentSentenceId).getIdRule());
			
			return ruleModel.getDetail();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public String getIncompleteWrongSentence() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getDetail().replaceAll("¤", "---");
		return "";
	}
	
	public String getWrongSentence() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getDetail().replaceAll("¤", currentWrongWord);
		return "";
	}
	
	public String getCorrectWord() {
		return sentences.get(currentSentenceId).getPropOk();
	}
	
	public String getCorrectSentence() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getDetail().replaceAll("¤", sentences.get(currentSentenceId).getPropOk());
		return "";
	}
	
	public ArrayList<String> getChoices() {
		
		String[] choices = (getCorrectWord() + "," + sentences.get(currentSentenceId).getPropNo()).split(",");
		ArrayList<String> choicesList = new ArrayList<String>(Arrays.asList(choices));
		ArrayList<String> sortedChoices = new ArrayList<String>();
		
		for (int i = 0; i < choices.length; ++i) {
			int index = (int) (Math.random() * choicesList.size());
			
			sortedChoices.add(choicesList.get(index));
			choicesList.remove(index);
		}
		
		return sortedChoices;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean characterIsFromWrongWord(int characterId) {
		if (!isFinished()) {
			int wrongWordFirstIndex = sentences.get(currentSentenceId).getDetail().split("¤")[0].length();
			int wrongWordLastIndex = wrongWordFirstIndex + currentWrongWord.length() - 1;
			
			return characterId >= wrongWordFirstIndex && characterId <= wrongWordLastIndex;
		}
		
		return false;
	}
	
	public void validateSentence(boolean answerIsCorrect) {
		if (answerIsCorrect) {
			++score;
		}
		
		++currentSentenceId;
		generateWrongWord();
	}
	
	private void generateWrongWord()
	{
		if (!isFinished()) {
			String[] badChoices = sentences.get(currentSentenceId).getPropNo().split(",");
			int badChoiceId = (int) (Math.random() * badChoices.length);
			
			currentWrongWord = badChoices[badChoiceId];
		}
		else {
			currentWrongWord = "";
		}
	}
}
