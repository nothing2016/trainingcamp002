package class02;

/**
 * 累加和问题1
 * 给定一个正整数的数字arr[], 和一个K值，请子数组的累加和刚好等于K的最长子数组的长度len
 *
 * 例如：arr[] = [1,2,3,5,3,3,1]   K = 6
 * 那么输出就是3 ，即[1,2,3]数组的累加，其数组长度为3。.虽然[3,3]的累加也==6，但他的长度只为2
 *
 * 题解：因为是正整数，所以具备单调性，使用滑动窗口即可
 */
public class Code01_LongestSumSubArrayLengthInPositiveArray {

	public static int getMaxLength(int[] arr, int K) {
		if (arr == null || arr.length == 0 || K <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == K) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < K) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
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
	public static int[] generatePositiveArray(int size, int value) {
		int[] ans = new int[size];
		for (int i = 0; i != size; i++) {
			ans[i] = (int) (Math.random() * value) + 1;
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
			int[] arr = generatePositiveArray(len, value);
			int K = (int) (Math.random() * value) + 1;
			int ans1 = getMaxLength(arr, K);
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
