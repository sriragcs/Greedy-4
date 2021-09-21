// Time Complexity : O(m+n), m -> Source length, n -> Target length
// Space Complexity : O(m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestWayToFormString {
	/********************* Brute Force *********************/
	// Time Complexity : O(m*n), m -> Source length, n -> Target length
	// Space Complexity : O(m)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int shortestWayBruteForce(String source, String target) {
		if (source == null || target == null) {
			return -1;
		}

		if (source.length() == 0 && target.length() == 0) {
			return 0;
		}

		if (source.equals(target)) {
			return 1;
		}

		int m = source.length();
		int n = target.length();

		Set<Character> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
			set.add(source.charAt(i));
		}

		int pos = 0;
		int i = 0;
		int ans = 1;

		while (i < n) {
			char ch = target.charAt(i);

			if (!set.contains(ch)) {
				return -1;
			}

			while (pos < m && source.charAt(pos) != ch) {
				pos++;
			}

			if (pos == m) {
				ans++;
				pos = 0;
			} else {
				pos++;
				i++;
			}
		}

		return ans;
	}

	/********************* HashMap + Binary Search *********************/
	// Time Complexity : O(m+n), m -> Source length, n -> Target length
	// Space Complexity : O(m)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int shortestWay(String source, String target) {
		if (source == null || target == null) {
			return -1;
		}

		if (source.length() == 0 && target.length() == 0) {
			return 0;
		}

		if (source.equals(target)) {
			return 1;
		}

		int m = source.length();
		int n = target.length();

		Map<Character, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < m; i++) {
			char ch = source.charAt(i);
			if (!map.containsKey(ch)) {
				map.put(ch, new ArrayList<>());
			}

			map.get(ch).add(i);
		}

		int pos = 0;
		int i = 0;
		int ans = 1;

		while (i < n) {
			char ch = target.charAt(i);

			if (!map.containsKey(ch)) {
				return -1;
			}

			List<Integer> idxList = map.get(ch);

			int bsIdx = binarySearch(idxList, pos);

			if (bsIdx == idxList.size()) {
				pos = 0;
				ans++;
			} else {
				pos = idxList.get(bsIdx) + 1;
				i++;
			}
		}

		return ans;
	}

	private int binarySearch(List<Integer> list, int target) {
		int low = 0;
		int high = list.size() - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (target == list.get(mid)) {
				return mid;
			} else if (list.get(mid) > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	public static void main(String[] args) {
		ShortestWayToFormString obj = new ShortestWayToFormString();
		String source = "abc";
		String target = "abcbc";

		System.out.println(
				"Minimum number of subsequences of source required to form target: " + obj.shortestWay(source, target));
	}

}
