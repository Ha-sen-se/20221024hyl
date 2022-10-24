package org.kosta.test;

import java.util.Arrays;

public class TestCase {
	public static void main(String[] args) {
		int[][] test = new int[8][9];
		for(int i=2;i<10;i++) {
			for(int j=1;j<10;j++) {
				test[i-2][j-1]=i*j;
			}
		}
		int i=2;
		for(int[] row: test) {
			System.out.println(i+"단");
			System.out.println(Arrays.toString(row));
			for(int value: row) {
				System.out.print(value);
			}
			System.out.println("-----------------------------");
			i++;
		}
	}
}
