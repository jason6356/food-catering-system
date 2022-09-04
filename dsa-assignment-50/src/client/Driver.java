package client;

import adt.*;
import entity.*;
import java.util.Scanner;


public class Driver {

    private static ListInterface<Order> orderList = new ArrayList<>();
    private static ListInterface<Food> menuItems = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {

        addDataIntoTheList();
        boolean exit = false;
        do {
            mainMenu();

            int choice = input.nextInt();

            //valid choice
            while (choice < 1 || choice > 7) {
                //clear the input
                input.nextLine();
                System.out.println("Invalid Input, Please Enter Again! \n\n");
                mainMenu();
                choice = input.nextInt();
            }

            switch (choice) {

                case 1:
                    makeOrder();
                    break;
                case 2:
                    deleteOrder();
                    break;
                case 3:
                    displayOrder();
                    break;
                case 4:
                    updateOrder();
                    break;
                case 5:
                    displayAllOrders();
                    break;
                case 6:
                    generateProfitReport();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    break;
            }

        } while (!exit);

        System.out.println("Program Done Successfully");

    }
    
    private static void generateProfitReport(){
    
        double profit = 0;
        System.out.println("Order ID\tItem Ordered          \tTotal Amount\tCummulative Profit");
        for(int i = 1; i <= orderList.getNumberOfEntries(); i++){
            System.out.println(orderList.getEntry(i).getOrderID());
            ListInterface<Food> orderedItems = orderList.getEntry(i).getOrderedItems();
            for(int j = 1; j <= orderedItems.getNumberOfEntries(); j++){
                profit += orderedItems.getEntry(j).getPrice();
                System.out.printf("%8s\t%-22s\t%12.2f\t%.2f\n","",orderedItems.getEntry(j).getName(),orderedItems.getEntry(j).getPrice(),profit);
            }
            System.out.println("============================================================================");
            System.out.printf("%8s\t%-22s\t%12.2f\t%.2f\n","","",orderList.getEntry(i).calculateTotalAmount(),profit);
        }
        
        System.out.println("\n\nTotal Profit Of The Day - RM" + profit);
    }

    private static void updateOrder() {
        
        
        displayOrderIDsInTheList();
        System.out.println("Enter the order ID to update the detail of the order ");
        String id = input.next();

        int orderPosition = searchOrderByID(id);

        if (orderPosition == -1) {
            System.out.println("No Such Order Found");
            return;
        }

        System.out.println("Order Found!");

        Order orderToUpdate = orderList.getEntry(orderPosition);

        System.out.println("=============");
        System.out.println(orderToUpdate.getOrderDetails());

        System.out.println("Which operation would u want to do ? - ");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. Discard");

        int choice = input.nextInt();

        if (choice == 1) {

            int length = menuItems.getNumberOfEntries();
            char addMore = 'N';

            do {
                System.out.printf("Current Order ID - %s\n", orderToUpdate.getOrderID());
                displayMenu();
                System.out.printf("\nEnter your choice (1 - %d) : ", length);

                int foodChoice = input.nextInt();

                while (foodChoice < 1 || foodChoice > length) {
                    System.out.println("Invalid Choice, Please Enter Again!");
                    input.nextLine();
                    displayMenu();
                    foodChoice = input.nextInt();
                }

                orderToUpdate.addItemToOrder(menuItems.getEntry(foodChoice));

                System.out.println("Added Item Successfully!");
                System.out.println("Would u like to add more ? (Y/N) - ");
                input.nextLine();
                addMore = input.next().charAt(0);

            } while (Character.toUpperCase(addMore) != 'N');
        } else if (choice == 2) {

            int length = orderToUpdate.getOrderedItems().getNumberOfEntries();
            char deleteMore = 'N';

            System.out.printf("Current Order ID - %s\n", orderToUpdate.getOrderID());
            for(int i = 1; i <= length ; i++)
                System.out.println(i + ".\n" + orderToUpdate.getOrderedItems().getEntry(i));
            System.out.printf("\nEnter your choice (1 - %d) : ", length);

            int foodChoice = input.nextInt();

            while (foodChoice < 1 || foodChoice > length) {
                System.out.println("Invalid Choice, Please Enter Again!");
                input.nextLine();
                System.out.printf("Current Order ID - %s\n", orderToUpdate.getOrderID());
                for(int i = 1; i <= length ; i++)
                    System.out.println(i + ".\n" + orderToUpdate.getOrderedItems().getEntry(i).toString());
                System.out.printf("\nEnter your choice (1 - %d) : ", length);
                foodChoice = input.nextInt();
            }
            
            orderToUpdate.getOrderedItems().remove(foodChoice);
            System.out.println("Successfully Deleted The Item");
            

        } else {
            System.out.println("Successfully Discarded!");
            return;
        }

    }

    private static void displayOrder() {

        displayOrderIDsInTheList();
        System.out.println("Enter the order ID to display the detail of the order - ");
        String id = input.next();

        int orderPosition = searchOrderByID(id);

        if (orderPosition == -1) {
            System.out.println("No Such Order Found");
            return;
        }

        Order orderToDisplay = orderList.getEntry(orderPosition);

        System.out.println("Order Details ");
        System.out.println("=====================");
        System.out.println(orderToDisplay.getOrderDetails());
    }

    private static int searchOrderByID(String id) {

        for (int i = 1; i <= orderList.getNumberOfEntries(); i++) {
            if (orderList.getEntry(i).getOrderID().equals(id)) {
                return i;
            }
        }

        return -1;
    }

    private static void deleteOrder() {
        
        displayOrderIDsInTheList();
        Order firstOrder = orderList.getEntry(1);
        Order lastOrder = orderList.getEntry(orderList.getNumberOfEntries());

        System.out.printf("\nCurrent Total Orders In Range of (%s - %s)", firstOrder.getOrderID(), lastOrder.getOrderID());
        System.out.println("Enter the Order ID to Delete The Order ");
        String orderIDToDel = input.next();

        int delPosition = searchOrderByID(orderIDToDel);

        if (delPosition == -1) {
            System.out.println("No Such Order Found!");
        } else {
            orderList.remove(delPosition);
            System.out.println("Successfully deleted the order!");
        }

    }

    private static void makeOrder() {

        Order order = new Order();
        int length = menuItems.getNumberOfEntries();
        char addMore = 'N';
        do {
            System.out.printf("Current Order ID - %s\n", order.getOrderID());
            displayMenu();
            System.out.printf("\nEnter your choice (1 - %d) : ", length);

            int foodChoice = input.nextInt();

            while (foodChoice < 1 || foodChoice > length) {
                System.out.println("Invalid Choice, Please Enter Again!");
                input.nextLine();
                displayMenu();
                foodChoice = input.nextInt();
            }

            order.addItemToOrder(menuItems.getEntry(foodChoice));

            System.out.println("Added Item Successfully!");
            System.out.println("Would u like to add more ? (Y/N) - ");
            input.nextLine();
            addMore = input.next().charAt(0);

        } while (Character.toUpperCase(addMore) != 'N');

        orderList.add(order);

    }
    
    private static void displayAllOrders(){
        
        System.out.println("Display By");
        System.out.println("======================");
        System.out.println("1. Ascending from total amount.");
        System.out.println("2. Ascending from order id");
        System.out.println("Enter Your Choice");
        int choice = input.nextInt();
        
        if(choice == 1){
            ListInterface<Order> result = sortOrderListByTotalAmount();
            System.out.println("Result");
            System.out.println("======================");
            for(int i = 1; i <= result.getNumberOfEntries(); i++){
                System.out.println(result.getEntry(i).getBriefDetails());
            }
        }else if (choice == 2){
            System.out.println("Result");
            System.out.println("======================");
            for(int i = 1; i <= orderList.getNumberOfEntries(); i++){
                System.out.println(orderList.getEntry(i).getBriefDetails());
            }
        }
        
        
        
    }

    //make sample data
    public static void addDataIntoTheList() {

        menuItems.add(new Food("Nasi Lemak", 5.00));
        menuItems.add(new Food("Nasi Goreng", 5.50));
        menuItems.add(new Food("Ayam Goreng", 3.00));
        menuItems.add(new Food("Nasi Ayam Penyet", 4.00));
        menuItems.add(new Food("Hainense Chicken Rice", 5.00));
        menuItems.add(new Food("Teh O Limau Ice", 1.5));
        menuItems.add(new Food("Kopi O Ice", 2.8));
        menuItems.add(new Food("Cam Ice", 3.2));
        menuItems.add(new Food("Teh Ice", 2.8));
    }

    public static void mainMenu() {

        System.out.println("Food Catering System");
        System.out.println("=========================");
        System.out.println("1. Make Order");
        System.out.println("2. Delete Order");
        System.out.println("3. Display Order Details");
        System.out.println("4. Update Order");
        System.out.println("5. Display All Orders");
        System.out.println("6. Generate Profit Report");
        System.out.println("7. Exit Program");
        System.out.println("\nEnter your choice (1 - 7) : ");

    }

    public static void displayMenu() {

        System.out.println("Menu Items");
        System.out.println("=========================");

        for (int i = 1; i <= menuItems.getNumberOfEntries(); i++) {
            Food item = menuItems.getEntry(i);
            System.out.printf("%d. %-20s\n", i, item.getName());
        }

    }
    
    public static void displayOrderIDsInTheList(){
            
        if(orderList.isEmpty())
            return;
        
        System.out.println("Current Order IDs - ");
        System.out.println("=========================");
        System.out.printf("%s ", orderList.getEntry(1).getOrderID());
        for(int i = 2; i <= orderList.getNumberOfEntries(); i++)
            System.out.printf("-> %s ", orderList.getEntry(i).getOrderID());

        System.out.println("");
    }
    
    public static ListInterface<Order> sortOrderListByTotalAmount(){
        
        Order[] arr = new Order[orderList.getNumberOfEntries()];
        
        for(int i = 1; i <= orderList.getNumberOfEntries(); i++)
            arr[i - 1] = orderList.getEntry(i);

        mergeSort(arr);
        
        ListInterface<Order> ls = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            ls.add(arr[i]);
        }
            
        return ls;
    }
    
    private static void mergeSort(Order[] array){
        
        int len = array.length;
        
        if(len < 2)
            return;
        
        int midIndex = len / 2;
        Order[] leftHalf = new Order[midIndex];
        Order[] rightHalf = new Order[len - midIndex];
        
        for(int i = 0; i < midIndex; i++){
            leftHalf[i] = array[i];
        }
        
        for(int i = midIndex; i < len; i++){
            rightHalf[i - midIndex] = array[i];
        }
        
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        
        merge(array,leftHalf,rightHalf);
        
    }
    
    private static void merge(Order[] inputArray, Order[] leftHalf, Order[] rightHalf){
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
    
        int i = 0, j = 0, k = 0;
        
        while(i < leftSize && j < rightSize){
            if(leftHalf[i].calculateTotalAmount() <= rightHalf[j].calculateTotalAmount()){
                inputArray[k] = leftHalf[i];
                i++;
            }
            else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }
        
        while(i < leftSize){
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }
        
        while(j < rightSize){
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
        
    }
    
    
}
