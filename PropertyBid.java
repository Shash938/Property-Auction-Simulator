import java.util.*;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        System.out.println("\uD835\uDCB2\uD835\uDC52\uD835\uDCC1\uD835\uDCB8\uD835\uDC5C\uD835\uDCC2\uD835\uDC52 \uD835\uDCC9\uD835\uDC5C \uD835\uDCAE\uD835\uDCCC\uD835\uDC52\uD835\uDC52\uD835\uDCC9 \uD835\uDC9E\uD835\uDCBD\uD835\uDCB6\uD835\uDCC7\uD835\uDCBE\uD835\uDC5C\uD835\uDCC9 \uD835\uDCAB\uD835\uDCC7\uD835\uDC5C\uD835\uDCC5\uD835\uDC52\uD835\uDCC7\uD835\uDCC9\uD835\uDCCE \uD835\uDC9C\uD835\uDCCA\uD835\uDCB8\uD835\uDCC9\uD835\uDCBE\uD835\uDC5C\uD835\uDCC3.!!");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter location: ");
        String location = in.nextLine();

        Scanner in2 = new Scanner(System.in);
        System.out.println("Enter price: ");
        int price = in2.nextInt();

        Random rand = new Random();
        int rand_int2 = rand.nextInt(100000000);

        System.out.println("any other bids?");
        System.out.println("Bid going upto: "+rand_int2);
        System.out.println("Player 1 do you have any bids left?");

        Scanner in3 = new Scanner(System.in);
        String bid = in3.nextLine();

        if(bid.equalsIgnoreCase("yes")||bid.equalsIgnoreCase("y")){
            System.out.println("What is your bid?");
            Scanner in4 = new Scanner(System.in);
            System.out.println("Enter price: ");
            int pri= in4.nextInt();
        }
        else if (bid.equalsIgnoreCase("no")||bid.equalsIgnoreCase("n")) {
            System.out.println(rand_int2);
        }
    }
}