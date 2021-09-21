// Time Complexity : O(n), n -> Number of dominoes
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.HashMap;
import java.util.Map;

public class MinimumDominoRotations {
	/********************* HashMap *********************/
	// Time Complexity : O(n), n -> Number of dominoes
	// Space Complexity : O(1)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int minDominoRotationsHashMap(int[] tops, int[] bottoms) {
		if (tops == null || tops.length == 0 || bottoms == null || bottoms.length == 0) {
			return -1;
		}

		int n = tops.length;

		Map<Integer, Integer> cntMap = new HashMap<>();
		int max = -1;

		for (int i = 0; i < n; i++) {
			cntMap.put(tops[i], cntMap.getOrDefault(tops[i], 0) + 1);
			if (cntMap.get(tops[i]) >= n) {
				max = tops[i];
			}
			cntMap.put(bottoms[i], cntMap.getOrDefault(bottoms[i], 0) + 1);
			if (cntMap.get(bottoms[i]) >= n) {
				max = bottoms[i];
			}
		}

		if (max == -1) {
			return -1;
		}

		int trot = 0;
		int brot = 0;

		for (int i = 0; i < n; i++) {
			if (tops[i] != max && bottoms[i] != max) {
				return -1;
			} else if (tops[i] != max) {
				trot++;
			} else if (bottoms[i] != max) {
				brot++;
			}
		}

		return Math.min(trot, brot);
	}

	/********************* Optimized Two Pass *********************/
	// Time Complexity : O(n), n -> Number of dominoes
	// Space Complexity : O(1)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int minDominoRotations(int[] tops, int[] bottoms) {
		if (tops == null || tops.length == 0 || bottoms == null || bottoms.length == 0) {
			return -1;
		}

		int res = checkRotations(tops, bottoms, tops[0]);

		if (res != -1) {
			return res;
		}

		return checkRotations(tops, bottoms, bottoms[0]);
	}

	private int checkRotations(int[] tops, int[] bottoms, int target) {
		int trot = 0;
		int brot = 0;

		for (int i = 0; i < tops.length; i++) {
			if (tops[i] != target && bottoms[i] != target) {
				return -1;
			} else if (tops[i] != target) {
				trot++;
			} else if (bottoms[i] != target) {
				brot++;
			}
		}

		return Math.min(trot, brot);
	}

	public static void main(String[] args) {
		MinimumDominoRotations obj = new MinimumDominoRotations();
		int[] tops = { 2, 1, 2, 4, 2, 2 };
		int[] bottoms = { 5, 2, 6, 2, 3, 2 };

		System.out.println("Minimum number of rotations required: " + obj.minDominoRotations(tops, bottoms));
	}

}
