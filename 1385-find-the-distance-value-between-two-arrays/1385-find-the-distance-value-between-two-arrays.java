
// class Solution
// {
//     public int findTheDistanceValue(int[] arr1, int[] arr2, int d)
//     {
//         int ans = 0;

//         for(int i = 0; i < arr1.length; i++)
//         {
//             boolean valid = true;

//             for(int j = 0; j < arr2.length; j++)
//             {
//                 if(Math.abs(arr1[i] - arr2[j]) <= d)
//                 {
//                     valid = false;
//                     break;
//                 }
//             }

//             if(valid)
//                 ans++;
//         }

//         return ans;
//     }


class Solution
{
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d)
    {
        Arrays.sort(arr2);
        int ans = 0;

        for(int x : arr1)
        {
            int l = 0, r = arr2.length;

            while(l < r)
            {
                int mid = l + (r - l) / 2;

                if(arr2[mid] < x - d)
                    l = mid + 1;
                else
                    r = mid;
            }

            if(l == arr2.length || arr2[l] > x + d)
                ans++;
        }

        return ans;
    }
}



