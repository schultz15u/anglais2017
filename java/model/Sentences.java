package model;

import java.util.List;

import model.DAO.DAOException;
import model.DAO.SentenceDAO;
import model.DAO.SentenceDAOJDBC;
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
		
		generateWrongWord();
		currentSentenceId = 0;
		score = 0;
	}
	
	public String getWrongSentence() {
		return sentences.get(currentSentenceId).getDetail().replaceAll("¤", currentWrongWord);
	}
	
	public String getCorrectSentence() {
		return sentences.get(currentSentenceId).getDetail().replaceAll("¤", sentences.get(currentSentenceId).getPropOk());
	}
	
	public int getScore() {
		return score;
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
		String[] badChoices = sentences.get(currentSentenceId).getPropNo().split(",");
		int badChoiceId = (int) (Math.random() * badChoices.length);
		
		currentWrongWord = badChoices[badChoiceId];
	}
}
