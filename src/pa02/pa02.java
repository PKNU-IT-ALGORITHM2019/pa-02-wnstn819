package pa02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class pa02 {
	static int n;
	static int save[];
	static int road[];
	static double dist[][];
	static double min;

	static class Coor {
		int x = 0;
		int y = 0;

		static double distance(Coor p1, Coor p2) {
			double dist;
			dist = Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));

			return dist;

		}

	}
	static void TSP(int len, double curdist) {

		if (min < curdist)
			return;
		else if (len == n) {
			double ret = curdist;
			ret += dist[save[0]][save[n-1]];
			if (min > ret) {
				min = ret;
				System.arraycopy(save, 0, road, 0, n);
			}
			return;
		} else {
			for (int i = len; i < n; i++) {
				double distlen = curdist;
				swap(len, i);
				distlen += dist[save[len]][save[len-1]];
				TSP(len + 1, distlen);
				swap(len, i);
			}
			return;
		}


	}
	static void swap(int index, int change) {
		int temp = save[index];
		save[index] = save[change];
		save[change] = temp;

	}


	public static void main(String[] args) throws FileNotFoundException {
		
		for(int i =0; i<7; i++) {
			long startTime = System.currentTimeMillis();
		Scanner scan = new Scanner(new File("input"+i+".txt"));
		n = scan.nextInt();
		Coor list[] = new Coor[n];
		dist = new double[n][n];
		save = new int[n];
		road = new int[n];
		min = 987654321;
		for (int j = 0; j < n; j++) {
			list[j] = new Coor();
		}
		for (int j = 0; j < n; j++) { // 파일에 있는 각 점의 x,y값 저장

			list[j].x = scan.nextInt();
			list[j].y = scan.nextInt();
			save[j]=j;

		}
		for (int k = 0; k < dist.length; k++) { // 0부터 n까지 점들의 이동거리 저장
			for (int j = 0; j < dist[i].length; j++) {
				dist[k][j] = Coor.distance(list[k], list[j]);

			}
		}
		TSP(1, 0);
		System.out.println(i+"번 " + "\nanswer : " + min);
		System.out.println(Arrays.toString(road));
		long endTime = System.currentTimeMillis();
		double lTime = (endTime - startTime)/1000.0;
		System.out.println("실행 시간 : " + lTime);

		scan.close();
		}
	}

}
		
		


