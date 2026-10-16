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
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304 
 * class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
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

        // Display products after the timer has stopped.
        displayProducts(products, compareType);

        // Display the correct algorithm name and run time.
        String algorithmName = getAlgorithmName(sortAlgorithm);

        System.out.printf(
            Locale.ROOT,
            "%s run time was: %.0f milliseconds%n",
            algorithmName,
            elapsedMilliseconds
        );
    }

    /* Returns the name of the selected sorting algorithm. */
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

    /*
     * Reads products from a text file.
     * The first line contains the product count.
     * Each remaining line contains a product type,
     * original price, and updated price.
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

    /* Creates the appropriate product subclass. */
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

    /* Displays products using the assignment's output rules. */
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
                valueLabel = "Calculated Difference:";
        }

        // Display every product when there are 10 or fewer.
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

        int interval = n <= 1000 ? 100 : 1000;

        // For more than 1000 products, stop intermediate
        // output at the 20,000th position.
        int displayLimit = n > 1000
                ? Math.min(n - 1, 20000)
                : n - 1;

        for (int position = interval;
                position <= displayLimit;
                position += interval) {

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

    /* Prints one product and its selected comparison value. */
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

    /* Returns the ordinal suffix for a positive integer. */
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