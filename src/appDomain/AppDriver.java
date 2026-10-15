package appDomain;

import java.util.Comparator;

import products.Products;
import utilities.*;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304 
 * class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
	/**
	 *  The main method is the entry point of the application.
	 *  
	 *  @param args The input to control the execution of the application.
	 */
	public static void main( String[] args )
	{
		
		//Temp switch case structure for placeholder sorting algorithms
		
		switch (args) {

	    case "b":
	        // BubbleSort.sort(products, comparator);
	    	break;

	    case "i":
	        // InsertionSort.sort(products, comparator);
	    	break;

	    case "s":
	        // SelectionSort.sort(products, comparator);
	    	break;

	    case "m":
	        // MergeSort.sort(products, comparator);
	    	break;

	    case "q":
	        // Median-of-Three Quick Sort
	        break;

	    case "z":
	        // CustomSort.sort(products, comparator);
	    	break;

	    default:
	        throw new IllegalArgumentException(
	            "Invalid sorting algorithm: ");
		}
	}

}
