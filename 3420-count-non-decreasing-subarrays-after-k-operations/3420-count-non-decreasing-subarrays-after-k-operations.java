class Solution {

    static class Group {
        long value;
        int count;

        Group(long value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<Group> deque = new ArrayDeque<>();

        int right = n - 1;
        long operations = 0;
        long answer = 0;

        for (int left = n - 1; left >= 0; left--) {

            long current = nums[left];
            int count = 1;

            while (!deque.isEmpty() &&
                   deque.peekFirst().value <= current) {

                Group g = deque.pollFirst();

                operations += (current - g.value) * g.count;

                count += g.count;
            }

            deque.offerFirst(new Group(current, count));

            while (operations > k) {

                Group g = deque.peekLast();

                operations -= g.value - nums[right];

                g.count--;

                if (g.count == 0) {
                    deque.pollLast();
                }

                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
}
