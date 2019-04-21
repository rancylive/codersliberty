package codersliberty.rough;

/**
 * The N Queen is the problem of placing N chess queens on an N×N chessboard so
 * that no two queens attack each other.
 * 
 * @author rchoudhury
 *
 */
public class NQueenProblem {

	public static void main(String[] args) {
		int N;
		int[][] board= new int[8][8];
		N=4;
		//initialize chess board
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				board[i][j]=0;
			}
		}
		System.out.println("The board is ");
		print(board,N);
		
		NQueenProblem nQueen=new NQueenProblem();
		boolean placed = nQueen.placeNQueen(board, 0, N);
		if(!placed) {
			System.out.println("Could not place the queens");
		} else {
			System.out.println("Placing queens safely: ");
			print(board, N);
		}
	}
	
	public boolean placeNQueen(int[][] board, int col, int N) {
		if(col>N) {
			return true;
		}
		for(int i=0;i<N;i++) {
			if(isSafe(board, col, i, N)) {
				board[i][col]=1;
				if(placeNQueen(board, col+i, N)==true) {
					return true;
				}
				board[i][col]=0;
			}
		}
		return false;
	}
	
	public boolean isSafe(int[][] board,int col, int row, int N) {
		for(int i=0;i<N;i++) {
			if(board[col][i]!=0) {
				return false;
			}
			if(board[i][row]!=0) {
				return false;
			}
		}
		for(int i=row, j=col;i<N && j<N;j++, i++) {
				if(board[i][j]!=0) {
					return false;
				}
		}
		for(int i=row, j=col;i>=0 && j>=0;j--, i--) {
			if(board[i][j]!=0) {
				return false;
			}
	}
		return true;
	}
	
	public static void print(int[][] board,int N) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
}