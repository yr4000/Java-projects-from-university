package trivia.trivia.il.ac.tau.cs.sw1.trivia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Font boldFont;
	private String lastAnswer = "";
	private questionBucket BUCKET = new questionBucket();
	private String correctAnswer;
	private String scoreString = "";

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);
		
		//create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));
		

		// "Browse" button 
		final Button browseButton = new Button(fileSelection,
				SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				filePathField.setText(GUIUtils.getFilePathFromFileDialog(shell));
			}
		});

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		playButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e){
				try {
					BUCKET.generateBucket(filePathField.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
					BUCKET.resetBucket();
					scoreLabel.setText("0");
					BUCKET.nextQuestion();
				
			}
		});
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game starts.
	 * See the updateQuestionPanel for creating the question and answer buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL,
				GridData.FILL, true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		//Initially, only displays a message
		Label label = new Label(questionPanel, SWT.NONE);
		label.setText("No question to display, yet.");
		label.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question,
			List<String> answers) {
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		Label instructionLabel = new Label(questionPanel, SWT.CENTER
				| SWT.WRAP);
		instructionLabel.setText(lastAnswer 
				+ "Answer the following question:");
		instructionLabel
				.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		Label questionLabel = new Label(questionPanel, SWT.CENTER
				| SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH
					| SWT.WRAP);
			answerButton.setText(answers.get(i));
			final boolean isCorrectAnswer = answers.get(i).equals(correctAnswer);
			GridData answerLayoutData = GUIUtils
					.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			answerButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e){
					if(!(BUCKET.strike==MAX_ERRORS || BUCKET.isEmpty())){
					if(isCorrectAnswer){
						BUCKET.score+=3;
						//System.out.println("Correct!");
						
					}
					else{
						BUCKET.score-=1;
						BUCKET.strike+=1;
						//System.out.println("Not Correct!");
					}
					scoreLabel.setText(""+BUCKET.score);
					if(!(BUCKET.strike==MAX_ERRORS || BUCKET.isEmpty()))
						BUCKET.nextQuestion();
					
					if(BUCKET.strike==MAX_ERRORS || BUCKET.isEmpty()){
						String message = "Your final score is " + BUCKET.score + " after " + BUCKET.QN +" questions";
						GUIUtils.showInfoDialog(shell, "Game Over", message);
					}
				}
				}
			});
		}

		// create the "Pass" button to skip a question
		Button passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.CENTER,
				GridData.CENTER, true, false);
		data.horizontalSpan = 2;
		passButton.setLayoutData(data);
		passButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				if(!(BUCKET.strike==MAX_ERRORS || BUCKET.isEmpty()))
					BUCKET.nextQuestion();
				
				if(BUCKET.QN==50){
						String message = "Your final score is " + BUCKET.score + " after " + BUCKET.QN +" questions";
						GUIUtils.showInfoDialog(shell, "Game Over", message);
						BUCKET.QN++;
				}
			}
		});

		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
	
	private class questionBucket{
		private ArrayList<ArrayList<String>> bucket = new ArrayList<ArrayList<String>>();
		//private HashSet<ArrayList<String>> alreadyAsked = new HashSet<ArrayList<String>>();
		private int score = 0;
		private int strike = 0;
		private int QN = 0;
		
		public void generateBucket(String file) throws FileNotFoundException{
			Scanner scanfile = new Scanner(new File(file));
			bucket.clear();
			while(scanfile.hasNextLine()){
				String[] q = scanfile.nextLine().split("\\t");
				ArrayList<String> question = new ArrayList<String>();
				question.addAll(Arrays.asList(q));
				bucket.add(question);
			}
			scanfile.close();
		}
		
		public List<String> askAQuestion(int num){
			ArrayList<String> question = bucket.remove(num);
			//alreadyAsked.add(question);
			String q0 = question.get(0);
			question.remove(0);
			Collections.shuffle(question);
			question.add(0, q0);
			return question;
		}
		
		public void nextQuestion(){
			QN++;
			int num = new Random().nextInt(bucket.size());
			correctAnswer = bucket.get(num).get(1);
			//if(BUCKET.alreadyAsked.contains(BUCKET.bucket.get(num))) continue;
			List<String> answers = askAQuestion(num);
			String question = answers.remove(0);
			//System.out.println(bucket.size());
			updateQuestionPanel(question, answers);
		}
		
		public boolean isEmpty(){
			return bucket.size()==0;
		}
		
		public void resetBucket(){
			score = 0; strike = 0; QN = 0;
		}
	}
}
