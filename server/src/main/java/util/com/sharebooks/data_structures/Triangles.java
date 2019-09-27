package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.Collections;

public class Triangles {

	public static void main(String[] args) {
		Triangles t = new Triangles();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(2);
		System.out.println(t.nTriang(list));
	}

	public int nTriang(ArrayList<Integer> list) {
		if (list == null || list.size() < 3)
			return 0;
		Collections.sort(list);
		int p1 = 0, p2 = 0, p3 = 0;
		int limit = list.size() - 1, count = 0;
		int l = 0, r = 0, mid = 0;

		while (p1 <= limit - 2) {
			int v1 = list.get(p1);
			p3 = limit;
			while (p3 >= p1 + 2) {
				int v3 = list.get(p3);
				l = p1 + 1;
				r = p3 - 1;
				//
				while (l <= r) {
					mid = (l + r) / 2;
					int v2 = list.get(mid);
					boolean ip = isTriangPossible(v1, v2, v3);
					if (ip)
						r = mid - 1;
					else
						l = mid + 1;
				}
				p2 = r;
				if (p2 < p3)
					count += p3 - p2 - 1;
				p3--;
			}
			p1++;
		}

		return count;
	}

	private boolean isTriangPossible(int v1, int v2, int v3) {
		return v1 + v2 > v3;
	}
}
