import java.util.Arrays;

public class ArrayUtils {
	public static void main(String[] args){
		int[] t= {1, 2, 3, 4, 5,6,7};
		int[] s= shiftArrayToTheRight( t,3);
		System.out.println(Arrays.toString(s));
		int[][] k={{1,2,3,4}, {5,6,7,8}, {9,10,11,12},{13,14,15,16}};
		printMatrix(k);
		int check= matrixTrace(k);
		System.out.println("check1: "+check);
		int[][] check2= matrixSwitchRows(k,1,3);
		printMatrix(check2);
		int[][] check3= matrixScalarRow(k,4,1);
		printMatrix(check3);
		int[][] Arr1={{1,2,3},{4,5,6},{7,8,9}};
		int[][] Arr2={{3,0,0},{0,2,0},{0,0,1}};
		//int[][] Arr1={{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
		//int[][] Arr2={{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}};
		int[][] check4= matrixMultiplication(Arr1,Arr2);
		printMatrix(check4);
	}
	public static void printMatrix(int[][] m){
		for (int[] row: m){
			System.out.print(Arrays.toString(row));
	}
		System.out.println("");
	}
	public static int[] shiftArrayToTheRight(int[] array, int move){
		if (move <=0){
			return array;
		}
		int n = array.length;
		move = move%n;
		int[] FArr = new int[n];
			for(int i=0;i<n;i++){
				if(i<move){
					FArr[i]=array[n-move+i];
				}
				else
					FArr[i]=array[i-move];
			}
	
		return FArr;
	}
	public static int matrixTrace (int[][] m){
		int sum = 0;
		for(int i=0;i<m.length;i++){
			sum += m[i][i];
		}
		return sum;
	}
	public static int [][] matrixSwitchRows (int[][] m, int I, int j){
		if(I==j){
			return m;
		}
		int[][] copym = Arrays.copyOf(m,m.length);
		int[] temp=copym[I];
		copym[I]=copym[j];
		copym[j]=temp;
		return copym;
	}
	
	public static int [][] matrixScalarRow (int[][] m, int s, int j){
		int[][] copym = Arrays.copyOf(m,m.length);
		for (int i=0;i<copym[j].length;i++){
			copym[j][i] *=s;
		}
		return copym;
	}
	
	public static int [][] matrixMultiplication (int[][] m, int[][] n){
		int culLen=m.length;
		int rowLen=n[0].length;
		int[][] newMat = new int[culLen][rowLen];
		for(int i=0;i<culLen;i++){
			for(int j=0;j<rowLen;j++){
				int sum=0;
				for (int k=0;k<culLen;k++){
						sum+=m[i][k]*n[k][j];
					}
					newMat[i][j]=sum;
				}
			}
		return newMat;
	}

}
