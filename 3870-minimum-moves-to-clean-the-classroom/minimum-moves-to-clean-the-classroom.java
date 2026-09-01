class Solution {
    public int minMoves(String[] classroom, int energy) {


        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        // Give every litter cell an index
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // If there is no litter
        if (litterCount == 0) {
            return 0;
        }

        int allCollected = (1 << litterCount) - 1;

        /*
         * visited[r][c][mask][energy]
         *
         * mask    = which litter has been collected
         * energy  = remaining energy
         */
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        // Queue stores: row, col, energy, mask, distance
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{
                startR, startC, energy, 0, 0
        });

        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int currEnergy = current[2];
            int mask = current[3];
            int moves = current[4];

            // All litter collected
            if (mask == allCollected) {
                return moves;
            }

            // If energy is 0, we cannot make another move
            if (currEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // Moving costs 1 energy
                int newEnergy = currEnergy - 1;

                int newMask = mask;

                // If we enter litter cell, collect it
                if (classroom[nr].charAt(nc) == 'L') {

                    int id = litterId[nr][nc];

                    newMask = mask | (1 << id);
                }

                // Reset area restores energy
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                // Avoid visiting the exact same state
                if (!visited[nr][nc][newMask][newEnergy]) {

                    visited[nr][nc][newMask][newEnergy] = true;

                    queue.offer(new int[]{
                            nr,
                            nc,
                            newEnergy,
                            newMask,
                            moves + 1
                    });
                }
            }
        }

        return -1;
    

        
    }
}