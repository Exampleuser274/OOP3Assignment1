/**
 * <p>
 * CPRG 304 - Object-Oriented Programming 3
 * Assignment 1: Complexity and Sorting
 *
 * Group Members:
 * Patrick Bouley
 * Alexander Kovach
 * Rnel Gem Macatuno
 * Oluwatofunmi Dorcas Olutimehin
 *
 * Date: October 23, 2026
 *
 * Description:
 * This application reads product information from external text files
 * and sorts the products in descending order using one of six
 * sorting algorithms selected through command-line arguments.
 *
 * Products can be compared by their original price, updated price,
 * or calculated real price difference using the Comparable and
 * Comparator interfaces.
 *
 * The application benchmarks the selected sorting algorithm and
 * displays the sorted results according to the assignment requirements.
 * </p>
 */


package appDomain;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Locale;

import products.*;
import utilities.*;

/**
 * Main application driver for the CPRG 304
 * Complexity and Sorting assignment.
 *
 * Processes command-line arguments to determine the input file,
 * comparison type, and sorting algorithm.
 *
 * Reads product records from external text files and creates
 * the appropriate product objects.
 *
 * Uses Comparable and Comparator implementations to sort
 * products in descending order with one of six algorithms.
 *
 * Measures the execution time of the selected sorting algorithm
 * and displays the results according to the assignment requirements.
 */

public class AppDriver {
	
	/**
	 *  The main method is the entry point of the application.
	 *  
	 *  @param args The input to control the execution of the application.
	 */
	
    public static void main(String[] args) {
        try {
            runApplication(args);
        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println(
                "Usage: java -jar Sort.jar "
                + "-f<file_path> -t<o|u|d> -s<b|i|s|m|q|z>"
            );
        }
    }

    /**
     * Processes command-line arguments, loads product data,
     * selects the comparison type and sorting algorithm,
     * measures sorting execution time, and displays the results.
     *
     * @param args Command-line arguments specifying the input file,
     *             comparison type, and sorting algorithm.
     * @throws IOException If the input file cannot be read.
     * @throws IllegalArgumentException If an argument or
     *                                  product record is invalid.
     */
    
    private static void runApplication(String[] args)
            throws IOException {

        String fileName = null;
        String compareType = null;
        String sortAlgorithm = null;

        // Parse command-line options in any order.
        for (String arg : args) {

            if (arg == null || arg.length() < 3
                    || !arg.startsWith("-")) {
                throw new IllegalArgumentException(
                    "Invalid argument: " + arg
                );
            }

            String option = arg.substring(1, 2)
                               .toLowerCase(Locale.ROOT);
            String value = arg.substring(2);

            // Remove surrounding quotation marks if present.
            if (value.length() >= 2
                    && value.startsWith("\"")
                    && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }

            if (value.isEmpty()) {
                throw new IllegalArgumentException(
                    "Missing value for option: " + arg
                );
            }

            switch (option) {
                case "f":
                    if (fileName != null) {
                        throw new IllegalArgumentException(
                            "File option specified more than once."
                        );
                    }
                    fileName = value;
                    break;

                case "t":
                    if (compareType != null) {
                        throw new IllegalArgumentException(
                            "Comparison option specified more than once."
                        );
                    }
                    compareType = value.toLowerCase(Locale.ROOT);
                    break;

                case "s":
                    if (sortAlgorithm != null) {
                        throw new IllegalArgumentException(
                            "Sorting option specified more than once."
                        );
                    }
                    sortAlgorithm = value.toLowerCase(Locale.ROOT);
                    break;

                default:
                    throw new IllegalArgumentException(
                        "Unknown option: " + arg
                    );
            }
        }

        // Make sure all required options were supplied.
        if (fileName == null || compareType == null
                || sortAlgorithm == null) {
            throw new IllegalArgumentException(
                "You must specify -f, -t, and -s."
            );
        }

        // Validate comparison type.
        if (!compareType.equals("o")
                && !compareType.equals("u")
                && !compareType.equals("d")) {
            throw new IllegalArgumentException(
                "Comparison type must be o, u, or d."
            );
        }

        // Validate sorting algorithm.
        if (!"bismqz".contains(sortAlgorithm)
                || sortAlgorithm.length() != 1) {
            throw new IllegalArgumentException(
                "Sorting algorithm must be b, i, s, m, q, or z."
            );
        }

        // Read products from the input file.
        Products[] products = readProducts(fileName);

        // Choose the comparison rule.
        Comparator<Products> comparator;

        switch (compareType) {
            case "o":
                comparator = new OriginalPriceComparator();
                break;

            case "u":
                comparator = new UpdatedPriceComparator();
                break;

            case "d":
                comparator = Comparator.naturalOrder();
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid comparison type."
                );
        }

        // Measure only the sorting algorithm's execution time.
        long startTime = System.nanoTime();

        // Run the selected sorting algorithm.
        switch (sortAlgorithm) {
            case "b":
                BubbleSort.sort(products, comparator);
                break;

            case "i":
                InsertionSort.sort(products, comparator);
                break;

            case "s":
                SelectionSort.sort(products, comparator);
                break;

            case "m":
                MergeSort.sort(products, comparator);
                break;

            case "q":
                QuickSort.sort(products, comparator);
                break;

            case "z":
                HeapSort.sort(products, comparator);
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid sorting algorithm: " + sortAlgorithm
                );
        }

        long endTime = System.nanoTime();

        double elapsedMilliseconds =
            (endTime - startTime) / 1_000_000.0;

        displayProducts(products, compareType);

        // Display the correct algorithm name and run time.
        String algorithmName = getAlgorithmName(sortAlgorithm);

        // Display runtime to three decimal places so any sorting operations 
        // under 1 second are not rounded to 0 milliseconds.
        	System.out.printf(
	            Locale.ROOT, 
	            "%s run time was: %.3f milliseconds%n",
	            algorithmName, 
	            elapsedMilliseconds );
    }


/**
 * Returns the full name of the selected sorting algorithm.
 *
 * Converts the single-character sorting option into a
 * readable name for displaying the benchmark results.
 *
 * @param sortAlgorithm The selected sorting option
 *                      (b, i, s, m, q, or z).
 * @return The name of the selected sorting algorithm,
 *         or "Unknown Sort" if the option is not recognized.
 */

    private static String getAlgorithmName(String sortAlgorithm) {
        switch (sortAlgorithm) {
            case "b":
                return "Bubble Sort";
            case "i":
                return "Insertion Sort";
            case "s":
                return "Selection Sort";
            case "m":
                return "Merge Sort";
            case "q":
                return "Quick Sort";
            case "z":
                return "Heap Sort";
            default:
                return "Unknown Sort";
        }
    }

    /**
     * Reads product records from the specified text file.
     * The first line determines the size of the Products array.
     * Each subsequent line contains a product type and two prices.
     *
     * @param fileName The path to the input file.
     * @return An array containing the products from the file.
     * @throws IOException If the file cannot be read.
     * @throws IllegalArgumentException If the file contains invalid data.
     */
    
    private static Products[] readProducts(String fileName)
            throws IOException {

        try (BufferedReader reader =
                Files.newBufferedReader(Path.of(fileName))) {

            String firstLine = reader.readLine();

            if (firstLine == null) {
                throw new IllegalArgumentException(
                    "The input file is empty."
                );
            }

            int count;

            try {
                count = Integer.parseInt(firstLine.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "The first line must contain the product count."
                );
            }

            if (count < 0) {
                throw new IllegalArgumentException(
                    "Product count cannot be negative."
                );
            }

            Products[] products = new Products[count];

            for (int i = 0; i < count; i++) {

                String line = reader.readLine();

                if (line == null) {
                    throw new IllegalArgumentException(
                        "Expected " + count
                        + " products, but found only " + i + "."
                    );
                }

                String[] parts = line.trim().split("\\s+");

                if (parts.length != 3) {
                    throw new IllegalArgumentException(
                        "Invalid product data on line "
                        + (i + 2) + ": " + line
                    );
                }

                try {
                    String type = parts[0];

                    double originalPrice =
                        Double.parseDouble(parts[1]);

                    double updatedPrice =
                        Double.parseDouble(parts[2]);

                    if (!Double.isFinite(originalPrice)
                            || !Double.isFinite(updatedPrice)) {
                        throw new IllegalArgumentException(
                            "Prices must be finite numbers on line "
                            + (i + 2) + "."
                        );
                    }

                    products[i] = createProduct(
                        type, originalPrice, updatedPrice
                    );

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                        "Invalid price on line " + (i + 2) + "."
                    );
                }
            }

            // Reject extra non-empty product lines.
            String extraLine;

            while ((extraLine = reader.readLine()) != null) {
                if (!extraLine.trim().isEmpty()) {
                    throw new IllegalArgumentException(
                        "The file contains more products than declared."
                    );
                }
            }

            return products;
        }
    }


/**
 * Creates a product object based on the product type
 * provided in the input file.
 *
 * Uses a switch statement to determine which of the
 * seven product subclasses should be instantiated.
 * The product type comparison is case-insensitive.
 *
 * @param type The product type read from the file.
 * @param originalPrice The original price of the product.
 * @param updatedPrice The updated price of the product.
 * @return A Products object representing the appropriate
 *         product subclass.
 * @throws IllegalArgumentException If the product type
 *                                  is not recognized.
 */

    private static Products createProduct(
            String type,
            double originalPrice,
            double updatedPrice) {

        switch (type.toLowerCase(Locale.ROOT)) {
            case "jeans":
                return new Jeans(originalPrice, updatedPrice);

            case "tshirts":
                return new TShirts(originalPrice, updatedPrice);

            case "skirts":
                return new Skirts(originalPrice, updatedPrice);

            case "dresses":
                return new Dresses(originalPrice, updatedPrice);

            case "shorts":
                return new Shorts(originalPrice, updatedPrice);

            case "jackets":
                return new Jackets(originalPrice, updatedPrice);

            case "shoes":
                return new Shoes(originalPrice, updatedPrice);

            default:
                throw new IllegalArgumentException(
                    "Unknown product type: " + type
                );
        }
    }

    /**
     * Displays selected elements of the sorted product array.
     *
     * Displays all products when the array contains 10 or fewer
     * elements. For larger arrays, displays the first, last,
     * and every 100th or 1000th element as required.
     *
     * @param products The sorted array of products.
     * @param compareType The comparison type: o, u, or d.
     */
    
    private static void displayProducts(
            Products[] products,
            String compareType) {

        int n = products.length;

        if (n == 0) {
            System.out.println("No products to display.");
            return;
        }

        String valueLabel;

        switch (compareType) {
            case "o":
                valueLabel = "Original Price:";
                break;

            case "u":
                valueLabel = "Updated Price:";
                break;

            default:
                valueLabel = "Real Difference:";
        }
        
        if (n <= 10) {
            for (int i = 0; i < n; i++) {
                printProduct(
                    (i + 1) + ordinalSuffix(i + 1) + " element:",
                    products[i],
                    compareType,
                    valueLabel
                );
            }
            return;
        }

        // Display the first element.
        printProduct(
            "First element is:",
            products[0],
            compareType,
            valueLabel
        );

        // Determine the display interval based on the array size
        int interval = n <= 1000 ? 100 : 1000;

        // Display every 100th or 1000th element.
        // Stop before the last element to avoid printing it twice.
        for (int position = interval; position < n; position += interval) {

            printProduct(
                position + ordinalSuffix(position) + " element:",
                products[position - 1],
                compareType,
                valueLabel
            );
        }

        // Display the last element.
        printProduct(
            "Last element is:",
            products[n - 1],
            compareType,
            valueLabel
        );
    }

    /**
     * Prints a product with its position, class name,
     * and selected comparison value.
     *
     * @param label The label identifying the product's position.
     * @param product The product to display.
     * @param compareType The selected comparison type.
     * @param valueLabel The description of the displayed value.
     */
    
    private static void printProduct(
            String label,
            Products product,
            String compareType,
            String valueLabel) {

        double value;

        switch (compareType) {
            case "o":
                value = product.getOriginalPrice();
                break;

            case "u":
                value = product.getUpdatedPrice();
                break;

            default:
                value = product.calcRealDiff();
        }

        System.out.printf(
            Locale.ROOT,
            "%-20s %-25s %-25s %.2f%n",
            label,
            product.getClass().getName(),
            valueLabel,
            value
        );
    }

/**
 * Determines the ordinal suffix for a positive integer.
 *
 * Used when displaying the position of sorted products,
 * such as 1-st, 2-nd, 3-rd, or 4-th.
 *
 * Handles the special cases of numbers ending in
 * 11, 12, or 13, which all use the "-th" suffix.
 *
 * @param number The positive integer being formatted.
 * @return The appropriate ordinal suffix (-st, -nd,
 *         -rd, or -th).
 */

    private static String ordinalSuffix(int number) {

        int lastTwoDigits = number % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 13) {
            return "-th";
        }

        switch (number % 10) {
            case 1:
                return "-st";

            case 2:
                return "-nd";

            case 3:
                return "-rd";

            default:
                return "-th";
        }
    }
}