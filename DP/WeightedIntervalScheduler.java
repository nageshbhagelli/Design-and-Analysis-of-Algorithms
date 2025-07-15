import java.util.*;

class DramaSchoolBooking {
    int start, finish, value;

    public DramaSchoolBooking(int start, int finish, int value) {
        this.start = start;
        this.finish = finish;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Start: " + start + ", Finish: " + finish + ", Value: " + value;
    }
}

public class WeightedIntervalScheduler {
    public static int latestNonConflict(DramaSchoolBooking[] jobs, int index) {
        for (int j = index - 1; j >= 0; j--) {
            if (jobs[j].finish <= jobs[index].start) {
                return j;
            }
        }
        return -1;
    }

    public static int findMaxProfitWithSchedule(DramaSchoolBooking[] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(job -> job.finish));
        int n = jobs.length;
        int[] dp = new int[n];
        int[] parent = new int[n];

        dp[0] = jobs[0].value;
        parent[0] = -1;
        for (int i = 1; i < n; i++) {
            int inclProfit = jobs[i].value;
            int l = latestNonConflict(jobs, i);
            if (l != -1) {
                inclProfit += dp[l];
            }
            if (inclProfit > dp[i - 1]) {
                dp[i] = inclProfit;
                parent[i] = l;
            } else {
                dp[i] = dp[i - 1];
                parent[i] = -1;
            }
        }
        System.out.println("Selected bookings for maximum profit:");
        printSchedule(jobs, parent, n - 1, dp);
        return dp[n - 1];
    }

    public static void printSchedule(DramaSchoolBooking[] jobs, int[] parent, int index, int[] dp) {
        if (index < 0)
            return;
        if (parent[index] != -1 || (index == 0 && dp[index] > 0)) {
            printSchedule(jobs, parent, parent[index], dp);
            System.out.println(jobs[index]);
        } else {
            printSchedule(jobs, parent, index - 1, dp);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of bookings: ");
        int n = scanner.nextInt();
        DramaSchoolBooking[] bookings = new DramaSchoolBooking[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Enter start, finish, and value for booking " + (i + 1) + ":");
            int start = scanner.nextInt();
            int finish = scanner.nextInt();
            int value = scanner.nextInt();
            bookings[i] = new DramaSchoolBooking(start, finish, value);
        }
        int maxProfit = findMaxProfitWithSchedule(bookings);
        System.out.println("Maximum Profit = " + maxProfit);
    }
}
