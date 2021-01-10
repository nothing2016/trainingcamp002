package class02;

import java.util.HashMap;
/**
 * 累加和问题２
 * 给定一个负数，0,正整数的数字arr[], 和一个K值，请子数组的累加和刚好等于K的最长子数组的长度len
 *
 * 例如：arr[] = [1,2,3,-1,0,1, 5,3,3,1]   K = 6
 * 那么输出就是3 ，即[1,2,3, -1,0,1,]数组的累加，其数组长度为6。.虽然[3,3]的累加也==6，但他的长度只为2
 *
 * 题解：1.遍历一遍，算出所有的累加和
 *       2. map 记录累加和第一次出现的位置，注意：必须补(0,-1)的值
 *       3.遍历一遍，用k-sum[i] 去查看map中是否有值
 *            a. 有： len = Math.max(i - map.get(sum - k), len)
 *            b. 没有： map.put(sum, i);
 *
 *  应用： 525. 连续数组 就是这个题的应用
 */
public class Code02_LongestSumSubArrayLength {

	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important
		int len = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) {
				len = Math.max(i - map.get(sum - k), len);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return len;
	}

	// for test
	public static int right(int[] arr, int K) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (valid(arr, i, j, K)) {
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}

	// for test
	public static boolean valid(int[] arr, int L, int R, int K) {
		int sum = 0;
		for (int i = L; i <= R; i++) {
			sum += arr[i];
		}
		return sum == K;
	}

	// for test
	public static int[] generateRandomArray(int size, int value) {
		int[] ans = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
		}
		return ans;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 500000;

		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len, value);
			int K = (int) (Math.random() * value) - (int) (Math.random() * value);
			int ans1 = maxLength(arr, K);
			int ans2 = right(arr, K);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println("K : " + K);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("test end");

	}

}
