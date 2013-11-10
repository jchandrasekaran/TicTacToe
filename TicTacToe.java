/**
 * Tic Tac Toe
 * 
 * Author: Jayashree Chandrasekaran
 * 
 */

import java.util.*;

/**
 * Class TicTacToe
 * 
 * @author Jayashree
 * 
 * @input User's preference of agents - Naive or Thoughtful
 * @output Result of a game of Tic-Tac-Toe between user's chosen agents
 * 
 *         This class displays all possible combinations of agents to the user
 *         and takes as input an integer form the user for his preference of 2
 *         agents. A new game is initialized between these two agents and
 *         depending on the agent types, moves of 'X' and 'O' are made. The
 *         final result as to which agent wins is displayed.
 * 
 */

class TicTacToe {

	// Two agents which will play against each other
	// Declaring them as a superclass Agent is necessary because we do not
	// yet know if these agents will be Naive or Thoughtful
	private Agent one;
	private Agent two;
	
	private static int input;

	// Variable for storing the final game result - tells which player has won
	// the game
	private int gameResults;

	public static void main(String[] args) {

		// Object is required to access the private methods and variables of the
		// class.
		TicTacToe newGame = new TicTacToe();

		// Variable for taking user input
		Scanner scan = new Scanner(System.in);

		try {
			// Options displayed to the user
			System.out.println("Tic Tac Toe Game!!");
			System.out.println("Select an option for the agents : ");
			System.out.println("1. Naive vs. Naive");
			System.out.println("2. Naive vs. Thoughtful");
			System.out.println("3. Thoughtful vs. Naive");
			System.out.println("4. Thoughtful vs. Thoughtful");

			int inputValue = scan.nextInt();
			input = inputValue;

			// Method call to finalize what kind of agents 'one' and 'two'
			// will be. This depends on the user's input.
			newGame.setAgent(inputValue);
		} catch (NumberFormatException e) {
			System.out.println("All inputs must be integers!");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Exception encountered");
			System.exit(1);
		} 

		// Method call to create a new instance of a game and make the two
		// agents play
		newGame.play();

		// Method call to analyze the results of the game played and determine
		// which agent won or if it was a tie.
		newGame.getGameResults();

		System.out.println("\nTRACES");
		System.out
				.println("======================================================");
		System.out.println("Agent 1");
		System.out.println("-------");
		System.out.println("Domain Knowledge Reasoning Trace");
		System.out.println("--------------------------------");
		System.out.println(newGame.one.agentReasoningTrace.toString());
		System.out.println("Strategic Knowledge Reasoning Trace");
		System.out.println("-----------------------------------");
		System.out.println(newGame.one.agentStrategicReasoningTrace.toString());
		System.out
				.println("======================================================");
		System.out.println("Agent 2");
		System.out.println("-------");
		System.out.println("Domain Knowledge Reasoning Trace");
		System.out.println("--------------------------------");
		System.out.println(newGame.two.agentReasoningTrace.toString());
		System.out.println("Strategic Knowledge Reasoning Trace");
		System.out.println("-----------------------------------");
		System.out.println(newGame.two.agentStrategicReasoningTrace.toString());
		System.out
				.println("======================================================");
		
		System.out.println("\nThis game has 9 stages : 0 - 8!\n");
		
		try{
			
			String moveNumber = "";
			do {
				
				System.out.println("=> Which stage do you want to know about? Input any number other than 0 - 8 to exit.");
				moveNumber = scan.next();
				
				int mn = Integer.parseInt(moveNumber);
				if (mn % 2 != 0){
					switch (input) {
						case 1: {
							System.out.println("The agent was a Naive Agent!");
							break;
						}
						case 2: {
							System.out.println("The agent was a Thoughtful Agent!");
							break;
						}
						case 3: {
							System.out.println("The agent was a Naive Agent!");
							break;
						}
						case 4: {
							System.out.println("The agent was a Thoughtful Agent!");
							break;
						}
					}
					
					System.out.println("Move initiated because:\n");
					System.out.println(newGame.two.agentStrategicTrace.get(mn/2));
					
				} else {
					switch (input) {
						case 1: {
							System.out.println("The agent was a Naive Agent!");
							break;
						}
						case 2: {
							System.out.println("The agent was a Naive Agent!");
							break;
						}
						case 3: {
							System.out.println("The agent was a Thoughtful Agent!");
							break;
						}
						case 4: {
							System.out.println("The agent was a Thoughtful Agent!");
							break;
						}
					}
					
					System.out.println("Move initiated because:\n");
					System.out.println(newGame.one.agentStrategicTrace.get((mn+1)/2));
				}
			} while (moveNumber.matches("[0-8]"));
		} catch (NumberFormatException e) {
			System.out.println("All inputs must be integers!");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Exception encountered");
			System.exit(1);
		} finally {
			scan.close();
		}
	}

	// Method to set the objects of 'one' and 'two' to the corresponding types
	// of agents
	private void setAgent(int agent) throws Exception {
		switch (agent) {
		case 1: {
			one = new NaiveAgent();
			two = new NaiveAgent();
			break;
		}
		case 2: {
			one = new NaiveAgent();
			two = new ThoughtfulAgent();
			break;
		}
		case 3: {
			one = new ThoughtfulAgent();
			two = new NaiveAgent();
			break;
		}
		case 4: {
			one = new ThoughtfulAgent();
			two = new ThoughtfulAgent();
			break;
		}
		default:
			throw new Exception("Agent number out of range!");
		}
	}

	// Method to create a new instance of a game to be played by 'one' and 'two'
	// The results of the game are saved in gameResults variable
	private void play() {
		GameEngine gameInstance = new GameEngine(one, two);
		gameResults = gameInstance.playGame();
	}

	// Method to analyze the results of the game to find out who won
	private void getGameResults() {
		if (gameResults == 1)
			System.out.println("First Agent Wins!");
		else if (gameResults == 2)
			System.out.println("Second Agent Wins!");
		else if (gameResults == 3)
			System.out.println("Its a Tie!");
	}

}

/**
 * Class Agent
 * 
 * @author Jayashree
 * 
 *         This is an abstract class. The purpose of this class is simple :
 *         There must be a superclass for an Agent as the type of agents that
 *         will play the game are decided only at run time
 * 
 */

abstract class Agent {

	// A random variable - needed to pick a state randomly in case of a Naive
	// Agent
	protected static Random r = new Random();

	public String type;

	// Given a current game state, pick a move.
	public abstract int pickMove(GameEngine game);

	// variable to store the reasoning trace of an agent
	protected StringBuffer agentReasoningTrace = new StringBuffer();

	// variable to store the reasoning trace of an agent
	protected StringBuffer agentStrategicReasoningTrace = new StringBuffer();

	protected ArrayList<String> agentStrategicTrace = new ArrayList<String>();
	
	
}

/**
 * Class NaiveAgent
 * 
 * @author Jayashree
 * 
 *         This agent makes a move randomly. It will neither select a winning
 *         move, nor block the other player's win. It will play poorly, possibly
 *         failing to block it's opponent or choosing moves in a less-than-ideal
 *         fashion.
 * 
 *         A random number generator is used to pick an empty cell on the board.
 *         The agent's move, either an X or an O, will be placed on that
 *         randomly chosen cell.
 * 
 */

class NaiveAgent extends Agent {

	// Simply pick a random move from remaining open squares.

	public int pickMove(GameEngine game) {
		return pickRandomMove(game);
	}

	protected int pickRandomMove(GameEngine game) {

		agentReasoningTrace = agentReasoningTrace
				.append("Random move picked!\n");
		agentStrategicReasoningTrace = agentStrategicReasoningTrace
				.append("\nOpponent has played -> Pick a move.\nI am a Naive Agent -> Pick a random move.\n");
		agentStrategicTrace.add("I am a Naive Agent. So pick a random move.");
		System.out.println("Picking a random move ...");
		// set selector to a random value between 0 and the number of empty
		// squares to select one of the empty squares.
		int selector = r.nextInt(9 - game.getTurnsElapsed());
		int selectedMove = 0;

		// iterate over all the squares and find out which ones are empty.
		// Return the nth empty square where n corresponds to the random number
		// generated with value less than the diff (9 - total squares left)
		for (selectedMove = 0; selectedMove < 9; selectedMove++) {
			if (game.getSquare(selectedMove) == 0) { // 0 denotes that the
														// square is empty
				if (selector == 0) {
					break;
				} else
					selector--;
			}
		}
		return selectedMove;
	}
}

/**
 * Class ThoughtfulAgent
 * 
 * @author Jayashree
 * 
 *         This class implements a Thoughtful Agent. This agent will play
 *         intelligently. While picking a move, it will first see if any winning
 *         move is available, if there is any square left on the board where if
 *         it places its move, it will win. If there is no such move, this agent
 *         will then see if it can block a winning move for the opponent, if
 *         there is any square on the board such that placing a move on it will
 *         make the opponent win. If again there is no such square, then this
 *         agent will chose a random square and make a move.
 * 
 *         The method that this agent employs to check whether it has a winning
 *         move is this : It simulates a move on every available square and
 *         calculates the result of the game because of that move. If it sees a
 *         win, then it picks that move.
 * 
 *         Similarly, for a blocking move, the agent places the opponent's
 *         symbol in every available empty square and calculates the result of
 *         the game - whether it is a win or a lose for the opponent. If its a
 *         win, then it places its own symbol on that square.
 * 
 */

class ThoughtfulAgent extends Agent {

	// Pick a move based on the current game. This follows the rules described
	// in the class description to select an winning move, a blocking move, or a
	// random move.
	public int pickMove(GameEngine game) {

		// Check to see if a winning move exists.
		int move = pickWinningMove(game);

		// If no winning move is found, check to see if a blocking move exists.
		if (move == -1) {
			move = pickBlockingMove(game);
		}

		// If a blocking move is also not found, then pick a random move and
		// play
		if (move == -1)
			move = pickRandomMove(game);

		return move;
	}

	// An winning move is a move that would allow the agent to win the game.
	// This detects an winning move on the current game board, or returns -1 if
	// one cannot be found.
	protected int pickWinningMove(GameEngine game) {
		System.out.println("Searching for winning moves ...");
		agentStrategicReasoningTrace = agentStrategicReasoningTrace
				.append("\nOpponent has played -> Pick a move.\nI am a Thoughtful Agent -> Check for Winning moves.\n");
		int moveType = (game.getNextMove() == 2) ? 2 : 1;

		// Check for winning moves at each spot by simulating that move on a
		// temp board. If one is found, return that move.
		for (int move = 0; move < 9; move++) {
			if ((moveType == 1 && game.checkAgentMove(move, moveType)
					.evaluateGameState() == 1)
					|| (moveType == 2 && game.checkAgentMove(move, moveType)
							.evaluateGameState() == 2)) {
				agentReasoningTrace = agentReasoningTrace
						.append("Winning move picked!\n");
				agentStrategicReasoningTrace = agentStrategicReasoningTrace
						.append("Winning move found -> Pick winning move.\n");
				agentStrategicTrace.add("Winning move found at stage "+(game.getTurnsElapsed()-1)+". So picked winning move.");
				System.out.println("Winning Move Found!");
				return move;
			}
		}
		System.out.println("No winning moves found!");
		return -1; // if no winning moves are found, return -1 to indicate
					// that no moves were found.
	}

	// A blocking move is a move that would prevent an opponent from winning.
	// This detects a blocking move on the current game board, or returns -1 if
	// one cannot be found.
	protected int pickBlockingMove(GameEngine game) {
		System.out.println("Searching for blocking moves ...");
		agentStrategicReasoningTrace = agentStrategicReasoningTrace
				.append("Winning move not found -> Check for Blocking moves.\n");
		int moveType = (game.getNextMove() == 1) ? 2 : 1;

		// check for blocking moves at each spot by simulating a win for the
		// opponent. If one is found, return that move.
		for (int move = 0; move < 9; move++) {
			if ((moveType == 2 && game.checkAgentMove(move, moveType)
					.evaluateGameState() == 2)
					|| (moveType == 1 && game.checkAgentMove(move, moveType)
							.evaluateGameState() == 1)) {
				agentReasoningTrace = agentReasoningTrace
						.append("No winning move found. Blocking move picked!\n");
				agentStrategicReasoningTrace = agentStrategicReasoningTrace
						.append("Blocking move found -> Pick blocking move.\n");
				agentStrategicTrace.add("Winning Move not found. Blocking move found at stage "+(game.getTurnsElapsed()-1)+". So picked blocking move.");
				System.out.println("Blocking Move Found!");
				return move;
			}
		}
		System.out.println("No blocking moves found!");
		return -1; // if no blocking moves are found, return -1 to indicate that
					// no moves were found.
	}

	// This method helps the agent pick a random move out of the remaining empty
	// squares. A random number generator picks a number between the number of
	// non-empty squares and 9 as n, say. The nth empty square from the starting
	// of the board is then selected as the agents's move.

	protected int pickRandomMove(GameEngine game) {

		agentReasoningTrace = agentReasoningTrace
				.append("No winning or blocking move found. Random move picked!\n");
		agentStrategicReasoningTrace = agentStrategicReasoningTrace
				.append("No Winning or Blocking moves found -> Pick a random move.\n");
		if(game.getTurnsElapsed() == 0)
			agentStrategicTrace.add("No Winning or Blocking moves found. So picked a random move.");
		else
			agentStrategicTrace.add("No Winning or Blocking moves found at stage "+(game.getTurnsElapsed()-1)+". So picked a random move.");
		System.out.println("Picking a random move ...");

		// set selector to a random value between 0 and the number of empty
		// squares to select one of the empty squares.
		int selector = r.nextInt(9 - game.getTurnsElapsed());
		int selectedMove = 0;

		// iterate over empty squares. Return the empty square that corresponds
		// to selector.
		for (selectedMove = 0; selectedMove < 9; selectedMove++) {
			if (game.getSquare(selectedMove) == 0) {
				if (selector == 0) {
					break;
				} else
					selector--;
			}
		}
		return selectedMove;
	}
}

/**
 * Class GameEngine
 * 
 * @author Jayashree
 * 
 *         This is the main game engine that makes the two agents interact with
 *         each other and thus play the game. This class describes the actual
 *         game of Tic Tac Toe that is played by the two agents. Constructors
 *         define the game along with the board and agents. The game is played
 *         one move by each player at a time. After an agent makes a move, the
 *         current agent is changed to be the opponent and the next move is
 *         played. After every move, the state of the board is printed and the
 *         state of the game is analyzed to see if any agent has won with a row,
 *         column or diagonal of same symbols. There is a maximum limit on the
 *         number of moves, which is the number of squares on the board.
 * 
 *         The game board is represented as a 3X3 set of squares denoted as
 *         shown: 
 *         			0 | 1 | 2 
 *         			3 | 4 | 5 
 *         			6 | 7 | 8
 * 
 *         The first agent plays the 'X' and the second agent plays the 'O'.
 *         First and second agents will depend on the user's initial choice. An
 *         'X' on the board has been given a value of 1 and an 'O' has been
 *         given a value of 2. An empty square on the board is denoted by a 0.
 * 
 *         An game state of 0 means that the game is still in progress, a state
 *         of 1 means that the first agent has won, 2 will mean that the second
 *         agent has won and 3 means that its a tie.
 * 
 */

class GameEngine {

	// These variables hold current game state information
	// Agent objects hold current players.
	private Agent ourAgent;
	private Agent opponent;

	// Current agent refers to the agent who plays the next move. The value of
	// this variable is changed to the other agent after every move in the game.
	private Agent currentAgent;

	// This variable is the next move, X or O, that should be made.
	// Representation - X: 1, O: 2
	private int nextMove;

	// Represents the game board.
	private int board[];

	// Current game state
	// Representation - InProgress: 0 , Won: 1, Lost: 2, Tied: 3, Invalid: -1
	private int gameState;

	// Number of squares that have been played on the board
	private int turnsElapsed;

	// Default constructor initializes a new game given two agents.
	public GameEngine(Agent one, Agent two) {
		// initialize to an empty board.
		board = new int[9];
		Arrays.fill(board, 0);

		// Initial game state parameters
		gameState = 0;
		turnsElapsed = 0;
		nextMove = 1; // X moves first

		// Agents participating in the game.
		ourAgent = one;
		opponent = two;

		// The first agent becomes the current Agent
		currentAgent = ourAgent;
	}

	// Copy Constructor.
	public GameEngine(GameEngine oldGame) {

		// The new game points to same agents as old game
		ourAgent = oldGame.ourAgent;
		opponent = oldGame.opponent;
		currentAgent = oldGame.currentAgent;

		// copying the previous, possiblly half played game board onto the
		// current board
		board = Arrays.copyOf(oldGame.board, oldGame.board.length);

		// Copying the oldGame's state.
		gameState = oldGame.gameState;
		turnsElapsed = oldGame.turnsElapsed;
		nextMove = oldGame.nextMove;
	}

	// Method to play out a given game to completion. Returns gameState to
	// indicate which team won.
	public int playGame() {
		while (gameState == 0) { // while the game is in progress
			try {
				// execute current agent's move, then update game state.
				executeMoveByAgent(currentAgent.pickMove(this));
				turnsElapsed++;
				printState(); // print out the state of the board after every
								// move
				gameState = evaluateGameState(); // Check victory conditions and
													// update the current game
													// state.
			} catch (Exception e) { // if an invalid move is made, don't execute
									// that move.
				System.out.println("Wrong move! Try again!");
				e.printStackTrace();
			}
		}
		return gameState; // at end of game, return gameState to specify which
							// team won.
	}

	// Execute a move for the current agent. Public wrapper for private method
	public void executeMoveByAgent(int move) throws Exception {
		executeMoveByAgent(move, nextMove);
	}

	// Executes a specified move on the gameboard of a specified type (X or O).
	// Updates the game state information on completion of the move to reflect
	// the result of the move.
	private void executeMoveByAgent(int move, int moveSymbol) throws Exception {

		if (move < 0 || move > 8 || board[move] != 0) {

			String errMessage = "Wrong Move!";
			throw new Exception(errMessage);

		} else {

			// update the board square with the required move (X or O)
			board[move] = moveSymbol;

			// update current agent so the other agent takes the next move.
			nextMove = (moveSymbol == 1) ? 2 : 1;
			if (currentAgent == ourAgent)
				currentAgent = opponent;
			else
				currentAgent = ourAgent;

			// update game state
			gameState = evaluateGameState();
		}
	}

	// Simulate a given move. Wrapper for checkMove method below.
	public GameEngine checkAgentMove(int move) {
		return checkAgentMove(move, nextMove);
	}

	// Simulates a given move and move type on the current game board. Returns a
	// copy of the game instance with the move completed and game state
	// reflected, but does not update the existing game instance.
	public GameEngine checkAgentMove(int move, int moveType) {

		// Copy the current game to a new Game instance.
		GameEngine tempGame = new GameEngine(this);

		// Execute the move on the new game state
		try {
			tempGame.executeMoveByAgent(move, moveType);
			tempGame.turnsElapsed++;
		} catch (Exception e) {
			// if an invalid move was attempted, indicate so in the new game's
			// gameState.
			tempGame.gameState = -1;
		}
		return tempGame;
	}

	// This checks for see if the game has concluded, either by one agent
	// winning or a tie. The return value indicates whether our agent won,
	// lost, or tied.
	public int evaluateGameState() {
		if (gameState == -1)
			return -1;

		// check each row, column, and diagonal for three matching moves. If
		// such a set is found, the game has ended.
		// First THree parameters of checkGame are the square numbers on the
		// board. The fourth parameter represents the symbol being played: 1 for
		// X and 2 for O.
		boolean win = checkGame(0, 1, 2, 1) || checkGame(3, 4, 5, 1)
				|| checkGame(6, 7, 8, 1)
				|| // check all rows
				checkGame(0, 3, 6, 1) || checkGame(1, 4, 7, 1)
				|| checkGame(2, 5, 8, 1) || // check all columns
				checkGame(0, 4, 8, 1) || checkGame(6, 4, 2, 1); // check both
																// diagonals

		boolean lose = checkGame(0, 1, 2, 2) || checkGame(3, 4, 5, 2)
				|| checkGame(6, 7, 8, 2)
				|| // check all rows
				checkGame(0, 3, 6, 2) || checkGame(1, 4, 7, 2)
				|| checkGame(2, 5, 8, 2) || // check all columns
				checkGame(0, 4, 8, 2) || checkGame(6, 4, 2, 2); // check both
																// diagonals

		// return a value corresponding to resulting game state.
		if (win)
			return 1;
		else if (lose)
			return 2;
		else if (turnsElapsed == 9) // game is a tie if there is no winner after
									// 9 turns.
			return 3;
		else
			return 0;
	}

	// Checks a set of three squares to see if they match (indicating a victory
	// for a player)
	private boolean checkGame(int square1, int square2, int square3,
			int moveSymbol) {
		if (board[square1] == moveSymbol && board[square2] == moveSymbol
				&& board[square3] == moveSymbol)
			return true;
		return false;
	}

	// Method to return an array containing all allowed moves (corresponding to
	// empty spaces). This method is called before every move, to check for the
	// available squares on the board where a symbol may be placed.
	public Integer[] possibleMoves() {
		ArrayList<Integer> allowedMoves = new ArrayList<Integer>();
		for (int i = 0; i < board.length; i++) {
			if (board[i] == 0)
				allowedMoves.add(i);
		}
		return allowedMoves.toArray(new Integer[0]);
	}

	// display the current game board.
	public void printState() {
		String[] strBoard = new String[board.length];
		// convert integer representation of game board to string
		// representation.
		for (int i = 0; i < board.length; i++) {
			switch (board[i]) {
			case 1:
				strBoard[i] = "X";
				break;
			case 2:
				strBoard[i] = "O";
				break;
			case 0:
				strBoard[i] = " ";
				break;
			}
		}

		// Display the state of the game board with the various Tic Tac Toe
		// symbols
		System.out.println(strBoard[0] + "|" + strBoard[1] + "|" + strBoard[2]);
		System.out.println(strBoard[3] + "|" + strBoard[4] + "|" + strBoard[5]);
		System.out.println(strBoard[6] + "|" + strBoard[7] + "|" + strBoard[8]);
		System.out.println();
	}

	// return the current value of a requested square on the game board.
	public int getSquare(int square) {
		return board[square];
	}

	// return number of moves that have been played thus far in the game
	public int getTurnsElapsed() {
		return turnsElapsed;
	}

	// Get the value of the next move.
	public int getNextMove() {
		return nextMove;
	}
}
