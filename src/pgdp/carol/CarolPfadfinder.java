package pgdp.carol;

import static pgdp.MiniJava.*;

import java.util.Arrays;

public class CarolPfadfinder {
    
    public static boolean lastTurnsAreUseless(char[] instr, int filled){
		if(instr[filled-1]=='r' && instr[filled-2]=='l') return true;
		else if(instr[filled-1]=='l' && instr[filled-2]=='r') return true;
		else if(instr[filled-1]=='r' && instr[filled-2]=='r') return true;
		else if(instr[filled-1]=='l' && instr[filled-2]=='l' && instr[filled-3]=='l') return true;
		else return false;
	}

	public static void main(String[] args) {
		/*
		 * You can change this main-Method as you want. This is not being tested.
		 */

		// Note that in this array initialization the rows are in reverse order and both
		// x- and y-axis are swapped.
		int[][] playground = { //
				{ -1, -1, -1,  2, -1, -1,  2 }, //
				{ -1,  2, -1,  2, -1, -1, -1 }, //
				{ -1, -1, -1, -1, -1,  2, -1 }, //
				{  2, -1,  2,  2, -1, -1, -1 }, //
				{ -1, -1, -1,  2, -1, -1, -1 }, //
				{ -1,  2,  2, -1, -1,  2,  2 }, //
				{ -1,  2, -1, -1,  2, -1, -1 }, //
				{ -1, -1, -1, -1, -1,  2, -1 }, //
				{  2, -1, -1,  2, -1, -1, -1 }, //
		};
		int startX = 0;
		int startY = 0;
		int startDir = 3;
		int startBlocks = 0;

		printPlayground(playground, startX, startY, startDir, startBlocks);

		int findX = 4;
		int findY = 4;

		// this is expected to have an optimal solution with exactly 40 instructions
		char[] instructions = null;
//		instructions = findOptimalSolution(playground, startX, startY, startDir, startBlocks, findX, findY, 40); // TODO implement
		boolean success = instructions != null;

		if (success) {
			write("SUCCESS");
			printPlayground(playground);
			write(Arrays.toString(instructions));
		} else {
			write("FAILED");
		}
	}
}
