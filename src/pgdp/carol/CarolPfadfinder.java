package pgdp.carol;

import static pgdp.MiniJava.*;

import java.util.Arrays;

public class CarolPfadfinder {
    
   public static boolean lastTurnsAreUseless(char[] instr, int filled){
		if(filled-2>=0 && instr[filled-1]=='r' && instr[filled-2]=='l') return true;
		else if(filled-2>=0 && instr[filled-1]=='l' && instr[filled-2]=='r') return true;
		else if(filled-2>=0 && instr[filled-1]=='r' && instr[filled-2]=='r') return true;
		else if(filled-3>=0 && instr[filled-1]=='l' && instr[filled-2]=='l' && instr[filled-3]=='l') return true;
		else return false;
	}

	public static int getMinimalStepsAndTurns(int x, int y, int direction, int findX, int findY){
		int d = 0;
		int s = 0;
		if(direction==0) {
			     if(x<findX && y==findY) { s = findX-x; }
			else if(x<=findX && y>findY) { d=1; s = findX-x+y-findY; }
			else if(x<=findX && y<findY) { d=1; s = findX-x+findY-y; }
			else if(x>findX && y>=findY) { d=2; s = x-findX+y-findY; }
			else if(x>findX && y<findY){ d=2; s = x-findX+findY-y; }
		}
		else if(direction==1) {
			if(x==findX && y<findY) { s = findY-y; }
			else if(x<findX && y<=findY) { d=1; s = findX-x+findY-y; }
			else if(x>findX && y<=findY) { d=1; s = x-findX+findY-y; }
			else if(x>=findX && y>findY) { d=2; s = x-findX+y-findY; }
			else if(x<findX && y>findY) { d=2; s = findX-x+y-findY; }
		}
		else if(direction==2) {
			if(x>findX && y==findY) { s = x-findX; }
			else if(x>=findX && y>findY) { d=1; s = x-findX+y-findY; }
			else if(x>=findX && y<findY) { d=1; s = x-findX+findY-y; }
			else if(x<findX && y>=findY) { d=2; s = x-findX+y-findY; }
			else if(x<findX && y<findY) { d=2; s = findX-x+findY-y; }
		}
		else {
			if(x==findX && y>findY) { s = y-findY; }
			else if(x<findX && y>=findY) { d=1; s = findX-x+y-findY; }
			else if(x>findX && y>=findY) { d=1; s = x-findX+y-findY; }
			else if(x>=findX && y<findY) { d=2; s = x-findX+findY-y; }
			else if(x<findX && y<findY) { d=2; s = findX-x+findY-y; }
		}
		return d+s;
	}
	
	public static boolean wasThereBefore(char[] instr, int filled){
		if(filled<1 || filled>instr.length) return false;
		int[][] arr = new int[2*filled+2][2*filled+2];
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				arr[i][j]=0;
			}
		}
		int x = filled;
		int y = filled;
		int direction = 0;
		if(filled==1 && instr[filled-1]!='r' && instr[filled-1]!='l') return false;
		for(int i=0; i<filled; i++){
			if (instr[i] == 'r') {
				direction--;
				if (direction == -1) direction = 3;
			}
			else if (instr[i] == 'l') {
				direction++;
				if (direction == 4) direction = 0;
			}
			else if(instr[i]=='s') {
				if(direction==0) x++;
				else if(direction==1) y++;
				else if(direction==2) x--;
				else y--;
			}
			else if(instr[i]=='n') {
				if (direction == 0) arr[x + 1][y]--;
				if (direction == 1) arr[x][y + 1]--;
				if (direction == 2) arr[x - 1][y]--;
				if (direction == 3) arr[x][y - 1]--;
			}
			else {
				if (direction == 0) arr[x + 1][y]++;
				if (direction == 1) arr[x][y + 1]++;
				if (direction == 2) arr[x - 1][y]++;
				if (direction == 3) arr[x][y - 1]++;
			}
			if(instr[i]=='n' || instr[i]=='p'){
				if(x!=filled || y!=filled) return false;
			}
			if(i==filled-1 && x==filled && y==filled && arr[x][y]==0) return true;
		}
		return false;
	}
	
	public static boolean findInstructions(int[][] playground, int x, int y, int direction, int blocks, int findX, int findY, char[] instructions){
// 		for(int i=0; i<instructions.length; i++){
			
// 		}
		return false;
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
