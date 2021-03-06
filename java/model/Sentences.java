package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.database.entries.RuleEntry;
import model.database.entries.SentenceEntry;
import model.database.tables.RuleTable;

public class Sentences {

	private List<SentenceEntry> sentences;
	private List<Boolean> answersAreCorrect;
	private int currentSentenceId;
	private String currentWrongWord;
	private int score;
	private String packageName;

	public Sentences() {
		sentences = new ArrayList<>();
		currentSentenceId = 0;
		currentWrongWord = "";
		score = 0;
	}

	public void initialize(SentencesManager sentencesManager, String packageName) {

		sentences = sentencesManager.getSentences(packageName);
		answersAreCorrect = new ArrayList<>();
		for (int i = 0; i < sentences.size(); ++i)
			answersAreCorrect.add(false);

		currentSentenceId = 0;
		score = 0;
		this.packageName = packageName;
		generateWrongWord();

		// Random sort of the sentences list
		ArrayList<SentenceEntry> sentencesTemp = new ArrayList<>();
		while (sentences.size() > 0) {
			int sentenceIdToTransfer = (int) (Math.random() * sentences.size());
			sentencesTemp.add(sentences.get(sentenceIdToTransfer));
			sentences.remove(sentenceIdToTransfer);
		}

		sentences = sentencesTemp;
	}

	public String getPackageName() {
		return packageName;
	}

	public int getSentencesCount() {

		if (sentences != null)
			return sentences.size();
		return 0;
	}

	public boolean isFinished() {
		return currentSentenceId >= sentences.size();
	}

	public String getRule() {

		try {
			RuleTable ruleTable = new RuleTable();
			RuleEntry ruleModel = ruleTable.getById(sentences.get(currentSentenceId).getIdRule());

			return ruleModel.getDetail();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getRuleName() {

		try {
			RuleTable ruleTable = new RuleTable();
			RuleEntry ruleModel = ruleTable.getById(sentences.get(currentSentenceId).getIdRule());

			return ruleModel.getName();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getIncompleteWrongSentence() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getDetail().replaceAll("@", "---");
		return "";
	}

	public String getWrongSentence() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getDetail().replaceAll("@", currentWrongWord);
		return "";
	}

	public String getCorrectWord() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getPropOk();
		return "";
	}

	public String getCorrectSentence() {
		if (!isFinished())
			return sentences.get(currentSentenceId).getDetail().replaceAll("@",
					"<span style='color:colorToChange'>" + sentences.get(currentSentenceId).getPropOk() + "</span>");
		return "";
	}

	public ArrayList<String> getChoices() {

		ArrayList<String> sortedChoices = new ArrayList<>();

		if (!isFinished()) {
			String[] choices = (sentences.get(currentSentenceId).getPropNo()).split(",");
			ArrayList<String> choicesList = new ArrayList<>(Arrays.asList(choices));

			for (int i = 0; i < choices.length && sortedChoices.size() < 4; ++i) {
				int index = (int) (Math.random() * choicesList.size());

				sortedChoices.add(choicesList.get(index));
				choicesList.remove(index);
			}

			sortedChoices.add((int) (Math.random() * (sortedChoices.size() + 1)), getCorrectWord());
		}

		return sortedChoices;
	}

	public int getScore() {
		return score;
	}

	public List<String> getRulesNames(SentencesManager sentencesManager, boolean forGoodAnswers) {

		ArrayList<SentenceEntry> sentencesEntries = new ArrayList<>();

		for (int i = 0; i < sentences.size(); ++i) {

			if (answersAreCorrect.get(i).equals(forGoodAnswers)) {
				sentencesEntries.add(sentences.get(i));
			}
		}

		return sentencesManager.getRulesNames(sentencesEntries);
	}

	public boolean characterIsFromWrongWord(int characterId) {

		if (!isFinished()) {
			int wrongWordFirstIndex = sentences.get(currentSentenceId).getDetail().split("@")[0].length();
			int wrongWordLastIndex = wrongWordFirstIndex + currentWrongWord.length() - 1;

			return characterId >= wrongWordFirstIndex && characterId <= wrongWordLastIndex;
		}

		return false;
	}

	public void validateSentence(boolean answerIsCorrect) {

		if (answerIsCorrect) {
			++score;
		}

		answersAreCorrect.set(currentSentenceId, answerIsCorrect);
		++currentSentenceId;
		generateWrongWord();
	}

	private void generateWrongWord() {

		if (!isFinished()) {
			String[] badChoices = sentences.get(currentSentenceId).getPropNo().split(",");
			int badChoiceId = (int) (Math.random() * badChoices.length);

			currentWrongWord = badChoices[badChoiceId];
		} else {
			currentWrongWord = "";
		}
	}
}
