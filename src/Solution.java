class Solution {
    public int ans = 0;
    private void mergeSort(int[] nums, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, helper, left, mid);
        mergeSort(nums, helper, mid + 1, right);
        merge(nums, helper, left, mid, right);
    }
    private void merge(int[] nums, int[] helper, int left, int mid, int right) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int helperIndex = left;
        while (leftIndex <= mid && rightIndex <= right) {
            if (nums[leftIndex] < nums[rightIndex]) {
                helper[helperIndex++] = nums[leftIndex++];
            }
            else {
                helper[helperIndex++] = nums[rightIndex++];
                this.ans += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid){
            helper[helperIndex++] = nums[leftIndex++];
        }
        while (rightIndex <= right){
            helper[helperIndex++] = nums[rightIndex++];
        }
        for (int i = left; i <= right; i++) {
            nums[i] = helper[i];
        }
    }
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int[] helper = new int[nums.length];
        mergeSort(nums, helper, 0, nums.length - 1);
        return this.ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,1};
        Solution solution = new Solution();
        solution.reversePairs(nums);
        System.out.println(solution.ans);
    }
}