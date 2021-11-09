package pgdp.carol;

import static pgdp.MiniJava.*;

import java.util.Arrays;

public class CarolPfadfinder {

    public static boolean lastTurnsAreUseless(char[] instr, int filled) {
        if (filled - 2 >= 0 && instr[filled - 1] == 'r' && instr[filled - 2] == 'l') return true;
        else if (filled - 2 >= 0 && instr[filled - 1] == 'l' && instr[filled - 2] == 'r') return true;
        else if (filled - 2 >= 0 && instr[filled - 1] == 'r' && instr[filled - 2] == 'r') return true;
        else if (filled - 3 >= 0 && instr[filled - 1] == 'l' && instr[filled - 2] == 'l' && instr[filled - 3] == 'l')
            return true;
        else return false;
    }

    public static int getMinimalStepsAndTurns(int x, int y, int direction, int findX, int findY) {
        int d = 0;
        int s = 0;
        if (direction == 0) {
            if (x < findX && y == findY) {
                s = findX - x;
            } else if (x <= findX && y > findY) {
                d = 1;
                s = findX - x + y - findY;
            } else if (x <= findX && y < findY) {
                d = 1;
                s = findX - x + findY - y;
            } else if (x > findX && y >= findY) {
                d = 2;
                s = x - findX + y - findY;
            } else if (x > findX && y < findY) {
                d = 2;
                s = x - findX + findY - y;
            }
        } else if (direction == 1) {
            if (x == findX && y < findY) {
                s = findY - y;
            } else if (x < findX && y <= findY) {
                d = 1;
                s = findX - x + findY - y;
            } else if (x > findX && y <= findY) {
                d = 1;
                s = x - findX + findY - y;
            } else if (x >= findX && y > findY) {
                d = 2;
                s = x - findX + y - findY;
            } else if (x < findX && y > findY) {
                d = 2;
                s = findX - x + y - findY;
            }
        } else if (direction == 2) {
            if (x > findX && y == findY) {
                s = x - findX;
            } else if (x >= findX && y > findY) {
                d = 1;
                s = x - findX + y - findY;
            } else if (x >= findX && y < findY) {
                d = 1;
                s = x - findX + findY - y;
            } else if (x < findX && y >= findY) {
                d = 2;
                s = x - findX + y - findY;
            } else if (x < findX && y < findY) {
                d = 2;
                s = findX - x + findY - y;
            }
        } else {
            if (x == findX && y > findY) {
                s = y - findY;
            } else if (x < findX && y >= findY) {
                d = 1;
                s = findX - x + y - findY;
            } else if (x > findX && y >= findY) {
                d = 1;
                s = x - findX + y - findY;
            } else if (x >= findX && y < findY) {
                d = 2;
                s = x - findX + findY - y;
            } else if (x < findX && y < findY) {
                d = 2;
                s = findX - x + findY - y;
            }
        }
        return d + s;
    }

    public static boolean wasThereBefore(char[] instr, int filled) {
        if (filled < 1 || filled > instr.length) return false;
        int[][] arr = new int[2 * filled + 2][2 * filled + 2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = 0;
            }
        }
        int x = filled;
        int y = filled;
        int direction = 0;
        if (filled == 1 && instr[filled - 1] != 'r' && instr[filled - 1] != 'l') return false;
        for (int i = 0; i < filled; i++) {
            if (instr[i] == 'r') {
                direction--;
                if (direction == -1) direction = 3;
            } else if (instr[i] == 'l') {
                direction++;
                if (direction == 4) direction = 0;
            } else if (instr[i] == 's') {
                if (direction == 0) x++;
                else if (direction == 1) y++;
                else if (direction == 2) x--;
                else y--;
            } else if (instr[i] == 'n') {
                if (direction == 0) arr[x + 1][y]--;
                else if (direction == 1) arr[x][y + 1]--;
                else if (direction == 2) arr[x - 1][y]--;
                else if (direction == 3) arr[x][y - 1]--;
            } else {
                if (direction == 0) arr[x + 1][y]++;
                else if (direction == 1) arr[x][y + 1]++;
                else if (direction == 2) arr[x - 1][y]++;
                else if (direction == 3) arr[x][y - 1]++;
            }
            if (instr[i] == 'n' || instr[i] == 'p') {
                if (x != filled || y != filled) return false;
            }
            if (i == filled - 1 && x == filled && y == filled && arr[x][y] == 0) return true;
        }
        return false;
    }


    public static boolean findInstructions(int[][] playground, int x, int y, int direction, int blocks, int findX, int findY, char[] instructions) {
        int number = getMinimalStepsAndTurns(x, y, direction, findX, findY);
        if (number > instructions.length) return false;
        int filled = 0;
        boolean result = recursiveAuxiliary(playground, x, y, direction, blocks, findX, findY, instructions, filled);
        if (result) {
            System.out.print("[");
            for (int i = 0; i < instructions.length; i++) {
                System.out.print(instructions[i]);
                if (i + 1 != instructions.length) System.out.print(", ");
            }
            System.out.print("]");
        }
        return result;
    }

    private static boolean recursiveAuxiliary(int[][] playground, int x, int y, int direction, int blocks, int findX, int findY, char[] instructions, int filled) {
        if (x == findX && y == findY) return true;
        if (filled == instructions.length) return false;
        if(filled>1) {
            if (wasThereBefore(instructions,filled)) return false;
            if (lastTurnsAreUseless(instructions,filled)) return false;
            if(instructions[filled]=='p' && instructions[filled-1]=='n') return false;
            if(instructions[filled]=='n' && instructions[filled-1]=='p') return false;
        }

        //go forward - 's'
        instructions[filled]='s';
        if (direction == 0) {
            if (x + 1 < playground.length) {
                if (playground[x][y] - playground[x + 1][y] == -1 || playground[x][y] - playground[x + 1][y] == 0 || playground[x][y] - playground[x + 1][y] == 1) {
                    if(recursiveAuxiliary(playground, x + 1, y, direction, blocks, findX, findY, instructions, filled+1)) return true;
                    else {
                        instructions[filled] = 'e';
                    }
                }
            }
        }
        if (direction == 1) {
            if (y + 1 < playground[0].length) {
                if (playground[x][y] - playground[x][y + 1] == -1 || playground[x][y] - playground[x][y + 1] == 0 || playground[x][y] - playground[x][y + 1] == 1) {
                    if(recursiveAuxiliary(playground, x, y+1, direction, blocks, findX, findY, instructions, filled+1)) return true;
                    else {
                        instructions[filled] = 'e';
                    }
                }
            }
        }
        if (direction == 2) {
            if (x - 1 >= 0) {
                if (playground[x][y] - playground[x - 1][y] == -1 || playground[x][y] - playground[x - 1][y] == 0 || playground[x][y] - playground[x - 1][y] == 1) {
                    if(recursiveAuxiliary(playground, x-1, y, direction, blocks, findX, findY, instructions, filled+1)) return true;
                    else {
                        instructions[filled] = 'e';
                    }
                }
            }
        }
        if (direction == 3) {
            if (y - 1 >= 0) {
                if (playground[x][y] - playground[x][y - 1] == -1 || playground[x][y] - playground[x][y - 1] == 0 || playground[x][y] - playground[x][y - 1] == 1) {
                    if(recursiveAuxiliary(playground, x, y-1, direction, blocks, findX, findY, instructions, filled+1)) return true;
                    else {
                        instructions[filled] = 'e';
                    }
                }
            }
        }

        //turn right - 'r'
        instructions[filled] = 'r';
        if (direction - 1 == -1) direction = 3;
        System.out.print(Arrays.toString(instructions) + "\n");
        System.out.print(filled+"\n");
        System.out.print(direction+"\n");
        printPlayground(playground, x, y, direction-1, blocks);
        if(recursiveAuxiliary(playground, x, y, direction-1, blocks, findX, findY, instructions, filled+1)) return true;
        else instructions[filled] = 'e';

        //turn left - 'l'
        instructions[filled] = 'l';
        if (direction+1 == 4 ) direction = 0;
        System.out.println(Arrays.toString(instructions) + "\n");
        System.out.print(filled+"\n");
        System.out.print(direction+"\n");
        printPlayground(playground, x, y, direction+1, blocks);
        if(recursiveAuxiliary(playground, x, y, direction+1, blocks, findX, findY, instructions, filled+1)) return true;
        else instructions[filled] = 'e';

        //take block - 'n'
        instructions[filled] = 'n';
        if (playground[x][y] != -1) {
            if (blocks < 9) {
                if (direction == 0) {
                    if (x + 1 < playground.length && playground[x + 1][y] > 0) {
                        playground[x + 1][y]--;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks + 1, findX, findY, instructions, filled+1)) return true;
                        else{
                            instructions[filled] = 'e';
                            playground[x + 1][y]++;
                        }
                    }
                }
                if (direction == 1) {
                    if (y + 1 < playground[0].length && playground[x][y + 1] > 0) {
                        playground[x][y + 1]--;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks + 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x][y + 1]++;
                        }
                    }
                }
                if (direction == 2) {
                    if (x - 1 >= 0 && playground[x - 1][y] > 0) {
                        playground[x - 1][y]--;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks + 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x - 1][y]++;
                        }
                    }
                }
                if (direction == 3) {
                    if (y - 1 >= 0 && playground[x][y - 1] > 0) {
                        playground[x][y - 1]--;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks + 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x][y - 1]++;
                        }
                    }
                }
            }
        }
        else instructions[filled] = 'e';

        // put block - 'p'
        instructions[filled] = 'p';
        if (playground[x][y] != -1) {
            if (blocks > 0) {
                if (direction == 0) {
                    if (x + 1 < playground.length && playground[x + 1][y] < 9) {
                        playground[x + 1][y]++;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks - 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x + 1][y]--;
                        }
                    }
                }
                if (direction == 1) {
                    if (y + 1 < playground[0].length && playground[x][y + 1] < 9) {
                        playground[x][y + 1]++;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks - 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x][y + 1]--;
                        }
                    }
                }
                if (direction == 2) {
                    if (x - 1 >= 0 && playground[x - 1][y] < 9) {
                        playground[x - 1][y]++;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks - 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x - 1][y]--;
                        }
                    }
                }
                if (direction == 3) {
                    if (y - 1 >= 0 && playground[x][y - 1] < 9) {
                        playground[x][y - 1]++;
                        if(recursiveAuxiliary(playground, x, y, direction, blocks - 1, findX, findY, instructions, filled+1)) return true;
                        else {
                            instructions[filled] = 'e';
                            playground[x][y - 1]--;
                        }
                    }
                }
            }
        }
        instructions[filled] = 'p';
        return false;
    }

    public static void main(String[] args) {
//		int x = 3;
//		int y = 3;
//		int direction = 2;
//		int findX = 5;
//		int findY = 4;
//		System.out.print(getMinimalStepsAndTurns(x,y,direction,findX,findY));

        /*
         * You can change this main-Method as you want. This is not being tested.
         */

        // Note that in this array initialization the rows are in reverse order and both
        // x- and y-axis are swapped.
        int[][] playground = { //
//                {0, 0, 0, 0, 0, 9,}, //
//                {0, 0, 0, 0, 0, 9,}, //
//                {9, 9, 9, 7, 9, 9,}, //
//                {9, 0, 0, 0, 0, 0,}, //
				{ -1, -1, -1,  2, -1, -1, }, //
				{ -1,  2,  2, -1, -1,  2, }, //
				{ -1,  2, -1, -1,  2, -1, }, //
				{ -1, -1, -1, -1, -1,  2, }, //
				{  2, -1, -1,  2, -1, -1, }, //
        };
        int startX = 0;
        int startY = 0;
        int startDir = 3;
        int startBlocks = 0;
        //char[] instructions = {'n','s','l','s','l','s','l','s'};
        //char[] instr = {'s','r','r'};
        //char[] instr = {'r','l','s','p','r','l'};
        //char[] instr = {'n','n','n','l'};
        //char[] instr = {'s','l','p','l','s'};
        //char[] instructions = {'s','l','l','s'};
        //int filled = 4;

        //System.out.print(wasThereBefore(instr,filled));

        //printPlayground(playground, startX, startY, startDir, startBlocks);

        //System.out.print(lastTurnsAreUseless(instr,filled));

//        int x = 0;
//        int y = 0;
//        int direction = 3;
//        int blocks = 0;
//        int findX = 3;
//        int findY = 5;
//        char[] instructions = new char[30];
//        for (int i = 0; i < 30; i++) {
//            instructions[i] = 'e';
//        }
//        boolean a = findInstructions(playground, x, y, direction, blocks, findX, findY, instructions);
//        System.out.print("\n" + a);

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