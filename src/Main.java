import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static List<String> groceries = new ArrayList<>();
    private static boolean isRunning;

    public static void main(String[] args) {
        isRunning = true;


        while(isRunning) {
            displayMenu();
            try {
                int chosenOption = Integer.parseInt(scanner.nextLine().trim());
                isRunning = choose(chosenOption);
            }catch (NumberFormatException e){
                System.out.println("Enter a valid Number (0 - 2): ");
            }
        }

    }

    public static void displayMenu(){
        System.out.print(
                """
                        
                        Available Actions\s
                        \t0 - to shut down
                        \t1 - to add item(s) to list
                        \t2 - to remove any item\s
                        Enter a number for which action you want to do:\s"""
        );
    }

    public static boolean choose(int choice){

            switch(choice){
                case 0 -> {
                    System.out.println("=".repeat(50));
                    System.out.println("!!Exiting program!!");
                    printList();
                    isRunning = false;
                }
                case 1 -> {
                    addItem();
                }

                case 2 -> {
                    remove();
                }
                default -> {
                    System.out.println("Wrong choice!!\n");
                }
            }
            return isRunning;
    }

    public static void addItem(){
        System.out.println("Enter grocery Item");
        String[] rawItem = scanner.nextLine().trim().split(",");

        if(rawItem.length == 0){
            System.out.println("No item entered");
            return;
        }

        List<String> trimmedList = new ArrayList<>();
        for(String item : rawItem){
            String trimmedItem = item.trim();
            if(trimmedItem.isEmpty()){
                continue;
            }
            if(groceries.contains(trimmedItem)){
                continue;
            }
            trimmedList.add(trimmedItem);
        }


        groceries.addAll(trimmedList);
        printList();
    }

    public static void remove(){
        if(groceries.isEmpty()){
            System.out.println("List is empty!");
            return;
        }

        printList();
        System.out.println("Enter number of list to delete: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if(choice >= groceries.size() || choice < 0){
                System.out.printf("Item number %d does not exist\n", choice);
                return;
            }
            String removerItem = groceries.remove(choice);
            System.out.printf("Removed, %s\n",removerItem);
        }catch (NumberFormatException e) {
            System.out.println("Enter a valid Number from 1 to " + groceries.size() + ": " );
        }
    }
    public static void printList(){
        int count = 1;
        groceries.sort(Comparator.naturalOrder());
        for(String item : groceries){
//            System.out.println(item);
            System.out.printf("%d - %s\n", count, item);
            count++;
        }
    }
}


