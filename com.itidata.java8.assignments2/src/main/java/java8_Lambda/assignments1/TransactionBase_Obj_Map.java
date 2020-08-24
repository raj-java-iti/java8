package java8_Lambda.assignments1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionBase_Obj_Map {
	private String transaction_id;
	private LocalDate transaction_date;
	private Long card_number;
	private Long transaction_value;
	private Long transaction_segment;
	private Long merchant_id;
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public LocalDate getTransaction_date() {  

		return transaction_date;
	}
	public void setTransaction_date(LocalDate transaction_date) {

		this.transaction_date = transaction_date;
	}
	public Long getCard_number() {
		return card_number;
	}
	public void setCard_number(Long card_number) {
		this.card_number = card_number;
	}
	public Long getTransaction_value() {
		return transaction_value;
	}
	public void setTransaction_value(Long transaction_value) {
		this.transaction_value = transaction_value;
	}
	public Long getTransaction_segment() {
		return transaction_segment;
	}
	public void setTransaction_segment(Long transaction_segment) {
		this.transaction_segment = transaction_segment;
	}
	public Long getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Long merchant_id) {
		this.merchant_id = merchant_id;
	}
	
	
	public static Function<String,TransactionBase_Obj_Map> getTransactionbaseMap() {
        return transactionbaseMap;
    }

    public static void setTransactionbaseMap(Function<String, TransactionBase_Obj_Map> transactionbaseMap) {
    	TransactionBase_Obj_Map.transactionbaseMap = transactionbaseMap;
    }
    
	public static List<TransactionBase_Obj_Map> mapCSV(String empCSVFilePath) {
        List<TransactionBase_Obj_Map> transactionbaseList = new ArrayList<TransactionBase_Obj_Map>();
        try {
            File inputF = new File(empCSVFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
// Skip the header since its just column name in table and in CSV file but not the data.
            transactionbaseList = br.lines().skip(1).map(transactionbaseMap).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return transactionbaseList;
    }
	public static Function<String, TransactionBase_Obj_Map> transactionbaseMap = (line) -> {
        String[] record = line.split(",");// This can be delimiter which
        TransactionBase_Obj_Map transactionbase_obj_map = new TransactionBase_Obj_Map();
        
        if (record[0] != null && record[0].trim().length() > 0) {
        	transactionbase_obj_map.setTransaction_id(record[0]);
        }
        if (record[1] != null && record[1].trim().length() > 0) {
            //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            //LocalDate localDate1 = LocalDate.parse(record[1],dateTimeFormatter);
        	transactionbase_obj_map.setTransaction_date(LocalDate.parse(record[1]));
        }
        if (record[2] != null && record[2].trim().length() > 0) {
        	transactionbase_obj_map.setCard_number(Long.valueOf(record[2]));
        }
        if (record[3] != null && record[3].trim().length() > 0) {
        	transactionbase_obj_map.setTransaction_value(Long.valueOf(record[3]));
        }
        if (record[4] != null && record[4].trim().length() > 0) {
        	transactionbase_obj_map.setTransaction_segment(Long.valueOf(record[4]));
        }
        if (record[5] != null && record[5].trim().length() > 0) {
        	transactionbase_obj_map.setMerchant_id(Long.valueOf(record[5]));
        }
        return transactionbase_obj_map;
    };
    public static Map<Long, Long> totalCardNumber(List<TransactionBase_Obj_Map> tblist) {
        return tblist.stream()
                .collect(Collectors.groupingBy(
                		TransactionBase_Obj_Map::getCard_number,
                        //Collectors.summingLong(CardBase_Obj_Map::getCard_number)));
                		Collectors.counting()));
    }
    


}
