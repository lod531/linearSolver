public class Main {

	public static void main(String[] args) 
	{
		int test[][] = {{1, 1, -1, 4}, {1, -2, 3, -6}, {2, 3, 1, 7}};
		solve(test);

	}

	public static void solve(int passedArray[][])
	{
		assert(passedArray != null);
		assert(passedArray[0].length == passedArray.length + 1);
		int lastColumn[] = new int[passedArray.length];
		for(int i = 0; i < lastColumn.length; i++)
		{
			lastColumn[i] = passedArray[i][passedArray[0].length-1];
		}
		int workingMatrix[][] = new int[passedArray.length][passedArray[0].length-1];
		for(int i = 0; i < workingMatrix.length; i++)
		{
			for(int j = 0; j < workingMatrix[0].length; j++)
			{
				workingMatrix[i][j] = passedArray[i][j];
			}
		}
		int det = determinant(workingMatrix);
		for(int i = 0; i < passedArray[0].length-1; i++)
		{
			System.out.println(determinant(replaceColumn(workingMatrix, lastColumn, i))/det);
		}
	}
	
	public static int determinant(int passedArray[][])
	{
		assert(passedArray != null && passedArray.length == passedArray[0].length);
		if(passedArray.length == passedArray[0].length && passedArray.length == 2)
		{
			return passedArray[0][0]*passedArray[1][1] - passedArray[0][1]*passedArray[1][0];
		}
		else
		{
			int result = 0;
			for(int i = 0; i < passedArray.length; i++)
			{
				result += passedArray[0][i] * ((i%2==0)? 1: -1) * determinant(minor(passedArray, 0, i));
			}
			return result;
		}
	}
	
	
	
	//row and column indexes to be passed as zero based
		public static int[][] minor(int passedArray[][], int row, int column)
		{
			assert(row > 0 && column > 0);
			assert(passedArray.length > row && passedArray[0].length > column);
			int result[][] = new int [passedArray.length-1][passedArray[0].length-1];
			for(int i = 0; i < passedArray.length; i++)
			{
				if(i != row)
				{
					for(int j = 0; j < passedArray[0].length; j++)
					{
						if(j != column)
						{
							result[(i > row)? i-1 : i][(j > column)? j-1 : j] = passedArray[i][j];
						}
					}
				}
			}
			return result;
		}
		
		public static int[][] replaceColumn(int passedArray[][], int column[], int columnIndex)
		{
			assert(column.length == passedArray.length);
			assert(passedArray[0].length < columnIndex);
			int resultArray[][] = new int[passedArray.length][passedArray[0].length];
			for(int i = 0; i < passedArray.length; i++)
			{
				for(int j = 0; j < passedArray[0].length; j++)
				{
					if(j == columnIndex)
					{
						resultArray[i][j] = column[i];
					}
					else
					{
						resultArray[i][j] = passedArray[i][j];
					}
				}
			}
			return resultArray;
		}
}
