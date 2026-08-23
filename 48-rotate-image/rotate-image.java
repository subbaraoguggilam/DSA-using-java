class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n ; i++){
            for(int j = i + 1; j < n; j++){
                int temp = matrix[i][j]; matrix[i][j] = matrix[j][i]; matrix[j][i]=temp;
            }
        }
        for (int i=0; i<n; i++){
            reverseRow(matrix[i]);
        }

    }

    private void reverseRow(int[] row){
        int left = 0, right = row.length-1;
        while(left < right){
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }
        
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna