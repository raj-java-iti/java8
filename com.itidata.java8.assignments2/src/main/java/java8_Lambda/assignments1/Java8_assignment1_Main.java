package java8_Lambda.assignments1;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8_assignment1_Main {
	
	public static void main(String[] args) {
		// Variable declarations
		String fileName_cb = "C:\\Users\\User\\Desktop\\ITIDATA\\Training_JavaAdvance_Spring_boot\\Data_for_Assignment\\CardBase.csv";
		String fileName_tb = "C:\\Users\\User\\Desktop\\ITIDATA\\Training_JavaAdvance_Spring_boot\\Data_for_Assignment\\TransactionBase.csv";
		//Below code snippet read csv file of CardBase.csv using Lambda...
		try (Stream<String> lines = Files.lines(Paths.get(fileName_cb))) {
			List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
			//values.forEach(value -> System.out.println("Reading CardBase.csv file using Lambda" +value));
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		//Below code snippet will read both CardBase.csv and TransactionBase.csv using Java streams...
		
        List<CardBase_Obj_Map> cardbase_List = CardBase_Obj_Map.mapCSV(fileName_cb); // Step 1 & Step 2.
       //Map<Long, Long> groupByCardNumber = CardBase_Obj_Map.totalCardNumber(cardbase_List); // Step 3.
       //  groupByCardNumber.entrySet().stream().forEach(System.out::println); // Step 4.
        //
       // System.out.println("Employee with max salary : "+CardBase_Obj_Map.mostExpensiveResource(cardbase_List));
            	
         List<TransactionBase_Obj_Map> TransactionBase_List = TransactionBase_Obj_Map.mapCSV(fileName_tb); // Step 1 & Step 2.
         //Map<Long, Long> groupByCardNumber1 = TransactionBase_Obj_Map.totalCardNumber(TransactionBase_List); // Step 3.
        //  groupByCardNumber1.entrySet().stream().forEach(System.out::println); // Step 4.
		
         /*TransactionBase_List.stream()
         .map(TransactionBase_Obj_Map::getCard_number)
         .collect(Collectors.toList())
	     .forEach(System.out::println);*/
         
         List<Long> cb =cardbase_List.stream()
         .map(CardBase_Obj_Map::getCard_number)
         .collect(Collectors.toList());
	     //.forEach(System.out::println);
         //System.out.println("cardbase_List" +cb);
                  
         List<Long> tb =TransactionBase_List.stream()
          .map(TransactionBase_Obj_Map::getCard_number)         
          .collect(Collectors.toList());
         //System.out.println("TransactionBase_List" +tb);
         cb.equals(tb);
         //cb.retainAll(tb);         
         System.out.println(cb.retainAll(tb));
	   
         
        
         
    		  
         /*Set<Long> ids = cardbase_List.stream()
          	        .map(CardBase_Obj_Map::getCard_number)
          	        .collect(Collectors.toSet());
            
          	List<TransactionBase_Obj_Map> parentBooks = TransactionBase_List.stream()
          	        .filter(book -> !ids.contains(book.getCard_number()))
          	        .collect(Collectors.toList());
          	Map<Long, Long> map = extracted(parentBooks);
			map.entrySet().stream().forEach(System.out::println); */

          
  
          
		
		
	}



}
