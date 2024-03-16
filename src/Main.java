public class Main {
    public static void main(String[] args) {
        // Đầu vào
        int[] numbers = {1, 3, 5, 6, 2, 7, 8, 0, 4, 3, 9, 2, 8, 1, 0, 5, 7, 4, 6, 9, 3, 2, 1, 8, 4, 0, 6, 7, 9, 5, 3, 2, 1, 0, 8, 4, 6, 7, 9, 5, 3, 2, 1, 0, 8, 4, 6, 7, 9, 5, 3, 2, 1, 0, 8, 4, 6, 7, 9, 5, 3, 2, 1, 0};

        // Tạo và bắt đầu hai luồng
        SumThread sumThread = new SumThread(numbers);
        MaxThread maxThread = new MaxThread(numbers);

        sumThread.start();
        maxThread.start();

        // Chờ cho đến khi hai luồng kết thúc
        try {
            sumThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Lấy kết quả từ mỗi luồng
        int sum = sumThread.getSum();
        int max = maxThread.getMax();

        // In ra kết quả
        System.out.println("Phần tử lớn nhất trong mảng là: " + max);
        System.out.println("Tổng của các phần tử trong mảng là: " + sum);
    }
}

// Luồng để tính tổng các phần tử trong mảng
class SumThread extends Thread {
    private int[] numbers;
    private int sum;

    public SumThread(int[] numbers) {
        this.numbers = numbers;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            sum += number;
        }
    }

    public int getSum() {
        return sum;
    }
}

// Luồng để tìm phần tử lớn nhất trong mảng
class MaxThread extends Thread {
    private int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
        this.max = Integer.MIN_VALUE;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
    }

    public int getMax() {
        return max;
    }
}

